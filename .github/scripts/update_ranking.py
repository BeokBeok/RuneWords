"""GA4 이벤트를 집계해 룬/룬워드 랭킹을 Remote Config `key_ranking`에 게시한다.

- rune_click(runeName), rune_words_click(runeWordsName)의 최근 N일 eventCount를 조회한다.
- 상위 항목만 JSON으로 만들어 Remote Config 템플릿의 `key_ranking` 파라미터 하나만 바꾼다.
- 다른 파라미터(예: key_force_update_version)는 건드리지 않는다.
- 값이 바뀌지 않으면 게시하지 않는다.

환경 변수:
- GA4_PROPERTY_ID: GA4 속성 ID (숫자)
- FIREBASE_PROJECT_ID: Firebase 프로젝트 ID
- GOOGLE_APPLICATION_CREDENTIALS: 서비스 계정 키 파일 경로
- RANKING_PERIOD_DAYS (선택, 기본 7), RANKING_SIZE (선택, 기본 5)
- DRY_RUN=true 이면 게시하지 않고 결과만 출력한다.
"""

import json
import os
import sys
from collections import Counter

PARAMETER_KEY = "key_ranking"
NOT_SET_VALUES = {"", "(not set)", "(other)"}


def raise_for_status(response):
    """실패하면 Google이 보낸 오류 본문을 출력한다. 본문에 권한 부족, API 비활성 같은 원인이 있다."""
    if response.ok:
        return
    print(f"{response.status_code} {response.request.method} {response.url}", file=sys.stderr)
    print(response.text, file=sys.stderr)
    raise_for_status(response)


def run_report(session, property_id, event_name, parameter_name, period_days):
    response = session.post(
        f"https://analyticsdata.googleapis.com/v1beta/properties/{property_id}:runReport",
        json={
            "dateRanges": [{"startDate": f"{period_days}daysAgo", "endDate": "yesterday"}],
            "dimensions": [{"name": f"customEvent:{parameter_name}"}],
            "metrics": [{"name": "eventCount"}],
            "dimensionFilter": {
                "filter": {"fieldName": "eventName", "stringFilter": {"value": event_name}}
            },
            "orderBys": [{"metric": {"metricName": "eventCount"}, "desc": True}],
            "limit": 200,
        },
    )
    raise_for_status(response)
    return [
        (row["dimensionValues"][0]["value"], int(row["metricValues"][0]["value"]))
        for row in response.json().get("rows", [])
    ]


def top_names(rows, size):
    """대소문자만 다른 이름은 합친다. 앱의 Rune.findByName도 대소문자를 무시한다."""
    counts = Counter()
    for name, count in rows:
        name = name.strip().lower()
        if name in NOT_SET_VALUES:
            continue
        counts[name] += count
    # 수가 같으면 이름순으로 정렬해 실행마다 순서가 바뀌지 않게 한다.
    return [name for name, _ in sorted(counts.items(), key=lambda item: (-item[1], item[0]))[:size]]


def build_ranking(rune_rows, rune_words_rows, period_days, size):
    return {
        "periodDays": period_days,
        "runes": top_names(rune_rows, size),
        "runeWords": top_names(rune_words_rows, size),
    }


def publish(session, project_id, ranking):
    url = f"https://firebaseremoteconfig.googleapis.com/v1/projects/{project_id}/remoteConfig"
    response = session.get(url)
    raise_for_status(response)
    etag = response.headers["ETag"]
    template = response.json()

    parameters = template.setdefault("parameters", {})
    current = parameters.get(PARAMETER_KEY, {}).get("defaultValue", {}).get("value")
    if current is not None:
        try:
            if json.loads(current) == ranking:
                print("랭킹이 바뀌지 않아 게시하지 않습니다.")
                return
        except json.JSONDecodeError:
            pass

    parameters[PARAMETER_KEY] = {
        "defaultValue": {"value": json.dumps(ranking, ensure_ascii=False)},
        "description": "GA4 rune_click / rune_words_click 랭킹. GitHub Actions(update_ranking)가 자동으로 갱신한다.",
        "valueType": "JSON",
    }
    template.pop("version", None)

    response = session.put(
        url,
        data=json.dumps(template, ensure_ascii=False).encode("utf-8"),
        headers={"Content-Type": "application/json; UTF-8", "If-Match": etag},
    )
    raise_for_status(response)
    print(f"게시 완료: version {response.json().get('version', {}).get('versionNumber')}")


def main():
    import google.auth
    from google.auth.transport.requests import AuthorizedSession

    property_id = os.environ["GA4_PROPERTY_ID"]
    project_id = os.environ["FIREBASE_PROJECT_ID"]
    period_days = int(os.environ.get("RANKING_PERIOD_DAYS", "7"))
    size = int(os.environ.get("RANKING_SIZE", "5"))
    dry_run = os.environ.get("DRY_RUN", "false").lower() == "true"

    credentials, _ = google.auth.default(
        scopes=[
            "https://www.googleapis.com/auth/analytics.readonly",
            "https://www.googleapis.com/auth/firebase.remoteconfig",
        ]
    )
    session = AuthorizedSession(credentials)
    print(f"서비스 계정: {getattr(credentials, 'service_account_email', '알 수 없음')}")

    ranking = build_ranking(
        rune_rows=run_report(session, property_id, "rune_click", "runeName", period_days),
        rune_words_rows=run_report(session, property_id, "rune_words_click", "runeWordsName", period_days),
        period_days=period_days,
        size=size,
    )
    print(json.dumps(ranking, ensure_ascii=False, indent=2))

    if not ranking["runes"] and not ranking["runeWords"]:
        print("집계 결과가 비어 있어 게시하지 않습니다. 맞춤 측정기준 등록 상태를 확인하세요.", file=sys.stderr)
        sys.exit(1)
    if dry_run:
        print("DRY_RUN: 게시하지 않습니다.")
        return
    publish(session, project_id, ranking)


if __name__ == "__main__":
    main()

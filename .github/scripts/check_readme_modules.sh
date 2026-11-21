#!/usr/bin/env bash
# settings.gradle.kts의 모듈 목록과 README.md 모듈 표가 같은지 검사한다.
set -euo pipefail

settings=$(grep -oE '"(:[a-z0-9:-]+)"' settings.gradle.kts | tr -d '"' | sort -u)
readme=$(grep -oE '^\| `:[a-z0-9:-]+` \|' README.md | grep -oE ':[a-z0-9:-]+' | sort -u)

if [ "$settings" != "$readme" ]; then
  echo "README.md 모듈 표가 settings.gradle.kts와 다릅니다."
  echo "< settings.gradle.kts 에만 있음 / > README.md 에만 있음"
  diff <(echo "$settings") <(echo "$readme") || true
  exit 1
fi
echo "README.md 모듈 표가 settings.gradle.kts와 같습니다."

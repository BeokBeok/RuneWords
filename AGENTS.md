# AGENTS.md — AI 에이전트 컨벤션 가이드

이 문서는 Cursor, Antigravity, GitHub Copilot 등 AI 에이전트가 RuneWords 프로젝트의 코드를 작성하거나 수정할 때 준수해야 할 빌드 최적화 규칙을 정의합니다.

---

## BuildConfig 사용 컨벤션

### 1. 기본값 비활성화 원칙

멀티 모듈의 빌드 속도 최적화 및 캐시 보존을 위해 라이브러리/피처 모듈의 `buildConfig`는 기본적으로 비활성화(`false`)되어 있습니다.

- AGP 8.0 이후 `buildFeatures.buildConfig`의 기본값은 `false`입니다.
- 공통 컨벤션 플러그인(`build-logic`)에 전체 일괄 활성화(`buildConfig = true`)를 선언하지 마십시오.

**왜 중요한가?**
- 100% 순수 Kotlin 모듈임에도 `BuildConfig.java` 생성을 위해 Java 컴파일러(`compileDebugJavaWithJavac`)가 강제 실행됩니다.
- 버전 정보나 빌드 플래그 변경 시 모든 모듈의 캐시가 연쇄 무효화(Cache Invalidation)되어 전체 재컴파일이 발생합니다.
- 서브모듈마다 동일한 이름의 `BuildConfig`가 생성되어 타 모듈의 것을 잘못 import하는 섀도잉(Shadowing) 버그가 유발됩니다.

### 2. 서브모듈에서 BuildConfig 필요 시 행동 요령

특정 서브모듈에서 `BuildConfig` 또는 `buildConfigField`가 필요한 경우:

1. **절대 타 모듈(예: `:app`, `:feature:home`)의 BuildConfig를 import하지 마십시오.** (Shadowing 버그 방지)
2. 반드시 해당 서브모듈의 `build.gradle.kts`에 명시적으로 `buildConfig = true`를 활성화한 후, 해당 모듈 고유 패키지 경로의 `BuildConfig`를 참조하십시오.

```kotlin
// 해당 서브모듈의 build.gradle.kts
android {
    buildFeatures {
        buildConfig = true
    }
}
```

### 3. 현재 BuildConfig 활성화 모듈 목록

다음 모듈만 `buildConfig = true`를 명시적으로 활성화합니다:

| 모듈 | 이유 |
|------|------|
| `:app` | `BuildConfig.DEBUG` 플래그, 앱 버전 정보 등 사용 |
| `:feature:detail` | `BuildConfig.DEBUG` 플래그로 배너 광고 단위 ID 선택 |

> **신규 모듈에서 BuildConfig가 필요한 경우**: 해당 모듈의 `build.gradle.kts`에 `buildFeatures { buildConfig = true }`를 추가하고 이 목록을 업데이트하십시오.

### 4. 오버엔지니어링 지양

단순 플래그나 모듈 전용 값을 위해 무리하게 인터페이스 + Hilt 주입 레이어를 만드는 오버엔지니어링을 피하십시오. 필요하다면 해당 모듈의 `build.gradle.kts`에 직접 `buildConfig = true`를 켜서 사용하는 직관적인 방식을 권장합니다.

---

## README 동기화 컨벤션

`README.md`의 "모듈 구조"와 "기술 스택" 섹션은 코드와 같은 내용을 유지해야 합니다.

### 1. 모듈을 추가하거나 삭제할 때

1. `settings.gradle.kts`의 `include(...)`를 수정합니다.
2. 같은 변경에서 `README.md`의 모듈 표에 행을 추가하거나 삭제합니다. 행 형식은 ``| `:모듈` | 역할 |``입니다.
3. 모듈 의존 관계가 바뀌면 `README.md`의 mermaid 도표도 수정합니다.

PR CI(`.github/scripts/check_readme_modules.sh`)가 `settings.gradle.kts`와 README 모듈 표를 비교합니다. 두 목록이 다르면 PR이 실패합니다.

### 2. 기술 스택이 바뀔 때

라이브러리나 프레임워크를 추가하거나 제거하면 `README.md`의 "기술 스택" 섹션을 수정합니다.

- 예: Room 제거, Ktor 추가, 테스트 프레임워크 교체
- 버전만 올리는 변경은 README를 수정하지 않습니다. README에는 버전을 적지 않습니다.

# repository-template

# 프로젝트 세팅 가이드라인

## PR 템플릿

PR을 만들 때 이 템플릿을 사용해 주세요. 관련된 이슈를 자동으로 닫는 방법도 포함되어 있습니다.

```markdown
## #️⃣ 완료한 이슈

> ex) Closes #이슈번호, Closes #이슈번호

## 📝 작업 내용

> 이번 PR에서 작업한 내용을 간략히 설명해주세요 (이미지 첨부 가능)

### 스크린샷 (선택사항)

> 변경된 UI가 있다면 스크린샷을 첨부해주세요.

## 💬 리뷰 요구사항 (선택사항)

> 리뷰어가 특별히 봐주었으면 하는 부분이 있다면 작성해주세요
>
> ex) 메서드 XXX의 이름을 더 잘 짓고 싶은데 혹시 좋은 명칭이 있을까요?
```

## 코드 포매터 설정 방법

이 프로젝트에서는 네이버 Java 코드 포매터를 따릅니다. 이를 IntelliJ에 자동으로 적용하도록 설정하는 방법은 다음과 같습니다.

### IntelliJ에 네이버 코드 포매터 설정하기

#### 네이버 코드 포매터 XML 다운로드

네이버 코드 포매터 XML 파일을 [다운로드](https://github.com/naver/hackday-conventions-java/blob/master/rule-config/naver-intellij-formatter.xml)합니다.

#### IntelliJ에 XML 파일 가져오기

1. IntelliJ를 열고, 상단 메뉴에서 `File > Settings` (또는 `Preferences` on macOS)를 클릭합니다.
2. `Editor > Code Style > Java`로 이동합니다.
3. 우측 상단의 톱니바퀴 버튼을 클릭하고, `Import Scheme > IntelliJ IDEA Code Style XML`을 선택합니다.
4. 다운로드한 `rule-config/naver-intellij-formatter.xml` 파일을 선택합니다.

#### 코드 스타일 설정 적용

1. 적용된 코드 스타일을 선택하고, `OK` 버튼을 클릭하여 설정을 저장합니다.

### 프로젝트에 코드 스타일 설정 파일 추가

프로젝트 내에서 모든 기여자들이 동일한 코드 스타일을 사용할 수 있도록 코드 스타일 설정 파일을 프로젝트에 포함시킵니다.

### 코드 저장 시 자동 포매팅 및 import 최적화 설정

1. IntelliJ를 열고, 상단 메뉴에서 `File > Settings` (또는 `Preferences` on macOS`)를 클릭합니다.
2. `Tools > Actions on Save`로 이동합니다.
3. "Reformat code" 및 "Optimize imports" 옵션을 선택합니다.
4. `OK` 버튼을 클릭하여 설정을 저장합니다.

이제 IntelliJ에서 파일을 저장할 때마다 자동으로 코드 포맷팅과 import 최적화가 수행됩니다.
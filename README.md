## 🌳 브랜치 전략

### `main`
- 목적: 배포될 코드만 병합
- 규칙:
  - 직접 `push` 금지
  - PR(Pull Request)를 통해서만 병합 가능
  - `hotfix`를 제외하고는 `release` 브랜치로부터 병합되어야 함
  - 최신 PR을 제외한 모든 PR은 자동 헤제

### `release`
- 목적: 통합 테스트 브랜치
- 규칙:
  - 직접 `push` 금지
  - PR(Pull Request)를 통해서만 병합 가능

### `feature`
- 목적: 기능 구현, 리팩토링, 설정 정보 추가
- 내용: 모든 기능 담당

### `issue/숫자`
- 목적: 이슈에 기반하여 브랜치 생성

---

## 📝 커밋 컨벤션

- Feat: 기능 추가
- Fix: 에러 수정
- Refactor: 코드 리팩토링
- Docs: 앱 설정에 영향을 미치지 않는 문서 작업

> 📌 참고: `커밋 컨벤션`은 함께 사용 가능<br/>
> ex) Feat, Fix: fix error, add api

## 🚀  트러블슈팅


#### [API 호출 제한 문제와 캐싱을 이용한 해결방안](https://daffy-molecule-d69.notion.site/API-2895b740caec454cb500090f68023c48?pvs=4)





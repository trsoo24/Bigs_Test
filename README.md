# 빅스 Back-End 개발자 실습 테스트
### 과제 요구 사항 :
- 단기예보를 확인할 수 있는 API 를 작성
- 공공데이터 포털의 기상청_단기예보 ((구)_동네예보) 조회서비스 를 사용
   - RestTemplate을 사용
- 멀티모듈 프로젝트를 작성

### 구현 API
- [x] 단기 예보를 DB에 저장하게 하는 API
    - POST 요청시 공공데이터 포털의 API를 호출하여 바로 DB 에 적재합니다.
       - POST `/api/sync`
- [x] 단기 예보를 조회 하는 API
    - GET 요청시, DB 에 저장된 데이터를 조회합니다.
       - GET `/api/weather`
    - 데이터가 없을 경우, Http status 204 오류를 응답해야 합니다.

### STACKS
- Java 17
- Spring Boot 3.2.5
- MySQL
- JPA

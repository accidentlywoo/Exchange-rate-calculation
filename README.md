# Exchange-rate-calculation

DB 커넥션 없음

포트 8080

크롬브라우저 이용

- 2021.3.6 환율 계산 API에서 문제 발생 중

## 개선 계획
현재 바로 api 연결해서 불안정함

1. H2 인메모리 디비 사용
2. Spring Scheduler로 매 10분마다 최신 데이터 적재
3. 예외, Excpetion 처리
4. 웹플럭스를 처음 배워서 재미있으니 더 해보기 



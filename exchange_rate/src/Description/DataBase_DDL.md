## 데이터베이스 설명
H2 인메모리 데이터베이스 사용.
적절한 위치에 DataBase설정하고 Server방식으로 접속 및 실행 시킨 후 DDL문 돌리면 문제없이 돌아갑니다!

## DDL
스키마 생성 후 

```
create table 스키마.EXCHANGE_RATE
(
	id BIGINT auto_increment,
	from_nation VARCHAR(3) not null,
	to_nation VARCHAR(3) default 'USD' not null,
	quotes double not null,
	"isCurrent" TINYINT not null,
	constraint EXCHANGE_RATE_PK
		primary key (ID)
);
```

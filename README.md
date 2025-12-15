Spring Boot 기반의 **확장자 차단/허용 관리 시스템**입니다.  
고정 확장자는 체크박스로 제공되며, 사용자는 커스텀 확장자를 추가/삭제/토글하여 웹에서 실시간으로 관리할 수 있습니다.

## 🛠 기술 스택

### Backend
- Java 17+
- Spring Boot 3.x
- Spring MVC (Thymeleaf)
- Spring Data JPA
- Hibernate
- H2

### Frontend
- HTML5 / CSS3
- Thymeleaf

🌐 웹 접속 방법
http://3.36.65.94/

💾 H2 Database
- 접속 URL
  http://3.36.65.94/h2-console

 - JDBC 설정
  JDBC URL: jdbc:h2:mem:extdb
  USERNAME: sa
  PASSWORD: (비어있음)

🧱 Entity 스키마
create table extension (
    id bigint auto_increment,
    ext varchar(20) not null unique,
    fixed boolean not null,
    blocked boolean not null,
    primary key (id)
);

👨‍💻 개발자 정보
Developer: 우복 정
Using Java/Spring Boot/Thymeleaf

# todolist

## 1. 도메인정의
- 도메인은 User와 Todo 2가지로 정의하였습니다. User가 있어야 Todo가 있을 수 있고, User가 Todo를 생성 수정 관리를 하기때문에 Todo를 User의 하위도메인으로 생각하였습니다.
- Todo를 User의 하위도메인으로 설정했기 때문에 Api에서 RestFul 하게 /users/{id}/todos로 하여 Todo를 조회하게 하였습니다.
- 로그인한 user는 자기 자신의 Todo만 조회해야 하므로 {id} 대신 me 라는 값으로 API를 구성하여, 클라이언트에서 user의 id를 받지 않고 API 호출 시 token에 들어있는 id 값으로 조회하도록 하였습니다.
- Entity에서 User와 Todo를 1:N으로 만들었습니다.
- Todo는 CREATED, PROCESSING, COMPLETED 3가지의 상태를 가지고, Service Logic에서 값을 변경하는 것이 아닌 도메인안에서 메소드로 정의하여 값을 수정하도록 하였습니다.
- User, Todo 모두 soft delete를 하여 실제 DB에서 삭제를 하는 것이아닌 표시를 해, 화면에 보이지 않도록 하였습니다. 여기에서 Boolean을 사용 할 수 있지만, 나중에 언제 탈퇴했는지도 알 수 있도록 하기 위해 삭제 날짜를 기록하여 하나의 컬럼으로 삭제 여부와 날짜까지 알 수 있도록 하였습니다.

## 2. 아키텍쳐 구성
- 3Tier로 하였습니다. 이유는 아직 트레픽을 파악 할 수 없고, 외부 통신이 많이 없는 서비스라 생각되어 MSA나 헥사고날로 진행하지 않았습니다.
- RestAPI와 Controller를 이동하는 Controller, 로직 구현을 위한 Service, 디비 저장을위한 Persistent로 패키지를 분류하였습니다.
- 각 레벨단에서 사용할 DTO들은 data라는 페키지안에 만들었습니다.
- MSA전환을 대비하여 도메인 단위로 패키지를 분리 하였습니다.

## 3. 기능 요구사항에 대한 설명
- Rest API로 설계하였습니다.
- Spring Security와 Jwt를 사용하였으며 발급받은 토큰은 Cookie에 저장하도록 하였습니다.
- 회원가입은 간단한 비밀번호 체크와 아이디, 닉네임의 중복체크를 구현하였고, 비밀번호는 암호화하여 DB에 저장하도록 하였습니다.
- 클라이언트에서 Cookie를 확인하여 Token이 있는지 확인하고 없다면 로그인 화면으로 이동하도록 하였습니다.
- 로그인시 token을 Cookie에 저장하고 홈화면으로 이동시켰습니다.
- 회원가입후에도 token을 Cookie에 저장하고 홈화면으로 이동시켰습니다.
- Soft delete로 인해 회원탈퇴시 userId와 nickName을 계속 점유하고 있기 때문에 탈퇴된 회원전용 테이블에 옮길지 다른 표시를 할지 생각하다 userId와 nickName을 id 값으로 변경하도록 하였습니다. 
- User는 Todo를 작성 할 수 있으며 간단하게 제목, 내용, 시작일 3개를 입력하여 저장 할 수 있습니다.
- 조회의 경우 전체조회와 최근한건 조회 모두 /users/me/todos 라는 API 하나에서 조회가 되어야하기 때문에 QueryString에서 limit의 값을 -1로 설정 했을 때엔 전체조회를 하도록 서버쪽에서 코딩을 하였습니다.
- 최근한건의 경우는 Rest API로는 만들 수 없다 생각하여, 클라이언트에서 offset = 0, limit = 1, order = DESC로 조회시 나올 수 있도록 하였습니다.
- Todo를 도메인으로 정의하여 메소드를 통해 상태를 변경하도록 하였습니다. 해당 기능은 하나의 작은 값만 변경하는 것으로 PutMapping 대신 FetchMapping을 사용하였습니다.

## 4. 확장가능한 코드 구성 또는 아키텍쳐 구성
- user를 위한 다양한 기능이 만들어지고, 각 기능들의 더 고도화가 되고, 트레픽이 많아져 분산이 필요하다면 MSA로 전환하겠습니다.
- 기능들의 주체인 user와 전반적인 시스템을 관리하는 BackOffice, 불특정한 외부와의 통신이 많은 서버의 경우는 헥사고날 아키텍처를 도입하여 구성 할 생각입니다.
- Spring Batch 또는 Jenkins Batch를 사용하여 설정한 시간 전에 push 알림을 보낼 수 있도록 notification 서버를 구축하고 API통신이 아닌 MQ를 사용하여 통신 할 생각입니다.
- 클라이언트와 서버간의 비밀번호와 같은 개인정보도 암호화하여 통신하도록 할 생각입니다.

## 5. 기타
- View 화면이 미완성입니다.🥲 yaml 설정 수정, todo라는 mysql schema 만 생성하면 swagger를 통해 모든 API를 확인 할 수 있습니다
- Swagger에도 Spring security를 넣어 우선 회원가입과 로그인을 통해 토큰을 발급받고, 해당 토큰을 Authorize에 넣고 사용 할 수 있도록 하였습니다.

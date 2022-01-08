# Team Meta Storage

<center><img src="http://teametastorage.com/img/companyImage.png" width="300" height="300"></center>

[![Build Status](http://192.168.11.21:9000/buildStatus/icon?job=teametastorage)](http://192.168.11.21:9000/job/teametastorage/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

![](https://img.shields.io/badge/Maintained-yes-green.svg)
![](https://img.shields.io/website-up-down-green-red/http/monip.org.svg)
![](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)

![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![](https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
![](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

## 목차
[1. **프로젝트 소개**](#프로젝트-소개)

[2. **개발 환경**](#개발-환경)

[3. **설계 구조**](#설계-구조)

[4. **상세 기능**](#상세-기능)

[5. **업데이트 진행**](#업데이트-진행)


## 프로젝트 소개
* 해당 프로젝트는 스프링부트 기반 웹 어플리케이션이며, 다음 목표를 달성하기 위하여 제작되었습니다.  
  * 기존의 레거시한 정보에 대한 정보 종합 및 관리에 사용  
  * 업무 및 과제 수행에 대한 계층적 정보 종합에 사용  
  * 기타 소규모 팀 단위 업무 진행시 프로젝트 일정 관리에 사용  
* 개발 효율성을 향상시키기 위하여 다음과 같은 인프라가 활용, 구축되었습니다.  
  * Jenkins 및 Docker 를 이용한 CICD 파이프라인이 구성되었습니다.  
  * Amazon Web Service 환경을 이용한 클라우딩 기술을 활용하여 구축하였습니다.  
* 현재 프로젝트는 정상 배포중이며, 어플리케이션은 [다음 주소](http://teametastorage.com/)를 통하여 접근이 가능합니다.  
  * 샘플 관리자 계정은 다음과 같습니다.  
    * ID:admin
    * PW:admin
* 해당 프로젝트는 [MIT 라이센스](https://choosealicense.com/licenses/mit/) 기반 배포 및 운영됩니다.  
  *  관련 의문사항이나 기타 2차 가공, 혹은 상업적 이용 등은 [이슈 생성](https://github.com/LucestDail/teametastorage/issues)을 통하여 문의 및 진행하시길 바랍니다.



## 개발 환경

- 코드 작성 환경 구성
  * Eclipse
    * 유료화 툴 사용을 가급적 배제 및 사용자 커스터마이징 포퍼먼스에 맞는 에디트 툴 선택
  * DBeaver
    * 이클립스 기반 무료 툴 중 가장 Reference 다수 보유한 DB 에디트 툴 선택

- 어플리케이션 환경 구성
  * Java 8
    * Java 8 이후의 상업적 이용에 대한 유료화 라이센스 및 Optional 활용에 따라 해당 버전 활용
    * 추가적인 Java 9 이후의 기능 사용이 진행 안될 것으로 판단, Java 8 선택
  * Spring Boot 2.5.2
    * 웹 프레임워크 초기 구축 효율화를 위해 SpringBoot 활용
    * Snapshot 버전에 대한 불확실성 배제를 위하여 현재 LTS 버전인 2.5.2 버전 선택
  * Maven 4.0.0
    * 빌드 툴 안정성 및 하위 호환성 고려 최신 버전 4.0.0 선택
  * spring-boot-starter-thymeleaf
    * View Template Engine 기반 스프링 진영에서 Active 하게 반응하는 뷰 엔진 활용
    * Document, Reference 다수 보유하며, Bootstrap 효응성 가장 우수하므로 선택
  * spring-boot-starter-data-jpa, lombok
    * Domain 단위 관리 및 데이터베이스 직접적 조작을 최소화 하기 위하여 코드 단위 관리에 최적화된 JPA 선택
    * 지연 저장 기능을 활용한 데이터 불시적인 트랜젝션 오류 최소화 위하여 활용
    * JPA 기능에 따른 코드 관리에 가장 적합한 라이브러리 Lombok 적용하여 코드 가독성 및 개발 효율성 도모
  * mariadb-java-client
    * Oracle 상업적 이용에 따른 유료 라이센스 상정, mysql 진영 무료 라이센스 데이터베이스 활용
    * Reference, Document 다수 보유한 MariaDB 선택
  
- 운영 환경 구성
  * spring-boot-starter-actuator, spring-boot-admin-starter-client, logback
    * 어플리케이션 운영 환경에 대한 가비지 메모리, 현재 시스템 가용상태 확인을 위한 모니터링, 로깅 기능 도입
  * Amazon Web Service (Ubuntu 20.04 LTS)
    * AWS EC2 인스턴스를 활용하여 서버 구축 및 어플리케이션 배포 
      * Local 환경에서 구축시 상시 서버 기기 구동이 필요하므로 클라우드 서버로 대체
      * GCP, Heroku, Naver Cloud, Gabia 등 여러 서비스 확인하였으나,  
         실제 운영 서버 구축시 추가적으로 발생할 인프라 확장성을 고려 AWS 기반 인프라로 선택
    * Ubuntu 최신 버전을 활용하여 서버 보안성 향상 의도
    * DevOps 서버를 추가로 구성하여 해당 어플리케이션에 대한 모니터링, 자동 배포 기능을 수행  
      * 모니터링 서버, 자동 배포 서버 각각 구축 및 어플리케이션 포트 연동
    * AWS RDS 를 활용하여 클라우딩 환경에서 데이터베이스 구축
  * jquery, jquery-ui, momentjs, fullcalendar
    * 이미지 기반 업무, 과제 계층 구조를 그리기 위하여 기존 제작된 달력 템플릿을 사용
    * 데이터 입력시에는 자체 비지니스 로직을 통한 데이터 입출입
  * Springboot Embded Apache Tomcat
    * 소규모 어플리케이션 구동시 가용성 및 추가적인 설정 고려 해당 WAS 선택

## 설계 구조

- 코드 구조  
  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810143810bae.png)
  * 프로젝트 구조는 메이븐에서 제시하는 권장 템플릿 구조로 진행하였습니다.
  * com.teametastorage
    * 최초 어플리케이션 진입점이며 main 이 실행됩니다.
  * .config
    * 로그, 웹 설정 등 어플리케이션 설정을 관리합니다.
  * .controller
    * Handler 에 대한 맵핑 컨트롤러를 구성 RESTful 한 URL 입출입을 관리합니다.
  * .domain
    * JPA 표준 작성에 기반하여, 테이블 생성과 명세를 작성합니다.
  * .dto
    * 데이터 입출입이 발생하는 중간 데이터에 대한 객체를 관리합니다.
  * .interceptor
    * 서비스단 진입과 관련한 인터셉트 기능을 수행합니다.
  * .repository
    * 표준 JPA 기반 데이터 입출입 인터페이스를 수행합니다.
  * .service
    * 메인 비지니스 로직 및 레포지토리를 통한 데이터 가공, 입출입을 통제합니다.
  * .util
    * 데이터 및 기타 로직 관리에 있어서 필요한 기능이 포함됩니다.  
  
- 운영 환경 구조
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/202108101438116ad.png)

  * 기본적으로 AWS IaaS 기반으로 구성되며 이를 통하여 어플리케이션을 설정하였습니다.  
  * 어플리케이션 서버는 Micro 기반으로 구성, Devops 서버는 small 을 활용하여 구성하였습니다.(Docker 기반 빌드 시 가용성 고려)  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810150737a19.png)

  * AWS Route 53 을 활용한 DNS 도메인을 적용하였으며, 이를 활용하기 위하여 ELB 를 통한 라우팅 기능을 구성하였습니다.  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/2021081015074055d.png)

  * HTTPS 적용시 현재 일부 기능 활용 및 호환이 제한되는 문제가 식별되어 HTTPS 를 HTTP 로 강제 라우팅 전환하여 서비스를 제공중입니다.  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810150742402.png)
  * AWS RDS 를 활용하여 추가적인 용량 확보가 용이하도록 클라우딩 데이터베이스 기능을 구성하였습니다.  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810150743bcc.png)

  * 내부적으로 Spring MVC 구조를 채택하여 맵핑 후 RESTFul 한 서비스 URL 제공이 가능하도록 구성하였습니다.  
  * 서버에 접근 가능한 대상은 포트로 구분하여 AWS VPC 를 세팅, 구성하였습니다.  
  * 개발 가용성 및 배포 시간을 단축하기 위하여 Jenkins - Docker 기반 CICD Pipeline 이 구축되었습니다.  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810150736567.png)
  * 어플리케이션 모니터링을 위한 SpringBootAdmin 이 적용되었습니다.  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/2021071909171161a.png)

- 데이터 구조  
![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810143808f3b.png)
  * 표준 JPA 기반 테이블 생성이 되었으며, Default Type 은 UTF-8 형식으로 데이터를 입출입, 통제합니다.  

## 상세 기능
- 로그인  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/2021071821444636e.png)
  - 일반적인 로그인 기능입니다.
  - 모바일 활용이 가능하도록 반응형으로 구성하였습니다.(이하 모든 페이지 반응형 구성 완료)

- 회원가입  
    ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/2021081014492423c.png)
  - 간단하게 입력이 가능한 정보들로만 구성하였으며, 개인이 특정되는 정보는 받지 않습니다.(정보화법률에 의거 근본적인 분쟁 소지 제거)
  - 최초 팀 가입시에는 해당 팀원이 관리자가 되며, 이후는 해당 관리자 팀원이 승인해야 로그인이 가능합니다.
  
- 메인(일정 확인)  
  ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/2021081014492520a.png)
  - 업무 입력 및 과제 입력을 통하여 제작된 일정을 볼 수 있습니다.
  - 최초 진입 페이지입니다.

- 개인정보  
  ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810144927c9d.png)
  - 개인정보를 변경이 가능합니다.

- 과제 입력  
  ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810144928d1e.png)
  - 업무에 1:N 으로 구성되는 과제 입력이 가능합니다.

- 업무 입력  
  ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/202108101449305f0.png)
  - 업무를 입력 가능합니다
  - 하위 항목으로 과제가 있으며, 달력에 직접 제시되는 항목입니다.

- 팀 단위 게시판 기능  
  ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810144931ff6.png)
  - 팀 단위로 REST 한 URL 로 표시되는 기능입니다.
  - 모든 기능은 모달로 제시됩니다.
  
- 팀원 관리 페이지(팀 관리자)  
  ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/2021081014493382d.png)
  - 관리자에게만 표시되는 기능입니다.
  - 팀원 가입 허가, 삭제 및 개인 정보 열람이 가능합니다.

- 팀원 확인 페이지  
  ![alt](https://blog-imgs-136-origin.fc2.com/d/a/i/dailusia/20210810144934eac.png)
  - 일반 팀 맴버가 확인이 가능한 팀원 목록입니다
  - 일반적인 아이디, 이름만 확인이 가능합니다.

## 업데이트 진행
  - Ver 1.000(21.03.01)
    - 인스턴스 배포
      - IP URL 형식으로 최초 서버 구성이 완료되었습니다.
    - CRUD 형식에 맞는 데이터 입출입 구현
      - 내장 Hibernate DB 를 활용한 간단한 입출입 게시판 구성이 완료되었습니다.
  - Ver 1.001(21.03.15)
    - RDS 연동을 통한 클라우딩 서버 구조 완성
      - RDS 연동을 통하여 RDS - EC2 데이터 연동이 완료되었습니다.
    - 데이터 구조 및 테이블 설계가 완료되었습니다.
  - Ver 1.002(21.03.29)
    - 데이터 입출입 비지니스 로직 구현이 완료되었습니다.
    - 최초 어플리케이션 디플로이(IP URL 형식)
  - Ver 1.010(21.04.12)
    - 데이터 구조 및 테이블 컬럼을 재조정합니다.
    - 로그인 기능이 추가됩니다.
    - JPA 기반 데이터 구조로 변경합니다.
  - Ver 1.011(21.04.26)
    - 게시판 기능이 확장 및 RESTful API 적용을 시작합니다.
  - Ver 1.012(21.05.03)
    - KMS 도입 및 HTTPS 도입이 완료되었습니다.
  - Ver 1.013(21.05.10)
    - 통일된 Controller 에서 각 서비스에 따른 구조로 분리합니다.
    - 게시판 RestFul 한 구조로 개편 및 추천, 조회수 기능이 추가됩니다.
  - Ver 1.014(21.05.24)
    - 댓글 기능 및 현재 세션에 따른 기능 조정이 이뤄집니다.
    - 운영사이트에 맞는 정책 게시판, 문의게시판 등 편의성 확장을 위한 기능이 도입됩니다.
  - Ver 1.020(21.06.07)
    - Jenkins CICD 파이프라인의 세부세팅 및 안정화가 완료되었습니다.
    - Jenkins 연동한 Docker 활용 가상환경 배포세팅이 완성되었습니다.
  - Ver 1.021(21.06.21)
    - AWS Route53 구매 및 연동이 완료되었습니다.
  - Ver 1.030(21.06.28)
    - 메인 디자인 템플릿이 변경되었습니다.
    - 공통된 디자인 템플릿이 동적으로 변경됩니다.
  - Ver 1.100(21.07.05)
    - 깃허브 저장소가 이동되었습니다.
    - 어플리케이션이 DNS 서버에 등록, 캐싱 확인되었습니다.
    - CKEditor 유료 라이센스 문제로 ToastUI로 대체합니다.
  - Ver 1.101(21.07.19)
    - SSL 적용시 내부 데이터 입출입에 문제를 확인하였습니다.
    - 일시적으로 HTTPS 기능이 비활성화됩니다.
    - 서버 라우팅 세팅 및 VPC 세팅이 조정됩니다.
    - LocalDateTime 기반의 업무, 과제 기능이 추가됩니다.
    - 모니터링 기능이 추가됩니다(Springboot admin)
  - Ver 1.102(21.08.02)
    - 1.101 기반 달력이 추가됩니다.
  - Ver 1.103(21.08.09)
    - README 마크다운 문서를 개편합니다.
  - Ver 1.104(21.08.17)
    - teamManage REST API 이슈를 수정합니다.
    - 로고 및 favicon url 을 동적에서 정적으로 변경합니다.
  - Ver 1.110(21.08.23)
    - 과제 및 업무 로직이 개편됩니다.
      - 기존 1:1 -> n:1(과제 n : 업무 1)
        - 이에 따라 업무 생성시 업무에 해당하는 과제를 복수 지정할 수 있습니다.
        - 업무 생성시 과제 생성이 선행됩니다.
        - 업무 생성시 과제 검색 자동완성 기능이 추가됩니다.
  - Ver 1.111(21.11.02)
    - Spring Boot Admin 추가 옵션이 적용됩니다.
      - JMX 및 Logger 기능, 추가적인 옵션들이 도입됩니다.
        - 빌드 정보 및 내부 하드 정보 확인이 가능합니다.
        - 프로그램상 정보를 Docker 로그가 아닌 관리자 페이지에서 확인이 가능합니다.
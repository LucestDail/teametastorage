<center><img src="http://teametastorage.com/img/companyImage.png" width="300" height="300"></center>

[![Build Status](http://ec2-3-36-84-108.ap-northeast-2.compute.amazonaws.com:8080/buildStatus/icon?job=teametastorage)](http://ec2-3-36-84-108.ap-northeast-2.compute.amazonaws.com:8080/job/teametastorage/)

  
# Team Meta Storage


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
* 개발 효율성을 향상시키기 위하여 다음과 같은 인프라가 활용되었습니다.  
  * Jenkins 를 이용한 CICD 파이프라인이 구성되었습니다.  
  * AWs 환경을 이용한 클라우딩 기술이 활용되었습니다.  
* 현재 프로젝트는 정상 배포중이며, 어플리케이션은 [다음 주소](http://teametastorage.com/)를 통하여 접근이 가능합니다.  
  * 샘플 관리자 계정은 다음과 같습니다.  
    * ID:admin
    * PW:admin
* 해당 프로젝트는 [MIT 라이센스](https://choosealicense.com/licenses/mit/) 기반 배포 및 운영됩니다.  
  *  관련 의문사항이나 기타 2차 가공 및 상업적 이용 등은 [이슈 생성](https://github.com/LucestDail/teametastorage/issues)을 통하여 문의 및 진행하시길 바랍니다.



## 개발 환경
* bootstrap 5.0.2
* thymeleaf 
* Java 8
* MariaDB
* mybatis
* lombok
* Spring Boot 2.5.3
* Gradle
* IntellJ 2021-01
* Amazon Web Service (Amazon Linux)
1. This project builded on apach tomcat server within SpringBoot Internal Web Server(ver 9.0.)
2. This project use maria db for storing database. you can download any version you want(but you have to write configuration about db connection).
[Download](https://mariadb.org/download/)
3. This project compiled from eclipse editor, you can download any version you want.
[Download](https://www.eclipse.org/downloads/)
## 설계 구조



## 상세 기능
Key features include:

- Member management
- Board
- Comment (depend on Board utility)   
- Meta Data saving

Following issue has been fixed:

- Ver.1.000
    * Basic template has been updated


## 업데이트 진행
If you have some problem or find some bugs in this project, You can announce with issue tab with this 

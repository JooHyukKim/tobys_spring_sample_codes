# 토비의 스프링 학습 후기와 주요 내용 정리


---
---


## 목차


0. 시작 계기
1. 후기
2. 핵심 내용 정리 모음

 
---
---

 
## 0. 시작 계기 


> 요즘 자바 개발자 중에 스프링을 사용하지 않고 개발하는 분이 계신가요?
>
> 자바와 스프링은 떨어뜨릴래야 떨어뜨릴 수 없는 그런 관계라고 볼 수 있겠네요.
>
> 물론 이 책을 읽기 전까지는 이유는 몰랐습니다. 
>
> 저는 자바로 개발을 하는 개발자이며 스프링을 사용해서 웹 서비스를 만드는 일을 하고 있습니다.
>
> 토비의 스프링을 시작하게 된 계기를 물으신다면
>
> 제가 사용하는 스프링이 과연 자바 위에서 어떻게 동작을 하고 있으며 어떤 원리로, 그리고 우리는 어떻게 혜택을 받고 있는지 알아가고자 시작하게된것 같네요.


---
---

<img src="./cover.jpeg" alt="cover" title="cover" width="400" alignCenter />


---
---


## 1. 후기


>
> 제가 처음 회사에 입사했을 떄 수석 백엔드 개발자 분께 스프링, 자바 관련 책을 추천해달라고 했던 적이 있었습니다.
>
> 토비의 스프링이라는 책을 추천해주시더군요. 마침 회사에도 있길래 집에 가져가서 읽어보았습니다. 아주 잠깐이요.
>
> 일단 상당히 두껍다는 점에서 기가 죽어있는 상황에서 스프링을 상당히 깊은 수준에서 설명하는 것이 잘 이해가 되지 않았습니다.
>
> 자바도 긴가민가 한 부분이 많았나 봅니다.
>
> 그렇게 책을 바로 덮고 잊고 산지 1년이란 시간이 지났네요.
>
> 최근 몇달 자바 외 업무 (서비스 아키텍처 설계, 문서 작성 등) 들에 치여살다가 다시 자바와 스프링을 사용하려니 상당히 어색하더군요. 
>
> 그래서 다시 책을 찾다가 다시 또 토비의 스프링을 만나게 되었습니다. 
>
> 이번에는 다르겠지 하며 100% 배우자는 마음으로 책을 읽기 시작했는데....
>
> 후기를 말씀드립니다.
>
> 자바를 사용한다면, 스프링을 사용하실 테고, 스프링을 사용한다면 이 책을 통하여 스프링의 원칙과 원리들에 대해 정말 설명을 들을 수 있는 좋은 책이라고 생각합니다.
>
> Vol.1 을 읽고나면 스프링의 원리와 원칙, 철학을 이해하시리라 믿습니다.
>
> 스프링의 추상화와 의존관계 주입, 그리고 AOP 에 대한 내용도 담겨있습니다.
>
> Vol.2 는 Vol.1의 실제 적용사례? 라고 보시면 좋을 것 같네요. 
>
> Vol.1은 정말 감탄하면서 읽었고
>
> Vol.2는 다시 현실 세계로 돌아온? 그런 기분입니다.  
>
> 후기가 매우 두서없지 않았나 싶은 마음을 가지고 마치겠습니다
>
> 그럼 20000
> 


---
---


## 2. 핵심 내용 정리 모음


### 목차 설명


> 대분류 : 내용 정리를 텍스트로 정리한 readme.md로 이동
>
> 소분류 : 해당 챕터의 코드 구현 branch로 이동


### [1 : 오브젝트와 의존관계 <-click](./노트정리/vol1/chapter1.md)


- ["1.1.-초난감-DAO"    --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.1.-초난감-DAO)
- ["1.2.-DAO의-분리" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.2.-DAO의-분리)
- ["1.3.-DAO의-확장" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.3.-DAO의-확장)
- ["1.4.-제어의-역전(IoC)" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.4.-제어의-역전(IoC))
- ["1.5-스프링의-IoC" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.5-스프링의-IoC)
- ["1.6.-싱글톤-레지스트리와-오브젝트-스코프" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.6.-싱글톤-레지스트리와-오브젝트-스코프)
- ["1.7.-의존관계-주입(DI)" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.7.-의존관계-주입(DI))
- ["1.8.-XML을-이용한-설정"   --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/1.8.-XML을-이용한-설정)


### [2 : 테스트 <-click](./노트정리/vol1/chapter2.md)


- ["2.1.-UserDaoTest-다시-보기"   --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/2.1.-UserDaoTest-다시-보기)
- ["2.2.-UserDaoTest-개선"   --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/2.2.-UserDaoTest-개선)
- ["2.3.-개발자를-위한-테스팅-프레임워크-JUnit"   --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/2.3.-개발자를-위한-테스팅-프레임워크-JUnit)
- ["2.4.-스프링-테스트-적용"   --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/2.4.-스프링-테스트-적용)
- ["2.5.-학습-테스트로-배우는-스프링"   --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/2.5.-학습-테스트로-배우는-스프링)


### [3 : 템플릿 <-click](./노트정리/vol1/chapter3.md)


- ["3.1.-다시-보는-초난감-DAO" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/3.1.-다시-보는-초난감-DAO)
- ["3.1.-다시-보는-초난감-DAO_v2_내부익명클래스활용" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/3.1.-다시-보는-초난감-DAO_v2_내부익명클래스활용)
- ["3.4.-컨텍스트와-DI" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/3.4.-컨텍스트와-DI)
- ["3.4.-컨텍스트와-DI_UserDAO에서-직접-DI-구현" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/3.4.-컨텍스트와-DI_UserDAO에서-직접-DI-구현)
- ["3.5.-템플릿과-콜백" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/3.5.-템플릿과-콜백)
- ["3.6.-스프링의-JdbcTemplate" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/3.6.-스프링의-JdbcTemplate)


### [4 : 예외 <-click](./노트정리/vol1/chapter4.md)


- ["4.1.-사라진-SQLException" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/4.1.-사라진-SQLException)
- ["4.2.-예외-전환" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/4.2.-예외-전환)


### [5 : 서비스 추상화 <-click](./노트정리/vol1/chapter5.md)


- ["5.1.-사용자-레벨-관리-기능-추가" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/5.1.-사용자-레벨-관리-기능-추가)
- ["5.2.-트랜잭션-서비스-추상화" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/5.2.-트랜잭션-서비스-추상화)
- ["5.3.-서비스-추상화와-단일-책임-원칙" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/5.3.-서비스-추상화와-단일-책임-원칙)
- ["5.4.-메일-서비스-추상화" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/5.4.-메일-서비스-추상화)


### [6 : AOP <-click](./노트정리/vol1/chapter6.md)


- ["6.1.-트랜잭션-코드의-분리" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/6.1.-트랜잭션-코드의-분리)
- ["6.2.-고립된-단위-테스트" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/6.2.-고립된-단위-테스트)
- ["6.3.-다이내믹-프록시와-팩토리빈" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/6.3.-다이내믹-프록시와-팩토리빈)
- ["6.4.-스프링의-프록시-팩토리-빈" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/6.4.-스프링의-프록시-팩토리-빈)
- ["6.5.스프링-AOP" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/6.5.스프링-AOP)
- ["6.6.-트랜잭션-속성" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/6.6.-트랜잭션-속성)
- ["6.7.-애노테이션-트랜잭션-속성과-포인트-컷" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/6.7.-애노테이션-트랜잭션-속성과-포인트-컷)


### [7 : 스프링 핵심 기술의 응용 <-click](./노트정리/vol1/chapter7.md)



- ["7.-7장" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/7.-7장)
- ["7.-XML-에서-Annotation-타입으로-전환" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/7.-XML-에서-Annotation-타입으로-전환)


### [8 : 스프링이란 무엇인가? <-click](./노트정리/vol1/chapter8.md)


- ["8.-스프링이란-무엇인가" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/8.-스프링이란-무엇인가)


### [9 : 스프링 프로젝트 시작하기 <-click](./노트정리/vol1/chapter9.md)


- ["9.-스프링-프로젝트-시작하기" --source code로 이동-->>](https://github.com/JooHyukKim/tobys_spring_sample_codes/tree/9.-스프링-프로젝트-시작하기)
 

### [10 : IoC 컨테이너와 DI <-click](./노트정리/vol2/chapter1.md)



### [11 : 데이터 액세스 기술 <-click](./노트정리/vol2/chapter2.md)







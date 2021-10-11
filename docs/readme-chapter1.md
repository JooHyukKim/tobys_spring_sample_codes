스프링이란 무엇인가?

애필르케이션의 기본 틀 - 스프링 컨테이너

- 스프링 컨테이너 또는 애플리케이션 컨텍스트라고 불리는 스프링 런타임 엔진을 제공. 스프링 컨테이너는 설정정보를 참고로해서 애플리케이션을 구성하는 오브젝트를 생성 및 관리.

공통 프로그래밍 모델

- IoC / DI
    - 오브젝트의 생명주기와 의존관게에 대한 프로그래밍 모델
    - 스프링이 직접 제공하는 모든 기술과 AP, 심지어 컨테이너도 IoC/DI 방식으로 작성되어 있다.
- 서비스 추상화
    - 종속성을 낮추고 이식성이 뛰어나다.
- AOP
    - 애플리케이션 코드에 산재해서 나타나는 부가적인 기능을 독립적으로 모듈화

기술api

- 방대한 양의 기술 API 를 제공한다.

---

1장 오브젝트와 의존관계

자바빈은

- 디폴트 생성자 : 가지고 있어야한다. 툴이나 프레임워크에서 리플렉션을 이용해 오브젝트를 생성하기 때문에 필요하다.
- 프로퍼티 : getter & setter 로 접근 조회

객체지향 설계 원칙 SOLID

- SRP / Single Responsibility : 단일 책임 원칙
- OCP / Open Closed Principle : 개방 폐쇄 원칙
- LSP / Liskov Substitution Principle : 리스코프 치환 원칙
- ISP / Interface Segregation Principle : 인터페이스 분리 원칙
- DIP / Dependency Inversion Principle : 의존관계 역전 원칙

계방 폐쇄 원칙

- 높은 응집도와 낮은 결합도
    - 응집도가 높다는건 클래스, 모듈이 하나의 책임 또는 관심사에만 집중되어 있다는 뜻.
    - 결함도는 느슨하게 연결된 형태를 유지하는 것이 바람직 하다.

전략 패턴

- 다양하게 자주 사용되는 페턴
- 전략 패턴은 개방 폐쇄의 원칙의 실현에도 가장 잘 들어 맞는 패턴
- 자신의 기능 맥락에서 필요에 따라 변경이 필요한 알고리즘을 인터페이스를 통해 통째로 외부로 분리시키고, 이를 구현한 구체적인 알고리즘 클래스를 필요에 따라 바꿔서 사용할 수 있게 하는 디자인패턴이다.
- 여기서 말하는 알고리즘이란 거창한 수학적 알고리즘을 말하는 게 아니고, 독립적인 책임으로 분리가 가능한 기능을 뜻한다.
- 전력패턴에서 클라이언트의 필요성.
    - 클라이언트는 컨텍스트가 사용할 전략을 컨텍스트의 생성자 등을 통해 제공해주는 게 일반적이다.

제어의 역전

- IoC는 디자인 패턴에서도 발견될 수 있는 프로그래밍 모델이다.
- 제어의 역전을에서는 프레임워크 또는 컨테이너와 같이 애플리케이션 컴포넌트의 생성과 관계설정, 사용, 생명주기 관리 등을 관장하는 존재가 필요하다.
- IoC를 애플리케이션 전반에 걸쳐 본격적으로 적용하려면 스프링과 같은 IoC 프레임워크의 도움을 받는 편이 훨씬 유리하다.

오브젝트 팩토리를 이용한 스프링 IoC

- 어플리케이션 컨텍스트와 설정정보
    - bean : 스프링에서 스프링이 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트
- 자바빈과 비슷한 오브젝트 단위의 컴포넌트
- 스프링 빈은 스프링 컨테이너가 생성과 관계설정, 사용 등을 제어해주는 제어의 역전이 적용된 오브젝트를 가리키는 말이다.
- 애플리케이션 컨텍스트는 Ioc방식을 따라 만들어진 일종의 빈 팩토리.

@Configuration

- Application 컨텍스트가 사용하는 설정 정보

스프링 IoC 용어정리

- 빈
    - 스프링이 IoC 방식으로 관리하는 오브젝트
    - 스프링이 직접 그 생성과 제어를 담당하는 오브젝트만 빈이라고 한다.
- 빈 팩토리
    - 스프링의 IoC를 담당하는 핵심 컨테이너
    - 빈의 등록, 생성, 조회, 반환, 관리 등을 담당.
    - 보통 어플리케이션은 빈팩토리를 확장한 애플리케이션 컨텍스트를 이용한다.
- 애플리케이션 컨텍스트.
    - 빈팩토리를 확장함. (빈의 등록 및 관리 기능)
    - 스프링의 부가 서비스를 추가로 제공
- 설정정보 / 설정 메타정보
    - 애플리케이션 컨텍스트 또는 빈 팩토리가 IoC를 적용하기 위해 사용하는 메타정보.
    - 대부분 IoC 컨테이너에 의해 관리되는 애플리케이션 오브젝트를 생성하고 구성할 때 사용된다.
    - 애플리케이션의 형상 정보라고도 부른다.
- 컨테이너 또는 IoC 컨테이너
    - IoC 방식으로 빈을 관리한다는 의미에서 애플리케이션 컨텍스트나 빈 팩토리를 컨테이너 또는 IoC 컨테이너라고도 한다.

자바의 동일성과 동등성

- 오브젝트가 같은가라는 말은 주의해서 써야한다.
- 자바에서는 두 개의 오브젝트가 완전히 같은 동일한(identical) 오브젝트라고 말하는 것과.
- 동일한 정보를 담고 있는 오브젝트라고 말하는 것은 분명한 차이가 있다.

싱글톤 레지스트리로서의 애플리케이션 컨텍스트

- 애플리케이션 컨텍스트는 IoC 컨테이너이자 동시에 싱글톤 레지스트리라고 한다.
- 자바의 기본적인 싱글톤 패턴의 방식의 여러가지단점
    - 싱글톤의 사용은 전역 상태를 만들 수 있기 때문에 바람직하지 못하다.
    - 서버 환경에서는 싱글톤이 하나만 만들어지는 것을 보장하지 못한다.
    - 싱글톤은 테스트하기가 힘들다.
    - private 생성자를 갖고 있기 때문에 상속할 수 없다.
- 싱글톤 레지스트리
    - 스프링이 직접 싱글톤 형태의 오브젝트를 만들고 관리하는 기능.
    - 스프링 컨테이너는 싱글톤 관리 컨테이너이기도 하다.
    - 싱글톤 레지스트리의 장점은 static 메소드와 private 생성자를 사용해야하는 비정상적인 클래스가 아니라 평범한 자바 클래스를 싱글톤으로 활용하게 해준다는 점이다.
        - 덕분에 싱글톤 방식으로 사용될 클래스라도 public 생성자를 가질 수 있다.
        - 테스트 환경에서 자유롭게 오브젝트를 만들 수 있고
        - 테스트를 위한 목 오브젝트로 대체하는 것도 간단하다.
        - UserDao(connectionMaker)과 같이 생성자 파라미터를 이용해서 사용할 오브젝트를 넣어주게 할 수도 있다.
    - 가장 중요한 것은 객체지향적인 설계 방식과 원직, 디자인패턴(싱글톤패턴 제외) 등을 적용하는 데 아무런 제약이 없다는 점.

싱글톤으로 만들어지기 때문에 주의해야 할 점

- 멀티스레드 환경이라면 여러 스레드가 동시에 접근해서 사용할 수 있다.
    - 그러므로 상태 관리에 주의를 기울여야 한다.
    - 기본적으로 싱글톤이 멀티스레드 환경에서 서비스 형태의 오브젝트로 사용되는 경우에는 상태정보를 내부에 갖고 있지 않은 무상태 방식으로 만들어져야 한다. 여러 스레드가 인스턴스 변수를 수정하지 않도록 말이다.
    - 상태가 없는 방식으로 요청에 대한 정보나, 데이터베이스나 서버의 리소스로부터 생성한 정보를 다루려면? 파라미터와 로컬변수, 리턴 값 등을 이용한다. 메소드 안에서 생성되는 로컬변수는 매번 새롭게 독립적인
      공간이 만들어지기 때문에 쓰레드가 변수의 값을 덮어쓸 일은 없다.

의존관계 주입 (DI)

- 제어의 역전과 의존관계 주입
- 제어의역전은 범위가 너무 넓어서 스프링이 제공하는 IoC를 DI, 의존관계 주입이라는 이름을 사용하기 시작했다.
- 다음 세가지 조건을 충족하는 작업
    - 클래스 모델이나 코드에는 런타임 시점의 의존관계가 드러나지 않는다. 그러기 위해서는 인터페이스에만 의존하고 있어야한다.
    - 런타임 시점의 의존관계는 컨테이너나 팩토리 같은 제3의 존재가 결정한다.
    - 의존관계는 사용할 오브젝트에 대한 레퍼런스를 외부에서 제공(주입)해줌으로써 만들어진다.

1장 정리

1장에서는 사용자 정보를 DB에 등록하거나 아이디로 조회하는 기능을 가진 간단한 DAO 코드를 만들고, 그 코드의 문제점을 살펴본 뒤 이를 다양한 패턴, 원칙, IoC/DI 프레임워크까지 적용해서 개선해왔다. 그
과정을 돌아보자

- 먼저 책임이 다른 코드를 분리해서 두 개의 클래스로 만들었다(관심사의 분리, 리팩토링).
- 그중에서 바뀔 수 있는 쪽의 클래스는 인터페이스를 고현하도록 하고, 다른 클래스에서 인터페이스를 통해서만 접근하도록 만들었다. 이렇게 해서 인터페이스를 정의한 쪽의 구현 방법이 달라져 클래스가 바뀌더라도, 그
  기능을 사용하는 클래스의 코드는 같이 수정할 필요가 없도록 만들었다.(전략패턴)
- 이를 통해 자신의 책임 자체가 변경되는 경우 외에는
    - 불필요한 변화가 발생하지 않도록 막아주고,
    - 자신이 사용하는 외부 오브젝트으 ㅣ기능은 자유롭게 확장하거나 변경할 수 있게 만들었다(개방폐쇄 원칙)
- 결국 한쪽의 기능 변화가 다른 쪽의 변경을 요구하지 않아도 되게 했고(낮은 결합도), 자신의 책임과 관심사에만 순수하게 집중하는(높은 응집도) 깔끔한 코드를 만들 수 있었다.
- 오브젝트가 생성되고 여타 오브젝트와 관계를 맺는 작업의 제어권을 별도의 오브젝트 팩토리를 만들어 넘겼다.
    - 또는 오브젝트 팩토리의 기능을 일반화한 IoC 컨테이너로 넘겨서
    - 오브젝트가 자신이 사용할 대상의 생성이나 선택에 관한 책임으로부터 자유롭게 만들어줬다.(제어의 역전/IoC).
- 전통적인 싱글톤 패턴 구현 방식의 단점을 살펴보고, 서버에서 사용되는 서비스 오브젝트로서의 장점을 살릴 수 있는 싱글톤을 사용하면서도
    - 싱글톤 패턴의 단점을 극복할 수 있도록 설계된 컨테이너를 활용하는 방법에 대해 알아봤다(싱글톤 레지스트리).
- 설계 시점과 코드에는 클래스와 인터페이스 사이의 느슨한 의존 관계만 만들어놓고, 
    - 런타임 시에 실제 사용할 구체적인 의존 오브젝트를 제3자(DI)의 도움으로 주입받아서 다이내믹한 의존관계를 갖게 해주는 IoC의 특별한 케이스를 알아봤다.
- 마지막으로, XML을 이용해 DI 설정정보를 만드는 방법과 의존 오브젝트가 아닌 일반 값으 ㄹ외부에서 설정해서 런타임 시에 주입하는 방법을 알아봤다(XML 설정).
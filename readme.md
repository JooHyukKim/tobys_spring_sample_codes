## 2장

> > > "항상 스프링 컨테이너 없이 테스트할 수 있는 방법을 가장 우선적으로 고려하자." - 토비
>
> 책 '토비의 스프링 vol.1 p196' 에서
> > 이 방법이 테스트 수행 속도가 가장 빠르고 테스트 자체가 간결하다. 테스트를 위해 필요한 오브젝트의 생성과 초기화가 단순하다면 이 방법을 가장 먼저 고려해야한다.

- 토비가 생각하는 스프링의 가장 큰 장점은 IoC/DI 와 테스트이다.
- 스프링의 창시자인 로드 존슨은 항상 네거티브 테스트를 먼저만들라 는 조언을 했다 .
- 그래서 항상 부정적인 테스트를 먼저 만들어야 테스트에서 더 많은 결과를 얻어낼 수 있을 것이다.
- 테스트 설정을 따로 만들었다고 하더라도 때로는 예외적인 의존관계를 강제로 구성해서 테스트해야 할 경우가 있다.
    - 이때는 컨텍스트에서 DI 받은 오브젝트에 다시 테스트 코드로 수동 DI 해서 테스트하는 방법을 사용하면 된다.
    - 테스트 코드나 클래스에 @DirtiesContext 애노테이션을 붙이는 것을 잊지 말자.


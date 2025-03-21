
# 어플리케이션 만드는 과정에서 코드를 바꿔야 했던 이유
- 고객이나 사용자가 다른것을 요구하거나 새로운 기능을 원할 때
- 회사에서 데이터베이스 종류를 바꾸고 데이터도 전과 다른 데서 구입하기로 했는데, 지금 사용하는 데이터 포멧과 완전히 다른 경우
- 소스 코드 취약점 발생으로 수정이 필요한 경우
- 의존 라이브러리 변경이 필요한 경우 (JDK1.7 -> 17)


# 객체지향 원칙
- 바뀌는 부분은 캡슐화 한다
- 상속보다는 구성을 활용
- 구현 보다는 인터페이스에 맞처서 프로그래밍
- 상호 작용하는 객체 사이에서는 가능하면 느슨한 결합 사용
- 클래스는 확장에는 열려 있어야 하지만 변경에는 닫혀 있어야 한다 (OCP)

# 디자인 원칙

1. 애플리케이션에서 달리지는 부분을 찾아내고, 달리 지지 않는 부분과 분리한다.
	- 캡슐화 
	- 요구 사항이 발생될 때 마다 달라지는 부분이 있다면 분리 해야 한다.
2. 구현보다는 인터페이스에 맞춰서 프로그래밍 한다.
	- 다형성
	- 인터페이스를 이용한 형태 지정
	- 상위 형식에 맞춰 프로그래밍 하여 다형성을 활용
3. 상속보다는 구성을 활용한다.
	- IS-A 관계에서 두 클래스를 합치는 행위를 구성을 이용한다로 볼 수 있다.
	- 구성을 활용하면 유연성을 향상 시킬 수 있다.
4. 상호 작용하는 객체 사이에는 가능하면 느슨한 결합을 사용해야 한다.
	- 객체 사이의 상호 의존성을 최소화할 수 있다.
5. OCP (Open-Closed Principle)  클래스는 확장에는 열려있어야 하지만, 변경에는 닫혀 있어야 한다. 
	- 기존 코드를 건드리지 않고 확장으로 새로운 행동을 추가
	- 주변 환경에 잘 적응하는 유연하고 튼튼한 디자인
	- 무조건 적인 OCP 적용은 시간낭비가 될 수 있으며, 필요이상 복잡하고 이해하기 힘든 코드를 만들게 되는 부작용이 발생

# 캡슐화된 행동 살펴보기
- 클래스 다이어그램을 그리게 되면 각 다이어그램의 화살표와 클래스들이 어떤 관계인지 확인
	- A는 B이다 (is-a : 상속, extends)
	- A에는 B가 있다 (has-a : 포함 , 객체 생성) 
		- A 클래스에서 B클래스를 선언하여 객체를 가지는 경우
	- A가 B를 구현한다 (inplements : 구현 , implements)


## 개발 하는 시간 vs 유지 보수 하는 시간 어떤게 더 길까?
=> 유지 보수에 들어가는 시간이 훨씬 많다.
=> 그래서 코드 재 사용에 노력을 기울여야 한다.




# 🌈 전략 패턴
알고리즘군을 정의하고 캡슐화해서 각각의 알고리즘군을 수정해서 쓸 수 있게 한다.
전략 패턴을 사용하면 클라이언트로부터 알고리즘을 분리해서 독립적으로 변경할 수 있다.



## 패턴과 전문 용어
패턴으로 소통하면 일상어로 구구절절 말할 때보다 훨씬 효율적인 의사 소통을 할 수 있다.

### 서로 알고 있는 패턴은 막강하다
- 다른 소속의 개발자와 소통할 떄 패턴으로 대화하면 모든 내용, 특성, 제약조건등을 함께 이야기 할 수 있다.
### 패턴을 사용하면 간단한 단어로 많은 얘기를 할 수 있다
- 내가 생각중인 설계에 대해 다른 개발자가 빠르고 정확하게 파악할 수 있다

### 패턴 수준에서 이야기하면 디자인에 더 오랫동안 집중 할 수 있다
- 객체와 클래스를 구현하는 것과 관련된 부수적인 내용에 시간을 버릴 필요가 없어서 디자인 수준에서 초점을 맞출 수 있다.
- 회의 본질이 아닌 부수 내용으로 주제가 새는 경우가 종종 있다.

### 전문 용어를 사용하면 개발팀의 능력을 극대화 할 수 있다.
- 모든 팀원이 잘알 고 있다면 오해의 소지가 줄어 작업을 빠르게 진행할 수 있다.



# 🌈 Observer 패턴
 > 객체들에게 연락 돌리기
 > 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체에게 연락이 가고 자동으로 내용이 갱신되는 방식으로 일대다 의존성을 정의

## Push vs Pull
### push : subject -> observer
- 변경 사항 생기면 모든 Observer들에게 알림
- 새로운 데이터 값이 추가되는 경우 대부분의 Observer에서 수정이 필요
### pull : subject <- observer
- 필요한 데이터를 당겨 오는 방식

## 정리
- Observer 패턴은 객체들 사이에 일대다 관계를 정의 
- 주제는 동일한 인터페이스를 써서 Observer에게 연락
- Observer 인터페이스를 구현하기만 하면 어떤 구상 클래스의 Observer라도 패턴에 참여할 수 있음
- 주제는 Observer들이 인터페이스를 구현한다는 것을 제외하면 Observer에 관해 전혀 모름.
- 따라서 이들 사이의 결합은 느슨한 결함(loose coupling)
- Observer 패턴을 사용하면 주제가 데이터를 보내거나(푸시) Observer가 데이터를 가져올(풀)수 있다.
- 일반적으로 풀 방식이 더 권장 된다
- Observer 패턴은 여러 개의 주제와 메시지 유형이 있는 복잡한 상황에서 사용하는 출판-구독 패턴과 친척
- Observer 패턴은 자주 쓰이는 패턴으로 모델-뷰-컨트롤러(MVC)에서 다시 확인 하자!

## 생각 (각 원칙에 알맞은 답변)
### 디자인 원칙 
> 애플리케이션에서 달리지는 부분을 찾아내고, 달리지지 않는 부분과 분리

```
옵저버 패턴에서 변하는것은 주제의 상태와 옵저버의 개수, 형식
옵저버 패턴에서는 주제를 바꾸지 않고도 주제의 상태에 의존하는 객체들을 변경 가능
나중에 바뀔것을 대비해 두면 편하게 작업가능
```

### 디자인 원칙
> 구현 보다는 인터페이스에 맞춰서 프로그래밍
```
주제와 옵저버에서 모두 인터페이스를 사용
주제는 Subject Interface로 Observer Interface를 구현하는 객체들의 등록과 탈퇴를 관리하고, 
그런 객체들에게 연락을 발송
느슨한 결합!!
```


### 디자인 원칙
> 상속보다는 구성을 활용
```
옵저버 패턴에서는 구성을 활용해서 옵저버들을 관리
주제와 옵저버 사이는 상속이 아닌 구성으로 이루어져있음.
게다가 실행 중에 구성되는 방식을 사용함.
```


# 🌈 Decorator 패턴
 > 객체에 추가 요소를 동적으로 더할 수 있다
 > 데코레이터를 사용하면 서브클래스를 만들 때 보다 훨씬 유연하게 기능을 확장할 수 있다
 > 원본 객체의 기능을 확장하면서도 동일한 인터페이스를 유지
 > implement or extends를 통해 인터페이스 규약을 따르고 컴포지션(has-a)을 통해 실제 기능을 위임하는 구조

 
 
## 주요 키워드
- 추상 클래스
- 슈퍼 클래스
	- 객체 지향 프로그래밍에서 상속관계에서 상위에 있는 클래스를 의미
	- 상속의 기본
		- 서브 클래스(자식클래스)는 슈퍼 클래스(부모클래스)의 특성을 물려 받음
		- extends 키워드를 사용. 상속 관계 (has-a)
		- 슈퍼 클래스의 public과 protected 멤버는 서브 클래스에서 접근 가능
		- private 멤버는 서브 클래스에서 직접 접근 불가
		- super 키워드를 통해 슈퍼 클래스의 메서드나 생성자 호출 가능


# 🌈 팩토리 패턴
> 객체를 생성할 때  필요한 인터페이스를 생성.
> 어떤 클래스의 인스턴스를 만들지는 서브클래스에서 결정
> 팩토리 메소드 패턴을 사용하면 클래스 인스턴스 만드는 일을 서브클래스에게 위임

## Intro
new 라는 연산자가 눈에 띈다면 '구상'이라는 용어를 떠올려라
구상 클래스를 바탕으로 코딩하면 나중에 코드를 수정 해야 할 가능성이 커지고 유연성이 떨어진다

예시)
```
Duck duck;

if(picnic){
	duck = new MallardDuck();
}else if(hunting){
	duck = new DecoyDuck();
}else if(inBathTub){
	duck = new RubberDuck();
}
```
구상 클래스의 인스턴스가 여러개 있으며, 인스턴스의 형식은 실행시에 주어진 조건에 따라 결정
변경 또는 확장이 필요한 경우 코드를 일일이 확인하고 수정이 필요함
[다형성 고려 No , 변경에 닫힘]


## 객체 생성 부분 캡슐화
> 갱체 생성을 처리하는 클래스를 팩토리(Factory)라고 한다


## 간단한 팩토리
> 간단한 팩토리(Simple factory)는 디자인 패턴이라기 보다는 프로그래밍에서 자주 쓰이는 관용구에 가깝다. 엄밀히 따지만 팩토리 패턴과 구분된다.


## 팩토리 메서드 패턴
> 사용하는 서브클래스에 따라 생산되는 객체 인스턴스가 결정
> 여러번 재사용 가능한 프레임워크를 만들 수 있음.

## 장점
- 객체 생성 코드와 사용 코드 분리
	- 클라이언트는 객체 생성 방식을 몰라도 됩니다.
- **확장성 강화**
	- 새로운 피자 종류 추가 시 기존 코드를 변경할 필요 없이 새로운 클래스만 추가하면 됩니다.
- **결합도 감소**
	- 클라이언트 코드가 구체 클래스에 의존하지 않아 유지보수가 용이합니다.

## 단점
- 클래스 수가 늘어나 코드 관리가 복잡해질 수 있습니다.
- 단순한 프로그램에는 과도한 설계일 수 있습니다.






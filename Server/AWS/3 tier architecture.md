
# aws vpc

![[Pasted image 20230418142438.png]]

![[Pasted image 20230418142549.png]]


VPC 생성하기 누르면
![[Pasted image 20230418142706.png]]

생성완료 후 `VPC 보기` 후 좌측 메뉴에서 서브넷 클릭


![[Pasted image 20230418142916.png]]



6개의 서브넷이 생성되어야 합니다.

원하는 서브넷을 선택하고 **Actions** -> **Edit Subnet Settings 를** 누르십시오 .

![[Pasted image 20230418143355.png]]


2개의 public 서브넷에서 아래와 같이 체크 후 저장

![[Pasted image 20230418143540.png]]




EC2 대시보드로 이동

![[Pasted image 20230418143758.png]]

![[Pasted image 20230418143816.png]]


![[Pasted image 20230418144324.png]]


EC2 대시보드에서 `시작 탬플릿` 메뉴 클릭
![[Pasted image 20230418144448.png]]

* ** 시작 템플릿  : 인스턴스를 시작 할 때, 필요한 구성요소를 모아놓은 템플릿
* 여러가지 버전을 만들어서 구성 해 놓을 수도 있고, 인스턴스의 설정을 바꿔서 올릴 수 도 있다.
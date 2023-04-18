
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

![[Pasted image 20230418145950.png]]

![[Pasted image 20230418150009.png]]
![[Pasted image 20230418150024.png]]
![[Pasted image 20230418150041.png]]


![[Pasted image 20230418150055.png]]



![[Pasted image 20230418150253.png]]
















고급세부정보 > 사용자 데이터 _- optional_ 란에 아래의 내용 기입
```
#!/bin/bash 
sudo yum update -y 
sudo yum install -y httpd 
sudo systemctl start httpd 
sudo systemct enable httpd 
echo echo "<html><body><h1>이 섹션에 도달했습니다. 계속 진행하세요.</h1> </body></html>" > /var/www/html/index.html
```
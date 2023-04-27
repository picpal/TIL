> Virtual Private Cloud 


![[Pasted image 20230420104211.png]]


# 기능
## Virtual Private Cloud(VPC)
- 자체 데이터 센터에서 운영하는 기존 네트워크
- VPC를 생성한 후 서브넷 추가할 수 있음

## 서브넷 
- [서브넷](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/configure-subnets.html)은 VPC의 IP 주소 범위
- Amazon EC2 인스턴스와 같은 AWS 리소스를 서브넷으로 실행할 수 있음
- 서브넷은 단일 가용 영역에 상주
- 서브넷을 추가한 후에는 VPC에 AWS 리소스 배포할 수 있음
- 서브넷을 인터넷, 다른 VPC 및 자체 데이터 센터에 연결하고 라우팅 테이블을 사용하여 서브넷으로/서브넷에서 트래픽을 라우팅

## IP 주소 지정
- VPC와 서브넷에 [IP 주소](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/vpc-ip-addressing.html)를 IPv4와 IPv6 모두 할당가능
- 퍼블릭 IPv4 및 IPv6 GUA 주소를 AWS로 가져오고 VPC의 리소스(예: EC2 인스턴스, NAT 게이트웨이, Network Load Balancer)에 할당 가능

## 라우팅
- [라우팅 테이블](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/VPC_Route_Tables.html)을 사용하여 서브넷 또는 게이트웨이의 네트워크 트래픽이 전달되는 위치를 결정

## 게이트웨이 및 엔드포인트
- [게이트웨이](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/extend-intro.html)는 VPC를 다른 네트워크에 연결합니다
- 예를 들면, [인터넷 게이트웨이](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/VPC_Internet_Gateway.html)를 사용하여 VPC를 인터넷에 연결합니다.
- [VPC 엔드포인트](https://docs.aws.amazon.com/vpc/latest/privatelink/privatelink-access-aws-services.html)를 사용하여 인터넷 게이트웨이 또는 NAT 장치를 사용하지 않고 AWS 서비스에 비공개로 연결합니다.

## 피어링 연결
- [VPC 피어링 연결](https://docs.aws.amazon.com/vpc/latest/peering/)을 사용하여 두 VPC의 리소스 간 트래픽을 라우팅

## 트래픽 미러링
- 네트워크 인터페이스에서 [네트워크 트래픽을 복사](https://docs.aws.amazon.com/vpc/latest/mirroring/)하고 심층 패킷 검사를 위해 보안 및 모니터링 어플라이언스로 전송

## Transit Gateway
- 중앙 허브 역할을 하는 [전송 게이트웨이](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/extend-tgw.html)를 사용하여 VPC, VPN 연결 및 AWS Direct Connect 연결 간에 트래픽을 라우팅합니다.

## VPC 흐름 로그
- [흐름 로그](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/flow-logs.html)는 VPC의 네트워크 인터페이스로 들어오고 나가는 IP 트래픽에 대한 정보를 캡처합니다.

## VPN 연결
- [AWS Virtual Private Network(AWS VPN)](https://docs.aws.amazon.com/ko_kr/vpc/latest/userguide/vpn-connections.html)을 사용하여 온프레미스 네트워크에 VPC를 연결합니다.



# VPC 작동방식

Amazon Virtual Private Cloud(Amazon VPC)를 이용하면 사용자가 정의한 가상 네트워크로 AWS 리소스를 시작할 수 있습니다. 이 가상 네트워크는 AWS의 확장 가능한 인프라를 사용한다는 이점과 함께 고객의 자체 데이터 센터에서 운영하는 기존 네트워크와 매우 유사합니다.
Amazon Virtual Private Cloud(Amazon VPC)를 이용하면 사용자가 정의한 가상 네트워크로 AWS 리소스를 시작할 수 있습니다. 이 가상 네트워크는 AWS의 확장 가능한 인프라를 사용한다는 이점과 함께 고객의 자체 데이터 센터에서 운영하는 기존 네트워크와 매우 유사합니다.
Amazon Virtual Private Cloud(Amazon VPC)를 이용하면 사용자가 정의한 가상 네트워크로 AWS 리소스를 시작할 수 있습니다. 이 가상 네트워크는 AWS의 확장 가능한 인프라를 사용한다는 이점과 함께 고객의 자체 데이터 센터에서 운영하는 기존 네트워크와 매우 유사합니다.


![[Pasted image 20230420105557.png]]
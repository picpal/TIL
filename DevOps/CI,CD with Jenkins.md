
# 사용 환경
- gitlab
- jenkins
- spring boot 

# Gitlab
- 사용자 생성
- repository 생성
- Access Token 생성

# Jenkins 
- 서버 정보(배포대상) 생성
- Gitlab 연결을 위한 Credentials 설정
	- Dashboard > Common > Credentials > Folder > Global credentials (unrestricted)
	- Add Credentials
	- username & password 
	- AT(access token) 방식은 Expiredate가 존재함
- 
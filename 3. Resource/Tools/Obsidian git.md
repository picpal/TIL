#git #obsidian

# 사용방법
1. git 설치
2. github 에서 repository 생성
3. 생성된 repository url copy
4. window
	1. 사용할 폴더 생성
	2. 터미널에서 폴더이동
	3. 이동한 폴더에서 git 명령어 실행 . 폴더에 .git 파일 생성되었는지 확인
		1. git 기본 정보 설정 ( 공백, 계정, 메일, default branch )
		```
		git config --global core.autocrlf true 
		git config --global user.name "계정"
		git config --global user.email "이메일"
		git config --global init.defaultBranch main
		```

		2.  github 연결
	```
		git init
		git remote add origin [repository url]
		git pull origin main
	```
	4. obsidian 실행
	5. 설정 > 커뮤니티 플러그인 > 탐색 > obsidian git 설치
	6. 커뮤니티 플러그인 > obsidian git 에서 옵션 설정
		- 옵션은 다른건 default 로 지정하고 필요한 경우 변경
		- 기본 backup 시간은 10분이며, 변경하고 싶은 경우 Authmatic > valut backup interval.. 항목의 숫자를 조절. 단위 : 분
		- pull  설정은 좀더 아래에 있음.
	7. 설정 후 초기 한번은 obsidian에서 branch를 선택하는 창이 나타남.
	8. 문서 내용을 편집한 뒤 github에서 변경사항이 commit이 진행되는지 확인.
	9. git 명령어에서 remote 서버를 origin 으로 설정 후 main branch를 사용하기 때문에 별도의 branch가 생성되지 않고 commit이 진행됨.
	
		
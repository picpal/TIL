#git #obsidian

# 사용방법
1. git 설치
2. github 에서 repository 생성
3. 생성된 repository url copy
4. window
	1. 사용할 폴더 생성
	2. 터미널에서 폴더이동
	3. 이동한 폴더에서 git 명령어 실행 . 폴더에 .git 파일 생성되었는지 확인
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
	9. git 명령ㅇ
		
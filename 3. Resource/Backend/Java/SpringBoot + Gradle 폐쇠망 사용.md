#폐쇠망 #offline

https://dev-secret-note.tistory.com/12

**1. Gradle을 다운 받습니다.** 

[https://gradle.org/releases/](https://gradle.org/releases/)


**2. IntelliJ에서 스프링부트 설정을 진행 합니다.**

설명을 드리기 위해서 아래에 가볍게 생성을 해줍니다. 

New Project 버튼을 클릭해 줍니다.

![](https://blog.kakaocdn.net/dn/b4iYoc/btrcb8eVMkY/vWbhUuFfr3qktp0ThhU0KK/img.png)

프로젝트를 새롭게 생성해 줍니다.

다음으로 Spring Initializr 을 선택해줍니다. 저는 spring boot 로 개발을 하기 위해서죠!

다른 설정으로 프로젝트를 진행하시는 분들은 다르게 설정하셔도 무관합니다.

![](https://blog.kakaocdn.net/dn/c7SFf2/btrb83kSHIq/IxS8OEoUArjWAsql6SBwwk/img.png)

Next를 클릭해주면 아래와 같이 기본 Developer Tools을 설정할수 있습니다.

![](https://blog.kakaocdn.net/dn/PM5Bd/btrb65XLBdH/houSjVE9S3wg8831Tfsmy0/img.png)

Finish 를 클릭해주시면 아래와 같이 셋팅이 됩니다.

![](https://blog.kakaocdn.net/dn/bxdICE/btrb81Oarjk/4Y4zqskZyqjB4RriYnm9Dk/img.png)

**3. build.gradle 에 사용할 라이브러리를 등록합니다.**

![](https://blog.kakaocdn.net/dn/bwYnfp/btrb7vhz3BZ/VylfEocUKqVmvWrtPzMtYk/img.png)

개발할때 필요한 library 들을 등록해줍니다.

**4. IntelliJ IDEA> Preferences... 를 선택해줍니다.** 

![](https://blog.kakaocdn.net/dn/bUpzye/btrb8gEXfcI/u7KSVG9qpmC3mlTn5V3c3K/img.png)

**5. Build, Execution, Deployment > Build Tools > Gradle 을 선택해 설정을 변경해 줍니다.** 

![](https://blog.kakaocdn.net/dn/WaIVy/btrb7yeMPHI/1TXB4ccfPbHGErJNvJKl0K/img.png)

**# General settings > Gradle user home :** 

(ex : /Users/Documents/IDE/offline)

![](https://blog.kakaocdn.net/dn/9nifx/btrb65XTaLR/xroPgHLS6TqUqma0pFOHIK/img.png)

*dependencies에 기록한 정보들이 home 위치에 다운로드 되니 위치를 명확히 해주셔야합니다.

**# Gradle projects > Gradle**

> Use Gradle from : Specified location 으로 변경해줍니다.

1번에서 다운 받은 Gradle 경로로 지정해줍니다.

![](https://blog.kakaocdn.net/dn/TrXsc/btrb9RknBFw/5kKvd4XlJEYYEYH1sxaa20/img.png)

그럼 home 위치에 아래와 같은 파일들이 생성 되는데

이 부분과 Gradle을 함께 압축하여 오프라인 환경에 같은 방식으로 셋팅해줍니다.

![](https://blog.kakaocdn.net/dn/GrRt1/btrcechhj55/ykvsdAkVVrKm81Ypu0OOzK/img.png)

생성된 dependencies 파일들

**6. 오프라인에서 설정하여 build가** **모두 끝나면 실행해 봅니다.** 

![](https://blog.kakaocdn.net/dn/kKAV2/btrb83MaoRQ/h5iNfSof495qO0yFLKuogK/img.png)

실행이 아주 잘되는군요.

여러분도 off-line 환경에서 프로젝트를 진행해야 한다면 

이 방법을 사용하는것을 추천 합니다.
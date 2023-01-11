# base64

## base64란?

- 8비트의 이진 데이터를 문자코드에 영향을 받지 않는 공통 ASCII 문자 들로만 이루어진 일련의 문자열로 바꾸는 인코딩 방식
- 데이터를 base64로 변환하는 것을 인코딩한다, 그 반대를 디코딩 한다고 표현한다.
- 6bit씩 잘라서 표현한다.
- 데이터를 64진법으로 표현하는 방식
- 웹에서 주고받을 때 인코딩 디코딩 주로 함

### encoding

1. ASCII 문자 a = 97 encoding
2. 2진수 변환 = 0110 0001 후 6개비트로 해야 하기 때문에 0으로 자릿수보정 011000 010000
3. 자릿수 보정한 것 -> base64는 6bit 묶음을 4세트로 모아 변환하는데 빈 공간은 ‘=’으로 대체
4. 011000은 24, 010000은 16 = 각각 Y, Q로 변환되고 뒤는 빈공간이기 때문에 ==으로 채워져 YQ== 이라는 값이 나옴

### decoding

1. YQ== 인데 다시 6비트씩의 이진수로 변환한다.
2. Y = 24, Q = 16 -> 0110 0001 -> 011000010000 디코딩 할 때는 8bit씩 묶는다.
3. 그럼 01100001 이 묶이고 채워준 0000은 없어진다.
4. 변환된 2진수를 10진수로 변환 -> 97
5. 97을 ASCII 표에서 찾으면 a가 그대로 나오게 된다.

### base64 example code

> java8 version 이상, java.util.Base64 사용

```java
  String sampleText = "dh in ds";
  System.out.println("인코딩할 문자열 : " + sampleText);
  //encoding
  String encodingText = Base64.getEncoder().encodeToString(sampleText.getBytes());
  System.out.println("base64로 인코딩된 문자열: " + encodingText);
  // ZGggaW4gZHM=

  //decoding
  //String new 할때, 바이트 배열이 문자열로 변환됨
  String decodingText = new String(Base64.getDecoder().decode(encodingText));
  System.out.println("base64로 디코딩된 문자열: " + decodingText);
```

# base64URL safe

## base64URL safe란?

- Base64를 사용 시, 마지막 ascii code 62,63번 글자가 +,/ 인 것과 pad(=) 가 url에서 사용.
  - +는 띄어쓰기 의미, /는 경로구분자, =는 name과 value사이에 쓰는 기호
- 이 문자들을 전송하게 되면 정상적으로 전송되지 않는 문제가 발생
- 오류를 막기위해 개선된 encoding 방식이 Url safe이다.
  - (62,63 변경) Pad(=)는 그대로 가져가지만, 브라우저에서 인식하지 않게끔 한다.

### base64URL safe example code

```java
  String sampleUrl = "https://www.google.com/search?q=Base64+encode";

  String encodingUrl = Base64.getUrlEncoder().encodeToString(sampleUrl.getBytes());
  System.out.println(encodingUrl); // aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS9zZWFyY2g_cT1CYXNlNjQrZW5jb2Rl

  String decodingUrl = new String(Base64.getUrlDecoder().decode(encodingUrl));
  System.out.println(decodingUrl); // "https://www.google.com/search?q=Base64+encode";
```

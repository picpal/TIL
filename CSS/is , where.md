# 브라우저 호환성

:is(), :where() 기능적인 가상 클래스는 현재 크롬(>=88), 파이어폭스(>=78), 사파리(>=14)에서 지원된다. 자세한 정보는 MDN의 브라우저 호환성 표를 참고하자. 이전 버전의 일부 브라우저에서는 :is() 선택자를 :matches()나 -webkit-any()로 지원한다. 자세한 내용은 MDN의 :is() 페이지를 참고해라.

<br />

# :is()와 :where() 살펴 보기

- 기능적인 의사 클래스 선택자이다.
- :로 시작하여 ()로 끝난다는 점에 주목하자.
- 해당하는 요소를 찾는 함수를 런타임에 동적으로 호출했다고 생각해라.
- CSS를 작성할 때 선택자의 중간, 시작 또는 끝에서 요소를 그룹화할 수 있다.
- 또한 이들은 CSS 명시도를 변경할 수 있어서 명시도를 무효화하거나 증가시킬 수도 있다.

<br />

# 선택자 그룹화

- :is()가 할 수 있는 그룹화라면 :where()도 할 수 있다.
- 그룹화는 선택자 어디에서나 사용할 수 있고, 중첩 및 결합도 할 수 있다.
- :is()나 :where()에서 이점을 얻을 수 있는 코드를 찾으려면 쉼표가 여러 개 있고 선택자가 반복되는 곳을 찾아라.

```css
/* 선택자 처음 */
:where(h1, h2, h3, h4, h5, h6) > b {
  color: hotpink;
}

/* 선택자 중간 */
article :is(header, footer) > p {
  color: gray;
}

/* 선택자 끝 */
.dark-theme :where(button, a) {
  color: rebeccapurple;
}

/* 여러 가지 사용 */
:is(.dark-theme, .dim-theme) :where(button, a) {
  color: rebeccapurple;
}

/* 결합 */
:is(h1, h2):where(.hero, .subtitle) {
  text-transform: uppercase;
}

/* 중첩 */
.hero:is(h1, h2, :is(.header, .boldest)) {
  font-weight: 900;
}
```

<br />

# 단순하고 복잡한 선택자를 :is()와 함께 사용하기

- 일반적으로 ,를 이용하여 선택자 목록을 만들 때, 선택자 중 하나라도 유효하지 않다면 모든 선택자가 무효화되고 요소를 찾는데 실패한다.
- :is()와 :where()은 실수를 용납하고 선택자 목록 전체가 무효화되는 것을 방지할 수 있다.

```css
article > :is(p, blockquote) {
  color: black;
}

:is(.dark-theme.hero > h1) {
  font-weight: bold;
}

article:is(.dark-theme:not(main .hero)) {
  font-size: 2rem;
}
```

<br />

# :is()와 :where()의 차이

- 명시도에 관한 한 :is()와 :where()은 극과 극을 달린다.
- :where()은 명시도가 없다.
- :where()은 매개변수로 전달된 선택자 목록의 모든 명시도를 무시한다.
- 이러한 기능은 다른 선택자에선 볼 수 없다.
- :is()는 가장 구체적인 선택자의 명시도를 따라간다.
- :is(a,div,#id)의 명시도는 ID의 명시도인 100점이다.
- 필자가 그룹화를 남발했을 때 선택자 목록에서 가장 높은 명시도를 따라가는 특징이 문제가 되었다.
- 높은 명시도를 가진 선택자를 따로 분리하여 큰 영향을 미치지 않게 하면서 가독성을 높일 수 있었다.

```css
article > :is(header, #nav) {
  background: white;
}

/* 아래가 더 낫다 */
article > header,
article > #nav {
  background: white;
}
```

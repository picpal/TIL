# install

> 설치 및 기본 config 파일 생성

```bash
npm install -D tailwindcss
npx tailwindcss init
```

# tailwind.config.js

> 위 명령어 실행 후 생성된 파일에 해당 내용 기입

```js
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {},
  },
  plugins: [],
};
```

# base css

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

# auto build

>

```bash
npx tailwindcss -i [변환할파일및경로] -o [변환되는파일및경로] -w

```

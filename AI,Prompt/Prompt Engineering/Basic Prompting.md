# Basic Prompts

간단한 프롬프트로도 많은 것을 얻을 수 있지만, 결과의 품질은 얼마나 많은 정보를 제공하고 얼마나 잘 만들었는지에 따라 달라집니다. 프롬프트에는 모델에 전달하는 지시 사항이나 질문과 같은 정보와 컨텍스트, 입력 또는 예제와 같은 기타 세부 정보가 포함될 수 있습니다. 이러한 요소를 사용하여 모델에 더 잘 지시하고 결과적으로 더 나은 결과를 얻을 수 있습니다.

간단한 프롬프트의 기본 예제를 살펴보면서 시작해 보겠습니다:
```prompt
The sky is
```

```answer
The sky is the region of the Earth's atmosphere that appears above the horizon, typically characterized by its blue color during the day and black color at night, and includes clouds, the sun, the moon, and stars.
```


이 기본 예시는 또한 구체적으로 달성하고자 하는 목표에 대해 더 많은 맥락이나 지침을 제공해야 할 필요성을 강조합니다.

조금 개선해 보겠습니다:

```prompt
Complete the sentence: 
The sky is
```

```answer
The sky is a beautiful and ever-changing canvas that never fails to amaze us with its stunning colors, shapes, and patterns.
```


이제 좀 나아졌나요? 

모델에 문장을 완성하라고 지시했더니 모델에 지시한 대로("Complete the sentence:") 결과가 훨씬 더 좋아 보입니다. 모델에 작업을 수행하도록 지시하는 최적의 프롬프트를 설계하는 이러한 접근 방식을 **프롬프트 엔지니어링**이라고 합니다.

위의 예는 오늘날 LLM으로 가능한 작업을 보여주는 기본적인 예시입니다. 오늘날의 LLM은 텍스트 요약부터 수학적 추론, 코드 생성에 이르기까지 모든 종류의 고급 작업을 수행할 수 있습니다.

# 프롬프트 서식 지정

위에서 매우 간단한 프롬프트를 시도해 보았습니다. 표준 프롬프트의 형식은 다음과 같습니다:

```
<Question>?
```

```
<Instruction>
```


이는 다음과 같이 많은 QA 데이터 세트에서 표준으로 사용되는 질문 답변(QA) 형식으로 형식화할 수 있습니다:

```
Q: <Question>?
A:
```

위와 같이 프롬프트할 때 제로 샷(Zero-shot) 프롬프트라고도 하는데, 이는 모델이 수행하기를 원하는 작업에 대한 예제나 데모 없이 모델에 직접 응답을 요구하는 것입니다. 일부 대규모 언어 모델에는 제로 샷 프롬프트를 수행할 수 있는 기능이 있지만, 이는 당면한 작업의 복잡성과 지식에 따라 달라집니다.

위의 표준 형식을 고려할 때 프롬프트에 대한 인기 있고 효과적인 기법 중 하나는 예시(예: 데모)를 제공하는 몇 개의 샷(few-shot) 프롬프트라고 합니다. 소수 샷 프롬프트는 다음과 같이 포맷할 수 있습니다:
```
<Question>?
<Answer>
<Question>?
<Answer>
<Question>?
<Answer>
<Question>?
```

QA 형식 버전은 다음과 같습니다:

```
Q: <Question>?
A: <Answer>
Q: <Question>?
A: <Answer>
Q: <Question>?
A: <Answer>
Q: <Question>?
A:
```


반드시 QA 형식을 사용해야 하는 것은 아닙니다. 프롬프트 형식은 당면한 작업에 따라 다릅니다. 예를 들어 간단한 분류 작업을 수행하고 다음과 같이 작업을 보여주는 예시를 제공할 수 있습니다:

```
This is awesome! // Positive
This is bad! // Negative
Wow that movie was rad! // Positive
What a horrible show! //
```

```output
Negative
```


몇 번의 데모를 통해 언어 모델이 작업을 학습하는 능력인 상황에 맞는 학습이 가능합니다.
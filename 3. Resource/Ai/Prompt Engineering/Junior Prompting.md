# Generate Prompting
- 참고 지식 생성
- 스스로 추가 지식 생성
- 답변의 정확성을 올리는 가장 단순한 방식

GPT3.5 버전 기준 

Prompt Text
```
첫번째 질문 : 세계기억력대회의 역사를 알려줘. 

두번째 질문 : 세계 기억력 대회를 누가 만들었지?
```

첫번째 질문을 함으로써 사전 지식을 생성하게 됨.
언어모델이 스스로 세계기억력 대회 역사에 대한 정보를 생성.


# COT : Chain-of-Thought 
- 복잡한 추론 작업을 수행하기 위해 중간 추론 과정을 순차적으로 연결
- 중간 추론 단계의 연속성과 유창한 표현이 중요
- 최종 결과를 유도하기 위한 과정을 포함
- 현재 ChatGPT에는 적용이 되어 있는 상태이다.
- 튜닝이나, AI개발 진행 시 필수 이론

```
1안 단계적으로 추론과정을 거치는 Chain-of-Thought 방식대로 문제를 해결하세요

2안 예시를 첨부 (비슷한 문제 제공)
Q : 문제
A : 추론과정을 상세히 나열

```

# Self-Consistency
- 복잡한 추론 작업에서 여러 추론 경로를 통해 얻은 다양한 답변 중에서 가장 일관된 답변을 선택하여 모델의 추론 능력을 향상 시키는 방법
- 다양한 COT를 퓨샷러닝처럼 예시로써 제시하여, 언어모델로 하여금 다양한 추론 경로를 사용하게 함
- 복잡한 추론 작업에서 모델의 추론 능력을 향상 시킬 수 있으며, 여러 추론을 통해 얻은 다양한 답변 중 가장 일관된 답변을 선택하여 모델의 성능을 향상시킬수 있다. 
```



```


# Automatic Reasoning and Tool-use(ART)
- 새로운 작업을 수행하기 위해 스스로 중간 추론 단계를 생성하고 외부 도구를 사용하는 방법
- 
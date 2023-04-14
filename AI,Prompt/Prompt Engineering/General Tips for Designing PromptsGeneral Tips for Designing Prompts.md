다음은 프롬프트를 디자인할 때 염두에 두어야 할 몇 가지 팁입니다:


# Start Simple

프롬프트 디자인을 시작할 때 최적의 결과를 얻기 위해 많은 실험이 필요한 반복적인 과정이라는 점을 명심해야 합니다. OpenAI나 Cohere's와 같은 간단한 플레이그라운드를 사용하는 것이 좋은 출발점입니다.

간단한 프롬프트로 시작하여 더 나은 결과를 목표로 더 많은 요소와 컨텍스트를 계속 추가할 수 있습니다. 이러한 이유로 프롬프트의 버전 관리가 매우 중요합니다. 가이드를 읽다 보면 구체성, 단순성, 간결성을 통해 더 나은 결과를 얻을 수 있는 많은 사례를 볼 수 있습니다.

여러 가지 하위 작업이 포함된 큰 작업이 있는 경우, 작업을 더 간단한 하위 작업으로 세분화하고 더 나은 결과를 얻을 때까지 계속 쌓아갈 수 있습니다. 이렇게 하면 처음에 프롬프트 디자인 프로세스에 너무 많은 복잡성이 추가되는 것을 방지할 수 있습니다.


# The Instruction

'쓰기', '분류', '요약', '번역', '정렬' 등과 같이 모델에 원하는 작업을 지시하는 명령을 사용하여 다양한 간단한 작업에 대한 효과적인 프롬프트를 디자인할 수 있습니다.

또한 무엇이 가장 효과적인지 확인하려면 많은 실험을 해봐야 한다는 점을 명심하세요. 다양한 키워드, 컨텍스트 및 데이터로 다양한 안내를 시도해 보고 특정 사용 사례와 작업에 가장 적합한 것이 무엇인지 확인하세요. 일반적으로 컨텍스트가 수행하려는 작업과 더 구체적이고 관련성이 높을수록 더 좋습니다. 다음 가이드에서 샘플링과 더 많은 컨텍스트 추가의 중요성에 대해 다루겠습니다.

지침은 프롬프트의 시작 부분에 배치하는 것이 좋다는 의견도 있습니다. 
또한 "###"과 같은 명확한 구분 기호를 사용하여 지시 사항과 문맥을 구분하는 것이 좋습니다.  
  
예를 들어

_Prompt:_

```
### Instruction ###Translate the text below to Spanish:Text: "hello!"
```

_Output:_

```
¡Hola!
```


# Specificity

모델이 수행하기를 원하는 명령과 작업에 대해 매우 구체적으로 설명하세요. 프롬프트가 더 설명적이고 상세할수록 더 나은 결과를 얻을 수 있습니다. 이는 원하는 결과나 생성 스타일이 있을 때 특히 중요합니다. 더 나은 결과로 이어지는 특정 토큰이나 키워드는 없습니다. 좋은 형식과 설명적인 프롬프트가 더 중요합니다. 실제로 프롬프트에 예시를 제공하는 것은 특정 형식의 원하는 결과물을 얻는 데 매우 효과적입니다.

프롬프트를 디자인할 때는 길이에 제한이 있으므로 프롬프트의 길이도 염두에 두어야 합니다. 얼마나 구체적이고 상세해야 하는지도 고려해야 할 사항입니다. 불필요한 세부 정보를 너무 많이 포함하는 것이 반드시 좋은 방법은 아닙니다. 세부 사항은 관련성이 있어야 하고 당면한 작업에 기여해야 합니다. 이 부분은 많은 실험이 필요합니다. 애플리케이션에 맞게 프롬프트를 최적화하기 위해 많은 실험과 반복을 권장합니다.

예를 들어 텍스트에서 특정 정보를 추출하는 간단한 프롬프트를 사용해 보겠습니다.

_Prompt:_

```
Extract the name of places in the following text. Desired format:Place: <comma_separated_list_of_company_names>Input: "Although these developments are encouraging to researchers, much is still a mystery. “We often have a black box between the brain and the effect we see in the periphery,” says Henrique Veiga-Fernandes, a neuroimmunologist at the Champalimaud Centre for the Unknown in Lisbon. “If we want to use it in the therapeutic context, we actually need to understand the mechanism.""

다음 텍스트에서 장소 이름을 추출합니다.  
원하는 형식입니다:  
장소: <컴마로 구분된_회사명_목록>  
입력: "이러한 발전은 연구자들에게 고무적이지만 여전히 많은 부분이 미스테리입니다. "우리는 종종 뇌와 말초에서 보이는 효과 사이에 블랙박스가 존재합니다."라고 리스본에 있는 샹팔리모드 미지의 센터의 신경면역학자인 헨리크 베이가-페르난데스는 말합니다. "치료적 맥락에서 이를 사용하려면 실제로 메커니즘을 이해해야 합니다.""
```

_Output:_

```
Place: Champalimaud Centre for the Unknown, Lisbon

장소 샹팔리모드 미지의 센터, 리스본
```


# Avoid Impreciseness

상세하게 작성하고 형식을 개선하는 방법에 대한 위의 팁을 고려할 때, 프롬프트에 대해 너무 똑똑해지려고 해서 부정확한 설명을 만들 수 있는 함정에 빠지기 쉽습니다. 구체적이고 직접적으로 설명하는 것이 더 나은 경우가 많습니다. 이는 효과적인 커뮤니케이션과 매우 유사하며, 직접적일수록 메시지가 더 효과적으로 전달됩니다.

예를 들어 프롬프트 엔지니어링의 개념을 배우는 데 관심이 있을 수 있습니다. 다음과 같이 시도해 볼 수 있습니다:

```
Explain the concept prompt engineering. Keep the explanation short, only a few sentences, and don't be too descriptive.

프롬프트 엔지니어링의 개념을 설명하세요. 설명은 몇 문장으로 짧게 하되 너무 장황하게 설명하지 마세요.
```

위의 프롬프트에서는 몇 개의 문장을 사용해야 하는지, 어떤 스타일을 사용해야 하는지 명확하지 않습니다. 위의 프롬프트로도 어느 정도 좋은 응답을 얻을 수 있지만, 더 좋은 프롬프트는 매우 구체적이고 간결하며 요점을 파악할 수 있는 프롬프트입니다. 예를 들면 다음과 같습니다:

```
Use 2-3 sentences to explain the concept of prompt engineering to a high school student.

고등학생에게 프롬프트 엔지니어링의 개념을 2~3문장으로 설명하세요.
```


# To do or not to do

프롬프트를 디자인할 때 흔히 사용하는 또 다른 팁은 하지 말아야 할 것을 말하지 말고 대신 해야 할 것을 말하는 것입니다. 이렇게 하면 보다 구체적이고 모델의 좋은 반응을 이끌어내는 세부 사항에 집중할 수 있습니다.

다음은 하지 말아야 할 것에 초점을 맞춰 지침을 작성했기 때문에 영화 추천 챗봇이 원하지 않는 작업을 정확히 수행하지 못한 예입니다.

_Prompt:_

```
The following is an agent that recommends movies to a customer. DO NOT ASK FOR INTERESTS. DO NOT ASK FOR PERSONAL INFORMATION.Customer: Please recommend a movie based on my interests.Agent: 
```

_Output:_

```
Sure, I can recommend a movie based on your interests. What kind of movie would you like to watch? Do you prefer action, comedy, romance, or something else?
```

더 나은 프롬프트는 다음과 같습니다:

_Prompt:_

```
The following is an agent that recommends movies to a customer. The agent is responsible to recommend a movie from the top global trending movies. It should refrain from asking users for their preferences and avoid asking for personal information. If the agent doesn't have a movie to recommend, it should respond "Sorry, couldn't find a movie to recommend today.".Customer: Please recommend a movie based on my interests.Agent:
```

_Output:_

```
Sorry, I don't have any information about your interests. However, here's a list of the top global trending movies right now: [list of movies]. I hope you find something you like!
```


위의 예시 중 일부는 "OpenAI API를 사용한 신속한 엔지니어링 모범 사례" 문서에서 채택한 것입니다.
https://help.openai.com/en/articles/6654000-best-practices-for-prompt-engineering-with-openai-api

이전 섹션에서는 LLM에 메시지를 표시하는 방법에 대한 기본적인 예시를 소개했습니다.

이 섹션에서는 프롬프트가 다양한 작업을 수행하는 데 어떻게 사용되는지에 대한 더 많은 예제를 제공하고 그 과정에서 핵심 개념을 소개합니다. 종종 개념을 배우는 가장 좋은 방법은 예제를 살펴보는 것입니다. 아래에서는 잘 만들어진 프롬프트가 다양한 유형의 작업을 수행하는 데 어떻게 사용될 수 있는지에 대한 몇 가지 예를 다룹니다.


# Text Summarization

자연어 생성의 표준 작업 중 하나는 텍스트 요약입니다. 텍스트 요약에는 다양한 유형과 영역이 포함될 수 있습니다. 사실 언어 모델의 가장 유망한 응용 분야 중 하나는 기사와 개념을 빠르고 읽기 쉬운 요약으로 요약하는 기능입니다. 프롬프트를 사용하여 기본적인 요약 작업을 해보겠습니다.

항생제에 대해 배우고 싶다고 가정하면 다음과 같은 프롬프트를 사용해 볼 수 있습니다:


_Prompt:_

```
Explain antibiotics
A:

항생제 설명
A:
```

_Output:_

```
Antibiotics are a type of medication used to treat bacterial infections. They work by either killing the bacteria or preventing them from reproducing, allowing the body’s immune system to fight off the infection. Antibiotics are usually taken orally in the form of pills, capsules, or liquid solutions, or sometimes administered intravenously. They are not effective against viral infections, and using them inappropriately can lead to antibiotic resistance.

항생제는 박테리아 감염을 치료하는 데 사용되는 약물의 일종입니다. 항생제는 박테리아를 죽이거나 번식을 방지하여 신체의 면역 체계가 감염과 싸울 수 있도록 하는 방식으로 작용합니다. 항생제는 일반적으로 알약, 캡슐 또는 액체 용액 형태로 경구 복용하거나 정맥으로 투여하기도 합니다. 항생제는 바이러스 감염에는 효과적이지 않으며, 부적절하게 사용하면 항생제 내성을 유발할 수 있습니다.
```


"A:"는 질문 답변에 사용되는 명시적 프롬프트 형식입니다. 여기서는 모델에 예상되는 추가 사항이 있음을 알리기 위해 이 형식을 사용했습니다. 이 예에서는 이것이 유용할 때와 유용하지 않을 때의 차이가 명확하지 않지만 나중에 예제에서 다루도록 하겠습니다. 정보가 너무 많아서 더 요약하고 싶다고 가정해 보겠습니다. 실제로 모델에 다음과 같이 한 문장으로 요약하도록 지시할 수 있습니다:


_Prompt:_

```
Antibiotics are a type of medication used to treat bacterial infections. They work by either killing the bacteria or preventing them from reproducing, allowing the body’s immune system to fight off the infection. Antibiotics are usually taken orally in the form of pills, capsules, or liquid solutions, or sometimes administered intravenously. They are not effective against viral infections, and using them inappropriately can lead to antibiotic resistance.Explain the above in one sentence:

항생제는 박테리아 감염을 치료하는 데 사용되는 약물의 일종입니다. 항생제는 박테리아를 죽이거나 번식을 방지하여 신체의 면역 체계가 감염과 싸울 수 있도록 하는 방식으로 작용합니다. 항생제는 일반적으로 알약, 캡슐 또는 액체 용액 형태로 경구 복용하거나 정맥으로 투여하기도 합니다. 항생제는 바이러스 감염에는 효과적이지 않으며, 부적절하게 사용하면 항생제 내성을 유발할 수 있습니다.
위의 내용을 한 문장으로 설명하세요:
```

_Output:_

```
Antibiotics are medications used to treat bacterial infections by either killing the bacteria or stopping them from reproducing, but they are not effective against viruses and overuse can lead to antibiotic resistance.

항생제는 박테리아를 죽이거나 번식을 막아 박테리아 감염을 치료하는 데 사용되는 약물이지만 바이러스에는 효과적이지 않으며 남용하면 항생제 내성을 유발할 수 있습니다.

```

나중에 가이드에서 다룰 내용인 위의 출력의 정확성에 너무 많은 주의를 기울이지 않고 모델은 단락을 한 문장으로 요약하려고 시도했습니다. 지침에 따라 더 영리하게 만들 수 있지만 그 내용은 다음 장에서 다루도록 하겠습니다. 여기서 잠시 멈추고 더 나은 결과를 얻을 수 있는지 실험해 보세요.


# Information Extraction (정보 추출)

언어 모델은 자연어 생성 및 관련 작업을 수행하도록 학습되어 있지만, 분류 및 기타 다양한 자연어 처리(NLP) 작업도 수행할 수 있습니다.

다음은 주어진 단락에서 정보를 추출하는 프롬프트의 예입니다.

_Prompt:_

```
Author-contribution statements and acknowledgements in research papers should state clearly and specifically whether, and to what extent, the authors used AI technologies such as ChatGPT in the preparation of their manuscript and analysis. They should also indicate which LLMs were used. This will alert editors and reviewers to scrutinize manuscripts more carefully for potential biases, inaccuracies and improper source crediting. Likewise, scientific journals should be transparent about their use of LLMs, for example when selecting submitted manuscripts.Mention the large language model based product mentioned in the paragraph above:


연구 논문의 저자 기여 진술 및 감사의 글에는 저자가 원고 작성 및 분석에 ChatGPT와 같은 AI 기술을 사용했는지 여부와 그 정도를 명확하고 구체적으로 명시해야 합니다. 또한 어떤 LLM을 사용했는지도 명시해야 합니다. 이렇게 하면 편집자와 검토자가 잠재적인 편견, 부정확성, 부적절한 출처 표시가 있는지 원고를 더욱 면밀히 검토하도록 경고합니다. 마찬가지로 과학 저널은 투고된 원고를 선정할 때 등 LLM 사용에 대해 투명하게 공개해야 합니다.
위 단락에서 언급한 대규모 언어 모델 기반 제품에 대해 언급하세요:
```

_Output:_

```
The large language model based product mentioned in the paragraph above is ChatGPT.

위 단락에서 언급한 대규모 언어 모델 기반 제품은 ChatGPT입니다.
```



위의 결과를 개선할 수 있는 방법은 여러 가지가 있지만, 이 방법은 이미 매우 유용합니다.

이제 모델에 수행할 작업을 간단히 지시하여 다양한 작업을 수행하도록 요청할 수 있다는 것은 분명한 사실일 것입니다. 이는 이미 AI 제품 개발자들이 강력한 제품과 경험을 구축하는 데 사용하고 있는 강력한 기능입니다.


단락 출처: ChatGPT: 연구를 위한 5가지 우선순위 https://www.nature.com/articles/d41586-023-00288-7


# Question Answering

모델이 특정 답변에 응답하도록 하는 가장 좋은 방법 중 하나는 프롬프트의 형식을 개선하는 것입니다. 앞서 설명한 대로 프롬프트에 지시 사항, 컨텍스트, 입력 및 출력 지표를 결합하여 더 나은 결과를 얻을 수 있습니다. 이러한 구성 요소가 반드시 필요한 것은 아니지만, 지시가 구체적일수록 더 나은 결과를 얻을 수 있으므로 좋은 관행이 됩니다. 다음은 보다 구조화된 프롬프트의 예시입니다.

_Prompt:_

```
Answer the question based on the context below. Keep the answer short and concise. Respond "Unsure about answer" if not sure about the answer.Context: Teplizumab traces its roots to a New Jersey drug company called Ortho Pharmaceutical. There, scientists generated an early version of the antibody, dubbed OKT3. Originally sourced from mice, the molecule was able to bind to the surface of T cells and limit their cell-killing potential. In 1986, it was approved to help prevent organ rejection after kidney transplants, making it the first therapeutic antibody allowed for human use.Question: What was OKT3 originally sourced from?Answer:


아래 문맥에 따라 질문에 답하세요. 답변은 짧고 간결하게 작성하세요. 답변이 확실하지 않은 경우 '답변이 확실하지 않음'으로 응답하세요.
맥락: 테플리주맙의 기원은 뉴저지의 Ortho Pharmaceutical이라는 제약회사에 있습니다. 그곳에서 과학자들은 OKT3라고 불리는 초기 버전의 항체를 생성했습니다. 원래 생쥐에서 추출한 이 분자는 T세포 표면에 결합하여 세포 살상 가능성을 제한할 수 있었습니다. 1986년에는 신장 이식 후 장기 거부 반응을 예방하는 데 도움이 되는 것으로 승인되어 인간에게 사용하도록 허용된 최초의 치료용 항체가 되었습니다.
질문: OKT3는 원래 어디에서 유래했나요?
답변:
```

_Output:_

```
Mice.

생쥐
```


# Text Classification ( 텍스트 분류 )

지금까지는 간단한 명령어를 사용하여 작업을 수행했습니다. 프롬프트 엔지니어는 더 나은 지침을 제공하는 데 더 능숙해져야 합니다. 하지만 그게 다가 아닙니다! 또한 더 어려운 사용 사례의 경우 지침을 제공하는 것만으로는 충분하지 않다는 것을 알게 될 것입니다. 이때는 프롬프트에서 사용할 수 있는 컨텍스트와 다양한 요소에 대해 더 많이 생각해야 합니다. 제공할 수 있는 다른 요소로는 입력 데이터나 예시가 있습니다.

텍스트 분류의 예를 제공하여 이를 설명해 보겠습니다.

_Prompt:_

```
Classify the text into neutral, negative or positive. 
Text: I think the food was okay. 
Sentiment:


텍스트를 중립, 부정 또는 긍정으로 분류합니다.
텍스트: 음식은 괜찮았던 것 같아요.
감정:
```

_Output:_

```
Neutral

중립
```


텍스트를 분류하라는 지시를 내렸고 모델은 '중립'이라는 정답으로 응답했습니다. 여기에 문제가 있는 것은 아니지만 실제로 필요한 것은 모델이 원하는 정확한 형식으로 레이블을 제공하는 것이라고 가정해 봅시다. 따라서 `Neutral` 대신 `neutral`으로 반환되기를 원합니다. 이를 어떻게 달성할 수 있을까요? 

여러 가지 방법이 있습니다. 여기서는 구체성을 중요하게 생각하므로 프롬프트에 더 많은 정보를 제공할수록 더 나은 결과를 얻을 수 있습니다. 올바른 동작을 지정하기 위해 예제를 제공해 볼 수 있습니다. 다시 시도해 봅시다:

_Prompt:_

```
Classify the text into neutral, negative or positive. 
Text: I think the vacation is okay.
Sentiment: neutral 
Text: I think the food was okay. 
Sentiment:

텍스트를 중립, 부정 또는 긍정으로 분류합니다.
텍스트: 휴가는 괜찮을 것 같아요.
감정: 중립
텍스트: 음식은 괜찮았다고 생각합니다.
감정:
```

_Output:_

```
neutral

중립
```

이번에는 모델이 제가 찾던 특정 레이블인 중립을 반환했습니다. 프롬프트에 제공된 예제가 모델의 구체적인 출력에 도움이 된 것 같습니다. 때때로 구체적 표현이 중요한 이유를 강조하려면 이 예제를 확인하고 문제를 찾아보세요:

_Prompt:_

```
Classify the text into nutral, negative or positive. 
Text: I think the vacation is okay.
Sentiment:


텍스트를 영양, 부정 또는 긍정으로 분류합니다.
텍스트: 휴가는 괜찮을 것 같아요.
감정:
```

_Output:_

```
Neutral

중립
```

What is the problem here?


# Conversation

프롬프트 엔지니어링을 통해 얻을 수 있는 가장 흥미로운 것 중 하나는 LLM 시스템에 작동 방식, 의도 및 정체성을 지시하는 것입니다. 이는 고객 서비스 챗봇과 같은 대화형 시스템을 구축할 때 특히 유용합니다.

예를 들어 질문에 대해 보다 기술적이고 과학적인 답변을 생성할 수 있는 대화형 시스템을 만들어 보겠습니다. 지시를 통해 어떻게 행동해야 하는지 명시적으로 알려주는 방식에 주목하세요. 이를 역할 프롬프트라고도 합니다.


_Prompt:_

```
The following is a conversation with an AI research assistant. The assistant tone is technical and scientific.
Human: Hello, who are you?
AI: Greeting! I am an AI research assistant. How can I help you today?
Human: Can you tell me about the creation of blackholes?
AI:

다음은 인공지능 연구 도우미와의 대화입니다. 어시스턴트의 어조는 기술적이고 과학적입니다.
인간: 안녕하세요, 누구세요?
AI: 안녕하세요! 저는 인공지능 연구 도우미입니다. 오늘은 무엇을 도와드릴까요?
인간: 블랙홀의 생성에 대해 알려주실 수 있나요?
AI:
```

_Output:_

```
Sure! Black holes are regions of spacetime where the gravitational force is so strong that nothing, not even light, can escape from it. They are created when a very massive star dies and its core collapses in on itself, forming a singularity of infinite density. The intense gravity of the singularity pulls in all the matter and radiation around it, creating the black hole.


네! 블랙홀은 중력이 너무 강해서 빛조차도 빠져나갈 수 없는 시공간 영역입니다. 블랙홀은 매우 거대한 별이 죽으면서 그 중심부가 스스로 붕괴하여 무한한 밀도의 특이점을 형성할 때 만들어집니다. 특이점의 강력한 중력은 주변의 모든 물질과 방사선을 끌어당겨 블랙홀을 만듭니다.
```


이 동작을 변경하여 시스템이 더 쉽게 접근할 수 있는 답변을 제공하도록 지시해 보겠습니다.

_Prompt:_

```
The following is a conversation with an AI research assistant. The assistant answers should be easy to understand even by primary school students.
Human: Hello, who are you?
AI: Greeting! I am an AI research assistant. How can I help you today?
Human: Can you tell me about the creation of black holes?
AI: 


다음은 인공지능 연구 도우미와의 대화입니다. 어시스턴트의 답변은 초등학생도 쉽게 이해할 수 있는 수준입니다.
사람: 안녕하세요, 누구세요?
AI: 안녕하세요! 저는 인공지능 리서치 어시스턴트입니다. 오늘은 무엇을 도와드릴까요?
인간: 네: 블랙홀의 생성에 대해 알려주실 수 있나요?
AI:
```

_Output:_

```
Sure! Black holes are created when a very massive star runs out of fuel and collapses in on itself. The gravity of the star is so strong that nothing, not even light, can escape its pull. This creates a point in space-time with an intense gravitational force called a black hole.

블랙홀은 매우 거대한 별이 연료가 고갈되어 스스로 붕괴할 때 만들어집니다. 별의 중력이 너무 강해서 빛은 물론 그 어떤 것도 별의 끌어당김에서 벗어날 수 없습니다. 이렇게 되면 시공간에 블랙홀이라고 불리는 강력한 중력을 가진 지점이 만들어집니다.
```


어느 정도 진전이 있었다고 생각합니다. 계속 개선할 수 있습니다. 더 많은 예제를 추가하면 더 나은 결과를 얻을 수 있을 것입니다.



# Code Generation

LLM이 매우 효과적인 애플리케이션 중 하나는 코드 생성입니다. 코파일럿이 그 좋은 예입니다. 영리한 프롬프트를 통해 수행할 수 있는 코드 생성 작업은 무궁무진합니다. 아래에서 몇 가지 예를 살펴보겠습니다.

먼저 사용자를 맞이하는 간단한 프로그램을 만들어 보겠습니다.

_Prompt:_

```
/*Ask the user for their name and say "Hello"*/
```

_Output:_

```
let name = prompt("What is your name?");console.log(`Hello, ${name}!`);
```


사용할 언어를 지정할 필요조차 없었다는 것을 알 수 있습니다.

이제 레벨을 조금 바꿔보겠습니다. 프롬프트를 디자인할 때 조금만 더 노력하면 LLM이 얼마나 강력해질 수 있는지 보여드리겠습니다.


_Prompt:_

```
"""
Table departments, columns = [DepartmentId, DepartmentName]
Table students, columns = [DepartmentId, StudentId, StudentName]
Create a MySQL query for all students in the Computer Science Department
"""
```

_Output:_

```
SELECT 
StudentId, StudentName 
FROM students 
WHERE DepartmentId IN (SELECT DepartmentId 
FROM departments
WHERE DepartmentName = 'Computer Science');
```

이것은 매우 인상적입니다. 이 경우 데이터베이스 스키마에 대한 데이터를 제공하고 유효한 MySQL 쿼리를 생성하도록 요청했습니다.


# Reasoning ( 추론 )

오늘날 LLM에게 가장 어려운 과제 중 하나는 아마도 어떤 형태의 추론이 필요한 과제일 것입니다. 추론은 LLM에서 나올 수 있는 복잡한 응용 프로그램의 유형으로 인해 제가 가장 흥미를 느끼는 분야 중 하나입니다.

수학적 능력과 관련된 작업에서 일부 개선이 있었습니다. 하지만 현재 LLM은 추론 작업을 수행하는 데 어려움을 겪고 있으므로 더욱 고급 프롬프트 엔지니어링 기술이 필요하다는 점에 유의해야 합니다. 이러한 고급 기법은 다음 가이드에서 다룰 예정입니다. 지금은 산술 능력을 보여줄 수 있는 몇 가지 기본적인 예제를 살펴보겠습니다.

_Prompt:_

```
What is 9,000 * 9,000?
```

_Output:_

```
81,000,000
```


좀 더 어려운 것을 시도해 봅시다.


_Prompt:_

```
The odd numbers in this group add up to an even number: 15, 32, 5, 13, 82, 7, 1. A: 
```

_Output_

```
No, the odd numbers in this group add up to an odd number: 119.
```

잘못된 정보입니다! 프롬프트를 개선하여 이 문제를 개선해 보겠습니다.

_Prompt:_

```
The odd numbers in this group add up to an even number: 15, 32, 5, 13, 82, 7, 1. Solve by breaking the problem into steps. First, identify the odd numbers, add them, and indicate whether the result is odd or even. 


이 그룹의 홀수는 15, 32, 5, 13, 82, 7, 1과 같이 짝수로 합산됩니다.
문제를 단계별로 나누어 풀어보세요. 먼저 홀수를 식별하고 더한 다음 결과가 홀수인지 짝수인지 표시합니다.
```

_Output:_

```
Odd numbers: 15, 5, 13, 7, 1Sum: 41 41 is an odd number.
```


훨씬 낫죠? 그런데 몇 번 시도해 보니 시스템이 가끔 실패합니다. 예제와 함께 더 나은 지침을 제공하면 더 정확한 결과를 얻는 데 도움이 될 수 있습니다.

이 가이드의 이 섹션에 일반적인 애플리케이션에 대한 더 많은 예제를 계속 포함할 예정입니다.

다음 섹션에서는 이러한 모든 작업과 더 어려운 작업의 성능을 개선하기 위한 훨씬 더 고급 프롬프트 엔지니어링 개념과 기법을 다룰 것입니다.
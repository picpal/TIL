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
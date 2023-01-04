const pieData = {
  사과: 500,
  호두: 200,
  블루베리: 666,
  치즈: 54,
  딸기: 120,
};

const chartDonut = c3.generate({
  bindto: "#piechart",
  data: {
    json: [pieData],
    keys: {
      value: Object.keys(pieData),
    },
    type: "donut",
  },
  donut: {
    title: "파이의 종류",
  },
});

const chartDonutColors = chartDonut.data.colors();

document.querySelector("#text-apple").style.color = chartDonutColors["사과"];
document.querySelector("#text-walnut").style.color = chartDonutColors["호두"];
document.querySelector("#text-berry").style.color =
  chartDonutColors["블루베리"];
document.querySelector("#text-cheese").style.color = chartDonutColors["치즈"];
document.querySelector("#text-strawberry").style.color =
  chartDonutColors["딸기"];

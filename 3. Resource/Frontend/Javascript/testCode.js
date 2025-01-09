const graph = {
  A: ["B", "C"],
  B: ["A", "D", "E"],
  C: ["A", "F"],
  D: ["B"],
  E: ["B", "F"],
  F: ["C", "E"],
};

const start = "A";

function dfs(graph, start) {
  let visited = new Set();
  let stack = [start];

  while (stack.length > 0) {
    let vertex = stack.pop();

    if (!visited.has(vertex)) {
      visited.add(vertex);
      stack.push(...graph[vertex].filter((v) => !visited.has(v)));
    }
  }

  return visited;
}

console.log(dfs(graph, start));
const a = new Set();
a.add("A");
console.log(!a.has("B"));

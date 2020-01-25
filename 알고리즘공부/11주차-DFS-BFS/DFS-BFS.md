## DFS/BFS
![dfsbfs][./image/dfs-bfs-example.gif]

### DFS (깊이우선탐색)
루트 노드(혹은 다른 임의의 노드)에서 시작해서 다음분기로 넘어가기 전에 해당 분기를 완벽하게 탐색하는 방법
ex) 특정 도시에서 다른 도시로 갈 수 있는지 없는지, 전자회로에서 특정 단장와 단자가 서로 연결되어 있는지

### DFS의 특징
- 모든 노드를 방문하고자 하는 경우에 선택
- BFS 보다 느리고, 간단하다
- 자기 자신을 호출하는 순환 알고리즘 형태를 가지고있다
- 어떤 노드를 방문했었는지 여부를 검사해야 한다

### DFS 구현방법
1. 순환 호출 이용
2. 명시적인 스택 이용

### DFS 순환 호출 qseudocode
```
void search(Node root) {
  if (root == null) return;
  // 1. root 노드 방문
  visit(root);
  root.visited = true; // 1-1. 방문한 노드를 표시
  // 2. root 노드와 인접한 정점을 모두 방문
  for each (Node n in root.adjacent) {
    if (n.visited == false) { // 4. 방문하지 않은 정점을 찾는다.
      search(n); // 3. root 노드와 인접한 정점 정점을 시작 정점으로 DFS를 시작
    }
  }
}
```

### BFS (너비우선탐색)
현재 점점에 연결된 가까운 점들부터 탐색

### BFS 구현방법
큐를 이용하여 구현

#dynamic 

> 로봇 길찾기 ( 최적경로 X )

1. 로봇은 위와 옆으로만 움직일 수 있음.
2. 이동 할 수 없는 off limit 칸이 존재함


- 재귀로 구현했을 경우에 로봇이 시작하는 곳에서 부터 목적지까지 찾은 다음 출력하게 되면 반환되는 값은 목적지 부터 출발지 까지 역순으로 반환되기 때문에, 목적지에서 출발지를 찾는 함수를 재귀로 호출하여 찾은 값을 반환하는 방식으로 해야한다. ( 스택 구조로 생각 )


```java
class Point {
	int row, col;
	Point (int row, int col){
		this.row = row;
		this.col = col;
	}
}

class Solution {
	public ArrayList<Point> findPath(boolean[][] grid){
		if(grid == null || grid.length == 0) return null;
		ArrayList<Point> path = new ArrayList<Point>();
		if(findPath(grid,grid.length-1,grid[0].length-1,path))
			return path;
		else return null;
	}
	public boolean findPath(boolean[][] grid, int row, int col, ArrayList<Point> path){
		if(!isInRange(grid,row,col)|| grid[row][col]) return false;
		if((row == 0 && col == 0) 
			|| findPath(grid,row,col-1,path) 
			|| findPath(grid,row-1,col,path)){
			Point p = new Point(row,col);
			path.add(p);
			return true;
		}
		return false;
	} 

	public booelan isInRange(boolean[][] grid, int row, int col){
		return row >= 0 && row <= grid.length - 1 
			&& col >= 0 && col <= grid[row].length - 1;
	}

}

class Test {
	public static boid main(String[] args){
		boolean[][] grid = {
			{false,false,false,false},
			{false,false,false,true},
			{true,true,false,false},
			{false,false,false,false},		
		}

		Solution sol = new Solution();
		sol.findPath(grid);
	}
}


```



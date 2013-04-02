class Point {
	public int x;
	public int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void rotate(double alpha){
		int x1 = x;
		int y1 = y;
		this.x = (int)Math.round(x1*Math.cos(alpha) - y1*Math.sin(alpha));
		this.y = (int)Math.round(x1*Math.sin(alpha) + y1*Math.cos(alpha));
	}
}
public class Vec {

	public int x;
	public int y;

	public Vec() {
		this.x = 0;
		this.y = 0;
	}

	public Vec(double x, double y) {
		this.x = (int)Math.round(x);
		this.y = (int)Math.round(y);
	}
	
	public void setVec(double x, double y) {
		this.x = (int)Math.round(x);
		this.y = (int)Math.round(y);
	}

	public boolean equals(Vec arg) {
		return (x == arg.x && y == arg.y);
	}

	public Vec plus(Vec arg) {
		return new Vec(this.x + arg.x, this.y + arg.y);
	}

	public Vec plus(double arg) {
		return new Vec(this.x + arg, this.y + arg);
	}

	public Vec mult(double arg) {
		return new Vec(this.x*arg, this.y*arg);
	}
	
	public double length() {
		return Math.sqrt(x*x+y*y);
	}
}

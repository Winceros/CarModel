import java.awt.*;
import javax.swing.*;


class Car extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size;
	private double alpha;
	private Vec r;
	private Vec v;
	private double w;
	private Vec acc;
	private final double dt = 1;

	public Car(Vec A, int size){
		setLayout(null);
		this.size = size;
		this.alpha = 0;
		this.r = A;
		this.v = new Vec(0,0);
		this.w = 0;
		this.acc = new Vec(0,0);
	}

	public void setCoord(int x, int y, double alpha){
		this.r.setVec(x, y);
		this.alpha = alpha;
	}
	
	public void move(Vec B) {
		double acc_coeff = 0;
		double w_acc = 0;
		double alphaRad = 0;
		
		for (int i=0; i < 200 && !r.equals(B) ; i++) {
			w_acc =  calcAngularAcceleration(B);
			w = w + w_acc;
			alpha = alpha + w;
			alphaRad = 3.14/180*alpha;

			v = v.plus(acc);
			r = r.plus(v);	
			
			acc_coeff = calcAcceleration(B);
			acc.setVec(acc_coeff*Math.cos(alphaRad), acc_coeff*Math.sin(alphaRad));
			
			System.out.println("acc=("+acc.x+","+acc.y+"), "+"w_acc="+w_acc+", w="+w+", r=("+r.x+","+r.y+"), v=("+v.x+","+v.y+")"+", alpha=" + alpha);
			
			this.repaint();
			
			try {
				Thread.sleep(100);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}			
		}
	}


	private double calcAngularAcceleration(Vec B) {
		double diff = Math.tan(alpha) - (B.y-r.y)/((B.x-r.x)+0.00001);
		if (diff < 0.01)
			return 1.0;
		else if (diff > 0.1)
			return -1.0;
		return 0;
	}

	private double calcAcceleration(Vec B) {
		if(v.length()>5)
				return 0;
			else
				return 1;
	}

	private void draw(Graphics g){
		Point leftUp = (new Point(-size,-size)); leftUp.rotate(alpha);
		Point rightUp = (new Point(size,-size)); rightUp.rotate(alpha);
		Point leftLow = (new Point(-size,size)); leftLow.rotate(alpha);
		Point rightLow = (new Point(size,size)); rightLow.rotate(alpha);
		g.drawLine(leftUp.x + r.x,leftUp.y + r.y,rightUp.x + r.x,rightUp.y + r.y);
		g.drawLine(leftUp.x + r.x,leftUp.y + r.y,leftLow.x + r.x,leftLow.y + r.y);
		g.drawLine(leftLow.x + r.x,leftLow.y + r.y,rightLow.x + r.x,rightLow.y + r.y);
		g.drawLine(rightLow.x + r.x,rightLow.y + r.y,rightUp.x + r.x,rightUp.y + r.y);
		g.drawLine(r.x, r.y , (rightLow.x-leftLow.x)/2 + r.x,(rightLow.y-leftLow.y)/2 + r.y);
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
	}
}
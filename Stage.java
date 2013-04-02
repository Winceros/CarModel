import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Stage extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Car car;
	private JButton button;


	public Stage(){
		car = new Car(new Vec(100,100), 15);
		button = new JButton("Go");
		 
		JFrame mf = new JFrame("Ride test");
		mf.setLayout(new BorderLayout());
		mf.setSize(500, 500);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		car.setBorder(BorderFactory.createLineBorder(Color.RED));
		car.setBackground(Color.WHITE);
		mf.add(car, BorderLayout.CENTER);
		mf.add(button, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//=========================================================
					new Thread(new Runnable() {
						@Override
						public void run() {
							car.move(new Vec(200,200));
						}
					}).start();
				//=========================================================
				}
		});
		mf.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Stage();
			}
		});

	}
}
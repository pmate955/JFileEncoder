package View;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		this.setTitle("File Encoder");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mf = new MainFrame();
	}

}

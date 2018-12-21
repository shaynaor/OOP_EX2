package Gui;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		MyFrame win = new MyFrame();
		Thread t1 = new Thread(win);
		t1.start();
		win.setVisible(true);
		win.setSize(win.getMyImage().getWidth(), win.getMyImage().getHeight());
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}

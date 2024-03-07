package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		Board board = new Board();

		JFrame jframe = new JFrame("Tic Tac Toe Game");
		jframe.setSize(600, 600);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.add(board);

		int w = (int) (dimension.getWidth() / 2) - (jframe.getWidth() / 2);
		int h = (int) (dimension.getHeight() / 2) - (jframe.getHeight() / 2);

		jframe.setLocation(w, h);
		jframe.setVisible(true);
	}

}

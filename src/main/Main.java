package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		Board board;
		int choice = JOptionPane.showConfirmDialog(null, "Người chơi O đi trước đúng không?", "Ai là người đi trước",
				JOptionPane.YES_NO_OPTION);
		if (choice == 0) {
			board = new Board(Cell.O_VALUE);
		} else {
			board = new Board(Cell.X_VALUE);
		}
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		JPanel primaryPanel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(primaryPanel, BoxLayout.Y_AXIS);
		primaryPanel.setLayout(boxLayout);

		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(flowLayout);

		board.setPreferredSize(new Dimension(600, 600));

		JButton btnStart = new JButton("start");
		JLabel lbTime = new JLabel("00:00");
		bottomPanel.add(lbTime);
		bottomPanel.add(btnStart);

		primaryPanel.add(board);
		primaryPanel.add(bottomPanel);

		JFrame jframe = new JFrame("Tic Tac Toe Game");

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(true);
		jframe.add(primaryPanel);

		int w = (int) (dimension.getWidth() / 2) - 300;
		int h = (int) (dimension.getHeight() / 2) - 300;

		jframe.setLocation(w, h);
		// help frame scale according to internal elements
		jframe.pack();
		// show screen
		jframe.setVisible(true);
	}

}

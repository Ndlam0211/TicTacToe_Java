package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
	public static final int R = 3;
	public static final int C = 3;

	private Image imgX;
	private Image imgO;
	private Cell matrix[][] = new Cell[R][C];

	// Người chơi đầu tiên đi O
	private String currentPlayer;

	public Board(String player) {
		this();
		this.currentPlayer = player;
	}

	public Board() {
		this.initMatrix();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				int x = e.getX();
				int y = e.getY();

				// tính toán x,y(tọa độ) rơi vào ô nào trong board để render ra X,O tùy ý
				for (int i = 0; i < R; i++)
					for (int j = 0; j < C; j++) {
						Cell cell = matrix[i][j];

						int cXStart = cell.getX();
						int cYStart = cell.getY();

						int cXEnd = cXStart + cell.getW();
						int cYEnd = cYStart + cell.getH();

						if (x >= cXStart && x <= cXEnd) {
							if (y >= cYStart && y <= cYEnd) {
								if (cell.getValue().equals(Cell.EMPTY_VALUE)) {
									// Cho người chơi đầu tiên
									cell.setValue(currentPlayer);
									// Đổi lượt chơi
									currentPlayer = (currentPlayer.equals(Cell.O_VALUE)) ? Cell.X_VALUE : Cell.O_VALUE;
									// gọi lại hàm paint để vẽ lại board
									repaint();
								}
							}
						}

					}

			}
		});

		try {
			imgX = ImageIO.read(getClass().getResource("X.png"));
			imgO = ImageIO.read(getClass().getResource("O.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth() / 3;
		int h = getHeight() / 3;

		Graphics2D graphics2d = (Graphics2D) g;

		int k = 0;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				int x = j * w;
				int y = i * h;

				// cập nhật ma trận
				Cell cell = matrix[i][j];
				cell.setX(x);
				cell.setY(y);
				cell.setH(h);
				cell.setW(w);

				Color color = (k % 2 == 0) ? Color.BLUE : Color.RED;

				graphics2d.setColor(color);
				graphics2d.fillRect(x, y, w, h);

				if (cell.getValue().equals(Cell.X_VALUE)) {
					Image img = imgX;
					graphics2d.drawImage(img, x, y, w, h, this);
				} else if (cell.getValue().equals(Cell.O_VALUE)) {
					Image img = imgO;
					graphics2d.drawImage(img, x, y, w, h, this);
				}

				k++;
			}
	}

	private void initMatrix() {
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				Cell cell = new Cell();
				matrix[i][j] = cell;
			}
	}
}

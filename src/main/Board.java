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

	public Board() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				System.out.println(e.getX());
				System.out.println(e.getY());
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
				int x = i * w;
				int y = j * h;

				Color color = (k % 2 == 0) ? Color.BLUE : Color.RED;

				graphics2d.setColor(color);
				graphics2d.fillRect(x, y, w, h);

				Image img = (k % 2 == 0) ? imgX : imgO;
				graphics2d.drawImage(img, x, y, w, h, this);

				k++;
			}
	}
}

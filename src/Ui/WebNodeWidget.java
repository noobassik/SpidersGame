package Ui;

import javax.swing.*;
import java.awt.*;

public class WebNodeWidget extends JPanel {

    private WebNodeItemWidget item = null;

    public static final int CELL_SIZE = 120;

    public WebNodeWidget() {
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(ImageUtils.BACKGROUND_COLOR);
    }

    public void addItem(WebNodeItemWidget item) {
        if (this.item == item)
            return;

        if (this.item != null) throw new IllegalArgumentException();

        int index = -1;

        this.item = item;
        add(item, index);
    }

    public void removeItem(WebNodeItemWidget item) {
        if (this.item == item) {
            int index = 0;

            remove(index);

            this.item = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(0, CELL_SIZE / 2, CELL_SIZE, CELL_SIZE / 2);
        g2.drawLine(CELL_SIZE / 2, 0, CELL_SIZE / 2, CELL_SIZE);
    }

}

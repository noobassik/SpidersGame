package Ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public abstract class WebNodeItemWidget extends JPanel {
    protected BufferedImage image;

    public WebNodeItemWidget(String imagePath, int imageWidth, int imageHeight) {
        setOpaque(false);
        setImage(imagePath, imageWidth, imageHeight);
        updateState();
    }

    protected BufferedImage getImage() {
        return image;
    }

    protected void updateState() {
        setPreferredSize(getDimension());
        repaint();
        revalidate();
    }

    protected void setImage(String path, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
            image = ImageUtils.resizeImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = image;
    }

    protected abstract Dimension getDimension();
}

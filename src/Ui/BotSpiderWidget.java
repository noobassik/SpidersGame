package Ui;

import Entities.Spider;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Ui.WebNodeWidget.CELL_SIZE;


public class BotSpiderWidget extends AnimalWidget {

    private final Spider botSpider;

    public BotSpiderWidget(Spider botSpider) {
        super("images/spider.png", CELL_SIZE / 2, CELL_SIZE - 24);
        this.botSpider = botSpider;
    }

    @Override
    protected BufferedImage getImage() {
        return GameWidgetUtils.spiderWithHealthImage(image, botSpider.getHealth());
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE / 2, CELL_SIZE);
    }

}

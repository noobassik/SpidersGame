package Ui;
import Entities.Spider;
import Setting.Web;
import Utils.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static Ui.WebNodeWidget.CELL_SIZE;
public class PlayerSpiderWidget extends AnimalWidget {

    private Spider playerSpider;

    public PlayerSpiderWidget(Spider playerSpider) {
        super(playerSpider.isPlayer() ? "images/playerSpider.png" : "images/spider.png", CELL_SIZE / 2, CELL_SIZE - 24);
        this.playerSpider = playerSpider;
        setFocusable(true);
        addKeyListener(new KeyController());
    }

    public void updatePlayerSpiderImage(){
        super.setImage("images/playerSpider.png", CELL_SIZE / 2, CELL_SIZE - 24);
        updateState();
    }

    @Override
    protected BufferedImage getImage() {
        return GameWidgetUtils.spiderWithHealthImage(image, playerSpider.getHealth());
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE / 2, CELL_SIZE);
    }

    private class KeyController implements KeyListener {

        @Override
        public void keyTyped(KeyEvent arg0) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {


        }

        @Override
        public void keyReleased(KeyEvent ke) {
            int keyCode = ke.getKeyCode();

            moveAction(keyCode);
        }

        private void moveAction(int keyCode) {
            Direction direction = directionByKeyCode(keyCode);
            if (direction != null) {
                Web web = playerSpider.getWebNode().getWeb();
                Spider temp = playerSpider;
                playerSpider.makeMove(direction);
                playerSpider = web.getPlayerSpider();
                if (playerSpider != temp){
                    playerSpider.makeMove(direction);
                }
                requestFocus();
            }
        }

        private Direction directionByKeyCode(int keyCode) {
            Direction direction = null;
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    direction = Direction.north();
                    break;
                case KeyEvent.VK_DOWN:
                    direction = Direction.south();
                    break;
                case KeyEvent.VK_LEFT:
                    direction = Direction.west();
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = Direction.east();
                    break;
            }
            return direction;
        }
    }

}

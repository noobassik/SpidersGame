package Entities;

import Setting.Web;
import Setting.WebNode;
import Utils.Direction;
import Utils.Game;

import java.util.ListIterator;

public class Spider extends Animal {
    private Game game;
    private int health;

    public Spider(WebNode webNode, int health, Web web) {
        super(webNode, web);
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return this.health == 0;
    }

    public void makeMove(Direction direction) {
        ListIterator<WebNode> iterator = (ListIterator<WebNode>) web.getWebNodes().iterator();
        if (iterator.hasNext()) {
            WebNode nextWebNode = webNode.getNextWebNode(direction);
            if (nextWebNode != null) {
                if (nextWebNode.getAnimal() == null) {
                    setHealth(this.health - 1);
                    if (isAlive()) {
                        this.die();
                    } else {
                        moveToNextNode(nextWebNode);
                    }
                } else if (nextWebNode.getAnimal() instanceof Insect) {
                    setHealth(this.health + ((Insect) nextWebNode.getAnimal()).getValue() - 1);
                    //nextWebNode.releaseAnimal();
                    nextWebNode.getAnimal().die();
                    moveToNextNode(nextWebNode);
                }
            } else {
                setHealth(this.health - 1);
            }

        }
    }

    public boolean isValid() {
        return true;
    }

    private void moveToNextNode(WebNode nextWebNode) {
        this.webNode.releaseAnimal();
        nextWebNode.setAnimal(this);
        setWebNode(nextWebNode);
    }

    private boolean eatInsect(Insect insect) {
        return true;
    }

    @Override
    public void die() {
        this.health = 0;
        super.web.removeSpider(this);
        super.webNode.releaseAnimal();
        if (this == web.getPlayerSpider()) {
            super.web.setPlayerSpider(null);
            this.game.changePlayerSpider(this); // TODO: game is null, верная ли связь - через обсервер
        }
        super.web = null;
        setWebNode(null);
    }

    @Override
    public Spider clone() {
        return new Spider((WebNode) this.webNode.clone(), this.health, this.web);
    }
}

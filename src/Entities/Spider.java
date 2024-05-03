package Entities;

import Events.BotSpiderActionEvent;
import Events.BotSpiderActionListener;
import Events.Controllers.SpiderControllerActionEvent;
import Events.Controllers.SpiderControllerActionListener;
import Events.PlayerActionEvent;
import Events.PlayerActionListener;
import Setting.WebNode;
import Utils.Direction;
import Utils.Game;

import java.util.ArrayList;
import java.util.ListIterator;

public class Spider extends Animal {
    private Game game;
    private int health;

    public Spider(WebNode webNode, int health) {
        super(webNode);
        this.health = health;

    }

    public void setGame(Game game){
        this.game = game;
    }

    public int getHealth() {
        return this.health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return this.health != 0;
    }

    public void makeMove(Direction direction) {
        setHealth(this.health - 1);
        if (!isAlive()) {
            this.die();
            return;
        }
        WebNode nextWebNode = webNode.getNextWebNode(direction);
        WebNode oldWebNode = this.getWebNode();
        ListIterator<WebNode> iterator = (ListIterator<WebNode>) web.getWebNodes().iterator();
        if (iterator.hasNext()) {
            if (nextWebNode != null) {
                if (nextWebNode.getAnimal() == null) {
                    moveToNextNode(nextWebNode);
                } else if (nextWebNode.getAnimal() instanceof Insect) {
                    setHealth(this.health + ((Insect) nextWebNode.getAnimal()).getValue());
                    nextWebNode.getAnimal().die();
                    moveToNextNode(nextWebNode);
                }
            }
        }
        if (isAlive()) {
            if (!this.game.getBot().isBotOwnsSpider(this)) {
                fireBotSpiderMoved();
            } else {
                fireSpiderMovedController(oldWebNode, nextWebNode);
                firePlayerMoved();
            }
        }
    }


    public boolean isValid() {
        return true;
    }

    private void moveToNextNode(WebNode nextWebNode) {
        this.webNode.setAnimal(null);
        this.setWebNode(null);
        nextWebNode.setAnimal(this);
    }

    private boolean eatInsect(Insect insect) {
        return true;
    }

    @Override
    protected void die() {
        this.health = 0;
        super.web.removeSpider(this);
        super.webNode.setAnimal(null);
        this.setWebNode(null);
        if (this == web.getPlayerSpider()) {
            super.web.setPlayerSpider(null);
            firePlayerDied();
            this.game.changePlayerSpider(this); //TODO: game is null, верная ли связь - через обсервер
        } else {
            fireBotSpiderDied();
        }
        super.web = null;
        setWebNode(null);
    }

    @Override
    public Spider clone() {
        return new Spider((WebNode) this.webNode.clone(), this.health);
    }

    private ArrayList<BotSpiderActionListener> botSpiderListenerList = new ArrayList<>();

    private ArrayList<PlayerActionListener> playerSpiderListenerList = new ArrayList<>();

    public void addPlayerSpiderActionListener(PlayerActionListener listener) {
        playerSpiderListenerList.add(listener);
    }

    public void removePlayerSpiderActionListener(PlayerActionListener listener) {
        playerSpiderListenerList.remove(listener);
    }

    protected void firePlayerMoved() {
        for (PlayerActionListener listener : playerSpiderListenerList) {
            PlayerActionEvent event = new PlayerActionEvent(listener);
            event.setPlayerSpider(this);
            listener.playerMoved(event);
        }
    }

    protected void firePlayerDied() {
        for (PlayerActionListener listener : playerSpiderListenerList) {
            PlayerActionEvent event = new PlayerActionEvent(listener);
            event.setPlayerSpider(this);
            listener.playerDied(event);
        }
    }

    public void addBotSpiderActionListener(BotSpiderActionListener listener) {
        botSpiderListenerList.add(listener);
    }

    public void removeBotSpiderActionListener(BotSpiderActionListener listener) {
        botSpiderListenerList.remove(listener);
    }

    protected void fireBotSpiderMoved() {
        for (BotSpiderActionListener listener : botSpiderListenerList) {
            BotSpiderActionEvent event = new BotSpiderActionEvent(listener);
            event.setBotSpider(this);
            listener.botMoved(event);
        }
    }

    protected void fireBotSpiderDied() {
        for (BotSpiderActionListener listener : botSpiderListenerList) {
            BotSpiderActionEvent event = new BotSpiderActionEvent(listener);
            event.setBotSpider(this);
            listener.botDied(event);
        }
    }

    private ArrayList<SpiderControllerActionListener> spiderControllerListenersList = new ArrayList<>();

    public void addSpiderControllerActionListener(SpiderControllerActionListener listener) {
        spiderControllerListenersList.add(listener);
    }

    public void removeSpiderControllerActionListener(SpiderControllerActionListener listener) {
        spiderControllerListenersList.remove(listener);
    }

    protected void fireSpiderMovedController(WebNode from, WebNode to) {
        for (SpiderControllerActionListener listener : spiderControllerListenersList) {
            SpiderControllerActionEvent event = new SpiderControllerActionEvent(listener);
            event.setSpider(this);
            event.setFrom(from);
            event.setTo(to);
            listener.spiderMoved(event);
        }
    }

}

package Entities;

import Events.*;
import Events.Controllers.SpiderControllerActionEvent;
import Events.Controllers.SpiderControllerActionListener;
import Setting.WebNode;
import Utils.Direction;
import Utils.Game;

import java.util.ArrayList;

public class Spider extends Animal {
    private Game game;
    private int health;

    public Spider(WebNode webNode, int health) {
        super(webNode);
        this.health = health;

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getHealth() {
        return this.health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void makeMove(Direction direction) {
        setHealth(this.health - 1);
        if (!isAlive()) {
            this.die();
            return;
        }
        WebNode oldWebNode = this.getWebNode();
        WebNode newWebNode = oldWebNode;
        WebNode nextWebNode = webNode.neighbour(direction);
        if (nextWebNode != null) {
            if (nextWebNode.getAnimal() == null) {
                moveToNextNode(nextWebNode);
                newWebNode = nextWebNode;
            } else if (nextWebNode.getAnimal() instanceof Insect) {
                setHealth(this.health + ((Insect) nextWebNode.getAnimal()).getValue());
                nextWebNode.getAnimal().die();
                moveToNextNode(nextWebNode);
                newWebNode = nextWebNode;
                if (this == web.getPlayerSpider()){
                    firePlayerAteInsect();
                }
            }
        }

            if (this.game.getBot().isBotOwnsSpider(this)) {
                fireSpiderMovedController(oldWebNode, newWebNode);
                fireBotSpiderMoved();
                fireStepHappened();
            } else {
                fireSpiderMovedController(oldWebNode, newWebNode);
                firePlayerMoved();
            }
    }


    private void moveToNextNode(WebNode nextWebNode) {
        this.webNode.setAnimal(null);
        this.setWebNode(null);
        nextWebNode.setAnimal(this);
    }

    @Override
    protected void die() {
        this.health = 0;
        fireSpiderDiedController(this.webNode);
        super.web.removeSpider(this);
        super.webNode.setAnimal(null);
        game.getBot().deleteSpiderFromList(this);
        this.setWebNode(null);
        if (this == web.getPlayerSpider()) {
            super.web.setPlayerSpider(null);
            firePlayerDied();
        } else {
            fireBotSpiderDied();
        }
        super.web = null;
        this.game = null;
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
            Runnable r = () -> listener.playerMoved(event);
            Thread newThread = new Thread(r, "Listener");
            newThread.start();
        }
    }

    protected void firePlayerAteInsect(){
        for(PlayerActionListener listener : playerSpiderListenerList){
            PlayerActionEvent event = new PlayerActionEvent(listener);
            event.setPlayerSpider(this);
            listener.playerAteInsect(event);
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

    private ArrayList<GameActionListener> gameListeners = new ArrayList<>();

    public void addGameListener(GameActionListener listener){
        gameListeners.add(listener);
    }

    protected void fireStepHappened(){
        for (GameActionListener listener : gameListeners){
            listener.gameStepHappened(new GameActionEvent(listener));
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

    protected void fireSpiderDiedController(WebNode from) {
        for (SpiderControllerActionListener listener : spiderControllerListenersList) {
            SpiderControllerActionEvent event = new SpiderControllerActionEvent(listener);
            event.setSpider(this);
            event.setFrom(from);
            listener.spiderDied(event);
        }
    }

}

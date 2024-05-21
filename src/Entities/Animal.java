package Entities;

import Events.Controllers.AnimalControllerActionEvent;
import Events.Controllers.AnimalControllerActionListener;
import Setting.Web;
import Setting.WebNode;

import java.util.ArrayList;

public abstract class Animal {
    protected Web web;
    protected WebNode webNode;

    protected enum size {
        SMALL,
        MIDDLE,
        BIG;
    }


    public Animal(WebNode webNode) {
        if (webNode != null) {
            if (webNode.getWeb().getWebNode(webNode.getPosition()).isEmpty()) {
                this.webNode = webNode;
                webNode.setAnimal(this);
            }
            this.web = webNode.getWeb();
        }
    }

    public boolean isPlayer() {
        return this.web.getPlayerSpider() == this;
    }

    public void setWeb(Web web) {
        this.web = web;
    }

    public WebNode getWebNode() {
        return this.webNode;
    }

    public void setWebNode(WebNode newNode) {
        this.webNode = newNode;
    }

    protected void die() {
        fireAnimalDiedController(this.webNode);
        this.web.removeInsect(this);
        this.web = null;
        this.webNode.setAnimal(null);
        this.setWebNode(null);
    }

    private ArrayList<AnimalControllerActionListener> animalControllerListenerList = new ArrayList<>();

    public void addAnimalControllerActionListener(AnimalControllerActionListener listener) {
        animalControllerListenerList.add(listener);
    }

    public void removeAnimalControllerActionListener(AnimalControllerActionListener listener) {
        animalControllerListenerList.remove(listener);
    }

    protected void fireAnimalDiedController(WebNode webNode) {
        for (AnimalControllerActionListener listener : animalControllerListenerList) {
            AnimalControllerActionEvent event = new AnimalControllerActionEvent(listener);
            event.setAnimal(this);
            event.setWebCross(webNode);
            listener.animalDied(event);
        }
    }

}

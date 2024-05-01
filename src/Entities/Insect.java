package Entities;

import Events.InsectActionEvent;
import Events.InsectActionListener;
import Setting.Web;
import Setting.WebNode;

import java.util.ArrayList;

public abstract class Insect extends Animal {
    protected int value;

    public Insect(WebNode webNode) {
        super(webNode);
    }

    public int getValue() {
        return this.value;
    }

    protected void setValue(int value) {
        this.value = value;
    }

    public abstract void disappearFromWeb();
    private ArrayList<InsectActionListener> insectListenersList = new ArrayList<>();

    public void addInsectActionListener(InsectActionListener listener) {
        insectListenersList.add(listener);
    }

    public void removeInsectActionListener(InsectActionListener listener) {
        insectListenersList.remove(listener);
    }

    protected void fireInsectDied(){
        for(InsectActionListener listener : insectListenersList){
            InsectActionEvent event = new InsectActionEvent(listener);
            event.setInsect(this);
            listener.insectDied(event);
        }
    }
    protected void fireInsectWasEaten(){
        for(InsectActionListener listener : insectListenersList){
            InsectActionEvent event = new InsectActionEvent(listener);
            event.setInsect(this);
            listener.insectWasEaten(event);
        }
    }
}

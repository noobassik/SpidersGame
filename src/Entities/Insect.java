package Entities;

import Events.InsectActionEvent;
import Events.InsectActionListener;
import Setting.WebNode;

import java.util.ArrayList;

public abstract class Insect extends Animal {
    protected int value;

    protected enum size {
        SMALL,
        MIDDLE,
        BIG;
    }

    public Insect(WebNode webNode) {
        super(webNode);
    }

    public int getValue() {
        return this.value;
    }

    protected void setValue(int value) {
        this.value = value;
    }

    protected abstract double getProbabilityToDisappear();

    public void disappearFromWeb(){
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= getProbabilityToDisappear()){
            die();
        }
    }
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

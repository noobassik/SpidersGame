package Events;

import Entities.Insect;

import java.util.EventObject;

public class InsectActionEvent extends EventObject {

    private Insect insect;

    public void setInsect(Insect insect){
        this.insect = insect;
    }
    public Insect getInsect(){
        return this.insect;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public InsectActionEvent(Object source) {
        super(source);
    }
}
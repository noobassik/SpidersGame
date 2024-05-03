package Events.Controllers;


import Entities.Animal;
import Setting.WebNode;

import java.util.EventObject;

public class AnimalControllerActionEvent extends EventObject {

    private Animal animal;
    private WebNode webNode;

    public void setAnimal(Animal animal){
        this.animal = animal;
    }

    public Animal getAnimal(){
        return animal;
    }

    public void setWebCross(WebNode webNode){
        this.webNode = webNode;
    }

    public WebNode getWebNode(){
        return webNode;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public AnimalControllerActionEvent(Object source) {
        super(source);
    }
}

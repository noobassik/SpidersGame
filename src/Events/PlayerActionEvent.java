package Events;

import Entities.Spider;

import java.util.EventObject;

public class PlayerActionEvent extends EventObject {

    private Spider playerSpider;

    public void setPlayerSpider(Spider playerSpider){
        this.playerSpider = playerSpider;
    }

    public Spider getPlayerSpider(){
        return this.playerSpider;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public PlayerActionEvent(Object source) {
        super(source);
    }
}

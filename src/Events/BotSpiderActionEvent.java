package Events;

import Entities.Spider;
import java.util.EventObject;

public class BotSpiderActionEvent extends EventObject {

    private Spider botSpider;

    public void setBotSpider(Spider bot){
        this.botSpider = bot;
    }
    public Spider getBotSpider(){
        return this.botSpider;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BotSpiderActionEvent(Object source) {
        super(source);
    }
}

package Events.Controllers;

import Entities.Spider;
import Setting.WebNode;

import java.util.EventObject;

public class SpiderControllerActionEvent extends EventObject {

    private Spider spider;
    private WebNode from;
    private WebNode to;

    public void setFrom(WebNode from) {
        this.from = from;
    }

    public WebNode getFrom() {
        return from;
    }

    public void setTo(WebNode to) {
        this.to = to;
    }

    public WebNode getTo() {
        return to;
    }

    public void setSpider(Spider spider){
        this.spider = spider;
    }

    public Spider getSpider(){
        return this.spider;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public SpiderControllerActionEvent(Object source) {
        super(source);
    }
}

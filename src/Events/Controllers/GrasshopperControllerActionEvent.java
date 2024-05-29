package Events.Controllers;

import Entities.Grasshopper;
import Setting.WebNode;

import java.util.EventObject;

public class GrasshopperControllerActionEvent extends EventObject {
    private Grasshopper grasshopper;
    private WebNode from;
    private WebNode to;

    public Grasshopper getGrasshopper() {
        return grasshopper;
    }

    public void setGrasshopper(Grasshopper grasshopper) {
        this.grasshopper = grasshopper;
    }

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


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GrasshopperControllerActionEvent(Object source) {
        super(source);
    }
}

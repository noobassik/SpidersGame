package Events;

import Utils.Game;
import java.util.EventObject;

public class GameActionEvent extends EventObject {

    private Game game;

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return this.game;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameActionEvent(Object source) {
        super(source);
    }
}
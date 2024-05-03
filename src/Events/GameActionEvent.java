package Events;

import Entities.Insect;
import Utils.Game;

import java.util.ArrayList;
import java.util.EventObject;

public class GameActionEvent extends EventObject {

    private Game game;

    private ArrayList<Insect> createdInsects;

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return this.game;
    }

    public void setCreatedInsects(ArrayList<Insect> createdInsects){
        this.createdInsects =createdInsects;
    }
    public ArrayList<Insect> getCreatedInsects(){
        return this.createdInsects;
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
package Setting;

import Entities.Animal;
import Utils.Direction;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WebNode {
    private Web web;
    private Animal animal;

    private Point position;

    public WebNode(Web web, Point position) {
        this.web = web;
        this.position = position;
    }
    private final Map<Direction, WebNode> neighbours = new HashMap<>();

    public WebNode neighbour(Direction direction) {
        if (neighbours.containsKey(direction)) {
            return neighbours.get(direction);
        }
        return null;
    }

    void setNeighbour(Direction direction, WebNode neighbour) {
        if (neighbour != this && !isNeighbour(neighbour)) {
            neighbours.put(direction, neighbour);
            neighbour.setNeighbour(direction.opposite(), this);
        }
    }

    public boolean isNeighbour(WebNode other) {
        return neighbours.containsValue(other);
    }

    public boolean hasNeighbour(Direction direction) {
        return neighbours.containsKey(direction);
    }
    public Point getPosition() {
        return this.position;
    }

    public Web getWeb(){
        return this.web;
   }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        if (animal != null){
            this.animal.setWebNode(this);
        }
    }

    public boolean isEmpty() {
        return this.getAnimal() == null;
    }

    public boolean isValid(Point position) {
        return position.x < web.getSize() - 1 && position.y < web.getSize() - 1 && position.x >= 0 && position.y >= 0;
    }

    @Override
    public Object clone() {
        return new WebNode(this.web, this.position);
    }
}

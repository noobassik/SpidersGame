package Ui;

import Entities.*;
import Setting.*;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory {
    private final Map<WebNode, WebNodeWidget> webNodes = new HashMap<>();
    private final Map<Animal, AnimalWidget> animals = new HashMap<>();

    public static final int CELL_SIZE = 120;

    private boolean playerInWeb = false;

    public WebNodeWidget create(WebNode webNode){
        if (webNodes.containsKey(webNode))
            return webNodes.get(webNode);

        WebNodeWidget item = new WebNodeWidget();
        webNodes.put(webNode, item);

        Animal animal = webNode.getAnimal();
        if (animal != null){
            this.create(animal);
        }

        return item;
    }

    public WebNodeWidget getWidget(WebNode webNode){
        return webNodes.get(webNode);
    }

    public PlayerSpiderWidget getPlayerSpiderWidget(Spider playerSpider){
        return (PlayerSpiderWidget) animals.get(playerSpider);
    }

    public InsectWidget getWidget(Insect insect) {
        return (InsectWidget) animals.get(insect);
    }

    public AnimalWidget getWidget(Animal animal){
        return animals.get(animal);
    }

    public void remove(Animal animal){
        animals.remove(animal);
    }

    public AnimalWidget create(Animal animal){
        if (animal == null){
            throw new IllegalArgumentException();
        }
        if (animal instanceof Spider && playerInWeb){
            throw new RuntimeException("Can not be 2 players in 1 game");
        }
        if (animals.containsKey(animal)){
            return animals.get(animal);
        }

        AnimalWidget animalWidget = createWidget(animal);
        WebNodeWidget webNodeWidgetWidget = webNodes.get(animal.getWebNode());

        if (animalWidget != null && webNodeWidgetWidget != null){
            webNodeWidgetWidget.addItem(animalWidget);
            animals.put(animal, animalWidget);
        }

        return animalWidget;
    }

    private AnimalWidget createWidget(Animal animal){
        int size = 120;
        if (animal instanceof Insect){
            size = calculateSize(((Insect) animal).getSize());
        }
        return switch (animal.getClass().getName().split("\\.")[animal.getClass().getName().split("\\.").length - 1]) {
            case "Spider" -> new PlayerSpiderWidget((Spider) animal);
            case "Fly" -> new InsectWidget("images/fly.png", size, size);
            case "Mosquito" -> new InsectWidget("images/mosquito.png", size, size);
            case "Grasshopper" -> new InsectWidget("images/grasshopper.png", size, size);
            case "Wasp" -> new InsectWidget("images/wasp.png", size, size);
            default -> null;
        };
    }

    private int calculateSize(int size) {
        return switch (size){
            case 1 -> 40;
            case 2 -> 60;
            default -> 90;
        };
    }
}

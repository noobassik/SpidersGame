package Ui;

import Entities.*;
import Setting.*;

import java.util.HashMap;
import java.util.Map;

public class WidgetFactory {
    private final Map<WebNode, WebNodeWidget> webNodes = new HashMap<>();
    private final Map<Animal, AnimalWidget> animals = new HashMap<>();

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
        return switch (animal.getClass().getName().split("\\.")[animal.getClass().getName().split("\\.").length - 1]) {
            case "Spider" -> new PlayerSpiderWidget((Spider) animal);
            case "Fly" -> new FlyWidget((Fly)animal);
            case "Mosquito" -> new MosquitoWidget((Mosquito) animal);
            case "Grasshopper" -> new GrasshopperWidget((Grasshopper)animal);
            case "Wasp" -> new WaspWidget((Wasp)animal);
            default -> null;
        };
    }
}

package Setting;

import Entities.Animal;
import Entities.Insect;
import Entities.Spider;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class Web {
    private Animal animal;
    private LinkedList<WebNode> webNodes = new LinkedList<WebNode>();
    private final int size;
    private Spider playerSpider;
    private ArrayList<Spider> spiderList;
    private ArrayList<Insect> insectList;

    public ArrayList<Spider> getSpiderList() {
        return new ArrayList<>(this.spiderList);
    }

    public ArrayList<Insect> getInsectList() {
        return new ArrayList<>(this.insectList);
    }

    public Web(int size) {
        this.size = size;
        this.insectList = new ArrayList<>();
        this.spiderList = new ArrayList<>();
        createWebNodes();
    }

    public void createWebNodes() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                WebNode next = new WebNode(this, new Point(i, j));
                webNodes.add(next);
            }
        }
    }

    public LinkedList<WebNode> getWebNodes() {
        return new LinkedList<>(this.webNodes);
    }

    public int getSize() {
        return this.size;
    }

    public void setPlayerSpider(Spider spider) {
        if (this.playerSpider == null){
            this.playerSpider = spider;
        }
    }

    public Spider getPlayerSpider() {
        return this.playerSpider;
    }

    public WebNode getWebNode(Point point) {
        ListIterator<WebNode> iterator = (ListIterator<WebNode>) webNodes.iterator();
        while (iterator.hasNext()) {
            iterator.nextIndex();
            WebNode node = iterator.next();
            if (point.equals(node.getPosition())) {
                return node;
            }
        }
        return null;
    }

    public void removeInsect(Animal animal) {
        if (animal instanceof Insect) {
            insectList.remove(animal);
            setAnimalToWebNode(null, animal.getWebNode().getPosition());
        }
    }

    public void removeSpider(Animal animal) {
        if (animal instanceof Spider) {
            spiderList.remove(animal);
            setAnimalToWebNode(null, animal.getWebNode().getPosition());
        }
    }

    public void addSpider(Animal animal) {
        if (animal instanceof Spider) {
            spiderList.add((Spider) animal);
            setAnimalToWebNode(animal, animal.getWebNode().getPosition());
        }
    }

    public void addInsect(Animal animal) {
        if (animal instanceof Insect && animal.getWebNode() != null) {
            insectList.add((Insect) animal);
            setAnimalToWebNode(animal, animal.getWebNode().getPosition());
        }
    }

    public ArrayList<WebNode> getEmptyWebNodes() {
        ArrayList<WebNode> emptyWebCrossList = new ArrayList<>(this.webNodes);
        ListIterator<WebNode> iterator = (ListIterator<WebNode>) webNodes.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getAnimal() != null) {
                emptyWebCrossList.remove(webNodes.get(iterator.previousIndex()));
            }
        }
        return emptyWebCrossList;
    }

    private void setAnimalToWebNode(Animal animal, Point position) {
        for (WebNode webNode : webNodes){
            if (webNode.getPosition().equals(position)){
                if (webNode != null) {
                    webNode.setAnimal(animal);
                }
                if (animal != null && webNode.isEmpty()) {
                    animal.setWebNode(webNode);
                }
                break;
            }
        }
    }
}
package Setting;

import Entities.Animal;
import Entities.Insect;
import Entities.Spider;
import Factories.*;

import java.util.ArrayList;

public class Nature {
    private Web web;
    private Bot bot;
    private static final ArrayList<InsectFactory> factories = new ArrayList<>();

    static {
        factories.add(new MosquitoFactory());
        factories.add(new WaspFactory());
        factories.add(new FlyFactory());
        factories.add(new GrasshopperFactory());
    }

    public Nature(Web web, Bot bot) {
        this.web = web;
        this.bot = bot;
    }

    public void generateAnimals() {
        createInsects();
        Spider playerSpider = new Spider(web.getEmptyWebNodes().get(0), 50);
        web.addSpider(playerSpider);
        web.addSpider(new Spider(web.getEmptyWebNodes().get(1), 15));
        web.addSpider(new Spider(web.getEmptyWebNodes().get(2), 10));
        web.addSpider(new Spider(web.getEmptyWebNodes().get(3), 5));
        web.setPlayerSpider(playerSpider);
        bot.addSpiders(web.getSpiderList());
    }

    public ArrayList<Insect> createInsects() {
        ArrayList <Insect> createdInsects = new ArrayList<>();
        for (InsectFactory factory : factories) {
            ArrayList<WebNode> emptyWebNodes = this.web.getEmptyWebNodes();
            if (emptyWebNodes.isEmpty()) {
                return null;
            }
            Insect insect = factory.createInsect();
            if (insect != null) {
                placeAnimalInWeb(getRandomWebNode(emptyWebNodes), insect);
                this.web.addInsect(insect);
                insect.setWeb(web);
                createdInsects.add(insect);
            }
        }
        return createdInsects;
    }

    private void placeAnimalInWeb(WebNode webNode, Animal animal) {
        webNode.setAnimal(animal);
    }

    private WebNode getRandomWebNode(ArrayList<WebNode> webNodeList) {
        return webNodeList.get((int) (Math.random() * (webNodeList.size() - 1)));
    }
}

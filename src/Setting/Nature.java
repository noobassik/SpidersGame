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
        web.addSpider(new Spider(web.getEmptyWebNodes().get(0), 100, this.web));
        web.addSpider(new Spider(web.getEmptyWebNodes().get(1), 100, this.web));
        bot.addSpiders(web.getSpiderList());
    }

    private void placeAnimalInWeb(WebNode webNode, Animal animal) {
        webNode.setAnimal(animal);
        animal.setWebNode(webNode);
    }

    private WebNode getRandomWebNode(ArrayList<WebNode> webNodeList) {
        return webNodeList.get((int) (Math.random() * (webNodeList.size() - 1)));
    }

    public void createInsects() {
        for (InsectFactory factory : factories) {
            ArrayList<WebNode> emptyWebNodes = this.web.getEmptyWebNodes();
            if (emptyWebNodes.isEmpty()) {
                return;
            }
            Insect insect = factory.createInsect();
            if (insect != null) {
                placeAnimalInWeb(getRandomWebNode(emptyWebNodes), insect);
                this.web.addInsect(insect);
            }
        }
    }

//    private final Map insects = { 'waps': new Wasp }
//    public static final int maxAmountOfInsects = 5;
//    public void generateInsects(ArrayList<AbstractInsectFactory> factories) {
//        for (int i = 0; i < maxAmountOfBots; i++) {
//            addInsect(factories.random().create());
//        }
//    }

//    new Wasp(3);
//    Wasp.create();
//    new WaspFactory().create();
}

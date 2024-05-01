import Entities.Fly;
import Entities.Insect;
import Entities.Spider;
import Setting.Bot;
import Setting.Nature;
import Setting.Web;
import Utils.SpiderMoveStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class AnimalTest {
    @Test
    public void PlayerSpiderDeath() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Nature nature = new Nature(web, bot);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 0)), 100);
        Spider botSpider = new Spider(web.getWebNode(new Point(0, 1)), 100);
//        nature.generateAnimals();
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        web.addSpider(botSpider);
      //  playerSpider.die();
        Assert.assertEquals(web.getWebNode(new Point(0, 0)).getAnimal(), null);
        Assert.assertEquals(web.getSpiderList().contains(playerSpider), false);
    }

    @Test
    public void BotSpiderDeath() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Nature nature = new Nature(web, bot);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 0)), 100);
        Spider botSpider = new Spider(web.getWebNode(new Point(0, 1)), 100);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        web.addSpider(botSpider);
        web.setPlayerSpider(playerSpider);
       // botSpider.die();
        Assert.assertEquals(web.getWebNode(new Point(0, 1)).getAnimal(), null);
        Assert.assertEquals(web.getSpiderList().contains(botSpider), false);
    }

    @Test
    public void InsectDeath() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Nature nature = new Nature(web, bot);
        Insect insect = new Fly(web.getWebNode(new Point(0, 0)));
        // nature.createAnimal(insect);
        web.addInsect(insect);
       // insect.die();
        Assert.assertEquals(web.getWebNode(new Point(0, 0)).getAnimal(), null);
        Assert.assertEquals(web.getInsectList().contains(insect), false);
    }
}

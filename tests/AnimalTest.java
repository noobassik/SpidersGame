import Entities.Fly;
import Entities.Insect;
import Entities.Spider;
import Setting.Bot;
import Setting.Nature;
import Setting.Web;
import Setting.WebNode;
import Utils.Direction;
import Utils.Game;
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
        Game game = new Game(web, nature, bot);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 0)), 1);
        Spider botSpider = new Spider(web.getWebNode(new Point(0, 1)), 100);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        web.addSpider(botSpider);
        playerSpider.setGame(game);
        playerSpider.makeMove(Direction.north());
        Assert.assertEquals(web.getWebNode(new Point(0, 0)).getAnimal(), null);
        Assert.assertEquals(web.getSpiderList().contains(playerSpider), false);
    }

    @Test
    public void BotSpiderDeath() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Nature nature = new Nature(web, bot);
        Game game = new Game(web, nature, bot);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 0)), 100);
        Spider botSpider = new Spider(web.getWebNode(new Point(0, 1)), 1);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        web.addSpider(botSpider);
        web.setPlayerSpider(playerSpider);
        botSpider.setGame(game);
        botSpider.makeMove(Direction.west());
        Assert.assertEquals(web.getWebNode(new Point(0, 1)).getAnimal(), null);
        Assert.assertEquals(web.getSpiderList().contains(botSpider), false);
    }

    @Test
    public void InsectDeath() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Nature nature = new Nature(web, bot);
        Game game = new Game(web, nature, bot);
        Insect insect = new Fly(web.getWebNode(new Point(0, 0)));
        web.addInsect(insect);
        Spider playerSpider = new Spider(web.getWebNode(new Point(1, 0)), 10);
        web.addSpider(playerSpider);
        playerSpider.setGame(game);
        playerSpider.makeMove(Direction.north());
        Assert.assertEquals(web.getWebNode(new Point(0, 0)).getAnimal(), playerSpider);
        Assert.assertEquals(web.getInsectList().contains(insect), false);
    }
}

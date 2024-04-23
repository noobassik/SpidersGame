import Entities.Grasshopper;
import Entities.Spider;
import Setting.Bot;
import Setting.Web;
import Utils.Direction;
import Utils.SpiderMoveStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class GameTest {
    @Test
    public void BotEatsInsect() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 0)), 100, web);
        Spider botSpider = new Spider(web.getWebNode(new Point(0, 1)), 100, web);
        Grasshopper insect = new Grasshopper(web.getWebNode(new Point(0, 2)), web);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        web.addSpider(botSpider);
        web.addInsect(insect);
        bot.addSpiders(web.getSpiderList());
        bot.makeSmartMove();
        Assert.assertEquals(new Point(0, 2), botSpider.getWebNode().getPosition());
        Assert.assertEquals(100 - 1 + insect.getValue(), botSpider.getHealth());
        Assert.assertEquals(null, insect.getWebNode());
    }

    @Test
    public void BotOutOfHealth() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Spider botSpider = new Spider(web.getWebNode(new Point(0, 1)), 1, web);
        Grasshopper insect = new Grasshopper(web.getWebNode(new Point(1, 2)), web);
        web.addSpider(botSpider);
        web.addInsect(insect);
        bot.addSpiders(web.getSpiderList());
        bot.makeSmartMove();
//        Assert.assertEquals(new Point(0, 2), botSpider.getWebNode().getPosition());
        Assert.assertEquals(0, botSpider.getHealth());
        Assert.assertEquals(null, botSpider.getWebNode());
    }

    // x - вертикаль, у - горизонталь
    @Test
    public void MoveToNorthEdge() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 2)), 100, web);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        playerSpider.makeMove(Direction.north());
        Assert.assertEquals(99, playerSpider.getHealth());
        Assert.assertEquals(new Point(0, 2), playerSpider.getWebNode().getPosition());
    }

    @Test
    public void MoveToEastEdge() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 2)), 100, web);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        playerSpider.makeMove(Direction.east());
        Assert.assertEquals(99, playerSpider.getHealth());
        Assert.assertEquals(new Point(0, 2), playerSpider.getWebNode().getPosition());
    }

    @Test
    public void MoveToSouthEdge() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Spider playerSpider = new Spider(web.getWebNode(new Point(2, 0)), 100, web);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        playerSpider.makeMove(Direction.south());
        Assert.assertEquals(99, playerSpider.getHealth());
        Assert.assertEquals(new Point(2, 0), playerSpider.getWebNode().getPosition());
    }

    @Test
    public void MoveToWestEdge() {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Spider playerSpider = new Spider(web.getWebNode(new Point(2, 0)), 100, web);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        playerSpider.makeMove(Direction.west());
        Assert.assertEquals(99, playerSpider.getHealth());
        Assert.assertEquals(new Point(2, 0), playerSpider.getWebNode().getPosition());
    }
}

import Entities.Fly;
import Entities.Grasshopper;
import Entities.Spider;
import Setting.Web;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class WebNodeTest {
    @Test
    public void ReleasePlayerSpider() {
        Web web = new Web(3);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 2)), 100);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        playerSpider.getWebNode().setAnimal(null);
        playerSpider.setWebNode(null);
        Assert.assertEquals(null, web.getWebNode(new Point(0, 2)).getAnimal());
        Assert.assertEquals(null, playerSpider.getWebNode());
    }

    @Test
    public void ReleaseBotSpider() {
        Web web = new Web(3);
        Spider playerSpider = new Spider(web.getWebNode(new Point(0, 2)), 100);
        Spider botSpider = new Spider(web.getWebNode(new Point(0, 1)), 100);
        web.addSpider(playerSpider);
        web.setPlayerSpider(playerSpider);
        web.addSpider(botSpider);
        botSpider.getWebNode().setAnimal(null);
        botSpider.setWebNode(null);
        Assert.assertEquals(null, web.getWebNode(new Point(0, 1)).getAnimal());
        Assert.assertEquals(null, botSpider.getWebNode());
    }

    @Test
    public void ReleaseInsect() {
        Web web = new Web(3);
        Grasshopper grasshopper = new Grasshopper(web.getWebNode(new Point(0, 2)));
        web.addInsect(grasshopper);
        grasshopper.getWebNode().setAnimal(null);
        grasshopper.setWebNode(null);
        Assert.assertEquals(null, web.getWebNode(new Point(0, 2)).getAnimal());
        Assert.assertEquals(null, grasshopper.getWebNode());
    }
    // при добавлении насекомого в занятую ячейку, добавление не происходит и насекомому ничего не присваивается, никуда не добавляется
    @Test
    public void InitializeAnimalInOccupiedWebNode() {
        Web web = new Web(3);
        Grasshopper grasshopper = new Grasshopper(web.getWebNode(new Point(0, 2)));
        Fly fly = new Fly(web.getWebNode(new Point(0, 2)));
        web.addInsect(grasshopper);
        web.addInsect(fly); // муха не добавляется никуда и поля null
        Assert.assertEquals(grasshopper, web.getWebNode(new Point(0, 2)).getAnimal());
        Assert.assertEquals(web.getWebNode(new Point(0, 2)), grasshopper.getWebNode());
        Assert.assertEquals(null, fly.getWebNode());
    }


}

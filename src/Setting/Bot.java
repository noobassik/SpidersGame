package Setting;

import Entities.Spider;
import Utils.Direction;
import Utils.SpiderMoveStrategy;

import java.util.ArrayList;

public class Bot {
    private Web web;
    private SpiderMoveStrategy spiderMoveStrategy;
    private ArrayList<Spider> botSpiderList = new ArrayList<>();

    public Bot(SpiderMoveStrategy spiderMoveStrategy, Web web) {
        this.spiderMoveStrategy = spiderMoveStrategy;
        this.web = web;
    }

    public ArrayList<Spider> getBotSpiderList() {
        return new ArrayList<>(this.botSpiderList);
    }

    public void addSpiders(ArrayList<Spider> spiders) {
        for (Spider spider : spiders) {
            if (!this.botSpiderList.contains(spider) && this.web.getPlayerSpider() != spider) {
                botSpiderList.add(spider);
            }
        }
    }

    public void addSpider(Spider spider) {
        if (!this.botSpiderList.contains(spider)) {
            botSpiderList.add(spider);
        }
    }

    // TODO: если не будет насекомых, то direction == null
    public synchronized void moveAllBots() {
        try {
            for (Spider spider : new ArrayList<>(this.botSpiderList)) {
                Direction direction = spiderMoveStrategy.findNearestInsect(spider.getWebNode());
                if (direction != null) {
                    spider.makeMove(direction);
                    Thread.sleep(50);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBotOwnsSpider(Spider spider) {
        return botSpiderList.contains(spider);
    }

    public void deleteSpiderFromList(int index) {
        this.botSpiderList.remove(index);
    }

    public void deleteSpiderFromList(Spider spider) {
        this.botSpiderList.remove(spider);
    }
}

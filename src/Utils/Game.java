package Utils;

import Entities.Spider;
import Setting.Bot;
import Setting.Nature;
import Setting.Web;

import java.util.Random;

public class Game {
    private Nature nature;
    private Web web;
    private Bot bot;

    public Game(Nature nature, Bot bot, Web web) {
        this.nature = nature;
        this.bot = bot;
        this.web = web;
    }

    // TODO: нужны ли вообще обсерверы, листенеры? - да
    public void startGame() {
        this.nature.generateAnimals();
        changePlayerSpider(web.getSpiderList().get(0));

        //this.web.getPlayerSpdier().makeMove(Direction.east());
        this.bot.makeSmartMove();
    }

    public void endGame() {

    }

    public Bot getBot() {
        return this.bot;
    }

    public void changePlayerSpider(Spider spider) {
        int index = new Random().nextInt(bot.getBotSpiderList().size());
        web.setPlayerSpider(bot.getBotSpiderList().get(index));
        bot.deleteSpiderFromList(index);
    }
}


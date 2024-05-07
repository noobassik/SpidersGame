package Utils;

import Entities.Insect;
import Entities.Spider;
import Events.*;
import Setting.Bot;
import Setting.Nature;
import Setting.Web;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Nature nature;
    private Web web;
    private Bot bot;

    public Game() {
        Web web = new Web(6);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Nature nature = new Nature(web, bot);
        this.web = web;
        this.nature = nature;
        this.bot = bot;
        startGame();
    }

    public void startGame() {
        this.nature.generateAnimals();
        for (Spider spider : this.web.getSpiderList()){
            spider.setGame(this);
        }
        web.getPlayerSpider().addPlayerSpiderActionListener(new PlayerSpiderObserver());

        for (Spider botSpider : bot.getBotSpiderList()){
            botSpider.addBotSpiderActionListener(new BotSpiderObserver());
        }

        for (Insect insect : this.web.getInsectList()){
            insect.addInsectActionListener(new InsectObserver());
        }
    }

    public void endGame() {
        System.out.println("The end");
        fireGameEnded();
    }

    public Web getWeb(){
        return this.web;
    }

    public Bot getBot() {
        return this.bot;
    }
    // TODO: это точно не public
    private void changePlayerSpider() {
        int index = new Random().nextInt(bot.getBotSpiderList().size());
        web.setPlayerSpider(bot.getBotSpiderList().get(index));
        bot.deleteSpiderFromList(index);
    }

    private void disappearInsects() {
        for (Insect insect : web.getInsectList()) {
            insect.disappearFromWeb();
        }
        for (Insect insect : insectsToRemove){
            web.removeInsect(insect);
        }
        insectsToRemove.clear();
    }

    private void generateInsects(){
        ArrayList<Insect> createdInsects = nature.createInsects();
        for (Insect insect : createdInsects){
            insect.addInsectActionListener(new InsectObserver());
        }
        fireInsectsAppeared(createdInsects);
    }

    private ArrayList<Spider> spidersToRemove = new ArrayList<>();
    private ArrayList<Insect> insectsToRemove = new ArrayList<>();

    // --------------------------- Game Observers ---------------------------------------
    private class PlayerSpiderObserver implements PlayerActionListener {
        @Override
        public void playerDied(PlayerActionEvent event) {
            if (web.getSpiderList().size() == 0){
                endGame();
            } else {
                spidersToRemove.add(event.getPlayerSpider());
                changePlayerSpider();
            }
        }

        @Override
        public void playerMoved(PlayerActionEvent event) {
            bot.moveAllBots(); // Если сходил паук-игрок, после него должны сходить пауки-боты
            disappearInsects(); // Пропадают насекомые
            generateInsects();
            fireGameStepHappened();
        }
    }

    private class BotSpiderObserver implements BotSpiderActionListener {

        @Override
        public void botMoved(BotSpiderActionEvent event) {

        }

        @Override
        public void botDied(BotSpiderActionEvent event) {
            spidersToRemove.add(event.getBotSpider());
        }
    }

    private class InsectObserver implements InsectActionListener {

        @Override
        public void insectDied(InsectActionEvent event) {
            insectsToRemove.add(event.getInsect());
        }

        @Override
        public void insectWasEaten(InsectActionEvent event) {
            insectsToRemove.add(event.getInsect());
            web.removeInsects(insectsToRemove);
            insectsToRemove.clear();
        }
    }
    // --------------------------- Game Listeners ---------------------------------------

    private ArrayList<GameActionListener> gameListeners = new ArrayList<>();

    public void addGameActionListener(GameActionListener listener){
        this.gameListeners.add(listener);
    }

    public void removeGameActionListener(GameActionListener listener){
        this.gameListeners.remove(listener);
    }

    protected void fireGameEnded(){
        for(GameActionListener listener : this.gameListeners){
            GameActionEvent event = new GameActionEvent(listener);
            event.setGame(this);
            listener.gameEnded(event);
        }
    }

    protected void fireGameStepHappened(){
        for(GameActionListener listener : gameListeners){
            GameActionEvent event = new GameActionEvent(listener);
            event.setGame(this);
            listener.gameStepHappened(event);
        }
    }

    protected void fireInsectsAppeared(ArrayList<Insect> createdInsects){
        for (GameActionListener listener : gameListeners){
            GameActionEvent event = new GameActionEvent(listener);
            event.setCreatedInsects(createdInsects);
            event.setGame(this);
            listener.insectsCreated(event);
        }
    }

}



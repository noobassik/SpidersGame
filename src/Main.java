import Setting.Bot;
import Setting.Nature;
import Setting.Web;
import Utils.Game;
import Utils.SpiderMoveStrategy;

public class Main {
    public static void main(String[] args) {
        Web web = new Web(3);
        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
        Bot bot = new Bot(spiderMoveStrategy, web);
        Nature nature = new Nature(web, bot);
        Game game = new Game(nature, bot, web);
        game.startGame();

    }
}
import Events.GameActionEvent;
import Events.GameActionListener;
import Ui.WebWidget;
import Ui.WidgetFactory;
import Utils.Game;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//        Web web = new Web(3);
//        SpiderMoveStrategy spiderMoveStrategy = new SpiderMoveStrategy(web);
//        Bot bot = new Bot(spiderMoveStrategy, web);
//        Nature nature = new Nature(web, bot);
//        Game game = new Game(nature, bot, web);
//        game.startGame();
        SwingUtilities.invokeLater(GamePanel::new);
    }

    static class GamePanel extends JFrame {
        private Game game;
        public WidgetFactory widgetFactory;

        public GamePanel() throws HeadlessException {
            setVisible(true);

            widgetFactory = new WidgetFactory();
            game = new Game();

            game.addGameActionListener(new GameController());

            JPanel content = (JPanel) this.getContentPane();
            content.add(new WebWidget(game.getWeb(), widgetFactory, game));

            widgetFactory.getPlayerSpiderWidget(game.getWeb().getPlayerSpider()).requestFocus();

            pack();
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        private final class GameController implements GameActionListener {

            @Override
            public void gameEnded(GameActionEvent event) {
                JOptionPane.showMessageDialog(GamePanel.this, "Игра закончена");
                System.exit(0);
            }

            @Override
            public void gameStepHappened(GameActionEvent event) {

            }

            @Override
            public void insectsCreated(GameActionEvent event) {

            }

        }
    }
}
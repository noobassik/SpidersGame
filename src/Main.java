import Events.GameActionEvent;
import Events.GameActionListener;
import Ui.WebWidget;
import Ui.WidgetFactory;
import Utils.Game;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
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
            setLocationRelativeTo(null);
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

            @Override
            public void playerChanged(GameActionEvent event) {

            }

        }
    }
}
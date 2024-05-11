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

        private final JLabel stepsMadeLabel;
        private final JLabel insectsEatenLabel;

        private int stepsMade = 0;
        private int insectsEaten = 0;
        private Game game;
        public WidgetFactory widgetFactory;

        public GamePanel() throws HeadlessException {
            setVisible(true);

            widgetFactory = new WidgetFactory();
            game = new Game();

            game.addGameActionListener(new GameController());

            JPanel content = (JPanel) this.getContentPane();
            // Добавляем JLabel'ы для сводки
            stepsMadeLabel = new JLabel("Шагов сделано: " + 0);
            insectsEatenLabel = new JLabel("Насекомых съедено: " + 0);

            JPanel summaryPanel = new JPanel();
            summaryPanel.add(stepsMadeLabel);
            summaryPanel.add(new JLabel("         "));
            summaryPanel.add(insectsEatenLabel);
            content.add(summaryPanel, BorderLayout.NORTH);

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
                stepsMade++;
                stepsMadeLabel.setText("Шагов сделано: " + stepsMade);
                insectsEatenLabel.setText("Насекомых съедено: " + insectsEaten);
            }

            @Override
            public void insectsCreated(GameActionEvent event) {

            }

            @Override
            public void playerChanged(GameActionEvent event) {

            }

            @Override
            public void playerAteInsect(GameActionEvent event) {
                insectsEaten++;
            }

        }
    }
}
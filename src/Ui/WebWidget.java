package Ui;

import Entities.Animal;
import Entities.Insect;
import Entities.Spider;
import Events.*;
import Events.Controllers.AnimalControllerActionEvent;
import Events.Controllers.AnimalControllerActionListener;
import Events.Controllers.SpiderControllerActionEvent;
import Events.Controllers.SpiderControllerActionListener;
import Setting.*;
import Utils.Game;

import javax.swing.*;
import java.awt.*;

public class WebWidget extends JPanel {
    private final Web web;
    private final WidgetFactory widgetFactory;
    private final Game game;

    public WebWidget(Web web, WidgetFactory widgetFactory, Game game) {
        this.web = web;
        this.widgetFactory = widgetFactory;
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fillWeb();
        subscribeOnEntities();
    }

    private void fillWeb() {
        for (int i = 0; i < web.getSize(); ++i) {
            JPanel row = createRow(i);
            add(row);
        }
    }

    private JPanel createRow(int rowIndex) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 0; i < web.getSize(); i++) {
            WebNode webNode = web.getWebNode(new Point(rowIndex, i));
            WebNodeWidget webNodeWidget = widgetFactory.create(webNode);

            row.add(webNodeWidget);
        }
        return row;
    }

    private void subscribeOnEntities() {
//        web.getPlayerSpider().addAnimalControllerActionListener(new AnimalController());
//        web.getPlayerSpider().addSpiderControllerActionListener(new SpiderController());

        for (Spider bot : web.getSpiderList()){
            bot.addAnimalControllerActionListener(new AnimalController());
            bot.addSpiderControllerActionListener(new SpiderController());
        }

        for (Insect insect : web.getInsectList()){
            insect.addAnimalControllerActionListener(new AnimalController());
        }

        game.addGameActionListener(new GameStepObserver());
    }

    private class AnimalController implements AnimalControllerActionListener{
        @Override
        public void animalDied(AnimalControllerActionEvent event) {
            Animal animal = event.getAnimal();
            AnimalWidget animalWidget = widgetFactory.getWidget(animal);
            WebNodeWidget webNodeWidget = widgetFactory.getWidget(event.getWebNode());

            webNodeWidget.removeItem(animalWidget);
            widgetFactory.remove(animal);
        }
    }

    private class SpiderController implements SpiderControllerActionListener{
        @Override
        public void spiderMoved(SpiderControllerActionEvent event) {
            AnimalWidget spiderWidget = widgetFactory.getWidget(event.getSpider());
            WebNodeWidget webNodeWidgetFrom = widgetFactory.getWidget(event.getFrom());
            WebNodeWidget webNodeWidgetTo = widgetFactory.getWidget(event.getTo());

            webNodeWidgetFrom.removeItem(spiderWidget);
            webNodeWidgetTo.addItem(spiderWidget);
        }

        @Override
        public void spiderDied(SpiderControllerActionEvent event) {
            AnimalWidget spiderWidget = widgetFactory.getWidget(event.getSpider());
            WebNodeWidget webNodeWidgetFrom = widgetFactory.getWidget(event.getFrom());

            webNodeWidgetFrom.removeItem(spiderWidget);
        }

    }

    private class GameStepObserver implements GameActionListener{

        @Override
        public void gameEnded(GameActionEvent event) {
        }

        @Override
        public void gameStepHappened(GameActionEvent event) {
            revalidate();
            repaint();
        }

        @Override
        public void insectsCreated(GameActionEvent event) {
            for (Insect insect : event.getCreatedInsects()){
                widgetFactory.create(insect);

                insect.addAnimalControllerActionListener(new AnimalController());
                System.out.println("Insect created at " + insect.getWebNode().getPosition().getX() + insect.getWebNode().getPosition().getY());
            }
        }
    }
}


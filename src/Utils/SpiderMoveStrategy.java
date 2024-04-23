package Utils;

import Entities.Insect;
import Setting.Web;
import Setting.WebNode;

import java.awt.*;
import java.util.ArrayList;

public class SpiderMoveStrategy {
    private Web web;

    public SpiderMoveStrategy(Web web) {
        this.web = web;
    }

    public Direction findNearestInsect(WebNode startWebNode) {
        if (startWebNode == null || this.web.getInsectList().isEmpty()) return null;

        Point nearestPoint = this.web.getInsectList().get(0).getWebNode().getPosition();
        double minDistance = startWebNode.getPosition().distance(nearestPoint);

        for (Insect insect : this.web.getInsectList()) {
            Point point = insect.getWebNode().getPosition();
            double distance = startWebNode.getPosition().distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }
        Direction direction = getDirection(startWebNode.getPosition(), nearestPoint);
        return direction;
    }

    private Direction getDirection(Point start, Point nearestPoint) {
        ArrayList<Direction> directionList = new ArrayList<>();
        if (nearestPoint.y - start.y > 0) {
            directionList.add(Direction.east());
        }
        if (nearestPoint.y - start.y < 0) {
            directionList.add(Direction.west());
        }
        if (nearestPoint.x - start.x > 0) {
            directionList.add(Direction.south());
        }
        if (nearestPoint.x - start.x < 0) {
            directionList.add(Direction.north());
        }
        int randomIndex = (int) (Math.random() * directionList.size());
        return directionList.get(randomIndex);
    }
}

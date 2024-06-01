package Entities;

import Events.Controllers.GrasshopperControllerActionEvent;
import Events.Controllers.GrasshopperControllerActionListener;
import Setting.WebNode;

import java.util.ArrayList;
import java.util.Random;

public class Grasshopper extends Insect {
    public static int size = Insect.size.MIDDLE.ordinal();
    public double probabilityToDisappear = calculateProbabilityToDisappear();
    public double probabilityToAppear = calculateProbabilityToAppear();

    private int attempts = 0;

    public Grasshopper(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 4);
    }

    @Override
    protected double getProbabilityToDisappear() {
        return this.probabilityToDisappear;
    }

    @Override
    public double getProbabilityToAppear() {
        return this.probabilityToAppear;
    }

    @Override
    protected double calculateProbabilityToDisappear() {
        return 0.35 * (size + 1) - (double)this.attempts / 10;
    }

    @Override
    protected double calculateProbabilityToAppear() {
        return 0.5 / (size + 1);
    }

    @Override
    public void disappearFromWeb() {
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= probabilityToDisappear){
            die();
        } else {
            attempts++;
            Random random = new Random();
            ArrayList<WebNode> emptyWebNodes = this.web.getEmptyWebNodes();
            int randomIndex = random.nextInt(emptyWebNodes.size());
            this.webNode.setAnimal(null);
            WebNode oldWebNode = this.webNode;
            WebNode newWebNode = emptyWebNodes.get(randomIndex);
            this.setWebNode(newWebNode);
            this.webNode.setAnimal(this);
            fireGrasshopperJumpedController(oldWebNode, newWebNode);
        }

    }

    private ArrayList<GrasshopperControllerActionListener> grasshopperListeners = new ArrayList<>();

    public void addGrasshopperActionListener(GrasshopperControllerActionListener listener) {
        grasshopperListeners.add(listener);
    }

    public void removeGrasshopperActionListener(GrasshopperControllerActionListener listener) {
        grasshopperListeners.remove(listener);
    }

    protected void fireGrasshopperJumpedController(WebNode from, WebNode to){
        for(GrasshopperControllerActionListener listener : grasshopperListeners){
            GrasshopperControllerActionEvent event = new GrasshopperControllerActionEvent(listener);
            event.setTo(to);
            event.setFrom(from);
            event.setGrasshopper(this);
            listener.jumpedTo(event);
        }
    }
}

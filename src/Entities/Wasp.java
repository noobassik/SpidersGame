package Entities;

import Setting.WebNode;

public class Wasp extends Insect {
    public static int size = Insect.size.BIG.ordinal();
    public double probabilityToDisappear = calculateProbabilityToDisappear();
    public double probabilityToAppear = calculateProbabilityToAppear();
    public static double probabilityToBite = 0.5;

    public Wasp(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 5);
    }

    public void sting(Spider spider) {
        double probability = Math.round(Math.random()*10)/10.0;
        if (probability <= probabilityToBite){
            int health = (int) Math.round(Math.random()*super.getValue());
            spider.getBitten(health);
        }
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
        return 0.3 * (size + 1);
    }

    @Override
    protected double calculateProbabilityToAppear() {
        return 0.5 / (size + 1);
    }
}

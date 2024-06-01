package Entities;

import Setting.WebNode;

public class Mosquito extends Insect {
    public static int size = Insect.size.SMALL.ordinal();
    public double probabilityToDisappear = calculateProbabilityToDisappear();
    public double probabilityToAppear = calculateProbabilityToAppear();

    public Mosquito(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 2);
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
        return 0.1 * (size + 1);
    }

    @Override
    protected double calculateProbabilityToAppear() {
        return 0.5 / (size + 1);
    }
}

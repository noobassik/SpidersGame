package Entities;

import Setting.WebNode;

public class Fly extends Insect {
    public double probabilityToDisappear = calculateProbabilityToDisappear();
    public double probabilityToAppear = calculateProbabilityToAppear();

    public Fly(WebNode webNode, int size) {
        super(webNode);
        super.setValue(super.getValue() + 3);
        super.size = size;
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
        return 0.2 * (size + 1);
    }

    @Override
    protected double calculateProbabilityToAppear() {
        return 0.5 / (size + 1);
    }
}

package Entities;

import Setting.WebNode;

public class Mosquito extends Insect {
    public static int size = Insect.size.SMALL.ordinal();
    public static double probabilityToDisappear = 0.1 * (size + 1); // TODO: вынести формулу в метод
    public static double probabilityToAppear = 0.5 / (size + 1);

    public Mosquito(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 2);
    }
    @Override
    protected double getProbabilityToDisappear() {
        return probabilityToDisappear;
    }
}

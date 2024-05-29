package Entities;

import Setting.WebNode;

public class Fly extends Insect {
    public static int size = Insect.size.SMALL.ordinal();
    public static double probabilityToDisappear = 0.2 * (size + 1);
    public static double probabilityToAppear = 0.5 / (size + 1);

    public Fly(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 3);
    }

    @Override
    protected double getProbabilityToDisappear() {
        return probabilityToDisappear;
    }
}

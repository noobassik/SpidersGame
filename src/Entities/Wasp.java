package Entities;

import Setting.WebNode;

public class Wasp extends Insect {
    public static int size = Insect.size.BIG.ordinal();
    public static double probabilityToDisappear = 0.3 * (size + 1);
    public static double probabilityToAppear = 0.5 / (size + 1);

    public Wasp(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 5);
    }

    @Override
    protected double getProbabilityToDisappear() {
        return probabilityToDisappear;
    }
}

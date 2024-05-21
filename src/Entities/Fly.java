package Entities;

import Setting.WebNode;

public class Fly extends Insect {
    public static final int size = Animal.size.SMALL.ordinal();
    public static final double probabilityToDisappear = 0.2 * (size + 1);
    public static final double probabilityToAppear = 0.5 / (size + 1);

    public Fly(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 3);
    }

    @Override
    public void disappearFromWeb() {
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= probabilityToDisappear){
            die();
        }
    }
}

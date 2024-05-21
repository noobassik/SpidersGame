package Entities;

import Setting.WebNode;

public class Wasp extends Insect {
    public static final int size = Animal.size.BIG.ordinal();
    public static final double probabilityToDisappear = 0.3 * (size + 1);
    public static final double probabilityToAppear = 0.5 / (size + 1);

    public Wasp(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 5);
    }
    @Override
    public void disappearFromWeb() {
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= probabilityToDisappear){
            die();
        }
    }
}

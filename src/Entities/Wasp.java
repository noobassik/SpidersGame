package Entities;

import Setting.WebNode;

public class Wasp extends Insect {
    public static final double probabilityToDisappear = 0.3;
    public static final double probabilityToAppear = 0.5;

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

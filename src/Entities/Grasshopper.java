package Entities;

import Setting.WebNode;

public class Grasshopper extends Insect {
    public static final double probabilityToDisappear = 0.35;
    public static final double probabilityToAppear = 0.5;

    public Grasshopper(WebNode webNode) {
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

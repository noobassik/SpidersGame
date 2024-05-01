package Entities;

import Setting.Web;
import Setting.WebNode;

public class Mosquito extends Insect {
    public static final double probabilityToDisappear = 0.1;
    public static final double probabilityToAppear = 0.5;

    public Mosquito(WebNode webNode) {
        super(webNode);
        super.setValue(super.getValue() + 1);
    }

    @Override
    public void disappearFromWeb() {
        double probability = Math.round(Math.random() * 10)/10.0;
        if (probability <= probabilityToDisappear){
            die();
        }
    }
}

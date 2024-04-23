package Entities;

import Setting.Web;
import Setting.WebNode;

public class Mosquito extends Insect {
    public static final double probabilityToDisappear = 0.1;
    public static final double probabilityToAppear = 0.5;

    public Mosquito(WebNode webNode, Web web) {
        super(webNode, web);
        super.setValue(super.getValue() + 1);
    }
}

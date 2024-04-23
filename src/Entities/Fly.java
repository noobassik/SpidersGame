package Entities;

import Setting.Web;
import Setting.WebNode;

public class Fly extends Insect {
    public static final double probabilityToDisappear = 0.2;
    public static final double probabilityToAppear = 0.5;

    public Fly(WebNode webNode, Web web) {
        super(webNode, web);
        super.setValue(super.getValue() + 2);
    }
}

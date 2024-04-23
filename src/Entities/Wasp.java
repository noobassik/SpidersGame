package Entities;

import Setting.Web;
import Setting.WebNode;

public class Wasp extends Insect {
    public static final double probabilityToDisappear = 0.3;
    public static final double probabilityToAppear = 0.5;

    public Wasp(WebNode webNode, Web web) {
        super(webNode, web);
        super.setValue(super.getValue() + 3);
    }
}

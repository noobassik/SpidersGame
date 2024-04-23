package Entities;

import Setting.Web;
import Setting.WebNode;

public class Grasshopper extends Insect {
    public static final double probabilityToDisappear = 0.35;
    public static final double probabilityToAppear = 0.5;

    public Grasshopper(WebNode webNode, Web web) {
        super(webNode, web);
        super.setValue(super.getValue() + 3);
    }
}

package Entities;

import Setting.Web;
import Setting.WebNode;

public abstract class Insect extends Animal {
    protected int value;

    public Insect(WebNode webNode, Web web) {
        super(webNode, web);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

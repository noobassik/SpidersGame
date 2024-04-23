package Entities;

import Setting.Web;
import Setting.WebNode;

public abstract class Animal {
    protected Web web;
    protected WebNode webNode;

    public Animal(WebNode webNode, Web web) {
        if (web.getWebNode(webNode.getPosition()).isEmpty()){
            this.webNode = webNode;
            webNode.setAnimal(this);
        }
        this.web = web;
    }

    public WebNode getWebNode() {
        return this.webNode;
    }

    public void setWebNode(WebNode newNode) {
        this.webNode = newNode;
    }

    protected void die() {
        this.web.removeInsect(this);
        this.web = null;
        this.webNode.setAnimal(null);
        this.setWebNode(null);
    }
}

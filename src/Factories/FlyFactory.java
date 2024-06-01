package Factories;

import Entities.Fly;
import Entities.Insect;

public class FlyFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Fly fly = new Fly(null);
        if (Math.random() < fly.getProbabilityToAppear()) {
            return fly;
        }
        return null;
    }
}

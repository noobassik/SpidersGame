package Factories;

import Entities.Fly;
import Entities.Insect;

public class FlyFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        if (Math.random() < Fly.probabilityToAppear) {
            return new Fly(null, null);
        }
        return null;
    }
}

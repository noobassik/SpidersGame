package Factories;

import Entities.Insect;
import Entities.Wasp;

public class WaspFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        if (Math.random() < Wasp.probabilityToAppear) {
            return new Wasp(null, null);
        }
        return null;
    }
}

package Factories;

import Entities.Insect;
import Entities.Wasp;

public class WaspFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Wasp wasp = new Wasp(null);
        if (Math.random() < wasp.getProbabilityToAppear()) {
            return wasp;
        }
        return null;
    }
}

package Factories;

import Entities.Grasshopper;
import Entities.Insect;

public class GrasshopperFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Grasshopper grasshopper = new Grasshopper(null);
        if (Math.random() < grasshopper.getProbabilityToAppear()) {
            return grasshopper;
        }
        return null;
    }
}

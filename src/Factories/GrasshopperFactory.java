package Factories;

import Entities.Grasshopper;
import Entities.Insect;

public class GrasshopperFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        if (Math.random() < Grasshopper.probabilityToAppear) {
            return new Grasshopper(null, null);
        }
        return null;
    }
}

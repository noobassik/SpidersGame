package Factories;

import Entities.Grasshopper;
import Entities.Insect;

import java.util.Random;

public class GrasshopperFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Random random = new Random();
        int randomDigit = random.nextInt(3) + 1;
        Grasshopper grasshopper = new Grasshopper(null, randomDigit);
        if (Math.random() < grasshopper.getProbabilityToAppear()) {
            return grasshopper;
        }
        return null;
    }
}

package Factories;

import Entities.Insect;
import Entities.Wasp;

import java.util.Random;

public class WaspFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Random random = new Random();
        int randomDigit = random.nextInt(3) + 1;
        Wasp wasp = new Wasp(null, randomDigit);
        if (Math.random() < wasp.getProbabilityToAppear()) {
            return wasp;
        }
        return null;
    }
}

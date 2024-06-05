package Factories;

import Entities.Fly;
import Entities.Insect;

import java.util.Random;

public class FlyFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Random random = new Random();
        int randomDigit = random.nextInt(3) + 1;
        Fly fly = new Fly(null, randomDigit);
        if (Math.random() < fly.getProbabilityToAppear()) {
            return fly;
        }
        return null;
    }
}

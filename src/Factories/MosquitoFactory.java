package Factories;

import Entities.Insect;
import Entities.Mosquito;

import java.util.Random;

public class MosquitoFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Random random = new Random();
        int randomDigit = random.nextInt(3) + 1;
        Mosquito mosquito = new Mosquito(null, randomDigit);
        if (Math.random() < mosquito.getProbabilityToAppear()) {
            return mosquito;
        }
        return null;
    }
}

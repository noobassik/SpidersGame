package Factories;

import Entities.Insect;
import Entities.Mosquito;

public class MosquitoFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        Mosquito mosquito = new Mosquito(null);
        if (Math.random() < mosquito.getProbabilityToAppear()) {
            return mosquito;
        }
        return null;
    }
}

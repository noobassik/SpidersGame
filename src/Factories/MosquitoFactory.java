package Factories;

import Entities.Insect;
import Entities.Mosquito;

public class MosquitoFactory implements InsectFactory {
    @Override
    public Insect createInsect() {
        if (Math.random() < Mosquito.probabilityToAppear) {
            return new Mosquito(null);
        }
        return null;
    }
}

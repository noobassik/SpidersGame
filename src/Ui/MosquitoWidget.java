package Ui;

import Entities.Mosquito;
import static Ui.WebNodeWidget.CELL_SIZE;

public class MosquitoWidget extends InsectWidget{

    private Mosquito mosquito;

    public MosquitoWidget(Mosquito mosquito){
        super("images/mosquito.png", (int) (CELL_SIZE/2.6), (int) (CELL_SIZE/1.58));
        this.mosquito = mosquito;
    }
}

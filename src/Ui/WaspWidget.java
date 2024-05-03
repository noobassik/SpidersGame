package Ui;

import Entities.Wasp;

import static Ui.WebNodeWidget.CELL_SIZE;

public class WaspWidget extends InsectWidget{

    private final Wasp wasp;

    public WaspWidget(Wasp wasp){
        super("images/wasp.png", (int) (CELL_SIZE/2.6), (int) (CELL_SIZE/1.58));
        this.wasp = wasp;
    }
}

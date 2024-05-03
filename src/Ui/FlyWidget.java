package Ui;

import Entities.Fly;

import static Ui.WebNodeWidget.CELL_SIZE;

public class FlyWidget extends InsectWidget{

    private final Fly fly;

    public FlyWidget(Fly fly){
        super("images/fly.png", (int) (CELL_SIZE/2.6), (int) (CELL_SIZE/1.58));
        this.fly = fly;
    }

}

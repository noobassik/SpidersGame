package Ui;

import Entities.Grasshopper;


import java.awt.*;

import static Ui.WebNodeWidget.CELL_SIZE;

public class GrasshopperWidget extends InsectWidget {

    private final Grasshopper grasshopper;

    public GrasshopperWidget(Grasshopper grasshopper) {
        super("images/grasshopper.png", CELL_SIZE/2, (int) (CELL_SIZE/1.33));
        this.grasshopper = grasshopper;
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(CELL_SIZE/2, (int) (CELL_SIZE/1.33));
    }
}

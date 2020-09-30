package modules;

import java.awt.*;

public class MyGridBagConstraints extends GridBagConstraints {
    public MyGridBagConstraints(int fill, int gridX, int gridY) {
        super.fill = fill;
        super.gridx = gridX;
        super.gridy = gridY;
    }
}

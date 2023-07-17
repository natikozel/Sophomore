package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import listeners.Observer;

public class MyLabel extends Label implements Observer {

    private boolean flag = false;

    public MyLabel(String text) {
        super(text);
        super.setVisible(false);
        super.setTextFill(Color.DARKGREEN);
        super.setAlignment(Pos.CENTER);
        super.setFont(Font.font("Arial Italic", 13));
    }

    @Override
    public void update() {
        if (!flag) {
            super.setVisible(true);
            flag = true;
        }
    }
}

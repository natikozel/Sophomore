package model;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import listeners.Observer;

public class MyButton extends Button implements Observer {

    private boolean flag = false;

    public MyButton(String text) {
        super(text);
        super.setVisible(false);
        super.setMinSize(100, 50);    }

    @Override
    public void update() {
        if (!flag) {
            super.setVisible(true);
            flag = true;
        }
    }
}

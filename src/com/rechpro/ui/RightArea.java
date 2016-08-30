package src.com.rechpro.ui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RightArea {

	protected void getRightArea(BorderPane root)
    {
        HBox hbox = new HBox();

        VBox vbox = new VBox(50);
        vbox.setPadding(new Insets(0, 20, 0, 20));
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(new Text("Additional Info 1"),
                new Text("Additional Info 2"), new Text("Additional Info 3"));
        hbox.getChildren().addAll(new Separator(Orientation.VERTICAL), vbox);

        root.setRight(hbox);
    }

}

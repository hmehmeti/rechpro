package com.rechpro.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Footer {

    protected void getFooter(BorderPane root)
    {
        VBox vbox = new VBox();

        HBox hbox = new HBox(20);
        hbox.setPadding(new Insets(5));
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(new Text("Footer Item 1")
                , new Text("Footer Item 2"), new Text("Footer Item 3"));
        vbox.getChildren().addAll(new Separator(), hbox);

        root.setBottom(vbox);
    }
}

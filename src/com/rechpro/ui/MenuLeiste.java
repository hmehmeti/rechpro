package src.com.rechpro.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class MenuLeiste {

	protected  void getMenu(BorderPane root) {
        MenuBar menuBar = new MenuBar();

        final Menu menuDatei = new Menu(IFormRechnung.MENU_DATEI);
        final Menu menuWaren = new Menu(IFormRechnung.BTN_WAREN);
        final Menu menuRechnung = new Menu(IFormRechnung.BTN_RECHNUNGEN);
        final Menu menuLogin = new Menu(IFormRechnung.MENU_LOGIN);

        menuBar.getMenus().addAll(menuDatei, menuWaren, menuRechnung, menuLogin);

        root.setTop(menuBar);
    }

}

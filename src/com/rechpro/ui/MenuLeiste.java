package com.rechpro.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuLeiste {

	protected  MenuBar getMenu() {
        MenuBar menuBar = new MenuBar();

        final Menu menuDatei = new Menu(IFormRechnung.MENU_DATEI);
        final Menu menuWaren = new Menu(IFormRechnung.BTN_WAREN);
        final Menu menuRechnung = new Menu(IFormRechnung.BTN_RECHNUNGEN);
        final Menu menuLogin = new Menu(IFormRechnung.MENU_LOGIN);

        menuBar.getMenus().addAll(menuDatei, menuWaren, menuRechnung, menuLogin);

        return menuBar;
    }

}

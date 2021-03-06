package com.rechpro.ui.selectionarea;

import java.util.HashMap;
import java.util.Map;

import com.rechpro.worker.CategoryController;
import com.rechpro.worker.CategoryParameters;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.PathClass;

/**
 * 
 * @author eosmanlliu
 *
 */
public class CategorySelectionArea extends ElementSelectionArea {

	private final String NEW_CATEGORY_TEXT = "Neue Kategorie";
	
	private CategoryVBoxGenerator categoryGenerator;
	private CategoryController categoryController;
	private Stage categoryStage;
	private StackPane stackPane;
	
	public CategorySelectionArea() {
		categoryGenerator = new CategoryVBoxGenerator();
		categoryController = new CategoryController();
		categoryStage = new Stage();
		stackPane = new StackPane();
	}
	
	public Node getTableViewCategories() {
		VBox categoryArea = new VBox(VBOX_SPACING);
		// show the list of categories in a table form
		loadSelectionArea("CategorySelectionTable.fxml", stackPane);
		
		// Button and Text to create new category
		Button createNewCategoryBtn = getNewCategoryCreateButton();
		VBox createNewCategoryBtnAndText = new VBox(VBOX_SPACING);
		createNewCategoryBtnAndText.getChildren().addAll(createNewCategoryBtn, new Text(NEW_CATEGORY_TEXT));
		categoryArea.setSpacing(ELEMENT_AREA_VBOX_SPACING);
		categoryArea.setPadding(new Insets(10, 0, 0, 10));
		categoryArea.getChildren().addAll(stackPane, createNewCategoryBtnAndText);
		return categoryArea;
	}
	
	protected Button getNewCategoryCreateButton() {
		Button createNewCatBtn = new Button();
		createNewCatBtn.setGraphic(categoryGenerator.createImageView(this.getClass(), PathClass.ADD_BTN,
				BUTTON_WIDTH_AND_HEIGHT, BUTTON_WIDTH_AND_HEIGHT));
		createNewCatBtn.setStyle(VBoxGenerator.BUTTON_STYLE);
		
		// when enetering a new category a new window is shown
		VBox categoryCreateWindow = categoryGenerator.createAddingNewCategoryTable();
		categoryStage.setScene(new Scene(categoryCreateWindow));
		
		createNewCatBtn.setOnAction(event -> {
			categoryGenerator.resetInputValues(categoryCreateWindow);
			categoryGenerator.setTextStyleToValid();
			categoryStage.show();
		});
		Button categorySaveBtn = categoryGenerator.getSaveButton();
		Button categoryCancelBtn = categoryGenerator.getCancelButton();
		if (categorySaveBtn != null && categoryCancelBtn != null) {
			categorySaveBtn.setOnAction(event -> {
				checkMandatoryFieldsAndSave(categoryCreateWindow);
			});
			categoryCancelBtn.setOnAction(event -> categoryStage.close());
		}
		
		return createNewCatBtn;
	}
	
	@Override
	void checkMandatoryFieldsAndSave(VBox categoryCreateWindow) {
		if (!categoryGenerator.areMandatoryInputsDone()) {
			categoryGenerator.setInfoMsg(categoryCreateWindow, VBoxGenerator.MISSED_REQUIRED_FIELD);
		} else {
			String categoryName = categoryGenerator.getCategoryName().getText();
			if (categoryController.existsCategoryName(categoryName)) {
				categoryGenerator.setInfoMsg(categoryCreateWindow, CategoryVBoxGenerator.CATEGORY_NAME_EXISTS);
			} else {
				transformAndPersist();
				categoryStage.close();
				loadSelectionArea("CategorySelectionTable.fxml", stackPane);
			}
		}
	}

	@Override
	public void transformAndPersist() {
		Map<Enum, String> categoryParameterList = new HashMap<>();
		categoryParameterList.put(CategoryParameters.NAME, categoryGenerator.getCategoryName().getText());
		categoryParameterList.put(CategoryParameters.DESCRIPTION, categoryGenerator.getCategoryDescription().getText());
		categoryController.transformAndPersist(categoryParameterList);
	}

}

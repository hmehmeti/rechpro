package com.rechpro.ui;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author kdogan 
 * @email kamuran1905@yahoo.de
 **/
public class RechnungArea {
	
	public RechnungArea(){
		
	}
	 @SuppressWarnings("unused")
	private void drawLine(GraphicsContext gc, int x1, int y1, int x2, int y2) {
	        gc.setStroke(Color.BLUE);
	        gc.setLineWidth(5);
	        gc.strokeLine(x1, y1, x2, y2);
	    
	    }

	public GridPane addGridPane() {
	
	    GridPane grid = new GridPane();
	    VBox mainWindow = new VBox(10);
	    //------------Header begin---------------------------//
	    HBox headerLogo = new HBox(400);
	    ImageView logo = createImageView("../img/ware.png", 80, 80);
	    Text emptyText = new Text();//hacky...
	    headerLogo.getChildren().addAll(emptyText,logo);
	    //------------Header end ----------------------------//
	    
	    //------------Adress of seller ----------------------//
	    VBox miniAdress = new VBox(5);
	    Text sellerAddress = new Text("Hier kommt Verkäufer Adress später Kunde.getAddress()");
	    sellerAddress.setFont(Font.font ("Verdana", 8));
	    Canvas lineCanvas = new Canvas(300, 1);
	    GraphicsContext gc = lineCanvas.getGraphicsContext2D();
	    drawLine(gc, 0, 0, 300, 0);
	    miniAdress.getChildren().addAll(sellerAddress,lineCanvas);
	    //------------Adress of seller end-------------------//
	    
	    //------- third row address of customer (left) date and id of bill (right) -----//
	    HBox customAndDate = new HBox(300);
	    VBox customAndAddress = new VBox(5);
	    Text custumName = new Text("Kunde Name");
	    Text custumAdress = new Text("Kundestr. 25 \n76133 Karlsruhe");
	    customAndAddress.getChildren().addAll(custumName, custumAdress);
	    
	    VBox date = new VBox(5);
	    Text dateText = new Text("Datum: 12.12.2014");
	    Text customNr = new Text("KundenNr.: 12345");
	    Text billId = new Text("RechungsNr.: 1234567887");
	    date.getChildren().addAll(dateText,customNr,billId);
	    
	    customAndDate.getChildren().addAll(customAndAddress, date);
	    
	    //------- third row address of customer (left) date and id of bill (right) end-----//
	    
	    //------ text Rechung --------------------------------//
	    VBox billHBox = new VBox();
	    Label billText = new Label("RECHNUNG");
	    //TODO draw here a line under Text
	   
	    billHBox.getChildren().add(billText);
	    //------ text Rechung  end----------------------------//
	    
	    //-------- list of items -----------------------------//
	    VBox warenList = new VBox();
	    TextArea listOfItems = new TextArea("HIER MUSS NOCH WAREN HINZUGFÜGT WERDEN");
	    listOfItems.setPrefSize(200, 200);
	    
	    warenList.getChildren().add(listOfItems);
	    // -------- list of items end ------------------------//
	    
	    //--------- footer ----------------------------------//
	    VBox footer = new VBox();
	    Canvas lineFooter1 = new Canvas(900, 1);
	    GraphicsContext gc2 = lineFooter1.getGraphicsContext2D();
	    drawLine(gc2, 0, -400, 500, -400);
	    
	    //TODO footer info
	    Text footerInfo = new Text("Füß Informationen");
	    
	    Canvas lineFooter2 = new Canvas(900, 1);
	    GraphicsContext gc3 = lineFooter2.getGraphicsContext2D();
	    drawLine(gc3, 0, -600, 500, -600);
	    
	    footer.getChildren().addAll(lineFooter1, footerInfo, lineFooter2);
	    
	    //--------- footer end ------------------------------//
	    
	    
	    mainWindow.getChildren().addAll(headerLogo, miniAdress,customAndDate, billHBox, warenList,footer);
	    
//	    HBox hbox = new HBox(30); // create a HBox to hold 2 vboxes 
//	    HBox hbox2 = new HBox(30); // create a HBox to hold 2 vboxes 
//	    VBox hbox3 = new VBox(30);
//	    // create a vbox with a textarea that grows vertically
//        VBox vbox = new VBox(10);        
//        Label lbName = new Label("I´m a label!");
//        TextField textField = new TextField();
//        Text textArea = new Text("kkdjfkdflkdjfkdfkjdfkj");
//        VBox.setVgrow(textArea, Priority.ALWAYS);        
//        vbox.getChildren().addAll(lbName, textField, textArea);
//         
//        // create a vbox that grows horizontally inside the hbox
//        VBox vbox2 = new VBox(10);        
//        Label lbName2 = new Label("I´m also a label!");
//        TextField tf2 = new TextField();
//        tf2.setPromptText("type here");
//        TextArea textArea2 = new TextArea();
//        textArea2.setPrefWidth(100);
//        vbox2.getChildren().addAll(lbName2, tf2, textArea2);
//        
//        HBox.setHgrow(vbox2, Priority.ALWAYS);
//     // the next two lines behave equally - try to comment the first line out and use the 2nd line
//        hbox.setPadding(new Insets(20));
//        //StackPane.setMargin(hbox, new Insets(20));
// 
//        hbox.getChildren().add(vbox);
//        hbox2.getChildren().add(vbox2);
//        hbox3.getChildren().addAll(hbox,hbox2);
//        
//        grid.getChildren().add(hbox3);
	    grid.getChildren().add(mainWindow);
	    
	    return grid;
	}
	
	private ImageView createImageView(String imgPath, int width, int hight) {
		ImageView ImgView = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
		ImgView.setFitHeight(hight);
		ImgView.setFitWidth(width);
		return ImgView;
	}
}

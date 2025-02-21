package application;
	
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.Serializable;
public class Main extends Application implements Serializable{
	
	
	
	public void start(Stage primaryStage) {
		try {
			Utility utility= new Utility();
			AnchorPane start= new AnchorPane();
			start.setStyle("-fx-background-color: #EAECF6");
			VBox leftVBox=new VBox(20);
			leftVBox.setPadding(new Insets(10, 1200, 10, 10));
			VBox midVBox= new VBox(10);
			midVBox.setPadding(new Insets(10, 300, 10, 300));
			HBox hbox= new HBox(20); 
			hbox.setPadding(new Insets(10, 10, 10, 1220));
			VBox vlog_sign= new VBox(20);
			vlog_sign.setPrefSize(250, 200);
			vlog_sign.setPadding(new Insets(20, 10, 40, 30));
			
			VBox vRight= new VBox(10);
			
			VBox vLogo= new VBox(20);
			vLogo.setPrefSize(280, 150);
			
			Image midImg= new Image(new FileInputStream("images\\avocadoTree.jpg"));
			ImageView midImgV= new ImageView(midImg);
			midImgV.setFitWidth(1500); midImgV.setFitHeight(800); 
			//midImgV.setLayoutX(310); midImgV.setLayoutY(160);
			start.getChildren().add(midImgV);

			Image leftGif= new Image(new FileInputStream("images\\AvocadoPongGif.gif"));
			ImageView leftGifV= new ImageView(leftGif);
			leftGifV.setFitWidth(280); leftGifV.setFitHeight(500); 
			leftGifV.setLayoutX(10); leftGifV.setLayoutY(270);
			start.getChildren().add(leftGifV);
			
			String cssLayout = "-fx-border-color: black;\n" +
	                "-fx-border-radius: 20;\n" +
	                "-fx-border-width: 1;\n" +
	                "-fx-background-color: #EAECF6;\n";
	        
			vlog_sign.setStyle(cssLayout);
			//vLogo.setStyle(cssLayout);
		
			//login and sign up screen
			Text adText= new Text("You have to LOGIN or SIGN UP to view and make post");
			 Font font = Font.font("tahoma", FontPosture.REGULAR, 20);
			 adText.setFont(font);
			 adText.setWrappingWidth(230);
			 adText.setLineSpacing(5);
			
			Button login= new Button("Login");
			login.setPrefSize(210, 40);
			
			
			Button signUp= new Button("Sign Up");
			signUp.setPrefSize(210, 40);
			
			//trending tab
			Text trendTop= new Text("Todays Trending");
			trendTop.setFont(font);
			
			TextField searchBar= new TextField();
			searchBar.setPrefSize(900, 30);
			searchBar.setPromptText("Searh");		

			Image logo= new Image(new FileInputStream("images\\Logo.jpg"));
			ImageView logoImgV= new ImageView(logo);
			logoImgV.setFitWidth(280); logoImgV.setFitHeight(245);
			
			Text pop= new Text("Popular accounts");
			pop.setFont(font);
			//logo.setTextAlignment(Pos.CENTER);
			
			start.getChildren().add(leftVBox);
			start.getChildren().add(midVBox);
			start.getChildren().add(hbox);
			midVBox.getChildren().add(searchBar);
			//midVBox.getChildren().add(hbox);
			hbox.getChildren().add(vRight);
			vRight.getChildren().add(vlog_sign);
			vlog_sign.getChildren().add(adText);
			vlog_sign.getChildren().add(login);
			vlog_sign.getChildren().add(signUp);
			leftVBox.getChildren().add(vLogo);
			vLogo.getChildren().add(logoImgV);
			
			
			
			primaryStage.setOnCloseRequest(event -> {
				utility.save();
			});
			
			Scene startingScreen = new Scene(start,1500,800, Color.BLUE);
			startingScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(startingScreen);
			primaryStage.show();
			
			signUp.setOnAction(e ->{
				utility.save();
				SignUpScreen.signUp(primaryStage, utility, startingScreen);
				
			});
			
			login.setOnAction(e->{
				utility.save();
				LoginScreen.login(primaryStage);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

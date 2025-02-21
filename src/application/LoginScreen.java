package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

	public class LoginScreen extends Main{
		
		public static void login(Stage primaryStage) {
			Utility utility= new Utility();
			try {
			AnchorPane bigBoy= new AnchorPane();
			bigBoy.setStyle("-fx-background-color: #EAECF6");
			HBox hBox= new HBox(20);
			hBox.setPadding(new Insets(10, 10, 10, 10));
			
			String cssLayout = "-fx-border-color: black;\n" +
	                "-fx-border-radius: 20;\n" +
	                "-fx-border-width: 1;\n" +
	                "-fx-background-color: #EAECF6;\n";
			
			
			Image img= new Image(new FileInputStream(new File("images\\avocadoBackground.jpg")));
			ImageView imgV1= new ImageView(img);
			ImageView imgV2= new ImageView(img); imgV2.setLayoutX(367);
			ImageView imgV3= new ImageView(img); imgV3.setLayoutX(734);
			ImageView imgV4= new ImageView(img); imgV4.setLayoutX(1001);
			ImageView imgV5= new ImageView(img); imgV5.setLayoutX(1368);
			ImageView imgV6= new ImageView(img); imgV6.setLayoutY(612);
			ImageView imgV7= new ImageView(img); imgV7.setLayoutY(612); imgV7.setLayoutX(367);
			ImageView imgV8= new ImageView(img); imgV8.setLayoutY(612); imgV8.setLayoutX(734);
			ImageView imgV9= new ImageView(img); imgV9.setLayoutY(612); imgV9.setLayoutX(1001);
			ImageView imgV0= new ImageView(img); imgV0.setLayoutY(612); imgV0.setLayoutX(1368);
			VBox leftV= new VBox();		VBox midV= new VBox(5);VBox rightV= new VBox();
			
			bigBoy.getChildren().add(imgV1); bigBoy.getChildren().add(imgV2); bigBoy.getChildren().add(imgV3); bigBoy.getChildren().add(imgV4); bigBoy.getChildren().add(imgV5);
			bigBoy.getChildren().add(imgV6); bigBoy.getChildren().add(imgV7); bigBoy.getChildren().add(imgV8); bigBoy.getChildren().add(imgV9); bigBoy.getChildren().add(imgV0);
			
			VBox login = new VBox(20);
			login.setPrefSize(280, 780);
			login.setPadding(new Insets(10, 10, 10, 10));
			login.setStyle(cssLayout);
			
			
			TextField username= new TextField();
			username.setPrefSize(210, 40);
			username.setPromptText("Username");
			username.setBackground(null);
			
			PasswordField password= new PasswordField();
			password.setPrefSize(210, 40);
			password.setPromptText("Password");
			password.setBackground(null);
			
			String rounded= "-fx-border-radius: 20;" +
					"-fx-border-color: black;\n";
			
			Font font = Font.font("garamond", FontPosture.REGULAR, 20);
			username.setStyle(rounded);	password.setStyle(rounded);
			username.setFont(font);	password.setFont(font);

			Button sumbit= new Button("Sumbit");
			sumbit.setPrefSize(220, 40);
			sumbit.setTranslateX(20);
			sumbit.setTranslateY(0);
			//String forSumbit= "-fx-border-radius: 20;\n" + "-fx-border-color: light-blue;\n";
			//sumbit.setStyle(forSumbit);
			
			login.getChildren().add(username);
			login.getChildren().add(password);	
			login.getChildren().add(sumbit);	
			hBox.getChildren().add(login);	
			bigBoy.getChildren().add(hBox);		
			
			sumbit.setOnAction(e->{
				if(utility.searchForUsernameInUse(username.getText())){
					Account temp = utility.getAccountBag().getAccount(username.getText());
					if(temp.getPassword().equals(password.getText())){
						Dictionary dictionary= new Dictionary();
						HomeScreen_LoggedIn.homeScreen(primaryStage,dictionary.getDictionary(), temp, temp);
					}	
				}else {
					username.setText("idk bro");
				}
			});
	
			
			Scene scene = new Scene(bigBoy,1500,800, Color.BLUE);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
//		public static boolean checkIfAccountIsReal(Utility utility, String username, String password) {
//			Account temp= utility.search(username);
//			if(temp!=null && temp.getPassword().equals(password)) {
//				return true;
//			}else {
//				return false;
//			}
//		}
	}


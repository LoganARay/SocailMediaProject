package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpScreen extends Main{
	
	public static void signUp(Stage primaryStage, Utility utility, Scene starting) {
		try {
		//Utility utility= new Utility();
		AnchorPane bigBoy= new AnchorPane();
		bigBoy.setStyle("-fx-background-color: #EAECF6");
		HBox align= new HBox(10);
		align.setPadding(new Insets(10, 10, 10, 1210));
		
		Text friends= new Text("Start making friends!");
		Font friendsFont= Font.font("impact", 64);
		friends.setFont(friendsFont);
		friends.setLayoutY(100);
		friends.setLayoutX(300);
		
		bigBoy.getChildren().add(friends);	
		
		VBox leftSide= new VBox(30);
		leftSide.setPrefSize(280, 780);
		leftSide.setPadding(new Insets(20, 10, 10, 10));
		
		Image gif = new Image(new FileInputStream("images\\SignUpGif.gif"));
		ImageView imageView = new ImageView(gif);
		imageView.setLayoutX(200);
		imageView.setLayoutY(200);
		imageView.scaleXProperty();
		bigBoy.getChildren().add(imageView);
		
		String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-radius: 20;\n" +
                "-fx-border-width: 1;\n" + 
					"-fx-blend-mode: darken";
		
		leftSide.setStyle(cssLayout);
		
		Text prompt= new Text("Enter all information below to make an account, all information can be changed after in profile settings. Other features like adding a profile picture or adding a bio and such can be done after your account is made.");
		prompt.setWrappingWidth(260);
		prompt.setLineSpacing(5);
		
		TextField username= new TextField();
		username.setPrefSize(210, 40);
		username.setPromptText("Username");
		username.setBackground(null);
		
		PasswordField password= new PasswordField();
		password.setPrefSize(210, 40);
		password.setPromptText("Password");
		password.setBackground(null);
		
		TextField firstName= new TextField();
		firstName.setPrefSize(210, 40);
		firstName.setPromptText("First Name");
		firstName.setBackground(null);
		
		TextField lastName= new TextField();
		lastName.setPrefSize(210, 40);
		lastName.setPromptText("Last Name");
		lastName.setBackground(null);
		
		Button sumbit= new Button("Sumbit");
		sumbit.setPrefSize(220, 40);
		sumbit.setTranslateX(20);
		sumbit.setTranslateY(0);
		String forSumbit= "-fx-border-radius: 20;\n" + "-fx-border-color: light-blue;\n";
		sumbit.setStyle(forSumbit);
		
		Text missing= new Text();
		missing.setWrappingWidth(260);
		missing.setLineSpacing(3);		
		missing.setFill(Color.DARKRED);
		
		String rounded= "-fx-border-radius: 20;" +
				"-fx-border-color: black;\n";
		
		username.setStyle(rounded); password.setStyle(rounded); firstName.setStyle(rounded); lastName.setStyle(rounded);
		Font font = Font.font("garamond", FontPosture.REGULAR, 20);
		prompt.setFont(font); username.setFont(font); password.setFont(font); firstName.setFont(font); lastName.setFont(font); sumbit.setFont(font);
		
		Font missingFont= Font.font("impact", FontWeight.BOLD, 24); missing.setFont(missingFont);
		
		bigBoy.getChildren().add(align);
		align.getChildren().add(leftSide);
		leftSide.getChildren().add(prompt);
		leftSide.getChildren().add(username);
		leftSide.getChildren().add(password);
		leftSide.getChildren().add(firstName);
		leftSide.getChildren().add(lastName);
		leftSide.getChildren().add(sumbit);
		leftSide.getChildren().add(missing);
		
		
		
		
		
		
		
		
		
		
		
		sumbit.setOnAction(e ->{
				if(username.getText().length()>2 && passwordChecker(password.getText()) && !firstName.getText().isEmpty() && !firstName.getText().isEmpty()) {
					if(!utility.searchForUsernameInUse(username.getText())) {
						try {
							createAccountFile(username.getText());
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						utility.insert(username.getText(), password.getText(), firstName.getText(), lastName.getText());
						utility.save();
						primaryStage.setScene(starting);
					}else {
						missing.setText("Username is in use, please select another valid username.");
					}
				}else {
					missing.setText("Your Username, Password, First name and Last name must all be filled out and"
							+ " must be vaild to create an account");
				}
			});
		
//		primaryStage.setOnCloseRequest(event -> {
//			try {
//				Utility.saveToFile(accBag);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		});
		
		Scene scene = new Scene(bigBoy,1500,800, Color.BLUE);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean passwordChecker(String password) {
		if(password.length()<7)
			return false;
		else if(!checkForCapitailLetters(password))
			return false;
		else if(!checkForLowerCaseLetters(password))
			return false;
		else if(!checkForNumbers(password))
			return false;
		else 
			return true;
	}
	
	public static int countWords(String str) {
		int num = 0;

		String pattern = "[A-Za-z]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);

		while (m.find()) {
			num++;
		}
		return num;
	}
	
	public static boolean checkForCapitailLetters(String str) {
		String pattern = "[QWERTYUIOPASDFGHJKLZXCVBNM]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}
	
	public static boolean checkForLowerCaseLetters(String str) {
		String pattern = "[qwertyuiopasdfghjklzxcvbnm]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}
	
	public static boolean checkForNumbers(String str) {
		String pattern = "[0123456789]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}
	
	public static void createAccountFile(String username) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("Accounts/" + username + ".txt"), "utf-8"))) {
	}
	}
}

package application;

import java.awt.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import javafx.collections.ObservableList;
import javafx.css.Style;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class HomeScreen_LoggedIn {
	
	public static void homeScreen(Stage primaryStage, HashSet<String> dictionary, Account user, Account displayedUsersPosts) {
		try {
		Utility utility= new Utility();
		AnchorPane start= new AnchorPane();
		
		Stage notifStage= new Stage();
		
		AnchorPane notifPane= new AnchorPane();
		
		Text textNotif= new Text("There are grammatical errors in your post such as 'your mom is skinny' we ran this through everything and we dont understand what you are saying, would you still like to post this?" );
		textNotif.setWrappingWidth(180);
		textNotif.setLayoutX(10);
		textNotif.setLayoutY(20);
		Button notifNo= new Button("No");
		notifNo.setPrefSize(50, 30);
		notifNo.setLayoutX(80);
		notifNo.setLayoutY(160);
		Button notifYes= new Button("Yes");
		notifYes.setPrefSize(50, 30);
		notifYes.setLayoutX(140);
		notifYes.setLayoutY(160);
		notifPane.getChildren().add(notifYes);
		notifPane.getChildren().add(notifNo);
		notifPane.getChildren().add(textNotif);
		Scene notif= new Scene(notifPane, 200, 200);
		notifStage.setScene(notif);
		notifStage.initModality(Modality.APPLICATION_MODAL);
		
		start.setStyle("-fx-background-color: #EAECF6");
		VBox leftVBox=new VBox(20);
		leftVBox.setPadding(new Insets(10, 1200, 10, 10));
		VBox midVBox= new VBox(10);
		midVBox.setPadding(new Insets(10, 10, 10, 300));
		HBox hbox= new HBox(20); 
		hbox.setPadding(new Insets(10, 10, 10, 1220));
		//trying to get Vbox to be smaller
		
		VBox vLogo= new VBox(20);
		vLogo.setPrefSize(280, 255);
		vLogo.setLayoutX(10);
		vLogo.setLayoutY(20);
		
		
		VBox vFriends= new VBox(20);
		vFriends.setPrefSize(280, 450);
		vFriends.setLayoutX(10);
		vFriends.setLayoutY(275);
			
		Button signOut= new Button("Sign Out");
		signOut.setPrefSize(280, 30);
		signOut.setLayoutY(750);
		signOut.setLayoutX(10);
		
		
		
		signOut.setOnAction(event->{
			LoginScreen.login(primaryStage);
		});
		
		String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-radius: 20;\n" +
                "-fx-border-width: 1;\n";
		
		//BorderPane root = new BorderPane();
	
		vFriends.setStyle(cssLayout);
		
		//middle screen
		AnchorPane posting= new AnchorPane();
		//posting.setPadding(new Insets(200, 1200, 100, 320));
		
		String postTabLayout = "-fx-border-color: #D1D8FF;\n" +
                "-fx-border-radius: 20;\n" +
                "-fx-border-width: 1;\n";
		
		
		
		posting.setPrefSize(1190, 200);
		posting.setStyle(postTabLayout);
		
		HBox topBar= new HBox(10);
		
		Image logo= new Image(new FileInputStream(user.getProfileImage()));
		ImageView logoImgV= new ImageView(logo);
		logoImgV.setFitWidth(280); logoImgV.setFitHeight(185);
		vLogo.getChildren().add(logoImgV);
		
		Button changeProfile=new Button("Add/Change Profile Image");
		changeProfile.setPrefSize(280,30);
		changeProfile.setLayoutX(10);
		changeProfile.setLayoutY(235);
		vLogo.getChildren().add(changeProfile);
		
		changeProfile.setOnAction(e-> { 
			FileChooser profileImage=new FileChooser();
			File reFile = profileImage.showOpenDialog(primaryStage);
			try {
				if(ImageIO.read(reFile)==null) {
					System.out.println("Not an image");
				}
				else {
					utility.getAccountBag().getAccount(user.getUsername()).setProfileImage(reFile);
				    utility.save();
				    HomeScreen_LoggedIn.homeScreen(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()));
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		
		TextField searchBar= new TextField();
		searchBar.setPrefSize(1090, 30);
		
		
		searchBar.setPromptText("Searh for User");
		topBar.getChildren().add(searchBar);
		midVBox.getChildren().add(topBar);
		
		
		searchBar.setOnKeyReleased(event->{
			if (event.getCode() == KeyCode.ENTER) {
				if(utility.getAccountBag().searchForUsernameInUse(searchBar.getText())) {
					utility.save();
					AccountProfileScreen.accountProfile(primaryStage, dictionary, user, utility.getAccountBag().getAccount(searchBar.getText()));
				}
			}
		});
		
		
		Font postFont = Font.font("tahoma", FontPosture.REGULAR, 20);
		Text create= new Text("Creat a post:");
		create.setX(30);
		create.setY(25);
		create.setFont(postFont);
		posting.getChildren().add(create);
		
//		AnchorPane postB= new AnchorPane();
//		postB.setPrefSize(1100, 200);
		//File postAttachment= new File();
		
		Button postSumbit= new Button("Post");
		postSumbit.setPrefSize(100, 60);
		postSumbit.setLayoutX(20);
		postSumbit.setLayoutY(35);
		posting.getChildren().add(postSumbit);
		
		Button makeAttachment= new Button("  Post and \n add img/vid \n");
		makeAttachment.setPrefSize(100, 60);
		makeAttachment.setLayoutX(20);
		makeAttachment.setLayoutY(125);
		posting.getChildren().add(makeAttachment);
		
		TextArea postBody= new TextArea();
		postBody.setPromptText("Start typing your post here");
		postBody.setFont(postFont);
		postBody.setLayoutX(130);
		postBody.setLayoutY(35);
		postBody.setPrefSize(1050, 150);
		postBody.setWrapText(true);
		posting.getChildren().add(postBody);
		
		postSumbit.setOnAction(event-> {
			if(!postBody.getText().equals(null)) {
				LinkedList<String> temp = checkForFakeWords(postBody.getText(), dictionary);
				for(int i=0; i<temp.size(); i++) {
					System.out.println(temp.get(i));
				}
				if(!(temp.size()>0)) {
					TreeSet <String> tempT= (TreeSet<String>)user.getFollowersList().clone();
					utility.addPost(user.getUsername(), postBody.getText());
					int numberOfPosts= utility.getPostBag().numberOfPosts()-1;
					utility.getAccountBag().getAccount(user.getUsername()).addPostIndex(numberOfPosts);
					for(int i=0; i<user.getFollowersList().size(); i++) {
						utility.getAccountBag().getAccount(tempT.pollFirst()).addToTimeline(numberOfPosts);
					}
					utility.save();
					PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()), utility.getPostBag().numberOfPosts()-1);
				}else {
					if(temp.size()>=4) {
						textNotif.setText("There are grammatical errors in your post such as '" + temp.get(0) + "', '" + temp.get(1) + "', '" + temp.get(2) + "' and more, would you like to post anyways?");
					}else if(temp.size()==3){
						textNotif.setText("There are grammatical errors in your post such as '" + temp.get(0) + "', '" + temp.get(1) + "', and '" + temp.get(2) + "', would you like to post anyways?");
					}else if(temp.size()==2){
						textNotif.setText("There are grammatical errors in your post such as '" + temp.get(0) + "', and '" + temp.get(1) + "', would you like to post anyways?");
					}else if (temp.size()==1){
						textNotif.setText("There is a grammatical error, it is '" + temp.get(0) + "', would you like to post anyways?");
					}
					notifStage.showAndWait();
				}
			}
		});
		
		makeAttachment.setOnAction(event->{
			FileChooser profileImage=new FileChooser();
			File reFile = profileImage.showOpenDialog(primaryStage);
			try {
				if(ImageIO.read(reFile)==null) {
					System.out.println("Not an image");
				}
				else {
					if(!postBody.getText().equals(null)) {
						LinkedList<String> temp = checkForFakeWords(postBody.getText(), dictionary);
						if(!(temp.size()>0)) {
							TreeSet <String> tempT= (TreeSet<String>)user.getFollowersList().clone();
							utility.addPost(user.getUsername(), postBody.getText());
							int numberOfPosts= utility.getPostBag().numberOfPosts()-1;
							utility.getPostBag().searchByPostNumber(numberOfPosts).setAttachment(reFile);
							utility.getAccountBag().getAccount(user.getUsername()).addPostIndex(numberOfPosts);
							for(int i=0; i<user.getFollowersList().size(); i++) {
								utility.getAccountBag().getAccount(tempT.pollFirst()).addToTimeline(numberOfPosts);
							}
							utility.save();
							PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()), numberOfPosts);
						}else {
							if(temp.size()>=4) {
								textNotif.setText("There are grammatical errors in your post such as '" + temp.get(0) + "', '" + temp.get(1) + "', '" + temp.get(2) + "' and more, would you like to post anyways?");
							}else if(temp.size()==3){
								textNotif.setText("There are grammatical errors in your post such as '" + temp.get(0) + "', '" + temp.get(1) + "', and '" + temp.get(2) + "', would you like to post anyways?");
							}else if(temp.size()==2){
								textNotif.setText("There are grammatical errors in your post such as '" + temp.get(0) + "', and '" + temp.get(1) + "', would you like to post anyways?");
							}else if (temp.size()==1){
								textNotif.setText("There is a grammatical error, it is '" + temp.get(0) + "', would you like to post anyways?");
							}
							notifYes.setAccessibleText(reFile.getPath());
							notifStage.showAndWait();
						}
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		notifYes.setAccessibleText("images\\empty.png");
		
		notifYes.setOnAction(event->{
			
			LinkedList<String> temp = checkForFakeWords(postBody.getText(), dictionary);
			TreeSet <String> tempT= (TreeSet<String>)user.getFollowersList().clone();
			utility.addPost(user.getUsername(), postBody.getText());
			int numberOfPosts= utility.getPostBag().numberOfPosts()-1;
			utility.getPostBag().searchByPostNumber(numberOfPosts).setAttachment(new File (notifYes.getAccessibleText()));
			utility.getAccountBag().getAccount(user.getUsername()).addPostIndex(numberOfPosts);
			for(int i=0; i<user.getFollowersList().size(); i++) {
				utility.getAccountBag().getAccount(tempT.pollFirst()).addToTimeline(numberOfPosts);
			}
			utility.save();
			notifStage.close();
			PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()), numberOfPosts);
		});
		
		notifNo.setOnAction(event->{
			notifStage.close();
		});
		
		
		midVBox.getChildren().add(posting);
		start.getChildren().add(midVBox);
		start.getChildren().add(vLogo);	
		start.getChildren().add(vFriends);
		start.getChildren().add(signOut);
		start.getChildren().add(hbox);
		
		ScrollPane postScroll= new ScrollPane();
		postScroll.setPrefHeight(500);
		VBox holder= new VBox();
		Font prevPostsFont = Font.font("tahoma", FontPosture.REGULAR, 15);
//		TreeSet<String> tree= (TreeSet<String>) user.getFollowingList().clone();
//		tree.equals(user.getFollowingList());
		LinkedList<Integer> timeline= (LinkedList<Integer>) user.getTimeline().clone();
		int timelineSize= timeline.size();

		if(timeline.size() > 0) {
			for(int i=0; i<timelineSize; i++) {
					Post tempP= utility.getPostBag().searchByPostNumber(timeline.removeLast());
					int number=i;
					AnchorPane post= new AnchorPane();
					post.setStyle(cssLayout);
					post.setPrefSize(1170, 200);
					Text tempT= new Text(tempP.getTitle());
					Text tempB= new Text(tempP.getBody());
					Button likes= new Button();
					Image postImg= new Image(new FileInputStream(tempP.getAttachment()));
					ImageView postImgV= new ImageView(postImg);
					postImgV.setX(10); postImgV.setY(10); 
					postImgV.setFitWidth(180); postImgV.setFitHeight(180);
					if(tempP.hasUserLiked(user.getUsername())) {
						likes.setText("Unlike");
					}else {
						likes.setText("Like");
					}
					likes.setPrefSize(50, 30);
					likes.setTranslateX(1110);
					likes.setTranslateY(160);
					tempT.setFont(prevPostsFont); tempB.setFont(prevPostsFont); 
					tempT.setTranslateX(210); tempT.setTranslateY(50);
					tempB.setTranslateX(230); tempB.setTranslateY(70);
					tempB.setWrappingWidth(950);
					post.getChildren().add(postImgV);
					post.getChildren().add(likes);
					post.getChildren().add(tempT);
					post.getChildren().add(tempB);
					holder.getChildren().add(post);
					post.setOnMousePressed(event -> {
						
						utility.save();
						ArrayList<Integer> temp= new ArrayList<>(); temp.add(number);
							PostScreen.post(primaryStage, dictionary, user, utility.getAccountBag().getAccount(tempP.getTitle()), tempP.getPostNum());
					});
					
					likes.setOnAction(event-> {
						if(tempP.hasUserLiked(user.getUsername())) {
							tempP.removeLike(user.getUsername());
						}else {
							tempP.addLike(user.getUsername());
						}
						utility.save();
						HomeScreen_LoggedIn.homeScreen(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()));
					});
				}
		}else {
			VBox post= new VBox(20);
			Image gif = new Image(new FileInputStream("images\\NoPostsGif.gif"));
			ImageView imageView= new ImageView(gif);
			post.getChildren().add(imageView);
			holder.getChildren().add(post);
		}
		
		//utility.getAccountBag().getAccount(user.getUsername()).setFollowing(saveReturn);
		
		primaryStage.setOnCloseRequest(event -> {
			utility.save();
		});
		
		postScroll.setContent(holder);
		midVBox.getChildren().add(postScroll);
		
		
		
		Scene homeScreen = new Scene(start,1500,800, Color.BLUE);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(homeScreen);
		//primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static LinkedList<String> checkForFakeWords(String post, HashSet<String> dictionary) {
		LinkedList<String> temp= new LinkedList<String>();
		StringTokenizer t = new StringTokenizer(post);
		String word ="";
		while(t.hasMoreTokens())
		{
		    word = t.nextToken();
		    if(word.contains(",")) { word=word.replaceAll(",", ""); }
		    else if(word.contains(".")) { word=word.replaceAll(".", ""); }
		    else if(word.contains("!")) { word=word.replaceAll("!", ""); }
		    else if(word.contains("\"")) { word=word.replaceAll("\"", ""); }
		    if(!dictionary.contains(word)) {
		    	temp.add(word);
		    }
		}
		return temp;
	}
}

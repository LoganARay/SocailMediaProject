package application;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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
import javafx.stage.Stage;

public class AccountProfileScreen {
	public static void accountProfile(Stage primaryStage, HashSet<String> dictionary, Account user, Account displayedUsersPosts) {
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
			//trying to get Vbox to be smaller
			
			VBox vLogo= new VBox(20);
			vLogo.setPrefSize(280, 150);
			
			VBox vProfile= new VBox(20);
			vProfile.setPrefSize(280, 150);
			
			   
			VBox vFriends= new VBox(20);
			vFriends.setPrefSize(280, 500);
			
			String cssLayout = "-fx-border-color: black;\n" +
	                "-fx-border-radius: 20;\n" +
	                "-fx-border-width: 1;\n";
			
			
			//BorderPane root = new BorderPane();
		
			vFriends.setStyle(cssLayout);
			HBox topBar= new HBox(10);
			
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
			Image accPic= new Image(new FileInputStream(displayedUsersPosts.getProfileImage()));
			ImageView accPicV= new ImageView(accPic);
			accPicV.setX(10); accPicV.setX(10);
			
			Button followOrUnfollow=new Button();
			followOrUnfollow.setLayoutX(10); followOrUnfollow.setLayoutY(205);
			
			if(user==displayedUsersPosts) {
				accPicV.setFitWidth(280); accPicV.setFitHeight(245);
			}
			else {
				accPicV.setFitWidth(280); accPicV.setFitHeight(185);
				followOrUnfollow.setPrefSize(280, 30);
				if(utility.getAccountBag().getAccount(user.getUsername()).isFollowing(displayedUsersPosts.getUsername())) {
					followOrUnfollow.setText("Unfollow User");
				}
				else {
					followOrUnfollow.setText("Follow User");
				}
				followOrUnfollow.setOnAction(event-> { 
					if(followOrUnfollow.getText().equals("Follow User")) {
						utility.getAccountBag().getAccount(user.getUsername()).addFollowing(displayedUsersPosts.getUsername());
						utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()).addFollower(user.getUsername());
					}else {
						utility.getAccountBag().getAccount(user.getUsername()).deleteFollowing(displayedUsersPosts.getUsername());
						utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()).deleteFollower(user.getUsername());
					}
				    utility.save();
				    HomeScreen_LoggedIn.homeScreen(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()));
				});
			}
//			
//			followOrUnfollow.setOnAction(e-> { 
//				utility.getAccountBag().getAccount(user.getUsername()).addOrDeleteFollowing(displayedUsersPosts.getUsername());
//				utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()).addOrDeleteFollower(user.getUsername());
//			    utility.save();
//			    HomeScreen_LoggedIn.homeScreen(primaryStage, user, user);
//			});
			
			Button backHome= new Button("Back to homescreen");
			backHome.setPrefSize(280, 30);
			backHome.setLayoutY(750);
			backHome.setLayoutX(10);
			
			backHome.setOnAction(event->{
			    HomeScreen_LoggedIn.homeScreen(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()));
			}); 

			start.getChildren().add(midVBox);
			start.getChildren().add(hbox);
			start.getChildren().add(accPicV);
			if(!(user==displayedUsersPosts)) {
				start.getChildren().add(followOrUnfollow);
			}
			start.getChildren().add(backHome);
			
			ScrollPane postScroll= new ScrollPane();
			postScroll.setPrefHeight(700);
			VBox holder= new VBox();
			Font prevPostsFont = Font.font("tahoma", FontPosture.REGULAR, 15);
			int numberOfPosts= displayedUsersPosts.getNumberOfPosts();
			if(numberOfPosts > 0) {
				for(int i=numberOfPosts-1; i>=0; i--) {
					int number=i;
					AnchorPane post= new AnchorPane();
					
					post.setStyle(cssLayout);
					post.setPrefSize(1170, 200);
					//utility.getPostBag().searchByPostNumber(displayedUsersPosts.getPostIndexFromPostIndex(i))
					Post tempP= utility.getPostBag().searchByPostNumber(displayedUsersPosts.getPostIndexFromPostIndex(i));
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
					post.getChildren().add(tempT);
					post.getChildren().add(tempB);
					post.getChildren().add(likes);
					holder.getChildren().add(post);
					
					post.setOnMousePressed(event -> {
						utility.save();
						
							PostScreen.post(primaryStage, dictionary, user, displayedUsersPosts, displayedUsersPosts.getPostIndexFromPostIndex(number));
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
			
			
			primaryStage.setOnCloseRequest(event -> {
				utility.save();
			});
			
			postScroll.setContent(holder);
			midVBox.getChildren().add(postScroll);
			
			Scene accountProfile = new Scene(start,1500,800, Color.BLUE);
			primaryStage.setScene(accountProfile);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<String> checkForFakeWords(String post, HashSet<String> dictionary) {
		LinkedList<String> temp= new LinkedList<String>();
		StringTokenizer t = new StringTokenizer(post);
		String word ="";
		while(t.hasMoreTokens())
		{
		    word = t.nextToken();
		    if(!dictionary.contains(word)) {
		    	temp.add(word);
		    }
		}
		return temp;
	}
}

package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import javafx.css.Style;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PostScreen {
	public static void post(Stage primaryStage, HashSet<String> dictionary, Account user, Account displayedUsersPosts, int postNum) {
		try {
		Utility utility= new Utility();
		AnchorPane base= new AnchorPane();
		
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
		
		
		Post mainPost= utility.getPostBag().searchByPostNumber(postNum);
		
		
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
		leftV.setPrefSize(300, 800); midV.setPrefSize(900, 780); rightV.setPrefSize(300, 800);
		midV.setLayoutY(10); midV.setLayoutX(300); rightV.setLayoutX(1200);
		midV.setStyle(cssLayout); 
		
		base.getChildren().add(imgV1); base.getChildren().add(imgV2); base.getChildren().add(imgV3); base.getChildren().add(imgV4); base.getChildren().add(imgV5);
		base.getChildren().add(imgV6); base.getChildren().add(imgV7); base.getChildren().add(imgV8); base.getChildren().add(imgV9); base.getChildren().add(imgV0);
		
		
		String postTabLayout = "-fx-border-color: #D1D8FF;\n" +
                "-fx-border-radius: 20;\n" +
                "-fx-border-width: 1;\n";
		Font postFont = Font.font("tahoma", FontPosture.REGULAR, 20);
		AnchorPane post= new AnchorPane();
		post.setPrefSize(900, 250);
		post.setStyle(postTabLayout);
		
		Image mainPostImg= new Image(new FileInputStream(mainPost.getAttachment()));
		ImageView mainPostImgV= new ImageView(mainPostImg);
		mainPostImgV.setFitWidth(180); mainPostImgV.setFitHeight(180);
		mainPostImgV.setX(10); mainPostImgV.setY(10);
		post.getChildren().add(mainPostImgV);
		
		Text postUser= new Text(); 
		Text postBody= new Text();
		Text postDate= new Text(mainPost.getDate());
		Button mainLikes= new Button();
		Text numOfLikes= new Text(mainPost.getNumberOfLikes()+ " ");
		numOfLikes.setLayoutX(825); numOfLikes.setLayoutY(220);
		mainLikes.setPrefSize(50, 30);
		mainLikes.setLayoutX(840); mainLikes.setLayoutY(210);
		if(mainPost.hasUserLiked(user.getUsername())) {
			mainLikes.setText("Unlike");
		}else {
			mainLikes.setText("Like");
		}
		
		mainLikes.setOnAction(event-> {
			if(mainPost.hasUserLiked(user.getUsername())) {
				utility.getPostBag().searchByPostNumber(postNum).removeLike(user.getUsername());
			}else {
				utility.getPostBag().searchByPostNumber(postNum).addLike(user.getUsername());
			}
			utility.save();
			PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()), postNum);
		});
		post.getChildren().add(mainLikes);
		post.getChildren().add(numOfLikes);
		postUser.setText(displayedUsersPosts.getUsername());
		postBody.setText(mainPost.getBody());
		
		postUser.setX(200); postUser.setY(40); 	postBody.setX(230); postBody.setY(70);
		postDate.setX(800); postDate.setY(20);
		postUser.setFont(postFont); 	postBody.setFont(postFont);
		postBody.setWrappingWidth(650);
		post.getChildren().add(postUser);	post.getChildren().add(postBody); post.getChildren().add(postDate);
		AnchorPane commenting= new AnchorPane();
		
		commenting.setPrefSize(900, 200);
		commenting.setStyle(postTabLayout);
		
		Text create= new Text("Creat a comment:");
		create.setX(30);
		create.setY(25);
		create.setFont(postFont);
		commenting.getChildren().add(create);
		
//		AnchorPane postB= new AnchorPane();
//		postB.setPrefSize(1100, 200);
		
		Button commentSumbit= new Button("Comment");
		commentSumbit.setPrefSize(100, 60);
		commentSumbit.setLayoutX(20);
		commentSumbit.setLayoutY(50);
		commenting.getChildren().add(commentSumbit);
		
		Button commentWithAttachment= new Button("Comment adding\nimg/vid");
		commentWithAttachment.setPrefSize(100, 60);
		commentWithAttachment.setLayoutX(20);
		commentWithAttachment.setLayoutY(120);
		commenting.getChildren().add(commentWithAttachment);
		
		
		TextArea commentBody= new TextArea();
		commentBody.setPromptText("Start typing your post here");
		commentBody.setFont(postFont);
		commentBody.setLayoutX(130);
		commentBody.setLayoutY(35);
		commentBody.setPrefSize(750, 150);
		commentBody.setWrapText(true);
		commenting.getChildren().add(commentBody);
		
		
		leftV.setOnMouseClicked(event ->{
			HomeScreen_LoggedIn.homeScreen(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()));
		});
		
		rightV.setOnMouseClicked(event ->{
			HomeScreen_LoggedIn.homeScreen(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(user.getUsername()));
		});
		
		//System.out.println(utility.getPost(number[0]).numberOfComments() + " ");
		
		
		base.getChildren().add(leftV);
		base.getChildren().add(midV);
		base.getChildren().add(rightV);
		//commenting.getChildren().add(holder);
		
		midV.getChildren().add(post);
		midV.getChildren().add(commenting);
		
		ScrollPane postScroll= new ScrollPane();
		postScroll.setPrefHeight(500);
		postScroll.setPrefWidth(900);
		VBox holder= new VBox(5);
		Font prevPostsFont = Font.font("tahoma", FontPosture.REGULAR, 15);
		if(mainPost.numberOfComments() >0) {
			for(int i=0; i<mainPost.numberOfComments(); i++) {
				int commentNumber=i;
				//commentScroll.setPrefHeight(120);
				AnchorPane comment= new AnchorPane();
				comment.setBorder(null);
				comment.setStyle(cssLayout);
				comment.setPrefSize(880, 200);
				Post tempP= utility.getPostBag().searchByPostNumber(mainPost.getCommentAtIndex(i));
				Text date= new Text(tempP.getDate());
				Text tempT= new Text(tempP.getTitle());
				Text tempB= new Text(tempP.getBody());
				Text numberOfLikes= new Text(tempP.getNumberOfLikes() + " ");
				Button likes= new Button();
				Image postImg= new Image(new FileInputStream(tempP.getAttachment()));
				ImageView postImgV= new ImageView(postImg);
				postImgV.setX(10); postImgV.setY(10);
				postImgV.setFitHeight(180); postImgV.setFitWidth(180);
				if(tempP.hasUserLiked(user.getUsername())) {
					likes.setText("Unlike");
				}else {
					likes.setText("Like");
				}
				numberOfLikes.setTranslateX(775);
				numberOfLikes.setTranslateY(170);
				likes.setPrefSize(50, 30);
				likes.setTranslateX(800);
				likes.setTranslateY(160);
				tempT.setFont(prevPostsFont); tempB.setFont(prevPostsFont); 
				date.setTranslateX(750); date.setTranslateY(20); 
				tempT.setTranslateX(200); tempT.setTranslateY(50);
				tempB.setTranslateX(220); tempB.setTranslateY(70);
				tempB.setWrappingWidth(640);
				comment.getChildren().add(numberOfLikes);
				comment.getChildren().add(postImgV);
				comment.getChildren().add(date);
				comment.getChildren().add(tempT);
				comment.getChildren().add(tempB);
				comment.getChildren().add(likes);
				holder.getChildren().add(comment);
				comment.setOnMousePressed(event -> {
					utility.save();
					PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(tempT.getText()), mainPost.getCommentAtIndex(commentNumber));
				});
				
				likes.setOnAction(event-> {
					if(tempP.hasUserLiked(user.getUsername())) {
						tempP.removeLike(user.getUsername());
					}else {
						tempP.addLike(user.getUsername());
					}
					utility.save();
					PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()), postNum);
				});
			}
		}else {
			VBox comment= new VBox(20);
			Image gif = new Image(new FileInputStream("images\\NoPostsGif.gif"));
			ImageView imageView= new ImageView(gif);
			comment.getChildren().add(imageView);
			holder.getChildren().add(comment);
		}
		postScroll.setContent(holder);
		midV.getChildren().add(postScroll);
		
		
		commentSumbit.setOnAction(event->{
			if(!commentBody.getText().equals(null)) {
				LinkedList<String> temp = checkForFakeWords(commentBody.getText(), dictionary);
				for(int i=0; i<temp.size(); i++) {
					System.out.println(temp.get(i));
				}
				if(!(temp.size()>0)) {
					TreeSet <String> tempT= (TreeSet<String>)user.getFollowersList().clone();
					utility.addPost(user.getUsername(), commentBody.getText());
					int numberOfPosts= utility.getPostBag().numberOfPosts()-1;
					utility.getAccountBag().getAccount(user.getUsername()).addPostIndex(numberOfPosts);
					utility.getPostBag().searchByPostNumber(postNum).addComment(numberOfPosts);
					for(int i=0; i<user.getFollowersList().size(); i++) {
						utility.getAccountBag().getAccount(tempT.pollFirst()).addToTimeline(numberOfPosts);
					}
					utility.save();
					PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()), postNum);
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
		
		commentWithAttachment.setOnAction(event->{
			FileChooser profileImage=new FileChooser();
			File reFile= profileImage.showOpenDialog(primaryStage);
			try {
				if(ImageIO.read(reFile)==null) {
					System.out.println("Not an image");
				}
				else {
					if(!postBody.getText().equals(null)) {
						LinkedList<String> temp = checkForFakeWords(postBody.getText(), dictionary);
						if(!(temp.size()>0)) {
							TreeSet <String> tempT= (TreeSet<String>)user.getFollowersList().clone();
							utility.addPost(user.getUsername(), commentBody.getText());
							int numberOfPosts= utility.getPostBag().numberOfPosts()-1;
							utility.getPostBag().searchByPostNumber(numberOfPosts).setAttachment(reFile);
							utility.getAccountBag().getAccount(user.getUsername()).addPostIndex(numberOfPosts);
							for(int i=0; i<user.getFollowersList().size(); i++) {
								utility.getAccountBag().getAccount(tempT.pollFirst()).addToTimeline(numberOfPosts);
							}
							utility.getPostBag().searchByPostNumber(postNum).addComment(numberOfPosts);
							utility.save();
							PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()), postNum);
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
			TreeSet <String> tempT= (TreeSet<String>)user.getFollowersList().clone();
			utility.addPost(user.getUsername(), commentBody.getText());
			int numberOfPosts= utility.getPostBag().numberOfPosts()-1;
			utility.getPostBag().searchByPostNumber(numberOfPosts).setAttachment(new File (notifYes.getAccessibleText()));
			utility.getPostBag().searchByPostNumber(postNum).addComment(numberOfPosts);
			utility.getAccountBag().getAccount(user.getUsername()).addPostIndex(numberOfPosts);
			for(int i=0; i<user.getFollowersList().size(); i++) {
				utility.getAccountBag().getAccount(tempT.pollFirst()).addToTimeline(numberOfPosts);
			}
			utility.save();
			notifStage.close();
			PostScreen.post(primaryStage, dictionary, utility.getAccountBag().getAccount(user.getUsername()), utility.getAccountBag().getAccount(displayedUsersPosts.getUsername()), postNum);
		});
		
		notifNo.setOnAction(event->{
			notifStage.close();
		});
		
		primaryStage.setOnCloseRequest(event -> {
			utility.save();
		});
		Scene scene = new Scene(base,1500,800, Color.BLUE);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
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

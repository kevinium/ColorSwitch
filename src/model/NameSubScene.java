package model;

import application.Main;
import application.SavedGame;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameViewManager;
import view.ViewManager;

public class NameSubScene extends SubScene{
	private AnchorPane root3;
	private PauseSubScene pausePane;
	private GameViewManager game;
	private TextField enterByUser;
	public NameSubScene(PauseSubScene pausePane,GameViewManager game) {
		super(new AnchorPane(), 250, 200);
		// TODO Auto-generated constructor stub
		this.pausePane = pausePane;
		this.game = game;
		setLayoutX(50);
		setLayoutY(-200);
		root3 = (AnchorPane) this.getRoot();
		root3.setId("popOut");
		
		msg();
		createNextButton();
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(350);

		
		transition.play();


	}
	
	public void moveOut() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(-200);

		
		transition.play();


	}
	
	private void msg() {
		Label enterMsg1 = new Label("Enter Name");
		enterMsg1.setId("ExitMsg");
		
		enterMsg1.setLayoutX(40);
		enterMsg1.setLayoutY(20);
		root3.getChildren().add(enterMsg1);
		
		enterByUser = new TextField(); 
		enterByUser.setId("enterNameButton");
		enterByUser.setLayoutX(0);
		enterByUser.setLayoutY(70);
		enterByUser.setPrefHeight(50);
		enterByUser.setPrefWidth(250); 
		enterByUser.setFocusTraversable(false);
		
		root3.getChildren().add(enterByUser);
		
	}
	
	private void createNextButton() {
		Button homeButton = new Button();
		homeButton.setId("hButton");
		homeButton.setPrefHeight(50);
		homeButton.setPrefWidth(50);
		homeButton.setLayoutX(100);
		homeButton.setLayoutY(130);
		homeButton.setFocusTraversable(false);
		root3.getChildren().add(homeButton);
		
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moveOut();
				if(enterByUser.getText().equals("god") || enterByUser.getText().equals("God") || enterByUser.getText().equals("GOD")) {
					Main.setGod();
				}
				game.setName(enterByUser.getText());
				//System.out.println(ViewManager.getSavedList().get(0).getName());
				pausePane.unBlurPause();
				pausePane.moveOutMain();
				Main.addSavedList(new SavedGame(game));
				pausePane.backToMain();	
				//ViewManager.addSavedList(game);
								
			}

		});
	}

}

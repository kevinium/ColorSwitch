package model;

import java.util.ArrayList;

import application.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameViewManager;
import view.ViewManager;

public class SavedGamesSubScene extends ColorSubscene {
	private ArrayList<Button> gamesButton;
	private ViewManager mainScreen;
	public SavedGamesSubScene(ViewManager mainScreen, double width, double height,int state,GameViewManager game,PauseSubScene pausePane) {
		super(mainScreen, width, height,state,game,pausePane);
		gamesButton = new ArrayList<>();
		this.mainScreen = mainScreen;
		getBackButton().setLayoutX(250);
		prefWidth(300);
		prefHeight(500);
		minWidth(300);
		minHeight(500);
		maxWidth(300);
		maxHeight(500);
		setLayoutX(50);
		setLayoutY(-500);

		Inside();
	}
	public void moveSubSceneIn() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);


		transition.setToY(+550);


		transition.play();


	}
	public void moveOut() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);


		transition.setToY(0);


		transition.play();


	}
	
	private void Inside() {
		InfoLabel cg = new InfoLabel("Choose Game");
		cg.setLayoutX(70);
		cg.setLayoutY(20);
		cg.setPrefWidth(200);

		createButtons();

		root2.getChildren().add(cg);
		
		for(int i=0;i<gamesButton.size();i++) {
			chooseGameHandler(i);
			
		}
	}
	
	private void chooseGameHandler(int i) {
		gamesButton.get(i).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				Main.getSavedGames().get(i).createGameLoop();		
//				Main.getSavedGames().get(i).input();
//				Main.getSavedGames().get(i).createNewGame(mainScreen.getMainStage());
				game = new GameViewManager(Main.getSavedGames().get(i));
				game.createNewGame(mainScreen.getMainStage());
				Main.getSavedGames().remove(i);
			}

		});
	}
	
	public void createButtons() {
		for(int i=0;i<Main.getSavedGames().size();i++) {
			Button temp = new Button();
			temp.setPrefHeight(40);
			if(Main.getSavedGames().size()>0) {
				temp.setText(Main.getSavedGames().get(i).getName());
			}
			//temp.setText("Game " + i );
			temp.setId("menuChoices");
			//temp
			temp.setLayoutX(30);
			temp.setLayoutY(60+60*i);
			root2.getChildren().add(temp);
			gamesButton.add(temp);
		}
	}
}

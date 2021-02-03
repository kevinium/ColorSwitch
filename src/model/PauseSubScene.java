package model;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameViewManager;
import view.ViewManager;

public class PauseSubScene extends SubScene{
	private AnchorPane root2;
	private GameViewManager game;
	private NameSubScene nameSubScene;
	private SettingsSubScene settingsSubScene;
	public PauseSubScene(GameViewManager game) {
		super(new AnchorPane(), 350, 550);
		// TODO Auto-generated constructor stub
		this.game = game;
		setLayoutX(30);
		setLayoutY(-550);
		root2 = (AnchorPane) this.getRoot();
		root2.setId("PausepopOut");
		getPlayButton();
		createSaveGame();
		createSettings();
		createHomeButton();
		
		createEnterNameSubScene();
		createSettingsSubScene();
		
	}
	
	private void getPlayButton() {
		 Button playButton = new Button();
		 playButton.setId("playButton");
		 playButton.setPrefHeight(100);
		 playButton.setPrefWidth(100);
		 playButton.setLayoutX(120);
		 playButton.setLayoutY(40);
		 playButton.setFocusTraversable(false);
		root2.getChildren().add(playButton);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("pexit");
				moveOut();
				game.unBlur();
								
			}

		});
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(600);

		
		transition.play();


	}
	
	public void moveOut() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(-600);

		
		transition.play();


	}
	
	private void createSaveGame() {
		Button saveButton = new Button("Save Game");
		saveButton.setId("saveButton");
		saveButton.setPrefHeight(80);
		saveButton.setPrefWidth(300);
		saveButton.setLayoutX(25);
		saveButton.setLayoutY(220);
		saveButton.setFocusTraversable(false);
		root2.getChildren().add(saveButton);
		
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				nameSubScene.moveSubScene();
				BoxBlur blur = new BoxBlur(5,5,5);
				for(Node x: root2.getChildren()) {
					if(x != nameSubScene) {
						x.setEffect(blur);
					}
				}
				
			}

		});
	}
	
	private void createEnterNameSubScene() {
		nameSubScene = new NameSubScene(this,game);
		root2.getChildren().add(nameSubScene);
	}
	private void createSettingsSubScene() {
		settingsSubScene = new SettingsSubScene(null,250,200,2,null,this);
		root2.getChildren().add(settingsSubScene);
	}
	
	public void unBlurPause() {
		// TODO Auto-generated method stub
		for(Node x:root2.getChildren()) {
			x.setEffect(null);
		}
		
		
	}
	
	private void createSettings() {
		Button settingsButton = new Button();
		settingsButton.setId("settingsButton");
		settingsButton.setPrefHeight(100);
		settingsButton.setPrefWidth(100);
		settingsButton.setLayoutX(40);
		settingsButton.setLayoutY(400);
		settingsButton.setFocusTraversable(false);
		root2.getChildren().add(settingsButton);
		
		settingsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				settingsSubScene.moveSubSceneIn();
				BoxBlur blur = new BoxBlur(5,5,5);
				for(Node x: root2.getChildren()) {
					if(x != settingsSubScene) {
						x.setEffect(blur);
					}
				}
				
								
			}

		});
	}
	
	protected void moveOutMain() {
		// TODO Auto-generated method stub
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		transition.setToX(0);
		transition.play();
		
	}
	protected void backToMain() {
		game.getGameStage().close();
		game.getGameTimer().stop();
		ViewManager viewManager = new ViewManager();
		
		viewManager.createMenu(game.getGameStage());
		
	}
	
	private void createHomeButton() {
		Button homeButton = new Button();
		homeButton.setId("homeButton");
		homeButton.setPrefHeight(100);
		homeButton.setPrefWidth(100);
		homeButton.setLayoutX(210);
		homeButton.setLayoutY(398);
		homeButton.setFocusTraversable(false);
		root2.getChildren().add(homeButton);
		
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moveOutMain();
				backToMain();				
								
			}

		});
	}

}

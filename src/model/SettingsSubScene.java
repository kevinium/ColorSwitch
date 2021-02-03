package model;


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

public class SettingsSubScene extends ColorSubscene {
	private InfoLabel soundLabel;
	private InfoLabel musicLabel;
	private Button musicButton;
	private Button soundButton;
	private static boolean isMusic = true, isSound = true;
	private int state;
	public SettingsSubScene(ViewManager mainScreen, double width, double height,int state,GameViewManager game,PauseSubScene pausePane) {
		super(mainScreen,width,height,state,game,pausePane);
		this.state = state;
		if(state==0) {
		setLayoutX(400);
		setLayoutY(10);
		}
		else {
			setLayoutX(-250);
			setLayoutY(300);
		}
		
		Inside();
		
		/*AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setId("popOut");*/
		
		
	}
	
	private void Inside() {
		soundLabel = new InfoLabel("SOUND");
		soundLabel.setLayoutX(10);
		soundLabel.setLayoutY(70);
		
		musicLabel = new InfoLabel("MUSIC");
		musicLabel.setLayoutX(10);
		musicLabel.setLayoutY(140);
		
		musicButton = new Button();
		musicButton.setLayoutX(160);
		musicButton.setLayoutY(140);
		musicButton.setPrefHeight(40);
		musicButton.setPrefWidth(50);
		musicButton.setMaxHeight(40);
		musicButton.setMaxWidth(50);
		musicButton.setMinHeight(40);
		musicButton.setMinWidth(50);
		if(isMusic) {
			musicButton.setId("onButton");
		}
		else {
			musicButton.setId("offButton");
		}
		
		musicButton.setFocusTraversable(false);
		
		UseMusicButton();
		
		soundButton = new Button();
		soundButton.setLayoutX(160);
		soundButton.setLayoutY(70);
		soundButton.setPrefHeight(40);
		soundButton.setPrefWidth(50);
		soundButton.setMaxHeight(40);
		soundButton.setMaxWidth(50);
		soundButton.setMinHeight(40);
		soundButton.setMinWidth(50);
		if(isSound) {
			soundButton.setId("onButton");
		}
		else{
			soundButton.setId("offButton");
		}
		
		soundButton.setFocusTraversable(false);
		
		UseSoundButton();
		addInPane();
	}
	

	public void moveSubSceneIn() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		
		if(state==0) {
			transition.setToX(-250);
		}
		else {
			transition.setToX(250);
		}
			
		
		transition.play();


	}
	
	public void moveOut() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);


		if(state==0) {
		transition.setToX(0);
		}
		else {
			transition.setToX(-250);
		}


		transition.play();


	}
	
	private void UseMusicButton() {
		musicButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(isMusic) {
					isMusic = false;
					Main.pauseMusic();
					musicButton.setId("offButton");
				}
				else {
					isMusic = true;
					Main.playMusic();
					musicButton.setId("onButton");
				}
			}


		});
	}
	
	private void UseSoundButton() {
		soundButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(isSound) {
					isSound = false;
					soundButton.setId("offButton");
				}
				else {
					isSound = true;
					soundButton.setId("onButton");
				}
			}


		});
	}
	
	private void addInPane() {
		getpane().getChildren().add(soundLabel);
		getpane().getChildren().add(musicLabel);
		getpane().getChildren().add(musicButton);
		getpane().getChildren().add(soundButton);
	}

	public static boolean getMusc() {
		// TODO Auto-generated method stub
		return isMusic;
	}
	public static boolean getSound() {
		// TODO Auto-generated method stub
		return isSound;
	}
}

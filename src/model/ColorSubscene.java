package model;

import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.*;
import view.GameViewManager;
import view.ViewManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;

public abstract class ColorSubscene extends SubScene{
	protected AnchorPane root2;
	private Button backButton;
	private ViewManager mainScreen;
	private int state;
	private PauseSubScene pausePane;
	GameViewManager game;
	public ColorSubscene(ViewManager mainScreen, double width, double height,int state,GameViewManager game, PauseSubScene pausePane) {
		super(new AnchorPane(), width, height);
		this.state = state;
		this.game = game;
		this.pausePane = pausePane;
		// TODO Auto-generated constructor stub
		prefWidth(300);
		prefHeight(200);
		this.mainScreen = mainScreen;
		root2 = (AnchorPane) this.getRoot();
		createBackButton();
		root2.setId("popOut");
	
		
	}
	
	public AnchorPane getpane() {
		return (AnchorPane) this.root2;
	}
	
	protected abstract void moveOut();
	private void createBackButton() {
		backButton = new Button();
		backButton.setLayoutX(200);
		backButton.setLayoutY(0);
		backButton.setPrefHeight(40);
		backButton.setPrefWidth(40);
		backButton.setMaxHeight(40);
		backButton.setMaxWidth(40);
		backButton.setMinHeight(40);
		backButton.setMinWidth(40);
		backButton.setId("backButton");
		backButton.setFocusTraversable(false);
		root2.getChildren().add(backButton);
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moveOut();
				backToMain();

			}


		});
		}
	
	private void backToMain() {
		if(state==0) {
			mainScreen.getLogo().setEffect(null);
			mainScreen.getNewGame().setEffect(null);
			mainScreen.getExitButton().setEffect(null);
			mainScreen.getHelpButton().setEffect(null);
			mainScreen.getSavedGame().setEffect(null);
			mainScreen.getSettingButton().setEffect(null);
		}
		else if (state==1) { //game pg blur
				game.unBlur();
		}
		else {
			pausePane.unBlurPause();
		}
	}
	public Button getBackButton() {
		return backButton;
	}

}

package model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import application.Main;
import application.SavedGame;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameViewManager;
import view.ViewManager;

public class ExitSubScene extends ColorSubscene {
	
	 ViewManager mainScreen;

	public ExitSubScene(ViewManager mainScreen, double width, double height,int state,GameViewManager game,PauseSubScene pausePane) {
		super(mainScreen, width, height,state,game,pausePane);
		this.mainScreen = mainScreen;
		prefWidth(200);
		prefHeight(200);
		setLayoutX(75);
		setLayoutY(-200);
		ExitpopBack();
		QuitGame();
		msg();
	}
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(420);

		
		transition.play();


	}
	
	public void moveOut() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(-200);

		
		transition.play();


	}
	
	private void ExitpopBack() {
		SmallButton stayButton  = new SmallButton("Stay");
		stayButton.setId("exitButton");
		stayButton.setLayoutX(150);
		stayButton.setLayoutY(150);
		stayButton.setPrefWidth(90);
		stayButton.setPrefHeight(15);
		root2.getChildren().add(stayButton);
	
	stayButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			moveOut();
			mainScreen.getLogo().setEffect(null);
			mainScreen.getNewGame().setEffect(null);
			mainScreen.getExitButton().setEffect(null);
			mainScreen.getHelpButton().setEffect(null);
			mainScreen.getSavedGame().setEffect(null);
			mainScreen.getSettingButton().setEffect(null);

		}


	});
	}
	
	private void QuitGame() {
		SmallButton quitButton  = new SmallButton("Quit");
		quitButton.setId("exitButton");
		quitButton.setLayoutX(10);
		quitButton.setLayoutY(150);
		quitButton.setPrefWidth(90);
		quitButton.setPrefHeight(15);
		root2.getChildren().add(quitButton);
	
		quitButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			try {
				Main.serialize();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			mainScreen.getMainStage().close();


		}


	});
	}
	
	private void msg() {
		Label quitMsg1 = new Label("Are you sure");
		Label quitMsg2 = new Label(" you want to Quit?");
		quitMsg1.setId("ExitMsg");
		quitMsg2.setId("ExitMsg");
		quitMsg1.setLayoutX(40);
		quitMsg1.setLayoutY(60);
		quitMsg2.setLayoutX(10);
		quitMsg2.setLayoutY(100);
		root2.getChildren().add(quitMsg1);
		root2.getChildren().add(quitMsg2);
		
	}
}



package view;

import javafx.event.Event;
import javafx.scene.effect.BoxBlur;
import java.util.ArrayList;

import application.Main;
import model.InfoLabel;
import model.SavedGamesSubScene;

import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.CSButton;
import model.ColorSubscene;
import model.ExitSubScene;
import model.HelpSubScene;
import model.SettingsSubScene;
import model.SmallButton;
import javafx.event.ActionEvent;

public class ViewManager {
	private ImageView logo;
	
	private static final int HEIGHT = 650;
	private static final int WIDTH = 400;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private SettingsSubScene settingsSubScene;
	private ExitSubScene exitSubScene;
	
	private SavedGamesSubScene savedGamesSubScene;
	private CSButton newGameButton;
	private CSButton savedGamesButton;
	private SmallButton exitButton;
	private SmallButton helpButton ;
	private Button settingsButton;
	private HelpSubScene helpSubScene;
	private GameViewManager gameViewManager;
	
	
	public void createMenu(Stage gameStage) {
		gameStage.hide();
		mainStage.show();
	}
	
	public ViewManager() {
		mainPane = new AnchorPane();
		mainPane.setId("mainPane");
		mainScene = new Scene(mainPane, WIDTH,HEIGHT);
		mainScene.getStylesheets().add("model/resources/mainPageStyle.css");
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createImage();
		createButtons();
		createSettingsSubScene();
		createExitSubscene();
		createSavedGamesSubScene();
		createHelpSubScreen();
		//createCredits();
		//System.out.println(Main.getSavedGames().size());
		
//		for(int i=0;i<Main.getSavedGames().size();i++) {
//			System.out.println(Main.getSavedGames().get(i).getName());
//		}
	}
	
	private void createImage() {
		Image logoi = new Image("model/resources/logo.png");
		logo = new ImageView(logoi);
		logo.setFitHeight(210);
		logo.setFitWidth(210);
		logo.setLayoutX(90);
		logo.setLayoutY(10);
		mainPane.getChildren().add(logo);
		
		
	}

	private void createButtons() {
		createSettingsButton();
		createNewGameButton();
		createSavedGamesButton();		
		createExitButton();		
		createHelpButton();						
	}
	private void createNewGameButton() {
		newGameButton  = new CSButton("New Game");
		newGameButton.setId("newGameButton");
		newGameButton.setLayoutX(105);
		newGameButton.setLayoutY(250);
		
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				startNewGame();
				
			}
			
		});
	}
	
	public void startNewGame() {
		gameViewManager = (new GameViewManager());
		gameViewManager.createNewGame(mainStage);
	}
	
	private void createSavedGamesButton() {
		savedGamesButton  = new CSButton("Saved Games");
		savedGamesButton.setId("newGameButton");
		savedGamesButton.setLayoutX(105);
		savedGamesButton.setLayoutY(350);
		
		savedGamesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				BoxBlur blur = new BoxBlur(5,5,5);
				logo.setEffect(blur);
				newGameButton.setEffect(blur);
				exitButton.setEffect(blur);
				helpButton.setEffect(blur);
				savedGamesButton.setEffect(blur);

				savedGamesSubScene.moveSubSceneIn();
			}


		});
	}
	
	
	
	private void createSettingsSubScene() {
		settingsSubScene = new SettingsSubScene(this,250,200,0,null,null);
		mainPane.getChildren().add(settingsSubScene);	
	}
	
	private void createSettingsButton() {
	    settingsButton = new Button();
		settingsButton.setLayoutX(350);
		settingsButton.setLayoutY(10);
		settingsButton.setPrefHeight(40);
		settingsButton.setPrefWidth(40);
		settingsButton.setMaxHeight(40);
		settingsButton.setMaxWidth(40);
		settingsButton.setMinHeight(40);
		settingsButton.setMinWidth(40);
		settingsButton.setId("settingsButton");
		mainPane.getChildren().add(settingsButton);
		
		settingsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				BoxBlur blur = new BoxBlur(5,5,5);
				logo.setEffect(blur);
				settingsSubScene.moveSubSceneIn();


			}


		});
	}
	
	private void createExitSubscene() {
		exitSubScene = new ExitSubScene(this,250,200,0,null,null);
		mainPane.getChildren().add(exitSubScene);
		
	}
	
	private void createExitButton() {
		exitButton  = new SmallButton("Exit");
		exitButton.setId("exitButton");
		exitButton.setLayoutX(30);
		exitButton.setLayoutY(500);
		mainPane.getChildren().add(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				exitSubScene.moveSubScene();
				BoxBlur blur = new BoxBlur(5,5,5);
				logo.setEffect(blur);
				newGameButton.setEffect(blur);
				exitButton.setEffect(blur);
				helpButton.setEffect(blur);
				savedGamesButton.setEffect(blur);
				settingsButton.setEffect(blur);

			}

		});
	}
	private void createSavedGamesSubScene(){
		savedGamesSubScene = new SavedGamesSubScene(this,300,500,0,null,null);
		mainPane.getChildren().add(savedGamesSubScene);

	}
	
	private void createHelpButton() {
		helpButton  = new SmallButton("Help");
		helpButton.setId("helpButton");
		helpButton.setLayoutX(250);
		helpButton.setLayoutY(500);
		mainPane.getChildren().add(newGameButton);
		mainPane.getChildren().add(savedGamesButton);
		mainPane.getChildren().add(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				helpSubScene.moveSubScene();
				BoxBlur blur = new BoxBlur(5,5,5);
				logo.setEffect(blur);
				newGameButton.setEffect(blur);
				exitButton.setEffect(blur);
				helpButton.setEffect(blur);
				savedGamesButton.setEffect(blur);
				settingsButton.setEffect(blur);

			}

		});
	}
	
	private void createHelpSubScreen() {
		helpSubScene = new HelpSubScene(this,300,500,0,null,null);
		mainPane.getChildren().add(helpSubScene);
	}
	
	//copyright - github link
	/*private void createCredits() {
		WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
		Hyperlink credits = new Hyperlink("Kevin Paul and Ananya Singh");
		credits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                webEngine.load("https://github.com/kevinium");
            }
        });
	}*/
	
	public Stage getMainStage() {
		return (Stage) mainStage;
	}
	
	public CSButton getNewGame() {
		return newGameButton;
	}
	public CSButton getSavedGame() {
		return savedGamesButton;
	}
	public SmallButton getExitButton() {
		return exitButton;
	}
	public SmallButton getHelpButton() {
		return helpButton;
	}
	public ImageView getLogo() {
		return logo;
	}
	
	public Button getSettingButton() {
		return settingsButton;
	}

	
	
	
	

}

package model;
import application.Main;
import application.Obstacle;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameViewManager;
import view.ViewManager;

public class LosingSubScene  extends SubScene{

	private GameViewManager mainScreen;
	protected AnchorPane root2;
	private SmallButton homeButton;
	private SmallButton yesButton;
	private Button retryButton;
	private int score;
	private double posBallRevived;
	private InfoLabel cg;
	
	public LosingSubScene(GameViewManager gameViewManager, double width, double height) {
		super(new AnchorPane(), width, height);
		setLayoutX(400);
		setLayoutY(150);
		
		
		// TODO Auto-generated constructor stub
		prefWidth(300);
		prefHeight(200);
		this.mainScreen = gameViewManager;
		
		root2 = (AnchorPane) this.getRoot();
		createHomeButton();
		createYesButton();
		createRetryButton(100,150);
		root2.setId("popOut");
		
		
		/*AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setId("popOut");*/
		
		
	}
	private void Inside() {
		cg= new InfoLabel("Revive?");
		InfoLabel ScoreLabel = new InfoLabel("Score: "+score);
		InfoLabel BestScoreLabel = new InfoLabel("Best Score: "+Main.getHighScore());
		ScoreLabel.setLayoutX(80);
		ScoreLabel.setLayoutY(20);
		ScoreLabel.setPrefWidth(200);
		cg.setLayoutX(80);
		cg.setLayoutY(80);
		cg.setPrefWidth(200);
		BestScoreLabel.setLayoutX(50);
		BestScoreLabel.setLayoutY(50);
		BestScoreLabel.setPrefWidth(200);
		root2.getChildren().add(cg);
		root2.getChildren().add(ScoreLabel);
		root2.getChildren().add(BestScoreLabel);
		
	}
	public void moveSubSceneIn(int score) {
		this.score = score;
		if(score>Main.getHighScore()) {
			Main.setHighScore(this.score);
		}
		Inside();
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		
		transition.setToX(-325);
		transition.play();
	}
	protected void moveOut() {
		// TODO Auto-generated method stub
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		transition.setToX(10);
		transition.play();
		
	}
	private void backToMain() {
		mainScreen.getGameStage().close();
		mainScreen.getGameTimer().stop();
		ViewManager viewManager = new ViewManager();
		
		viewManager.createMenu(mainScreen.getGameStage());
		
	}
	private void createHomeButton() {
		homeButton = new SmallButton("NO");
		homeButton.setLayoutX(140);
		homeButton.setLayoutY(110);
		homeButton.setPrefWidth(90);
		
		homeButton.setFocusTraversable(false);
		homeButton.setId("exitButton");
		root2.getChildren().add(homeButton);
		
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moveOut();
				backToMain();

			}


		});
	}
	private void createHomeButton2() {
		Button homeButton = new Button();
		homeButton.setId("homeButton2");
		homeButton.setPrefHeight(50);
		homeButton.setPrefWidth(50);
		homeButton.setLayoutX(140);
		homeButton.setLayoutY(130);
		homeButton.setFocusTraversable(false);
		root2.getChildren().add(homeButton);
		
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moveOut();
				backToMain();				
								
			}

		});
	}
	private void createYesButton() {
		yesButton = new SmallButton("YES");
		yesButton.setLayoutX(20);
		yesButton.setLayoutY(110);
		yesButton.setPrefWidth(90);
		
		
		yesButton.setId("yesButton");
		root2.getChildren().add(yesButton);
		
		yesButton.setFocusTraversable(false);
		
		yesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(Main.isGod() || score >=3) {
					moveOut();
					mainScreen.unBlur(true);
					
					if(!Main.isGod()) {
						mainScreen.reduceScore(3);
					}
				}
				else {
					cg.setText("Less Score");
					cg.setLayoutX(cg.getLayoutX() - 18);
					root2.getChildren().remove(yesButton);
					root2.getChildren().remove(homeButton);
					root2.getChildren().remove(retryButton);
					createHomeButton2();
					createRetryButton(40,130);
				}
				

			}


		});
	}
	
	private void createRetryButton(int posx,int posy) {
		retryButton = new Button();
		retryButton.setId("retryButton");
		retryButton.setPrefHeight(50);
		retryButton.setPrefWidth(50);
		retryButton.setLayoutX(posx);
		retryButton.setLayoutY(posy);
		retryButton.setFocusTraversable(false);
		root2.getChildren().add(retryButton);
		
		retryButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				moveOut();
				mainScreen.getGameStage().close();
				mainScreen.getGameTimer().stop();
				ViewManager view = new ViewManager();
				view.startNewGame();
				
			}

		});
	}


}

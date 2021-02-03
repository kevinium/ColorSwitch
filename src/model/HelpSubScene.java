package model;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import view.GameViewManager;
import view.ViewManager;

public class HelpSubScene extends ColorSubscene{
	
	public HelpSubScene(ViewManager mainScreen, double width, double height,int state,GameViewManager game,PauseSubScene pausePane) {
		super(mainScreen, width, height,state,game,pausePane);
		getBackButton().setLayoutX(250);
		prefWidth(200);
		prefHeight(200);
		setLayoutX(50);
		setLayoutY(-500);
		msg();
		addImages();
	}
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(550);

		
		transition.play();


	}
	
	public void moveOut() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		transition.setToY(-400);

		
		transition.play();


	}
	
	
	private void msg() {
		Label ins1 = new Label("How to Play?");
		Label ins2 = new Label("To make the Ball Jump:");
		Label ins3 = new Label("Press ");
		Label ins4 = new Label("To Pause:");
		Label ins5 = new Label("Press ");
		Label ins6 = new Label("Or ");
		Label ins7 = new Label("To See Instructions:");
		Label ins8 = new Label("Press ");
		ins1.setId("ExitMsg");
		ins2.setId("ExitMsg");
		ins3.setId("ExitMsg");
		ins4.setId("ExitMsg");
		ins5.setId("ExitMsg");
		ins6.setId("ExitMsg");
		ins7.setId("ExitMsg");
		ins8.setId("ExitMsg");


		ins1.setLayoutX(70);
		ins1.setLayoutY(10);
		ins2.setLayoutX(10);
		ins2.setLayoutY(50);
		ins3.setLayoutX(10);
		ins3.setLayoutY(170);
		ins6.setLayoutX(180);
		ins6.setLayoutY(170);
		ins4.setLayoutX(90);
		ins4.setLayoutY(300);
		ins5.setLayoutX(10);
		ins5.setLayoutY(350);
		
		ins7.setLayoutX(10);
		ins7.setLayoutY(400);
		ins8.setLayoutX(10);
		ins8.setLayoutY(450);
		
		root2.getChildren().add(ins1);
		root2.getChildren().add(ins2);
		root2.getChildren().add(ins3);
		root2.getChildren().add(ins4);
		root2.getChildren().add(ins5);
		root2.getChildren().add(ins6);
		root2.getChildren().add(ins7);
		root2.getChildren().add(ins8);
		
	}
	
	private void addImages() {
		Image space = new Image("model/resources/SpaceWHITE.png");
		ImageView spacebar = new ImageView(space);
		spacebar.setFitHeight(150);
		spacebar.setFitWidth(150);
		spacebar.setLayoutX(120);
		spacebar.setLayoutY(60);
		root2.getChildren().add(spacebar);
		
		Image arrowup = new Image("model/resources/UPWHITE.png");
		ImageView arrow = new ImageView(arrowup);
		arrow.setFitHeight(60);
		arrow.setFitWidth(60);
		arrow.setLayoutX(170);
		arrow.setLayoutY(210);
		root2.getChildren().add(arrow);
		
		Image Esc = new Image("model/resources/EscWHITE.png");
		ImageView esc = new ImageView(Esc);
		esc.setFitHeight(60);
		esc.setFitWidth(60);
		esc.setLayoutX(170);
		esc.setLayoutY(335);
		root2.getChildren().add(esc);
		
		Image iImg = new Image("model/resources/iWhite.png");
		ImageView i = new ImageView(iImg);
		i.setFitHeight(60);
		i.setFitWidth(60);
		i.setLayoutX(170);
		i.setLayoutY(430);
		root2.getChildren().add(i);
		
	}

}

package application;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import view.GameViewManager;

public class SavedGame implements Serializable{

	private transient ArrayList<Obstacle> Obstacles;
	private ArrayList<SavedObs> ObsData;
	private transient ArrayList<Star> stars;
	private ArrayList<Double> StarData;
	private transient ArrayList<ChangeColor> changeColors;
	private ArrayList<Double> CCData;
	private transient Circle ball;
	private String ballId;
	private Double ballPos;
	private int score;
	private String name;
	private double angle;
	private int obSpeed;
	
	private static final int GAME_WIDTH = 400;
	private static final int GAME_HEIGHT = 650;
	private static final long serialVersionUID = 42L;
	
	public SavedGame(GameViewManager game) {
		this.Obstacles = new ArrayList<>(game.getObstacles());
		this.stars = new ArrayList<>(game.getStars());
		this.changeColors = new ArrayList<>(game.getChangeColor());
		this.ball = game.getBall();
		this.score = game.getScore();
		this.name = game.getName();
		this.angle = game.getAngle();
		this.obSpeed = game.getObSpeed();
		ObsData = new ArrayList<>();
		for(Obstacle x: Obstacles) {
			ObsData.add(new SavedObs(x));
		}
		StarData = new ArrayList<>();
		for(Star x:stars) {
			StarData.add(x.getImg().getLayoutY());
		}
		CCData = new ArrayList<>();
		for(ChangeColor x:changeColors) {
			CCData.add(x.getImg().getLayoutY());
		}
		ballId = game.getBall().getId();
		ballPos = game.getBall().getLayoutY();
		
	}
	public void init() {
		ball = new Circle(10, Color.TRANSPARENT);
		
		ball.setLayoutX(GAME_WIDTH/2);
		ball.setLayoutY(ballPos);
		ball.setId(ballId);
	
		Obstacles = new ArrayList<>();
		for(SavedObs x:ObsData) {
			Obstacle k = null;
			if(x.type == 6) {
				CompoundObstacle kk = new CompoundObstacle();
				for(SavedObs i:x.getPrim()) {
					PrimitiveObstacle r = null;
					if(i.type == 1) {
						r = new Ring();
					}
					else if(i.type == 2) {
						r = new Square();
					}
					else if(i.type == 3) {
						r = new BallRing();
					}
					else if(i.type == 4) {
						r = new BallSquare();
					}
					else if(x.type == 5){
						k = new Cross();
					}
					r.getImg().setLayoutX(i.x);
					r.getImg().setLayoutY(i.y);
					r.getImg().setFitHeight(i.h);
					r.getImg().setFitWidth(i.w);
					r.getImg().setRotate(i.rot);
					kk.getList().add(r);
					
				}
				Obstacles.add(kk);
			}
			else {
				if(x.type == 1) {
					k = new Ring();
				}
				else if(x.type == 2) {
					k = new Square();
				}
				else if(x.type == 3) {
					k = new BallRing();
				}
				else if(x.type == 4) {
					k = new BallSquare();
				}
				else if(x.type == 5){
					k = new Cross();
				}
				k.getImg().setLayoutX(x.x);
				k.getImg().setLayoutY(x.y);
				k.getImg().setFitHeight(x.h);
				k.getImg().setFitWidth(x.w);
				k.getImg().setRotate(x.rot);
				Obstacles.add(k);
			}
			
			
		}
		changeColors = new ArrayList<>();
		for(Double x:CCData) {
			ChangeColor k = new ChangeColor();
			k.getImg().setLayoutY(x);
			changeColors.add(k);
			
		}
		stars = new ArrayList<>();
		for(Double x:StarData) {
			Star k = new Star();
			k.getImg().setLayoutY(x);
			stars.add(k);
			
		}
		
	}
	public ArrayList<Obstacle> getObstacles(){
		return Obstacles;
	}
	public ArrayList<Star> getStars(){
		return stars;
	}
	public ArrayList<ChangeColor> getChangeColor(){
		return changeColors;
	}
	public String getName() {
		return name;
	}
	public Circle getBall() {
		return ball;
	}
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	public double getAngle() {
		// TODO Auto-generated method stub
		return angle;
	}
	public int getObSpeed() {
		// TODO Auto-generated method stub
		return obSpeed;
	}
}

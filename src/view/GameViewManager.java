package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.BallRing;
import application.BallSquare;
import application.ChangeColor;
import application.CompoundObstacle;
import application.Cross;
import model.HelpSubScene;
import model.LosingSubScene;
import application.Obstacle;
import application.PrimitiveObstacle;
import model.PauseSubScene;
import model.SettingsSubScene;
import application.Ring;
import application.SavedGame;
import application.Square;
import application.Star;
import javafx.scene.Node;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameViewManager {
	private AudioClip jumpNote;
	private boolean inTime;
	private boolean shield;
	private boolean antiGrav;
	private AnchorPane gamePane;
	private Timeline animation;
	private Timeline animation2;
	private Scene gameScene;
	private Stage gameStage;
	private String gameName;
	private static final int GAME_WIDTH = 400;
	private static final int GAME_HEIGHT = 650;
	private Stage menuStage;
	private Button PauseButton;
	private Button helpButton;
	private Label scorePrint;
	private HelpSubScene helpSubScene;
	private PauseSubScene pauseSubScene;
	private int target;
	private Circle ball;
	private AnimationTimer gameTimer ;
	private static double currPos;
	private static boolean jump = true;
	private static boolean first = true;
	private static double g = 0;
	private static double u = 0;
	private static ImageView logo;
	private ArrayList<Obstacle> Obstacles;
	private ArrayList<Star> stars;
	private ArrayList<ChangeColor> changeColors;
	private static double angle = 0;
	private static LosingSubScene losingSubScene;
	private static long animationStart;
	private int score;

	private static Canvas canvas;
	private static int mint = 0;
	private static boolean disableSpace = false;
	private int obSpeed;
	private Obstacle helpOb;
	private static ArrayList<Integer> primitiveObstacles;
	private double pos;
	private double starPos;
	private double switchPos;
	private ImageView powerUp;
	private ImageView powerUp2;
	private GraphicsContext gg;
	private boolean upp = false;
	private List<Particle> particles = new ArrayList<>();
	private Emitter emitter = new FireEmitter();
	private void onUpdate() {
		//gg.setGlobalAlpha(0.1);
		gg.setGlobalBlendMode(BlendMode.SRC_OVER);
		gg.setFill(Color.WHITE);
		gg.clearRect(0,0,GAME_WIDTH, GAME_HEIGHT);
		if(upp) {
			particles.addAll(emitter.emit(ball.getLayoutX(), ball.getLayoutY()));
		}
		int i = 0;
		for(Iterator<Particle> it = particles.iterator();it.hasNext();) {
			Particle p = it.next();
			p.update(i%2 == 0);
			i++;
			if(!p.isAlive()) {
				it.remove();
				continue;
			}
			p.render(gg);
		}
	}
	public GameViewManager() {
		
		String path = "src/model/resources/jump.wav";  
		jumpNote = new AudioClip(new File(path).toURI().toString());
		jump = true;
		first = true;
		inTime = false;
		shield = false;
		antiGrav = false;
		Image s = new Image("model/resources/shield.png");
		powerUp = new ImageView(s);
		powerUp.setLayoutX(10);
		powerUp.setLayoutY(100);
		powerUp.setFitHeight(150);
		powerUp.setFitWidth(150);
		Image q = new Image("model/resources/antiGravLogo.png");
		powerUp2 = new ImageView(q);
		powerUp2.setLayoutX(GAME_WIDTH-10 -150);
		powerUp2.setLayoutY(100);
		powerUp2.setFitHeight(150);
		powerUp2.setFitWidth(150);
		g = 0;
		u = 0;
		angle = 0;
		score=0;
		//highScore = 0;
		disableSpace = false;
		gameTimer = null;
		Obstacles = new ArrayList<>();
		stars = new ArrayList<>();
		changeColors = new ArrayList<>();
		gamePane = new AnchorPane();
		gamePane.setId("gamePane");
		gameScene = new Scene(gamePane, GAME_WIDTH,GAME_HEIGHT);
		gameScene.getStylesheets().add("model/resources/gameStyle.css");
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		target = 1;
		obSpeed = 2;
		createPauseButton();
		createHelpButton();
		createScoreLabel(score);
		

		
		createBall();
		createImage();
		primitiveObstacles = new ArrayList<>();
		 for(int i=0;i<=5;i++) {
			 primitiveObstacles.add(i+1);
		 }

		pos = 30;
		Obstacle obUse = new Ring();
		Obstacles.add(obUse);
		obUse.getImg().setLayoutY(pos);
		pos = pos - 80 - 280;
		
		//Create first star
		Star star = new Star();
		stars.add(star);
		starPos = 145;
		star.getImg().setLayoutY(starPos);
		switchPos = -30;
		gamePane.getChildren().add(obUse.getImg());
		gamePane.getChildren().add(star.getImg());
		for(int i=0;i<2;i++) {
			starPos = starPos - 360;
			createObstacleInitial();
			pos = pos - 80 - 280;
			switchPos = switchPos - 80 - 280; 
		}
		
		

		createGameLoop();
		
		input();
		canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
		gg = canvas.getGraphicsContext2D();
		
	}
	public GameViewManager(SavedGame savedGame) {
		
		savedGame.init();
		String path = "src/model/resources/jump.wav";  
		jumpNote = new AudioClip(new File(path).toURI().toString());
		jump = true;
		first = true;
		inTime = false;
		shield = false;
		antiGrav = false;
		Image s = new Image("model/resources/shield.png");
		powerUp = new ImageView(s);
		powerUp.setLayoutX(10);
		powerUp.setLayoutY(50);
		powerUp.setFitHeight(150);
		powerUp.setFitWidth(150);
		Image ss = new Image("model/resources/antiGravLogo.png");
		powerUp2 = new ImageView(ss);
		powerUp2.setLayoutX(GAME_WIDTH-10 -150);
		powerUp2.setLayoutY(100);
		powerUp2.setFitHeight(150);
		powerUp2.setFitWidth(150);
		
		angle = savedGame.getAngle();
		obSpeed = savedGame.getObSpeed();
		g = 0;
		u = 0;
		score=savedGame.getScore();
		target = score/5 + 5;
		disableSpace = false;
		gameTimer = null;
		Obstacles = new ArrayList<>(savedGame.getObstacles());
		stars = new ArrayList<>(savedGame.getStars());
		changeColors = new ArrayList<>(savedGame.getChangeColor());
		gamePane = new AnchorPane();
		gamePane.setId("gamePane");
		gameScene = new Scene(gamePane, GAME_WIDTH,GAME_HEIGHT);
		gameScene.getStylesheets().add("model/resources/gameStyle.css");
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
		createPauseButton();
		createHelpButton();
		createScoreLabel(score);
		

		
		createBall(savedGame.getBall());
		
		primitiveObstacles = new ArrayList<>();
		 for(int i=0;i<=5;i++) {
			 primitiveObstacles.add(i+1);
		 }

		for(Obstacle x: Obstacles) {
			if(x instanceof CompoundObstacle) {
				for(PrimitiveObstacle q:x.getList()) {
					gamePane.getChildren().add(q.getImg());
				}
			}
			else {
				gamePane.getChildren().add(x.getImg());
				
			}
			
		}
		for(Star x: stars) {
			gamePane.getChildren().add(x.getImg());
		}
		for(ChangeColor x: changeColors) {
			gamePane.getChildren().add(x.getImg());
		}
		unBlur();
		createGameLoop();
		input();		
		canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
		gg = canvas.getGraphicsContext2D();
	}
	public void dier() {
		ball.setStroke(Color.TRANSPARENT);
		ball.setStrokeWidth(0);
		shield = false;
	}
	public void faller() {
		ball.setStroke(Color.TRANSPARENT);
		ball.setStrokeWidth(0);
		antiGrav = false;
	}
	public void input() {
		disableSpace = false;
		first = true;
		int i = gamePane.getChildren().indexOf(ball);
		gamePane.getChildren().get(i).requestFocus();
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(!disableSpace) {
					if(event.getCode() == KeyCode.P && inTime && !antiGrav) {
						inTime =false;
						shield = true;
						ball.setStroke(Color.WHITE);
						ball.setStrokeWidth(10);
						
						animation2 = new Timeline(new KeyFrame(Duration.seconds(4), e-> dier()));
						animation2.setCycleCount(1);
						animation2.play();
					}
					if(event.getCode() == KeyCode.S && inTime && !shield) {
						inTime =false;
						antiGrav = true;
						ball.setStroke(Color.BLACK);
						ball.setStrokeWidth(10);
						
						animation2 = new Timeline(new KeyFrame(Duration.seconds(8), e-> faller()));
						animation2.setCycleCount(1);
						animation2.play();
					}
					/*if(event.getCode() == KeyCode.L && !first) {
						createLosingSubScene();
						losingSubScene.moveSubSceneIn(score);
						BoxBlur blur = new BoxBlur(5,5,5);
						for(Node x: gamePane.getChildren()) {
							if(x != losingSubScene) {
								x.setEffect(blur);
							}
						}
						first = true;
						disableSpace = true;
						
					}*/
					if(event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.UP) {
						if(SettingsSubScene.getSound()) {jumpNote.play();}
						jump = true;
						currPos = ball.getLayoutY();
						u = 5;
						g = 0;
						if(first) {
							first = false;
							
						}
						//System.out.println("okk");
					}
					
					if(event.getCode() == KeyCode.ESCAPE && !first) {
						first = true;
						disableSpace = true;
						createPauseSubScreen();
						pauseSubScene.moveSubScene();
						BoxBlur blur = new BoxBlur(5,5,5);
						for(Node x: gamePane.getChildren()) {
							if(x != pauseSubScene) {
								x.setEffect(blur);
							}
						}
					}
					
			 		if(event.getCode() == KeyCode.I && !first) {
						first = true;
						disableSpace = true;
						createHelpSubScreen();
						helpSubScene.moveSubScene();
						BoxBlur blur = new BoxBlur(5,5,5);
						for(Node x: gamePane.getChildren()) {
							if(x != helpSubScene) {
								x.setEffect(blur);
							}
						}
					}
					
					
				}
				
				
			}
			
		});
		
	}
	
	private void move() {
		
		if(!first) {
			if(stars.size()>0 && ball.getLayoutY()-stars.get(0).getImg().getLayoutY()<stars.get(0).getImg().getFitHeight()) {
				
				gotStar(stars.get(0));
			}
			if(changeColors.size()>0 && ball.getLayoutY()-changeColors.get(0).getImg().getLayoutY()<changeColors.get(0).getImg().getFitHeight()) {
				
				gotChangeColor(ball);
			}
			
			for(Obstacle y: Obstacles) {
				if(y instanceof CompoundObstacle) {
					for(int i=0;i<y.getList().size();i++) {
						ImageView x = y.getList().get(i).getImg();
						if(y.getList().get(i) instanceof BallSquare || y.getList().get(i) instanceof Square) {
							x.setRotate(-1 * angle);
						}
						else {
							x.setRotate(angle);
						}
						
					}
				}
				else {
					ImageView x = y.getImg(); 
					if(y instanceof BallSquare || y instanceof Square) {
						x.setRotate(-1 * angle);
					}
					else {
						x.setRotate(angle);
					}
					

				}
			}
			
			for(int i  = 0;i<Obstacles.size();i++) {
				Obstacle y= Obstacles.get(i);
				if(y.getImg().getLayoutY()>GAME_HEIGHT) {
					Obstacles.remove(y);
					 y= Obstacles.get(i);
				}
				//System.out.println(y.getImg().getLayoutY());
				if(ball.getLayoutY() >= y.getImg().getLayoutY() && ball.getLayoutY() <= y.getImg().getFitHeight() + y.getImg().getLayoutY()) {
					if(!shield && y.collision(ball)) {
						gamePane.getChildren().remove(ball);
						disableSpace = true;
						
						gamePane.getChildren().add(canvas);
						upp = true;
						//create timer of chotu
						animation = new Timeline(new KeyFrame(Duration.seconds(0.07), e-> uptick()));
						animation.setCycleCount(1);
						animation.play();
						
						first = true;
					}
				}
			}
			for(Star x: stars) {
				x.getImg().setRotate(-1*angle);
				
			}
			angle = (angle+obSpeed + (score/5))%360;
		
			if(ball.getLayoutY()<GAME_HEIGHT-40 && !jump && !antiGrav) {
				
				ball.setLayoutY(ball.getLayoutY()+g);
				g+=0.34;
			}
			if(ball.getLayoutY()<GAME_HEIGHT/2 - 15) {
				if(jump && ball.getLayoutY()>20) {
					if(logo!=null) {logo.setLayoutY(logo.getLayoutY()+u+u/2);}
					for(Obstacle y:Obstacles) {
						if(y instanceof CompoundObstacle) {
							for(int i=0;i<y.getList().size();i++) {
								ImageView x = y.getList().get(i).getImg();
								x.setLayoutY(x.getLayoutY()+u+u/2);
							}
						}
						else {
						ImageView x = y.getImg();
						y.getImg().setLayoutY(y.getImg().getLayoutY()+u+u/2);
						}
					}
					for(Star x:stars) {
						x.getImg().setLayoutY(x.getImg().getLayoutY()+u+u/2);
					}
					for(ChangeColor x: changeColors) {
						x.getImg().setLayoutY(x.getImg().getLayoutY()+u+u/2);
					}
//					u-=0.34;
//					if(u <=0) {
//						g = 0;
//						jump = false;
//					}
				}
			}
			if(jump && ball.getLayoutY()>20) {
				ball.setLayoutY(ball.getLayoutY()-u);
				u-=0.34;
				if(u <=0) {
					g = 0;
					jump = false;
				}
			}
			if(ball.getLayoutY()<20) {
				jump = false;
			}
			
		}
		else {
			onUpdate();
		}
		
		
	}
	public void uptick() {
		upp = false;
		createLosingSubScene();
		losingSubScene.moveSubSceneIn(score);
		BoxBlur blur = new BoxBlur(5,5,5);
		for(Node io: gamePane.getChildren()) {
			if(io != losingSubScene && io != canvas) {
				io.setEffect(blur);
			}
		}
	}
	public void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				animationStart = now;
				move();
				generateObstacles();
				
			}
			
		};
		gameTimer.start();
	}
	
	private void createLosingSubScene() {
		losingSubScene = new LosingSubScene(this,250,200);
		gamePane.getChildren().add(losingSubScene);	
	}
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		getGameStage().show();
	}
	
	private void createBall() {
		//System.out.println("ball");
		ball = new Circle(10, Color.TRANSPARENT);
		
		ball.setLayoutX(GAME_WIDTH/2);
		ball.setLayoutY(GAME_HEIGHT-100);
		ball.setId("ball1");
		gamePane.getChildren().add(ball);
		
	}
	private void createBall(Circle b) {
		//System.out.println("ball");
		ball = new Circle(10, Color.TRANSPARENT);
		
		ball.setLayoutX(GAME_WIDTH/2);
		ball.setLayoutY(b.getLayoutY());
		ball.setId(b.getId());
		gamePane.getChildren().add(ball);
		
	}
	private void createImage() {
		Image logoi = new Image("model/resources/logo.png");
		logo = new ImageView(logoi);
		logo.setFitHeight(210);
		logo.setFitWidth(210);
		logo.setLayoutX(90);
		logo.setLayoutY(325);
		gamePane.getChildren().add(logo);
		
		
	}
	private void createObstacleInitial(){
		ChangeColor switColor = new ChangeColor();
		changeColors.add(switColor); 
		switColor.getImg().setLayoutY(switchPos);
		Star star = new Star();
		stars.add(star);
		star.getImg().setLayoutY(starPos);
		
		gamePane.getChildren().add(star.getImg());
		gamePane.getChildren().add(switColor.getImg());
		
		//For a single obstacle
		Random rand = new Random(); 
        int obNo =  primitiveObstacles.get(rand.nextInt( primitiveObstacles.size()));
        Obstacle obUse = new Ring();
        if(obNo == 1) {
        	//Ring
        	obUse = new Ring();
        }
        else if(obNo == 2) {
        	//Square
        	obUse = new Square();
        }
        else if(obNo == 3) {
        	//RingWithBalls
        	obUse = new BallRing();
        }
        else if(obNo == 4) {
        	//SquareWithBalls
        	obUse = new BallSquare();
        }
        else if(obNo == 5) {
        	//Cross
        	obUse = new Cross();
        }
		Obstacles.add(obUse);
		obUse.getImg().setLayoutY(pos);
		gamePane.getChildren().add(obUse.getImg()); 
		
		
		
	}
	
	private void generateObstacles() {
		if(Obstacles.get(0).getImg().getLayoutY()>GAME_HEIGHT) {
			ChangeColor switColor = new ChangeColor();
			changeColors.add(switColor); 
			switchPos = Obstacles.get(Obstacles.size()-1).getImg().getLayoutY() - 40;
			switColor.getImg().setLayoutY(switchPos);
			gamePane.getChildren().add(switColor.getImg());
			Star star = new Star();
			stars.add(star);
			/*check it out*/
			starPos = stars.get(stars.size()-1).getImg().getLayoutY() - 60 -  280;
			star.getImg().setLayoutY(starPos);
			
			gamePane.getChildren().add(star.getImg());
			Obstacles.remove(0);
			/*Random rand = new Random(); 
	        int obNo =  primitiveObstacles.get(rand.nextInt( primitiveObstacles.size()));
	        Obstacle obUse = new Ring();
	        if(obNo == 1) {
	        	//Ring
	        	obUse = new Ring();
	        }
	        else if(obNo == 2) {
	        	//Square
	        	obUse = new Square();
	        }
	        else if(obNo == 3) {
	        	//RingWithBalls
	        	obUse = new BallRing();
	        	
	        }
	        else if(obNo == 4) {
	        	//SquareWithBalls
	        	obUse = new Ring();
	        }
	        else if(obNo == 5) {
	        	//Cross
	        	obUse = new Ring();
	        }
			Obstacles.add(obUse);
			pos = Obstacles.get(Obstacles.size()-2).getImg().getLayoutY() -80-280;
			obUse.getImg().setLayoutY(pos);
			gamePane.getChildren().add(obUse.getImg()); 
			System.out.println(Obstacles.get(Obstacles.size()-1).getImg().getLayoutY());*/
			
			
			//For compound
			pos = Obstacles.get(Obstacles.size()-1).getImg().getLayoutY() - 80 - 280;
			CompoundObstacle comp = new CompoundObstacle(primitiveObstacles,gamePane,pos,score);
			Obstacles.add(comp);
			
		}
	}
	
	private void gotStar(Star x){
		score++;
		scorePrint.setText(score+"");
		gamePane.getChildren().remove(x.getImg());
		stars.remove(0);
		if(score == target) {
			target+= 5;
			//clk1  = 1;
			//System.out.println("start");
			
			inTime = true;
			gamePane.getChildren().add(powerUp);
			gamePane.getChildren().add(powerUp2);
			animation = new Timeline(new KeyFrame(Duration.seconds(1.5), e-> timelabel()));
			animation.setCycleCount(1);
			animation.play();
			
		}
		
	
		//add a new star
	}
	private void timelabel() {
		inTime = false;
		gamePane.getChildren().remove(powerUp);
		gamePane.getChildren().remove(powerUp2);
		//System.out.println("end");
	}
	private void gotChangeColor(Circle ball){
		Random rand = new Random();
		int k = rand.nextInt(4)+1;
		String newId = "ball"+k;
		if(ball.getId().equals(newId)) {
			newId = "ball" + ((k%4)+1);
		}
		ball.setId(newId);
		
		
		gamePane.getChildren().remove(changeColors.get(0).getImg());
		changeColors.remove(0);
		
	
		//add a new ChangeColor
	}
	
	public Stage getGameStage() {
		return (Stage) gameStage;
	}
	public void unBlur(boolean revived) {
		// TODO Auto-generated method stub
		scorePrint.setText(score+"");
		gamePane.getChildren().remove(canvas);
		for(Node x:gamePane.getChildren()) {
			x.setEffect(null);
		}
		gamePane.getChildren().add(ball);
		disableSpace = false;
		if(revived) {
			for(Obstacle y:Obstacles) {
				ImageView x = y.getImg();
				if(ball.getLayoutY() >x.getLayoutY() && ball.getLayoutY() <x.getLayoutY() + x.getFitHeight()) {
					ball.setLayoutY(x.getLayoutY() + x.getFitHeight() + 30);
				}

			}
		}
		
		
	}
	
	public void unBlur() {
		// TODO Auto-generated method stub
		for(Node x:gamePane.getChildren()) {
			x.setEffect(null);
		}
		disableSpace = false;
		
		
	}
	
	private void createPauseButton() {
		PauseButton = new Button();
		PauseButton.setId("pauseButton");
		PauseButton.setPrefHeight(50);
		PauseButton.setPrefWidth(50);
		PauseButton.setLayoutX(340);
		PauseButton.setLayoutY(20);
		PauseButton.setFocusTraversable(false);
		gamePane.getChildren().add(PauseButton);
		
		PauseButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				first = true;
				createPauseSubScreen();
				pauseSubScene.moveSubScene();
				BoxBlur blur = new BoxBlur(5,5,5);
				for(Node x: gamePane.getChildren()) {
					if(x != pauseSubScene) {
						x.setEffect(blur);
					}
				}
				
			}

		});
	}
	
	
	
	private void createHelpButton() {
		helpButton = new Button();
		helpButton.setId("helpButton");
		helpButton.setPrefHeight(50);
		helpButton.setPrefWidth(50);
		helpButton.setLayoutX(340);
		helpButton.setLayoutY(90);
		helpButton.setFocusTraversable(false);
		gamePane.getChildren().add(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method 
				first = true;
				createHelpSubScreen();
				helpSubScene.moveSubScene();
				BoxBlur blur = new BoxBlur(5,5,5);
				for(Node x: gamePane.getChildren()) {
					if(x != helpSubScene) {
						x.setEffect(blur);
					}
				}
				
			}

		});
	}
	
	
	
	private void createScoreLabel(int currScore) {
		scorePrint = new Label(currScore + "");
		scorePrint.setId("score");
		scorePrint.setPrefHeight(50);
		scorePrint.setPrefWidth(50);
		scorePrint.setLayoutX(20);
		scorePrint.setLayoutY(20);
		gamePane.getChildren().add(scorePrint);
			
		
	}
	
	private void createHelpSubScreen() {
		helpSubScene = new HelpSubScene(null,300,500,1,this,null);
		gamePane.getChildren().add(helpSubScene);
	}
	
	private void createPauseSubScreen() {
		pauseSubScene = new PauseSubScene(this);
		gamePane.getChildren().add(pauseSubScene);
	}
	public AnimationTimer getGameTimer() {
		// TODO Auto-generated method stub
		return gameTimer;
	}
	
	public Circle getBall() {
		return ball;
	}
	
	public String getName() {
		return gameName;
	}
	public void setName(String s) {
		gameName = s;
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

	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	public void modify(SavedGame savedGame) {
		// TODO Auto-generated method stub
		this.ball = savedGame.getBall();
		this.Obstacles = new ArrayList<>(savedGame.getObstacles());
		this.stars = new ArrayList<>(savedGame.getStars());
		this.changeColors = new ArrayList<>(savedGame.getChangeColor());
		this.gameName = savedGame.getName();
		this.score = savedGame.getScore();
		
	}
	public double getAngle() {
		// TODO Auto-generated method stub
		return angle;
	}
	public int getObSpeed() {
		// TODO Auto-generated method stub
		return obSpeed;
	}
	public void reduceScore(int i) {
		// TODO Auto-generated method stub
		score-=i;
	}
	
}
package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class CompoundObstacle extends Obstacle{
	private ArrayList<Integer> choosePrimitive;
	private ArrayList<Integer> choose;
	private ArrayList <PrimitiveObstacle> primitive; //chosen obstacles
	private int howMany;
	private AnchorPane gamePane;
	private double pos;
	private int score;
	private Random rand = new Random();
	
	public CompoundObstacle(ArrayList<Integer> choosePrimitive, AnchorPane gamePane, double pos,int score) {
		this.choosePrimitive = choosePrimitive;
		choose = new ArrayList<>();
		primitive = new ArrayList<>();
		this.score = score;
		choose.add(2);
		choose.add(3);
		this.gamePane = gamePane;
		this.pos = pos;
		
		if(score<5) {
			howMany = 2;
		}
		else if(score<7) {
			howMany = 3;
		}
		else {
	        howMany =  choose.get(rand.nextInt( choose.size()));
		}
		addObs();
	}
	public CompoundObstacle() {
		primitive = new ArrayList<>();
	}
	public void addObs() { 
        if(howMany==2) {
        	//temp remove - ballring
        	choosePrimitive.remove(4);
        	//choosePrimitive.remove(2);
            int obNo1 =  choosePrimitive.get(rand.nextInt(choosePrimitive.size()));
            choosePrimitive.add(5);
            int obNo2 =  choosePrimitive.get(rand.nextInt(choosePrimitive.size()));
       
            makePrimitive(obNo1); //can use the existing size
            primitive.get(0).getImg().setLayoutY(pos);
            makePrimitive(obNo2);
            primitive.get(1).getImg().setFitHeight(200);
            primitive.get(1).getImg().setFitWidth(200);
            primitive.get(1).getImg().setLayoutX(100);
            primitive.get(1).getImg().setLayoutY(pos + 40);
            gamePane.getChildren().add(primitive.get(0).getImg()); 
    		gamePane.getChildren().add(primitive.get(1).getImg()); 
        }
        
        else {
        	choosePrimitive.remove(4);
            int obNo1 =  choosePrimitive.get(rand.nextInt(choosePrimitive.size()));
            int obNo2 =  choosePrimitive.get(rand.nextInt(choosePrimitive.size()));
            choosePrimitive.add(5);
            int obNo3 =  choosePrimitive.get(rand.nextInt(choosePrimitive.size()));
            
            makePrimitive(obNo1);
            primitive.get(0).getImg().setLayoutY(pos);
    		gamePane.getChildren().add(primitive.get(0).getImg()); 
            makePrimitive(obNo2);
            primitive.get(1).getImg().setFitHeight(200);
            primitive.get(1).getImg().setFitWidth(200);
            primitive.get(1).getImg().setLayoutX(100);
            primitive.get(1).getImg().setLayoutY(pos + 40);
    		gamePane.getChildren().add(primitive.get(1).getImg()); 
            makePrimitive(obNo3);
            primitive.get(2).getImg().setFitHeight(160);
            primitive.get(2).getImg().setFitWidth(160);
            primitive.get(2).getImg().setLayoutX(120);
            primitive.get(2).getImg().setLayoutY(pos + 60);
    		gamePane.getChildren().add(primitive.get(2).getImg());
        }
        
        
       
        
	}
	
	public void makePrimitive(int obNo) {
		PrimitiveObstacle obUse = new Ring();
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
        	obUse = new Ring();
        }
		primitive.add(obUse);
	}
	
	public boolean collision(Circle ball) {
		if(primitive.size()==2) {
			return primitive.get(0).collision(ball) || primitive.get(1).collision(ball);
		}
		return primitive.get(0).collision(ball) || primitive.get(1).collision(ball) || primitive.get(2).collision(ball);
	}
	
	@Override
	public ImageView getImg() {
		return primitive.get(0).getImg();
	}
	
	@Override
	public ArrayList<PrimitiveObstacle> getList(){
		return primitive;
	}

}

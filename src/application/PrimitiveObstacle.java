package application;



import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import application.Obstacle;

public abstract class PrimitiveObstacle extends Obstacle{
	private ArrayList<PrimitiveObstacle> primitive;
	public abstract boolean collision(Circle ball);
	public abstract ImageView getImg();
	
	@Override
	public ArrayList<PrimitiveObstacle> getList(){
		return primitive;
	}
	
}

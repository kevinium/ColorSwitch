package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public abstract class Obstacle implements Serializable{
	private double speed;
	protected double pos;
	protected boolean enable; 
	
	protected ImageView obImg;
	 
	
	public ImageView getImg() {
		return this.obImg;
	}
	public abstract boolean collision(Circle ball);
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double s) {
		speed = s;
	}
	public boolean getEnable() {
		return enable;
	}
	public void toggleEnable() {
		enable = !enable;
	}
	public abstract ArrayList<PrimitiveObstacle> getList();
	

}

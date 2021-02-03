package application;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BallRing extends PrimitiveObstacle {
	//private ImageView RingAdd;
	public BallRing() {
		Image RingObstacle = new Image("model/resources/Ballring.png");
		obImg = new ImageView(RingObstacle);
		obImg.setFitHeight(280);
		obImg.setFitWidth(280);
		obImg.setLayoutX(60);
		obImg.setLayoutY(30);
	}
	@Override
	public ImageView getImg() {
		return obImg;
	}
	
	@Override
	public boolean collision(Circle ball){
		if(ball.getLayoutY() >= this.getImg().getLayoutY() && ball.getLayoutY() <= this.getImg().getFitHeight() + this.getImg().getLayoutY()) {
		WritableImage image = this.getImg().snapshot(new SnapshotParameters(), null);
		
		PixelReader pr = image.getPixelReader();
		if(pr!=null) {
			Color color = pr.getColor((int)image.getWidth()/2, (int)(((ball.getLayoutY()-this.getImg().getLayoutY())/(this.getImg().getFitHeight())) * image.getHeight()));
			//System.out.println(ball.getId());
			

			int r = (int)(color.getRed()*255);
			int g = (int)(color.getGreen()*255);
			int b = (int)(color.getBlue()*255);
			if(r == 0 && g == 222 && b == 255) {
				//System.out.println("blue");
				String v = "ball1";
				if(!v.equals(ball.getId())) {
					//System.out.println(ball.getId() + "" + v + "" + (ball.getId() == v));
					return true;
				}
			}
			if(r == 255 && g == 0 && b == 138) {
				//System.out.println("pink");
				String v = "ball2";
				if(!v.equals(ball.getId())) {
					//System.out.println(ball.getId() + "" + v + "" + (ball.getId() == v));
					return true;
				}
			}
			if(r == 126 && g == 0 && b == 255) {
				//System.out.println("purple");
				String v = "ball3";
				if(!v.equals(ball.getId())) {
					//System.out.println(ball.getId() + "" + v + "" + (ball.getId() == v));
					return true;
				}
			}
			if(r == 255 && g == 252 && b ==0 ) {
				//System.out.println("yellow");
				String v = "ball4";
				if(!v.equals(ball.getId())) {
					//System.out.println(ball.getId() + "" + v + "" +(ball.getId() == v));
					return true;
				}
			}
			
			//}
			
		}
		}
		return false;
	}

	
		
	
	
}

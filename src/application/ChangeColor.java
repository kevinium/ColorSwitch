package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChangeColor {
	private ImageView cc;
	public ChangeColor() {
		Image starimg = new Image("model/resources/changeColor.png");
		cc = new ImageView(starimg);
		cc.setFitHeight(50);
		cc.setFitWidth(50);
		cc.setLayoutX(175);
		cc.setLayoutY(30);
	}

	public ImageView getImg() {
		return cc;
	}
}

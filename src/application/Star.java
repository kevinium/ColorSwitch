package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Star {
	private ImageView star;
	public Star() {
		Image starimg = new Image("model/resources/star.png");
		star = new ImageView(starimg);
		star.setFitHeight(50);
		star.setFitWidth(50);
		star.setLayoutX(175);
		star.setLayoutY(30);
	}

	public ImageView getImg() {
		return star;
	}
}

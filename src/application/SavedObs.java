package application;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedObs implements Serializable {
	int type;
	//String path;
	double x, y, w, h, rot;
	ArrayList<SavedObs> prim;
	private static final long serialVersionUID = 24L;
	SavedObs(Obstacle k){
		if(k instanceof CompoundObstacle) {
			type = 6;
			prim = new ArrayList<>();
			for( Obstacle i: k.getList()){
				prim.add(new SavedObs(i));
			}
		}
		else {
			if(k instanceof Ring) {
				type = 1;
			}
			else if(k instanceof Square) {
				type = 2;
			}
			else if(k instanceof BallRing) {
				type = 3;
			}
			else if(k instanceof BallSquare) {
				type = 4;
			}
			else if(k instanceof Cross) {
				type = 5;
			}
			
			//path = k.getImg().getImage().getUrl();
			x = k.getImg().getLayoutX();
			y = k.getImg().getLayoutY();
			
			w = k.getImg().getFitWidth();
			h = k.getImg().getFitHeight();
			
			rot = k.getImg().getRotate();
		}
		
		
	}
	public ArrayList<SavedObs> getPrim() {
		// TODO Auto-generated method stub
		return prim;
	}

}

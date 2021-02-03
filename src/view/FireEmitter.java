package view;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

public class FireEmitter extends Emitter{

	@Override
	public List<Particle> emit(double x, double y) {
		// TODO Auto-generated method stub
		List<Particle> particles = new ArrayList<>();
		int numParticles = 12;
		for(int i =0; i< numParticles;i++) {
			Particle p = new Particle(x, y, new Point2D((Math.random() - 0.5)*25, (Math.random()-0.7)* 35), 5, 1.0, chooser(), BlendMode.HARD_LIGHT);
			particles.add(p);
			p = new Particle(x, y, new Point2D((Math.random() - 0.5)*0.5, (Math.random()-0.7)* 0.5), 5, 1.0, chooser(), BlendMode.HARD_LIGHT);
			particles.add(p);
			
		}
		return particles;
	}
	public Color chooser() {
		Color c = Color.ANTIQUEWHITE;
		int s = (int)(Math.random()*4);
		if(s == 0) {
			c = Color.rgb(255,0,138);
		}
		else if(s == 1) {
			c = Color.rgb(126,0,255);
		}
		else if(s == 2) {
			c = Color.rgb(255, 252, 0);
		}
		else {
			c = Color.rgb(0, 222, 252);
		}
		return c;
	}

}

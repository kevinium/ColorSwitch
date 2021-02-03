package view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;

public class Particle {

	private double x, y;
	private Point2D velocity;
	private double radius;
	private Paint color;
	private BlendMode blendMode;
	private double life = 1.0;
	private double decay;
	public Particle(double x, double y, Point2D velocity, double radius, double expireTime, Paint color, BlendMode blendMode) {
		super();
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.radius = radius;
		this.color = color;
		this.blendMode = blendMode;
		
		this.decay = 0.0003/expireTime;
	}
	public void update(boolean towards) {
		x+=velocity.getX();
		y+=velocity.getY();
		life-=decay;
		velocity = velocity.add(0,0.8);
		if(towards) {
			radius += 0.5;
		}
		else {
			radius-= 0.5;
		}
			
	}
	public void render(GraphicsContext g) {
		g.setGlobalAlpha(life);
		g.setGlobalBlendMode(blendMode);
		g.setFill(color);
		g.fillOval(x,y,radius, radius);
	}
	public boolean isAlive() {
		return life>0;
	}
	
}

package application;

import java.io.Serializable;

public class HS implements Serializable{

	private int h;
	public HS(int x) {
		h = x;
	}
	public int getHighScore() {
		return h;
	}
	public void setHighScore(int x){
		h = x;
	}
}

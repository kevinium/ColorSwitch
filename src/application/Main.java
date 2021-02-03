package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GameViewManager;
import view.ViewManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class Main extends Application {
	
	private static HS hs;
	private static boolean god;
	private static ArrayList<SavedGame> savedGames = new ArrayList<>();
	private static Media media;
	private static MediaPlayer mediaPlayer;
	public static void addSavedList(SavedGame g){
		savedGames.add(g);
	}

	@Override
	public void start(Stage primaryStage) {
		
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		
		savedGames = new ArrayList<>();
		hs = new HS(0);
		deserialize();
		String path = "src/model/resources/ES_Reef Break - Power Druid.wav";  
		
		god = false;
	       
	    media = new Media(new File(path).toURI().toString());  
	          
	           
	    mediaPlayer = new MediaPlayer(media);  
  
	    mediaPlayer.play(); 
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		launch(args);
		 
		
	}
	public static boolean isGod() {
		return god;
	}
	public static void setGod() {
		god = true;	
	}
	public static void playMusic() {
		mediaPlayer.play();
	}
	public static void pauseMusic() {
		mediaPlayer.pause();
	}

	public static ArrayList<SavedGame> getSavedGames() {
		return savedGames;
	}
	public static int getHighScore() {
		return hs.getHighScore();
	}
	public static void setHighScore(int x) {
		hs.setHighScore(x);
	}

	public static void serialize() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		ObjectOutputStream out = null;
		File f = new File("out.txt");
		f.delete();
		try {
			out = new ObjectOutputStream(new FileOutputStream("out.txt", true));
			out.writeObject(hs);
			for(SavedGame x: savedGames) {
				out.writeObject(x);
			}
		} finally {
			out.close();
		}
		
		
	}
	public static void deserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in  = null;
		try {
			in = new ObjectInputStream(new FileInputStream("out.txt"));
			hs = (HS)in.readObject();
			
			while(true) {
				try {
					
					savedGames.add((SavedGame)in.readObject());
				}
				catch( Exception e){
					
					break;
				}
			}
		} 
		catch(FileNotFoundException e){
			//System.out.println("no file yet");
		}
		finally {
			//in.close();
		}
	}
}
/*

--module-path "/Library/Java/JavaVirtualMachines/javafx-sdk-15.0.1/lib" --add-modules javafx.controls,javafx.fxml
--add-modules javafx.controls,javafx.media

*/

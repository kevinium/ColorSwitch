package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CSButton extends Button {
	

	private final String FONT_PATH = "src/model/resources/LEMONMILK-Regular.otf";
	public CSButton(String text) {
		//setStyle(BUTTON_FREE_STYLE);		
		//setBackground(getBackground());
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(20);
		initialiseButtonListeners();
	}
	

	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 25));
			System.out.println("Font Not Found");
		}

	}

	

	private void initialiseButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					//dosmth
				}
			}
		});

		
	}
}
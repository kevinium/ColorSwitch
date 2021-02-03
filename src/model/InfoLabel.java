package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;


public class InfoLabel extends Label {

	public InfoLabel(String text) {
		setPrefWidth(80);
		setPrefHeight(30);
		setText(text);
		setWrapText(true);
		setId("infoLabel");
	}

}

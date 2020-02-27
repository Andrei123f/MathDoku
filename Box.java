package gui;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Box {
	private int boxId;
	private Text text;
	private Rectangle border;
	private ArrayList<Box> set;
	private Text textArea;
	
	public Box() {
		border=new Rectangle(100,100);
		border.setFill(null);
		border.setStroke(Color.BLACK);
		border.setStrokeWidth(1);
	}
	
	public void setBoxId(int id) {
		this.boxId=id;
	}
	public void setSet(ArrayList<Box> set) {
		this.set=set;
	}
	public ArrayList<Box> getSet(){
		return this.set;
	}
	public int getBoxId() {
	return this.boxId;	
	}
	
	public Rectangle getBorder() {
		return this.border;
	}
	public Text getTextArea() {
		return this.textArea;
	}
	public void setTextArea(String s) {
		this.textArea=new Text(s);
	}
	
}

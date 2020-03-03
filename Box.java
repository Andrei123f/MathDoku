package gui;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Box {
	private int boxId;
	private Text text;
	private Rectangle border;
	private ArrayList<Box> set;
	private Text textArea;
	private Text TextFill;
	private Rectangle target;
	
	public Box() {
		border=new Rectangle(100,100);
		border.setFill(Color.TRANSPARENT);
		border.setStroke(Color.BLACK);
		border.setStrokeWidth(1);
		TextFill=new Text("VALUE");
		textArea=new Text("");
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
		this.textArea.setText(s);
	}
	
	public Text getTextFill() {
		return this.TextFill;
	}
	
	public void setTextFill(String s) {
		this.TextFill.setText(s);
		
	}
	
	public void setClicked(Pane root) {
		target=new Rectangle(100,100);
		target.setX(border.getX());
		target.setY(border.getY());
		target.setFill(null);
		target.setStroke(Color.RED);
		target.setStrokeWidth(2);
		root.getChildren().add(target);
		
	}
	public void setUnClicked(Pane root) {
		root.getChildren().remove(this.target);
		
	}
	public Rectangle getClickedRect() {
		return this.target;
	}
}

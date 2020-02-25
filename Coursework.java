package gui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Coursework extends Application{
	private Pane root=new Pane();
	private int numberOfCages=15;
	private ArrayList<ArrayList> cages=new ArrayList<ArrayList>();
	private ArrayList<Box> boxes=new ArrayList<Box>();
	
	public void prepareObjects() {
		for(int i=1;i<=numberOfCages;i++)
			cages.add(new ArrayList<Box>());
		
	}
	public void searchNeighnours() {
			System.out.println();
			int column=1,i=0,j;
			for(Box box:boxes) {
				int boxId=box.getBoxId();
				j=i; //the last element 
				i++;	//the current element
				
				if(i!=36) {
					int currBoxId=boxes.get(i).getBoxId();
					int lastBoxId=boxes.get(j).getBoxId();
					if(currBoxId < lastBoxId) {
						column++;
						System.out.println("New column! : " + column);
					}
				}
				/*
				if(column==1) {
					System.out.println("The box with the id of " + boxId +" has no left neighbour");
				}
				else if(column == 6) {
					System.out.println("The box with the id of "+ boxId+" has no right neighbour");
				}
				else {
					System.out.println("The box with the id of "+boxId+" has left and right neighbours");
				}
				if(boxId-6>=1) {
					System.out.println("The box with the id of " + boxId +" has an upper neighbour");
				}
				else {
					System.out.println("The box with the id of " + boxId +" does not have an upper neighbour");
				}
				*/
				 if(boxId+6<=36) {
					 System.out.println("The box with the id of " + boxId +" has an below neighbout");
					 Rectangle border=box.getBorder();
					double x=border.getX();
					double y=border.getY();
					double width=border.getWidth();
					double height=border.getHeight();
					 
					 System.out.println(boxId+" X:"+x);
					 System.out.println(boxId+" Y:"+y);
					 
					 double startX=x;
					 double startY=y+height;
					 double endX=x+width;
					 double endY=startY;
					 Line line=new Line(startX,startY,endX,endY);
					 line.setStroke(Color.RED);
					 line.setStrokeWidth(2);
					 root.getChildren().add(line);
				 }
				 else {
					 //System.out.println("The box with the id of " + boxId +" does not have below neighbour");
				 }
				
				
			}
			
	}
	
	public void drawCage(Box box,boolean r,boolean l, boolean u,boolean d) {
		ArrayList<Box> cage=box.getSet();
		int boxId=box.getBoxId();
		if(r) { //right			
			
			
		}
		if(l) {//left
			
		}
		if(u) {//up
			
		}
		if(d) {//down
			
		}
	}
	

	public void start(Stage stage) throws Exception {
		stage.setTitle("Mathdoku");
		GridPane gridPane=new GridPane();
		HBox hBox=new HBox(10); //parent for the buttons
		hBox.setPadding(new Insets(10,10,10,10));
		Button undo=new Button("undo");
		Button redo=new Button("redo");
		Button clear=new Button("clear");
		TextField file=new TextField();
		TextField input=new TextField();
		this.prepareObjects();
		
		int id;
		for(int i=1;i<=6;i++) {
			id=i;
			for(int j=1;j<=6;j++) {
				Box box=new Box();
				box.setBoxId(id);
				boxes.add(box);
				Rectangle border=box.getBorder();
				root.getChildren().addAll(border);
				border.setX(i*100);
				border.setY(j*100);
				id+=6;
			}
		}
		addBoxToCage(boxes.get(0),cages.get(0));
		addBoxToCage(boxes.get(1),cages.get(0));
		addBoxToCage(boxes.get(12),cages.get(1));
		addBoxToCage(boxes.get(13),cages.get(1));
		ArrayList<Box> cage=cages.get(0);
		
		boxes.get(0).setSet(cages.get(0));
		boxes.get(1).setSet(cages.get(0));
		boxes.get(12).setSet(cages.get(1));
		boxes.get(13).setSet(cages.get(1));
		for(Box box : cage) {
			System.out.println("The box id of the first cage: " + box.getBoxId());
		}
		
		cage =cages.get(1);
		for(Box box : cage) {
			System.out.println("The box id of the secong cage: " + box.getBoxId());
		}
		searchNeighnours();

		hBox.getChildren().addAll(undo,redo,clear,file);
		root.getChildren().addAll(gridPane,hBox);
		stage.setScene(new Scene(root));
		stage.show();
		
	}
	
	public void addBoxToCage(Box box, ArrayList<Box> cage) {
		cage.add(box);
	}
	public boolean isBoxInCage(Box box, ArrayList<Box> cage) {
		return cage.contains(box);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
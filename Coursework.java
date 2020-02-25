package gui;
/*
 * go trhough each node and check its neighbours
 * if they are part of the same cage, do not draw a line between them
 * if they are not, draw a line between them
 */
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Coursework extends Application {
	//the main class
	public static void main(String[] args) {
		launch(args);
	}
	
	//the class that sets everything up
	private class Setter{
		private int numberOfCages=15;
		private ArrayList<ArrayList> cages;
		private ArrayList<Box> boxes;
		
		public Setter() {
			boxes=new ArrayList<Box>();
			cages=new ArrayList<ArrayList>();
			for(int i=1;i<=numberOfCages;i++) {
				cages.add(new ArrayList<Box>());
			}		
		}
		
	//the function that creates the view
	private Parent createContent(){
		Pane root=new Pane();
		root.setPrefSize(800, 800);
		
		int id;
		for(int i=1;i<=6;i++) { // horizontal
			id=i;
			for(int j=1;j<=6;j++) { //vertical
				Box box= new Box(id);
				box.setBoxId(id);
				boxes.add(box);
				box.setTranslateY(j*100);
				box.setTranslateX(i*100);
				root.getChildren().add(box);
				id+=6;
			}
		}
		System.out.println(boxes.get(1).getBoxId());
		//adding some random boxes in the same cage.
		
		addBoxToCage(boxes.get(0),cages.get(0));
		addBoxToCage(boxes.get(1),cages.get(0));
		addBoxToCage(boxes.get(12),cages.get(1));
		addBoxToCage(boxes.get(13),cages.get(1));
		
		ArrayList<Box> cage=cages.get(0);
		for(Box box : cage) {
			System.out.println("The box id of the first cage: " + box.getBoxId());
		}
		
		cage =cages.get(1);
		for(Box box : cage) {
			System.out.println("The box id of the secong cage: " + box.getBoxId());
		}
		drawCages();
		
		return root;
	}
	//the function that adds the box to the specific cage
	private void addBoxToCage(Box box,ArrayList<Box> cage) {
		cage.add(box);
	}
	
	private boolean cageContainsBox(ArrayList<Box> cage, Box box) {
		return(cage.contains(box));
	}
	//for drawing the cages i will determine whether the neighbours of the box are in the same cage
	//only if the box has neighbours!
	//For each box, it can have a upper neighbour,bottom neighbour,left and right neighbour
	//i need to determine on which line is situated the current box we are looking at.
	//for line 1 the range for left - right neighbours is 1-6
	//for line 2 the range for left - right neighbours is 7-12
	//for line 3 the range for left - right neighbours is 13-18
	//for line 4 the range for left - right neighbours is 19-24
	//for line 5 the range for left - right neighbours is 25-30
	//for line 6 the range for left - right neighbours is 31-32
	private void drawCages() {
		int i=0;
		int j;
		int row=1;
		for(Box box : boxes) {
			int boxId=box.getBoxId();
			j=i; //the current element from the boxes ararylist
			i++; //the next element from the boxes arraylist
			
			if(boxId == 36) {
				System.out.println("The box is 36!"); //36 is n*n
			}
			else {
				//check if the next element boxId is less than the current element boxId
				//if it is not, we increment row and we get the current row we are in
				//
			}
			
			
			if(boxId-6>=1) {
				System.out.println("The box with the id of " + boxId +" has an upper neighbour");
			}
			else {
				System.out.println("The box with the id of " + boxId +" does not have an upper neighbour");
			}
			
			 if(boxId+6<=36) {
				 System.out.println("The box with the id of " + boxId +" has an below neighbout");
			 }
			 else {
				 System.out.println("The box with the id of " + boxId +" does not have below neighbour");
			 }
			
		}
	}
	
	
	}
	
	//The start class of the app
	public void start(Stage stage) {
		stage.setTitle("MathDoku");
		Setter setter=new Setter();
		stage.setScene(new Scene(setter.createContent()));
		stage.show();
	}
	
	
	//The box class
	private class Box extends StackPane{
		private int boxId;
		private Text text;
		
		
		public Box(int indent) {
			text=  new Text();
			Rectangle border=new Rectangle(100,100);
			border.setFill(null);
			border.setStroke(Color.BLACK);
			setAlignment(Pos.CENTER);
			this.setText(indent);
			
			getChildren().addAll(border,this.text);
		}
		
		
		public void setBoxId(int id) {
			this.boxId=id;
		}
		
		
		public int getBoxId() {
			return (this.boxId);
		}
		
		public void setText(int input) {
			String inputS=Integer.toString(input);
			this.text.setText(inputS);
		}
		
		
		public Text getText() {
			return this.text;
		}

	}
}
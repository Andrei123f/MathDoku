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

	public static void main(String[] args) {
		launch(args);
	}
	
	private class Setter{
		private int numberOfCages=15;
		private ArrayList<ArrayList> cages;
		
		public Setter() {
			cages=new ArrayList<ArrayList>();
			for(int i=1;i<=numberOfCages;i++) {
				cages.add(new ArrayList<Box>());
				System.out.println("created a cage!");
			}
			int i=1;
			for(ArrayList<Box> box :cages ){
				System.out.println(box+ " "+i );
				i++;
			}
				
		}
		

	private Parent createContent(){
		Pane root=new Pane();
		root.setPrefSize(800, 800);
		ArrayList<Box> cage=cages.get(1);

		int id;
		for(int i=1;i<=6;i++) { // horizontal
			id=i;
			for(int j=1;j<=6;j++) { //vertical
				Box box= new Box(id);
				box.setTranslateY(j*100);
				box.setTranslateX(i*100);
				root.getChildren().add(box);
				id+=6;
			}
			
		}
		
		
		return root;
	}
	}

	public void start(Stage stage) {
		stage.setTitle("MathDoku");
		Setter setter=new Setter();
		stage.setScene(new Scene(setter.createContent()));
		stage.show();
	}
	
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
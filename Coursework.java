package gui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Coursework extends Application{
	private Box clickedBox; //the selected box
	private Pane root=new Pane();
	private int numberOfCages=20;
	private ArrayList<ArrayList> cages=new ArrayList<ArrayList>();
	private ArrayList<Box> boxes=new ArrayList<Box>();
	
	public void prepareCages() {
		for(int i=1;i<=numberOfCages;i++)
			cages.add(new ArrayList<Box>());
		
	}
	
	public void readFile() {
		Scanner x;
		File file=new File("input.txt");
		
		//opening the file
		try {
			x=new Scanner(file);
			System.out.println("found the file and is ready!");
			
			//reading from the file
/*
 * Each line defines one cage of the puzzle.
The line starts with the target followed immediately by the arithmetic operator (or none if it's a single cell) for that cage.
This is followed by a space and a sequence of cell IDs that belong to the cage (consecutive cell IDs are separated by a comma). 
Here cells are numbered from 1 to (NxN), where 1 to N are the cells in the top row (left to right), N+1 to 2N are the cells in the 
second row from the top, and so on.
 */			
			int i=0; //used for getting the correct cage
			while(x.hasNext()) {
				String line=x.nextLine(); //getting the current line
				ArrayList<Box> cage=cages.get(i); //the cage of the current line
				String targetBoxId=line.split(" ")[0].replaceAll("\\D+", ""); //getting the id of the targetCage
				String operand=line.split(" ")[0].replaceAll("[0-9]",""); //getting the operand of the targetCage
				System.out.println("the box id: "+ targetBoxId +" the operand: " + operand);

				//addBoxToCage(getBoxById(targetBoxId), cage); //adding the targetCage to the cage
				//getBoxById(targetBoxId).getBorder().setFill(Color.BLACK);
				String part2=line.split(" ")[1]; //part 2 of the line
				String[] numbers=part2.split(",");
				int targetBoxID=Integer.parseInt(numbers[0]);
				String label=targetBoxId+operand;
				Box tB=this.getBoxById(targetBoxID);
				tB.setTextArea(label);
				Text text=tB.getTextArea();
				text.setX(tB.getBorder().getX()+5);
				text.setY(tB.getBorder().getY()+15);
				
				root.getChildren().addAll(text);
				
				
				//System.out.println("The second part:" + part2);
				//System.out.println("The numbers of the second part:");
				for(String s : numbers) {
					int boxId=Integer.parseInt(s);
					Box box=getBoxById(boxId);
					Text textFill=box.getTextFill();
					textFill.setX(box.getBorder().getX()+box.getBorder().getWidth()/2);
					textFill.setY(box.getBorder().getY()+box.getBorder().getWidth()/2);
					root.getChildren().add(textFill);
					addBoxToCage(getBoxById(boxId),cage);
				}
				System.out.println("The box id:" + i + "target box id: " + cage.get(0).getBoxId());
				
				i++;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		
	}
	
	
	//check only bottom and right neighbour if you can 
	public void searchNeighnours() {
			System.out.println();
			int column=1,i=0,j;
			for(Box box:boxes) {
				int boxId=box.getBoxId();
				
				 if(column == 6) {
					//System.out.println("The box with the id of "+ boxId+" has no right neighbour");
				}
				else {
				//	System.out.println("The box with the id of "+boxId+" has left and right neighbours");
					drawCage(box,true,false);
					
				}
				
				 if(boxId+6<=36) {
					// System.out.println("The box with the id of " + boxId +" has an below neighbout");
					 drawCage(box,false,true);
				 }
				 else {
					 //System.out.println("The box with the id of " + boxId +" does not have below neighbour");
				 }
					j=i; //the last element 
					i++;	//the current element
					if(i!=36) {
						int currBoxId=boxes.get(i).getBoxId();
						int lastBoxId=boxes.get(j).getBoxId();
						if(currBoxId < lastBoxId) {
							column++;
						//	System.out.println("New column! : " + column);
						}
					}
				
				
			}
			
	}
	
	public void drawCage(Box box,boolean r,boolean d) {
		ArrayList<Box> cage=box.getSet();
		int boxId=box.getBoxId();
		double x=box.getBorder().getX();
		double y=box.getBorder().getY();
		double width=box.getBorder().getWidth();
		double height=box.getBorder().getHeight();
		
		if(cage!=null) {
	//	System.out.println("The box with the id of " + boxId+" has a cage!");
		if(r) { //right			
			int rightBoxId=boxId+1;
			Box rightNeighbour=getBoxById(rightBoxId);
			if(!cage.contains(rightNeighbour)) {
				 double startX=x+width;
				 double startY=y;
				 double endX=x+width;
				 double endY=y+height;
				 Line line=new Line(startX,startY,endX,endY);
				 line.setStroke(Color.BLACK);
				 line.setStrokeWidth(4);
				 root.getChildren().add(line);
				
			}
			
		}
		if(d) {//down
			int downtBoxId=boxId+6;
			Box downNeighbour=getBoxById(downtBoxId);
			for(Box boxo : cage) {
			//	System.out.println("The id of the boxo: " + boxo.getBoxId());
			}
			
			if(cage.contains(downNeighbour) !=true) {
				 double startX=x;
				 double startY=y+height;
				 double endX=x+width;
				 double endY=startY;
				 Line line=new Line(startX,startY,endX,endY);
				 line.setStroke(Color.BLACK);
				 line.setStrokeWidth(3);
				 root.getChildren().add(line);
				//draw a line	
			}
		}
			
		}
	}
	
	public Box getBoxById(int id) {
		int currId;
		Box searchedBox=new Box();
		
		for(Box box : boxes) {
			currId=box.getBoxId();
			if(currId==id) {
				return box;
			}
		}
		return searchedBox;
	}
	

	public void start(Stage stage) throws Exception {
		stage.setTitle("Mathdoku");
		GridPane gridPane=new GridPane();
		HBox hBox=new HBox(10); //parent for the buttons
		hBox.setPadding(new Insets(10,10,10,10));
		Button undo=new Button("UNDO");
		Button redo=new Button("REDO");
		Button clear=new Button("CLEAR");
		TextField file=new TextField();
		TextField input=new TextField();
		Button setButton=new Button("SET");
		Button b1=new Button("+1");
		Button b2=new Button("-1");
		
		b1.setOnMouseClicked(new EventHandler() {
			public void handle(Event arg0) {
				if(clickedBox!=null) {
					String inputS=clickedBox.getTextFill().getText();
					
					if(inputS.equals("VALUE")) {
						clickedBox.setTextFill("1");
					}
					else {
						int inputN=Integer.parseInt(inputS);
						if(inputN<6) {
						inputN++;
						inputS=Integer.toString(inputN);
						clickedBox.setTextFill(inputS);
						}
						else {
							System.out.println("Limit reached!");
						}
					}
				}
				
			}
			
		});
		b2.setOnMouseClicked(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				if(clickedBox!=null) {
					String inputS=clickedBox.getTextFill().getText();
					
					if(inputS.equals("VALUE")) {
						System.out.println("Cannot decrement!");
					}
					else {
						int inputN=Integer.parseInt(inputS);
						if(inputN>1) {
						inputN--;
						inputS=Integer.toString(inputN);
						clickedBox.setTextFill(inputS);
						}
						else {
							System.out.println("Limit reached!");
						}
					}
				}
				
			}
			
		});
		
		setButton.setOnMouseClicked(new EventHandler() {

			public void handle(Event arg0) {
				if(clickedBox!=null) {
					String numb = input.getText();
					int number=Integer.parseInt(numb);
					if(number<=6 && number>=1)
					clickedBox.setTextFill(numb);
					else
						System.out.println("The number is greater than 6 or negative or 0!");
				}
				
			}
			
		});
		
		
		this.prepareCages();
		
		int id;
		for(int i=1;i<=6;i++) {
			id=i;
			for(int j=1;j<=6;j++) {
				Box box=new Box();
				box.setBoxId(id);
				boxes.add(box);
				Rectangle border=box.getBorder();	
				border.setX(i*100);
				border.setY(j*100);
				/*
				 * get text area, text target and rect and set the eventhandler for each.
				 * new MouseEvent();
				 * 
				 */
				border.setOnMouseClicked(new MouseHandler(box));
				box.getTextArea().setOnMouseClicked(new MouseHandler(box));
				box.getTextFill().setOnMouseClicked(new MouseHandler(box));
				
				root.getChildren().addAll(border);
				id+=6;
			}
		}
		readFile();
		searchNeighnours();
		

		root.getChildren().addAll(gridPane);
		hBox.getChildren().addAll(undo,redo,clear,file,input,setButton,b1,b2);
		root.getChildren().addAll(hBox);
		stage.setScene(new Scene(root));
		stage.show();
		
	}
	
	public void addBoxToCage(Box box, ArrayList<Box> cage) {
		cage.add(box);
		box.setSet(cage);
	}
	public boolean isBoxInCage(Box box, ArrayList<Box> cage) {
		return cage.contains(box);
	}
	
	//Handler for selecting a box
	class MouseHandler implements EventHandler<MouseEvent>{
		private Box box;
		public MouseHandler(Box in) {
			box=in;
			
		}
		public void handle(MouseEvent arg0) {
			// TODO Auto-generated method stub

			if(clickedBox==null) {
				clickedBox=box;
				clickedBox.setClicked(root);
				System.out.println("Selected:" +clickedBox.getBoxId());
			}
			else {
				if(box!=clickedBox) {
				clickedBox.setUnClicked(root);
				System.out.println("Deselected:" +clickedBox.getBoxId());
				clickedBox=box;
				clickedBox.setClicked(root);
				System.out.println("Selected:" +clickedBox.getBoxId());
				}
				else {
					System.out.println("Selecting the same box!");
				}

			}
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
}
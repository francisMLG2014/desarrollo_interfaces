package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Ej 1
			StackPane panel=new StackPane();
			Rectangle r1=new Rectangle(400,400,Color.DARKGREEN);
			Rectangle r2=new Rectangle(300,300,Color.GREEN);
			Rectangle r3=new Rectangle(200,200,Color.LIGHTGREEN);
			panel.getChildren().addAll(r1,r2,r3);
			//Ej 2
			BorderPane panelBorde=new BorderPane();
			Rectangle re_centro=new Rectangle(300,300,Color.DARKGREEN);
			Rectangle re_izq=new Rectangle(400,50,Color.DARKGREEN);
			Rectangle re_der=new Rectangle(400,50,Color.DARKGREEN);
			Rectangle re_arriba=new Rectangle(50,300,Color.DARKGREEN);
			Rectangle re_abajo=new Rectangle(500,300,Color.ALICEBLUE);
			
			panelBorde.setTop(re_arriba);
			panelBorde.setCenter(re_centro);
			panelBorde.setBottom(re_abajo);
			panelBorde.setRight(re_der);
			panelBorde.setLeft(re_izq);
			
			
			
			//Ej 3
			BorderPane bordes=new BorderPane();
			GridPane panelTelefono=new GridPane();
			
			//fila 0
			Button b7=new Button("7");
			b7.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {}
				
			});
			Button b8=new Button("8");
			Button b9=new Button("9");
			panelTelefono.add(b7, 0, 0);
			panelTelefono.add(b8, 1, 0);
			panelTelefono.add(b9, 2, 0);
			//fila 1
			Button b4=new Button("4");
			Button b5=new Button("5");
			Button b6=new Button("6");
			Button bLlamar=new Button("Llamar");
			panelTelefono.add(b4, 0, 1);
			panelTelefono.add(b5, 1, 1);
			panelTelefono.add(b6, 2, 1);
			panelTelefono.add(bLlamar, 3, 1);
			//fila 2
			Button b1=new Button("1");
			Button b2=new Button("2");
			Button b3=new Button("3");
			Button bColgar=new Button("Colgar");
			panelTelefono.add(b1, 0, 2);
			panelTelefono.add(b2, 1, 2);
			panelTelefono.add(b3, 2, 2);
			panelTelefono.add(bColgar, 3, 2);
			//fila 3
			Button b0=new Button("0");
			panelTelefono.add(b0, 1, 3);
			
			
			
			
			
			
			Scene scene = new Scene(panel,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

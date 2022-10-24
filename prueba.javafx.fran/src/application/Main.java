package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			Button btn= new Button("Click aqui");
			btn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					System.out.println("Hello World");
				}
				
			});
			Label lb=new Label("Hola mundo");
			
			StackPane panel=new StackPane();
			//Primero se posicionan
			panel.setAlignment(lb,Pos.TOP_CENTER);
			panel.setAlignment(btn,Pos.CENTER);

			//Luego se añaden
			panel.getChildren().addAll(lb,btn);
			Scene scene=new Scene(panel,400,300);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Introduccion a java fx");
			primaryStage.getIcons().add(new Image("./application/icon.png"));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

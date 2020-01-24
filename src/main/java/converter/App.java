package converter;

import converter.controller.Converter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		try {
			Parent root = FXMLLoader.load(Converter.class.getResource("Converter.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("MM Font Converter");
			stage.setResizable(false);
			stage.getIcons().add(new Image("zawtouni.png"));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stage.setOnCloseRequest(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Close Confirmation");
			alert.setContentText("Do you want to really close?");
			alert.showAndWait().ifPresent(type -> {
				if(type == ButtonType.CANCEL)
					e.consume();
			});
		});
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

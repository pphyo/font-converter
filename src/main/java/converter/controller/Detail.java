package converter.controller;

import java.net.URL;
import java.util.ResourceBundle;

import converter.entity.FileViewer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Detail implements Initializable {

	@FXML
	private Label name;
	@FXML
	private Label path;
	@FXML
	private Label zawOrUni;
	@FXML
	private Label fileOrFolder;
	@FXML
	private Label title;
	@FXML
	private VBox root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		title.setText("\u00A9 Detail for selected item \u00A9");
		root.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER)
				close();
		});
	}
	
	public static void show(FileViewer fv) {
		try {

			FXMLLoader loader = new FXMLLoader(Detail.class.getResource("Detail.fxml"));
			Parent root = loader.load();
			
			Detail controller = loader.getController();
			if(null != fv) {
				controller.name.setText(fv.getName());
				controller.path.setText(fv.getPath());
				controller.zawOrUni.setText(fv.getZawOrUni());
				controller.fileOrFolder.setText(fv.getFileOrFolder());
			}

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Detail");
			stage.setResizable(false);
			stage.getIcons().add(new Image("view.png"));
//			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void close() {
		name.getScene().getWindow().hide();
	}

}
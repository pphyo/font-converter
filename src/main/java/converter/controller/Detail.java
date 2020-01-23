package converter.controller;

import converter.entity.FileViewer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Detail {

	@FXML
	private Label name;
	@FXML
	private Label path;
	@FXML
	private Label zawOrUni;
	@FXML
	private Label fileOrFolder;
	
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
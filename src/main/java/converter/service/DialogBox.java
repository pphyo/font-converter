package converter.service;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogBox {
	
	public static void openBox(String title, String contentText) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(title);
		dialog.setContentText(contentText);
		dialog.setHeaderText(null);
		dialog.setResizable(false);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		dialog.show();
	}
	
}

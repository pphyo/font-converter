package converter.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.google.myanmartools.TransliterateU2Z;
import com.google.myanmartools.TransliterateZ2U;
import com.google.myanmartools.ZawgyiDetector;

import converter.entity.FileViewer;
import converter.service.DialogBox;
import converter.service.Factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Converter implements Initializable {

	@FXML
	private Label item;
	@FXML
	private TableView<FileViewer> list;
	@FXML
	private Label title;
	private Factory repo;
	private static final ZawgyiDetector detector = new ZawgyiDetector();
	private static final TransliterateZ2U z2UConverter = new TransliterateZ2U("Zawgyi to Unicode");
	private static final TransliterateU2Z u2ZConverter = new TransliterateU2Z("Unicode to Zawgyi");
	private FileViewer fv;
	List<FileViewer> files;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repo = Factory.getInstace();
		title.setText("\u00A9 Myanmar Font Converter \u00A9");
		item.setText("0 item");	
		createContextMenu();
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		search();
	}
	
	private void createContextMenu() {
		MenuItem remove = new MenuItem("Remove");
		remove.setOnAction(a -> remove());
		MenuItem detail = new MenuItem("Detail");
		detail.setOnAction(a -> detail());
		ContextMenu ctx = new ContextMenu(remove, detail);
		list.setContextMenu(ctx);
	}
	
	public void fileChoose() {
		
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("Select File");
			fc.setInitialDirectory(new File(System.getProperty("user.home"), "Desktop"));

			List<File> files = fc.showOpenMultipleDialog(item.getScene().getWindow());
			
			for(File file : files) {
				fv = new FileViewer();
				fv.setName(file.getName());
				fv.setPath(file.getPath());
				fv.setZawOrUni(chickUniOrZawgyi(file));
				fv.setFileOrFolder("File");
				repo.add(fv);
				list.getItems().add(fv);
			}
			
			search();

		} catch (Exception e) {
			DialogBox.openBox("Information", "Didn't choose");
		}
	}

	public void folderChoose() {
		try {
			DirectoryChooser dc = new DirectoryChooser();
			dc.setTitle("Select Folder");
			dc.setInitialDirectory(new File(System.getProperty("user.home"), "Desktop"));
			File files = dc.showDialog(item.getScene().getWindow());
			
			fv = new FileViewer();
			fv.setName(files.getName());
			fv.setPath(files.getPath());
			fv.setZawOrUni(chickUniOrZawgyi(files));
			fv.setFileOrFolder("Folder");
			repo.add(fv);
			list.getItems().add(fv);
			search();
		} catch (Exception e) {
			DialogBox.openBox("Information", "Didn't choose");
		}
	}

	private String chickUniOrZawgyi(File files) {
		return detector.getZawgyiProbability(files.getPath()) > 0.999 ? "Zawgyi" : "Unicode";
	}

	public void zawToUni() {
		List<FileViewer> items = list.getItems();
		for(FileViewer fv : items) {
			if(detector.getZawgyiProbability(fv.getPath()) > 0.999) {
				File file = new File(fv.getPath());
				fv.setPath(z2UConverter.convert(fv.getPath()));
				file.renameTo(new File(fv.getPath()));
			}
		}
		
		DialogBox.openBox("Complete", "Convert Successfully");
		
	}

	public void uniToZaw() {
		List<FileViewer> items = list.getItems();
		for(FileViewer fv : items) {
			if(detector.getZawgyiProbability(fv.getPath()) < 0.001) {
				File file = new File(fv.getPath());
				fv.setPath(u2ZConverter.convert(fv.getPath()));
				file.renameTo(new File(fv.getPath()));
			}
		}
		
		DialogBox.openBox("Complete", "Convert Successfully");
		
	}

	private void search() {
		list.getItems().clear();
		list.getItems().addAll(repo.search());
		String items = String.valueOf(repo.getListSize()).concat(repo.getListSize() == 1 ? " item" : " items");
		item.setText(items);
	}

	private void remove() {
		files = list.getSelectionModel().getSelectedItems();
		for(FileViewer fv : files) {
			repo.remove(fv);
		}
		search();
	}
	
	private void detail() {
		Detail.show(fv);
	}
}
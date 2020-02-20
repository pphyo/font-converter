package converter.service;

import java.util.ArrayList;
import java.util.List;

import converter.entity.FileViewer;

public class Factory {
	
	private List<FileViewer> list;
	private static Factory INSTANCE;
	
	private Factory() {
		list = new ArrayList<>();
	}
	
	public static Factory getInstace() {
		if(null == INSTANCE) {
			INSTANCE = new Factory();
		}
		return INSTANCE;
	}
	
	public void add(FileViewer fv) {
		list.add(fv);		
	}
	
	public void remove(FileViewer fv) {
		list.remove(fv);
	}
	
	public int getListSize() {
		return list.size();
	}
	
	public List<FileViewer> search() {
		return list;
	}

}
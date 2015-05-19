package com.mycompany.app;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "editorBean")
public class EditorBean {
	private String message = "Hello World from EditorBean ";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

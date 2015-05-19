package com.musicmanager.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "indexBean")
@ViewScoped
public class IndexBean implements Serializable {
	
	private static final long serialVersionUID = -1345961166268131776L;
	
	private String title = "Music Manager";
	private boolean showPanel = true;
	
	public IndexBean() {
		
	}
	
	public void togglePanel() {
		this.showPanel = !showPanel;
	}
	
	public void viewAllFilesAction(ActionEvent e) {
		System.out.println("viewAllFilesAction(ActionEvent e)");
        try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/MusicManagerWebApp/view_all_files.xhtml?");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void checkForDupesAction(ActionEvent e) {
		System.out.println("checkForDupesAction(ActionEvent e)");
	}
	
	public void listDupesAction(ActionEvent e) {
		System.out.println("listDupesAction(ActionEvent e)");
		DuplicateViewManagedBean bean = new DuplicateViewManagedBean();
        try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/MusicManagerWebApp/view_duplicates.xhtml?");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        System.out.println("here");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isShowPanel() {
		return showPanel;
	}

	public void setShowPanel(boolean showPanel) {
		this.showPanel = showPanel;
	}
	
	
}

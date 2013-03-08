package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Teaching;

public class TeachingSeanceWidget extends Composite{

	protected VerticalPanel mainPanel;
	protected Label headerPanel;
	protected Label label;
	
	protected Teaching teaching;
	protected int posH; // [0-4] horizontal position on the grid
	protected int posV; // [0-65] vertical position on the grid
	
	public TeachingSeanceWidget(String text){
		mainPanel = new VerticalPanel();
		headerPanel = new Label(text);
		headerPanel.setHeight("15px");
		headerPanel.setWidth("100%");
		headerPanel.setStyleName("test-header");
		headerPanel.setHorizontalAlignment(Label.ALIGN_CENTER);
		
		label = new Label("infos complémentaires");
		label.setWidth("100%");
		label.setHorizontalAlignment(Label.ALIGN_CENTER);
		
		mainPanel.add(headerPanel);
		mainPanel.add(label);
		
		initWidget(mainPanel);
		this.setStyleName("teaching-cell");
		ScheduleDragController.getInstance().makeDraggable(this, headerPanel);
	}
	
	public TeachingSeanceWidget(Teaching teaching, int posH, int posV){
		this(teaching.getModule()+" "+teaching.getType());
		this.teaching = teaching;
		this.posH = posH;
		this.posV = posV;
	}
	
	public void setPosH(int newPosH){
		posH = newPosH;
	}
	
	public void setPosV(int newPosV){
		posV = newPosV;
	}
	
	public int getPosH(){
		return posH;
	}
	
	public int getPosV(){
		return posV;
	}
}

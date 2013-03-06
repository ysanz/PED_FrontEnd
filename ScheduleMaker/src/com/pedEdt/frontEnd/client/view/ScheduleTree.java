package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;

public class ScheduleTree extends Composite {

	protected ScrollPanel scrollPanel;
	protected Tree mainTree;
	protected TreeItem ue1;
	protected TreeItem ue2;
	protected TeachingTreeWidget mod1;
	protected TeachingTreeWidget mod2;
	protected TeachingTreeWidget mod3;
	
	
	@SuppressWarnings("deprecation")
	public ScheduleTree() {
		
		scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);
		
//		scrollPanel.setStylePrimaryName("scroll-area");
//		DOM.setStyleAttribute(scrollPanel.getElement(), "overflowX", "scroll");
//		DOM.setStyleAttribute(scrollPanel.getElement(), "overflowY", "scroll");
		scrollPanel.setWidth("150px");
		
		mainTree = new Tree();
		ue1 = new TreeItem("UE 1");
		ue1.setState(true);
		ue2 = new TreeItem("UE 2");
		ue2.setState(true);
		mod1 = new TeachingTreeWidget("Module 1");
		mod2 = new TeachingTreeWidget("Module 2");
		mod3 = new TeachingTreeWidget("Module 3");
		
		ScheduleDragController.getInstance().makeDraggable(mod1);
		ScheduleDragController.getInstance().makeDraggable(mod2);
		ScheduleDragController.getInstance().makeDraggable(mod3);
		
		mainTree.addItem(ue1);
		mainTree.addItem(ue2);
		ue1.addItem(mod1);
		ue1.addItem(mod2);
		ue2.addItem(mod3);
		scrollPanel.add(mainTree);
	}

}

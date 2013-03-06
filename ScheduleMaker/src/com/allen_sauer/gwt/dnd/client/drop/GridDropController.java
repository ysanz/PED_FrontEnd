package com.allen_sauer.gwt.dnd.client.drop;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.util.DebugPanel;
import com.pedEdt.frontEnd.client.view.TeachingSeanceWidget;
import com.pedEdt.frontEnd.client.view.TeachingTreeWidget;

public class GridDropController extends AbsolutePositionDropController{
	
	private int gridX;
	private int gridY;
	
	
	public GridDropController(AbsolutePanel dropTarget) {
		super(dropTarget);
		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / 5);
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / 66);
	}
	
	public void setGridX(int newGridX){
		gridX = newGridX;
	}
	
	public void setGridY(int newGridY){
		gridY = newGridY;
	}
	
	public void onDrop(final DragContext context){
		//super.onDrop(context);

		//get the top and left position and the widget
		int top =draggableList.get(0).desiredY;
		int left=draggableList.get(0).desiredX;
		Widget widget = context.draggable;

		//set the 'snapped' top and left position of the widget
		//left = Math.max(0, Math.min(left, dropTarget.getOffsetWidth() - widget.getOffsetWidth()));
		//top = Math.max(0, Math.min(top, dropTarget.getOffsetHeight() - widget.getOffsetHeight()));
		left = Math.round((float) left / gridX) * gridX;
		left = Math.max(0,left);
		top = Math.round((float) top / gridY) * gridY;
		top = Math.max(0,top);
		
		//debug
		DebugPanel.getInstance().vpan.clear();
		DebugPanel.getInstance().vpan.add(new Label("left = "+left));
		DebugPanel.getInstance().vpan.add(new Label("top = "+top));
		//end debug
		
		if( widget instanceof TeachingTreeWidget){
			TeachingSeanceWidget l = new TeachingSeanceWidget("hello");
			l.setHeight((gridY*8)+"px");
			l.setWidth((gridX-1)+"px");
			l.setStyleName("teaching-cell");
			l.setHorizontalAlignment(Label.ALIGN_CENTER);
			ScheduleDragController.getInstance().makeDraggable(l);
			dropTarget.add(l, left, top);
			draggableList.get(0).positioner.removeFromParent();
		}
		else {
			dropTarget.add(widget, left, top);
			draggableList.get(0).positioner.removeFromParent();
		}	
	}
	
	@Override
	public void onMove(final DragContext context) {
		super.onMove(context);

		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / 5);
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / 66);


		for (Draggable draggable : draggableList) {
			
			//debug
			DebugPanel.getInstance().vpan.clear();
			DebugPanel.getInstance().vpan.add(new Label("contextDesiredX = "+context.desiredDraggableX));
			DebugPanel.getInstance().vpan.add(new Label("contextDesiredY = "+context.desiredDraggableY));
			//end debug
			
			draggable.desiredX = context.desiredDraggableX - dropTargetOffsetX + draggable.relativeX;
			draggable.desiredY = context.desiredDraggableY - dropTargetOffsetY + draggable.relativeY;
			
			//debug
			DebugPanel.getInstance().vpan.add(new Label("desiredX = "+draggable.desiredX));
			DebugPanel.getInstance().vpan.add(new Label("desiredY = "+draggable.desiredY));
			DebugPanel.getInstance().vpan.add(new Label("gridX = "+gridX));
			DebugPanel.getInstance().vpan.add(new Label("gridY = "+gridY));
			//end debug

//			draggable.desiredX = Math.max(0, Math.min(draggable.desiredX, dropTargetClientWidth - draggable.offsetWidth));
//			draggable.desiredY = Math.max(0, Math.min(draggable.desiredY, dropTargetClientHeight - draggable.offsetHeight));
			draggable.desiredX = (int)Math.floor((double) draggable.desiredX / gridX) * gridX;
			draggable.desiredX = Math.max(0, draggable.desiredX);
			draggable.desiredY = (int)Math.round((double) draggable.desiredY / gridY) * gridY;
			draggable.desiredY = Math.max(0, draggable.desiredY);
			
			//debug
			DebugPanel.getInstance().vpan.add(new Label("left = "+draggable.desiredX));
			DebugPanel.getInstance().vpan.add(new Label("top = "+draggable.desiredY));
			//end debug

			
			draggable.positioner.setWidth(gridX+"px");
			draggable.positioner.setHeight("80px");
			dropTarget.add(draggable.positioner, draggable.desiredX, draggable.desiredY);
		}
	}
	
}
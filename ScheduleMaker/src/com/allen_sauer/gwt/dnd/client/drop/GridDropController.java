package com.allen_sauer.gwt.dnd.client.drop;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.pedEdt.frontEnd.client.view.Grid;
import com.pedEdt.frontEnd.client.view.SeanceWidget;
import com.pedEdt.frontEnd.client.view.SeanceWidgetsManager;
import com.pedEdt.frontEnd.client.view.TreeTeachingWidget;

public class GridDropController extends AbsolutePositionDropController{
	
	private int gridX;
	private int gridY;
	private int days;
	private int intervals;
	private SeanceWidgetsManager swManager;
	
	public GridDropController(Grid grid) {
		super(grid.getDroppableArea());
		
		days = grid.getNbDays();
		intervals = grid.getNbIntervals()+1; //TODO: why +1 necessary to fit the grid?
		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / days);
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / intervals);
		swManager = new SeanceWidgetsManager(grid);
	}
	
	private int applyVerticalAttraction(int posV, int length){
		posV = Math.min(posV, intervals - 1 - length);
		return posV;
	}
	
	
	public void onDrop(final DragContext context){

		//get the top and left position and the widget
		int top =draggableList.get(0).desiredY;
		int left=draggableList.get(0).desiredX;
		SeanceWidget widget = null;
		
		if( context.draggable instanceof TreeTeachingWidget){
			SeanceWidget tw = new SeanceWidget(((TreeTeachingWidget) context.draggable).getTeaching());
			int lenght = ((TreeTeachingWidget)context.draggable).getLengthOnGrid();
			tw.setLength(lenght);
			tw.setHeight((gridY*lenght+lenght)+"px");
			tw.setWidth((gridX-1)+"px");
			widget = tw;
		}
		else if( context.draggable instanceof SeanceWidget){
			widget = (SeanceWidget) context.draggable;
		}	
		
		int posH = Math.round((float) left / gridX);
		int posV = Math.round((float) top / gridY);
		posV = applyVerticalAttraction(posV, widget.getLength());

		widget.setPosV(posV);
		widget.setPosH(posH);
		swManager.addSeance(widget);
		swManager.doPositionning();
		draggableList.get(0).positioner.removeFromParent();	
	}
	
	@Override
	public void onMove(final DragContext context) {
		super.onMove(context);

		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / days); 
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / intervals);
		

		for (Draggable draggable : draggableList) {
			draggable.desiredX = context.desiredDraggableX - dropTargetOffsetX + draggable.relativeX;
			draggable.desiredY = context.desiredDraggableY - dropTargetOffsetY + draggable.relativeY;
			
			//border correction
			draggable.desiredY -= draggable.desiredY/10;

			int posH = Math.round((float) draggable.desiredX / gridX);
			int posV = Math.round((float) draggable.desiredY / gridY);
			int length = 0;
			if( draggable.widget instanceof TreeTeachingWidget){
				length = ((TreeTeachingWidget)draggable.widget).getLengthOnGrid();
				
			}
			else if( draggable.widget instanceof SeanceWidget){
				length = ((SeanceWidget)draggable.widget).getLength();		
			}
		
			posV = applyVerticalAttraction(posV, length);

			draggable.positioner.setWidth((gridX-1)+"px");
			draggable.positioner.setHeight((gridY*length+length)+"px");
			draggable.desiredX = posH * gridX;
			draggable.desiredX = Math.max(0, draggable.desiredX);
			draggable.desiredY = posV * gridY;
			draggable.desiredY = Math.max(0, draggable.desiredY);
			
			int postionerY = draggable.desiredY + (draggable.desiredY/10);
			dropTarget.add(draggable.positioner, draggable.desiredX, postionerY);
		}
	}	
}
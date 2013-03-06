package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class ScheduleGrid extends Composite {
	
	protected AbsolutePanel grid;
   
    private final int days = 5;
    private final int HOURS_PER_DAY = 11;
    private final int intervalsPerHour = 6; // 10 minutes
    private final int intervalSize = 10; //px
    
    private final int GRID_WIDTH = 800;
    private final int GRID_HEIGHT = intervalsPerHour*intervalSize*HOURS_PER_DAY;

	public ScheduleGrid() {
		grid = new AbsolutePanel();
	    initWidget(grid);
	    
	    grid.clear();

        this.setHeight(GRID_HEIGHT + "px");

        float dayWidth = GRID_WIDTH / days;
        float dayLeft = 0f;

        for (int i = 0; i < HOURS_PER_DAY; i++) {
            //create major interval
            SimplePanel sp1 = new SimplePanel();
            sp1.setStyleName("major-time-interval");
            sp1.setHeight(intervalSize + "px");

            //add to body
            grid.add(sp1);

            for (int x = 0; x < intervalsPerHour - 1; x++) {
                SimplePanel sp2 = new SimplePanel();
                sp2.setStyleName("minor-time-interval");

                sp2.setHeight(intervalSize + "px");
                grid.add(sp2);
            }
        }

        for (int day = 0; day < days; day++) {
            dayLeft = dayWidth * day;
            SimplePanel dayPanel = new SimplePanel();
            dayPanel.setStyleName("day-separator");
            grid.add(dayPanel);
            DOM.setStyleAttribute(dayPanel.getElement(), "left", dayLeft + "px");
        }
	}

	public AbsolutePanel getDroppableArea() {
		return grid;
	}
	
	public int getNbDays(){
		return days;
	}
	
	public int getNbIntervals(){
		return intervalsPerHour * HOURS_PER_DAY;
	}
	
	public int getWidth(){
		return GRID_WIDTH;
	}
	public int getHeight(){
		return GRID_HEIGHT;
	}
		
}

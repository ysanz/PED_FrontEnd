package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;


//TODO: Continue... bugged at the moment, height is not what's expected
public class GridTimeline extends Composite {

	private FlexTable timelinePanel;
	
	private final int intervalsPerHour = 6;
	private final int pixelsPerInterval = 10;
	private final int dayStartAt = 8;
	private final int HOURS_IN_DAY = 11;
	
	public GridTimeline(){
		timelinePanel = new FlexTable();
		timelinePanel.setWidth("50px");
		timelinePanel.setCellSpacing(0);
		timelinePanel.setCellSpacing(0);
		timelinePanel.setBorderWidth(0);
		initWidget(timelinePanel);
		int hourHeight = intervalsPerHour*pixelsPerInterval;
        
		for(int i=0; i < HOURS_IN_DAY; i++){
        	Label hourLbl = new Label((dayStartAt+i)+"h");
        	hourLbl.setStyleName("hour-label");
        	hourLbl.setHeight(hourHeight+"px");
        	hourLbl.setWidth("50px");
        	timelinePanel.setWidget(i, 0, hourLbl);
        }
	}
}

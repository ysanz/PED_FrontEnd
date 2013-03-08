package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Tree;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public class TreeSemesterWidget extends Tree{
	
	protected Semester semester;
	
	public TreeSemesterWidget(Semester semester){
		super();
		this.semester = semester;
	}

	public Semester getSemester(){
		return semester;
	}
	
	public void createTree(){
		for (TeachingUnit teachingUnit : semester.getTeachingUnits()) {
			TreeTeachingUnitWidget widget = new TreeTeachingUnitWidget(teachingUnit);
			widget.createTree();
			addItem(widget);
		}
	}
	
}

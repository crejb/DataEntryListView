package com.cjb.dataentrylistview;

import android.content.Context;

/*
 * Interface that each different type of list item implements.
 * The main task is to display a UI for allowing the user to specify
 * a new value of the correct type e.g. a Date picker for a Date field,
 * a Text Entry dialog for a String field.
 */
public interface DataEntryListItem extends Comparable<DataEntryListItem> {
	
	String GetDescription();
	String GetDisplayValue();
	void DisplayValueEditor(Context context, OnEditCompleteListener onEditCompleteListener);
	
	public interface OnEditCompleteListener{
		void OnEditComplete();
	}
}


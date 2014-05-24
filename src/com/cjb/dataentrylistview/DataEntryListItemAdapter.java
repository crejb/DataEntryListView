package com.cjb.dataentrylistview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cjb.dataentrylistview.DataEntryListItem.OnEditCompleteListener;

public class DataEntryListItemAdapter extends ArrayAdapter<DataEntryListItem> {
	private List<DataEntryListItem> _items;
	private LayoutInflater _layoutInflator;
	private Context _context;

	public DataEntryListItemAdapter(Context context, int textViewResourceId, List<DataEntryListItem> items){
		super(context, textViewResourceId, items);
		_items = items;
		_layoutInflator = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_context = context;
	}
	
	@Override
	public int getViewTypeCount() {
		return 1;
	}
	
	@Override
	public int getItemViewType(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = _layoutInflator.inflate(R.layout.layout_data_entry_list_item, null);
			convertView.setTag(new ViewHolder(convertView));			
		}

		DataEntryListItem listItem = _items.get(position);
		ViewHolder viewHolder = (ViewHolder)convertView.getTag();
		viewHolder.populateView(listItem);
		return convertView;
	}
	
	/*
	 * The View Layout has 2 labels: Description and Value
	 * When clicking on an item, it will trigger the specific 
	 * value picker UI for that type 
	 */
	private class ViewHolder{
		private View _listItemView;
		private TextView _txtDescription;
		private TextView _txtValue;
		
		public ViewHolder(View view){
			_listItemView = view;
			_txtDescription = (TextView) view.findViewById(R.id.label_description);
			_txtValue = (TextView) view.findViewById(R.id.label_value);
		}
		
		public void populateView(final DataEntryListItem listItem){
			// Create the callback for handling completion of editing the value,
			// so we can display the value in the list
			final OnEditCompleteListener onEditCompleteListener = new OnEditCompleteListener() {
				@Override
				public void OnEditComplete() {
					_txtValue.setText(listItem.GetDisplayValue());
				}
			};
			
			// Attach to the Click event of the list item to call the
			// specific editor for this list item type 
			_listItemView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						listItem.DisplayValueEditor(_context, onEditCompleteListener);
					}
	            });
			
			// Set the values to display in the list
			_txtDescription.setText(listItem.GetDescription());
			_txtValue.setText(listItem.GetDisplayValue());
		}
	}
}

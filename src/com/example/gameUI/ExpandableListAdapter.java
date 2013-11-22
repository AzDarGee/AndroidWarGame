package com.example.gameUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.Map;

import com.example.androidwargame.R;




public class ExpandableListAdapter extends BaseExpandableListAdapter{

	private Activity context;
    private Map<String, List<String>> optionCollections;
    private List<String> child;
 
    public ExpandableListAdapter(Activity context, List<String> child,
            Map<String, List<String>> optionCollections) {
        this.context = context;
        this.optionCollections = optionCollections;
        this.child = child;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return optionCollections.get(child.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String laptop = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }
 
        TextView item = (TextView) convertView.findViewById(R.id.child);
 
//        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
//        delete.setOnClickListener(new OnClickListener() {
// 
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Do you want to remove?");
//                builder.setCancelable(false);
//                builder.setPositiveButton("Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                List<String> child =
//                                    laptopCollections.get(laptops.get(groupPosition));
//                                child.remove(childPosition);
//                                notifyDataSetChanged();
//                            }
//                        });
//                builder.setNegativeButton("No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
 
        item.setText(laptop);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return optionCollections.get(child.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return child.get(groupPosition);
    }
 
    public int getGroupCount() {
        return child.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String option = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.drawer_listview_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.child);
        //item.setTypeface(null, Typeface.BOLD);
        item.setText(option);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
	
}

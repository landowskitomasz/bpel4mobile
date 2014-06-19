package com.bpel4mobile.hotel.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.bpel4mobile.hotel.android.model.Room;
import com.bpel4mobile.hotel.android.model.Task;

import com.bpel4mobile.hotel.android.R;
import com.bpel4mobile.hotel.android.model.TaskName;
import org.springframework.util.StringUtils;

import java.util.List;

public class TasksListAdapter extends ArrayAdapter<Task> {

    private int viewResourceId;
	
	private LayoutInflater inflater;

	public TasksListAdapter(Context context, int viewResourceId,
                            List<Task> objects) {
		super(context, 0, objects);
		this.viewResourceId = viewResourceId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	private class ViewHolder {
		View colorRectangle;
		TextView name;
        TextView room;
        TextView floor;
        TextView category;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(viewResourceId, null);
			holder = createViewHolder(convertView);
			convertView.setTag(holder);
		}
		else if(convertView.getTag() == null || !(convertView.getTag() instanceof ViewHolder)){
			holder = createViewHolder(convertView);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder)convertView.getTag();
		}
		Task task = getItem(position);

        if(TaskName.verifyTask.equals(task.getName())){
            holder.name.setText(R.string.verifyTaskName);
        } else if(TaskName.cleanUpTask.equals(task.getName())) {
            holder.name.setText(R.string.cleanUpTaskName);
        } else {
            Log.e(this.getClass().getName(), "Unknown task.");
        }

        Room room = task.getRequest().getRoom();
        holder.room.setText("" + room.getNumber());
        holder.floor.setText("" + room.getFloor());
        holder.category.setText(room.getCategory().getName());

		return convertView;
	}

	private ViewHolder createViewHolder(View convertView) {
		ViewHolder holder = new ViewHolder();
        holder.colorRectangle = convertView.findViewById(R.id.task_item_view);
		holder.name = (TextView)convertView.findViewById(R.id.task_name_label);
        holder.room = (TextView)convertView.findViewById(R.id.room_number);
        holder.floor = (TextView)convertView.findViewById(R.id.floor);
        holder.category = (TextView)convertView.findViewById(R.id.category_name);
		return holder;
	}

}
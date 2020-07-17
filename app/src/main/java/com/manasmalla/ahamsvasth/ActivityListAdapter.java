package com.manasmalla.ahamsvasth;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ActivityListAdapter extends BaseAdapter {

    Context context;
    List<String> text, dateStart, dateEnd;
    List<Integer> distances;

    public ActivityListAdapter(Context context, List<String> text, List<String> dateStart, List<String> dateEnd, List<Integer> distances){
        this.context = context;
        this.text = text;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.distances = distances;
    }


    @Override
    public int getCount() {
        return text.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_list_adapter, parent, false);
            viewHolder.activity = convertView.findViewById(R.id.textView42);
            viewHolder.dateS = convertView.findViewById(R.id.textView44);
            viewHolder.dateE = convertView.findViewById(R.id.textView46);
            viewHolder.distance = convertView.findViewById(R.id.textView48);
            viewHolder.flag = convertView.findViewById(R.id.imageView39);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        if (text != null) {
            viewHolder.activity.setText(text.get(position));
            viewHolder.dateS.setText(dateStart.get(position));
            viewHolder.dateE.setText(dateEnd.get(position));
            Log.d("d",distances.toString());
            if (distances.get(position) == 0){
                viewHolder.distance.setText("");
                viewHolder.flag.setVisibility(View.GONE);
            }else {
                viewHolder.distance.setText(distances.get(position) + " m");

                viewHolder.flag.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    private static class ViewHolder {

        TextView activity, dateS, dateE, distance;
        ImageView flag;

    }

    public List<String> getArray(){
        return text;
    }
}

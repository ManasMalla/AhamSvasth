package com.manasmalla.ahamsvasth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RecommendationAdapter extends BaseAdapter {
    Context context;
    List<String> text;
    List<Integer> drawableBullets;
    float textSize;

    public RecommendationAdapter(Context context, List<String> text, List<Integer> drawableBullets, float textSize) {
        this.context = context;
        this.text = text;
        this.drawableBullets = drawableBullets;
        this.textSize = textSize;
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
            convertView = inflater.inflate(R.layout.recommendation_adapter, parent, false);
            viewHolder.textTextView = convertView.findViewById(R.id.recommendationListTextView);
            viewHolder.bulletImageView = convertView.findViewById(R.id.recommendationListBulletImageView);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.textTextView.setText(text.get(position));
        viewHolder.textTextView.setTextSize(textSize);

        viewHolder.bulletImageView.setImageResource(drawableBullets.get(position));

        return convertView;
    }

    private static class ViewHolder {

        TextView textTextView;
        ImageView bulletImageView;

    }

    public List<String> getArray() {
        return text;
    }
}

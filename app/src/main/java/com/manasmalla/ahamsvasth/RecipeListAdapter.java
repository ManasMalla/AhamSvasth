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

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends BaseAdapter {

    Context context;
    List<Recipe> recipes;

    public RecipeListAdapter(Context context, List<Recipe> recipes){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listview_adapter_recipe, parent, false);
            viewHolder.recipeNameTextView = convertView.findViewById(R.id.recipeNameTxtView_adapter);
            viewHolder.recipeImageView = convertView.findViewById(R.id.recipeImageView_adapter);
            viewHolder.recipeNutrient1TextView = convertView.findViewById(R.id.nutritionTV1);
            viewHolder.recipeNutrient2TextView = convertView.findViewById(R.id.nutritionTV2);
            viewHolder.recipeNutrient3TextView = convertView.findViewById(R.id.nutritionTV3);
            viewHolder.recipeNutrient4TextView = convertView.findViewById(R.id.nutritionTV4);
            viewHolder.recipeNutrient5TextView = convertView.findViewById(R.id.nutritionTV5);
            viewHolder.recipeNutrient6TextView = convertView.findViewById(R.id.nutritionTV6);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        viewHolder.recipeNameTextView.setText(recipes.get(position).recipeName);
        viewHolder.recipeImageView.setImageResource(recipes.get(position).recipeImage);
        viewHolder.recipeNutrient1TextView.setText(recipes.get(position).nutrients.get("Energy"));
        viewHolder.recipeNutrient2TextView.setText(recipes.get(position).nutrients.get("Proteins"));
        viewHolder.recipeNutrient3TextView.setText(recipes.get(position).nutrients.get("Carbohydrates"));
        viewHolder.recipeNutrient4TextView.setText(recipes.get(position).nutrients.get("Fats"));
        viewHolder.recipeNutrient5TextView.setText(recipes.get(position).nutrients.get("Fibre"));
        viewHolder.recipeNutrient6TextView.setText(recipes.get(position).nutrients.get("Proteins"));
        return convertView;
    }

    private static class ViewHolder {

        TextView recipeNameTextView;
        ImageView recipeImageView;
        TextView recipeNutrient1TextView;
        TextView recipeNutrient2TextView;
        TextView recipeNutrient3TextView;
        TextView recipeNutrient4TextView;
        TextView recipeNutrient5TextView;
        TextView recipeNutrient6TextView;

    }
}

package com.example.zomy.potspans.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zomy.potspans.R;
import com.example.zomy.potspans.model.RecipeItem;
import com.example.zomy.potspans.utils.OnListItemInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RecipeItem} and makes a call to the
 */
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private ArrayList<RecipeItem> mValues;
    private final OnListItemInteractionListener mListener;
    private Activity mActivity;

    public RecipeRecyclerViewAdapter(ArrayList<RecipeItem> items, OnListItemInteractionListener listener, Activity context) {
        mValues = items;
        mListener = listener;
        mActivity = context;
    }

    public ArrayList<RecipeItem> getValues() {
        return mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.recipeTitle.setText(mValues.get(position).getTitle());
        int rankD = (int)Double.parseDouble(mValues.get(position).getSocialRank())*100;
        String rank = mActivity.getString(R.string.recipe_rank) + rankD/100f ;
        holder.recipeRank.setText(rank);
        Picasso.with(mActivity).
                load(holder.mItem.getImageUrl())
                .placeholder(R.drawable.recipe_placeholder)
                .error(R.drawable.recipe_error_placeholder)
                .into(holder.recipeImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListItemInteraction(holder.mItem);
                }
            }
        });
    }

    public int getItemPosition(RecipeItem movieItem){
        return mValues.indexOf(movieItem);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.recipe_title)
        public TextView recipeTitle;
        @BindView(R.id.recipe_image)
        public ImageView recipeImage;
        public RecipeItem mItem;
        @BindView(R.id.recipe_rank)
        public TextView recipeRank;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }

    public void setValues(ArrayList<RecipeItem> mValues) {
        this.mValues = mValues;
        notifyDataSetChanged();
    }

    public void addValues(ArrayList<RecipeItem> mValues){
        Log.d("hazem", "addValues: " + mValues.size());
        this.mValues.addAll(mValues);
        notifyDataSetChanged();
    }
}

package com.example.zomy.potspans.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by
 * ZoMy on 11/2/2016.
 */

public class RecipesList implements Parcelable{

    @SerializedName("count")
    private int count;
    @SerializedName("recipes")
    private ArrayList<RecipeItem> recipes;

    public int getCount() {
        return count;
    }

    public ArrayList<RecipeItem> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "RecipesList{" +
                "count=" + count +
                ", recipes=" + recipes +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeTypedList(this.recipes);
    }

    public RecipesList() {
    }

    protected RecipesList(Parcel in) {
        this.count = in.readInt();
        this.recipes = in.createTypedArrayList(RecipeItem.CREATOR);
    }

    public static final Creator<RecipesList> CREATOR = new Creator<RecipesList>() {
        @Override
        public RecipesList createFromParcel(Parcel source) {
            return new RecipesList(source);
        }

        @Override
        public RecipesList[] newArray(int size) {
            return new RecipesList[size];
        }
    };
}

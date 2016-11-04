package com.example.zomy.potspans.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by
 * ZoMy on 11/2/2016.
 */

public class Recipe implements Parcelable{

    @SerializedName("recipe")
    private RecipeItem recipeItem;

    public RecipeItem getRecipe() {
        return recipeItem;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeItem=" + recipeItem +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.recipeItem, flags);
    }

    public Recipe() {
    }

    protected Recipe(Parcel in) {
        this.recipeItem = in.readParcelable(RecipeItem.class.getClassLoader());
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}

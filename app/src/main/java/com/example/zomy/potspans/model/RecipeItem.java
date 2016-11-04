package com.example.zomy.potspans.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by
 * ZoMy on 11/2/2016.
 */

public class RecipeItem implements Parcelable {
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("f2f_url")
    private String f2fUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("source_url")
    private String sourceUrl;
    @SerializedName("recipe_id")
    private String recipeId;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("social_rank")
    private String socialRank;
    @SerializedName("publisher_url")
    private String publisherUrl;
    @SerializedName("ingredients")
    private ArrayList<String> ingredients;

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getF2fUrl() {
        return f2fUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSocialRank() {
        return socialRank;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    @Override
    public String toString() {
        return "RecipeItem{" +
                "publisher='" + publisher + '\'' +
                ", f2fUrl='" + f2fUrl + '\'' +
                ", title='" + title + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", recipeId='" + recipeId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", socialRank='" + socialRank + '\'' +
                ", publisherUrl='" + publisherUrl + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.publisher);
        dest.writeString(this.f2fUrl);
        dest.writeString(this.title);
        dest.writeString(this.sourceUrl);
        dest.writeString(this.recipeId);
        dest.writeString(this.imageUrl);
        dest.writeString(this.socialRank);
        dest.writeString(this.publisherUrl);
        dest.writeStringList(this.ingredients);
    }

    public RecipeItem() {
    }

    protected RecipeItem(Parcel in) {
        this.publisher = in.readString();
        this.f2fUrl = in.readString();
        this.title = in.readString();
        this.sourceUrl = in.readString();
        this.recipeId = in.readString();
        this.imageUrl = in.readString();
        this.socialRank = in.readString();
        this.publisherUrl = in.readString();
        this.ingredients = in.createStringArrayList();
    }

    public static final Creator<RecipeItem> CREATOR = new Creator<RecipeItem>() {
        @Override
        public RecipeItem createFromParcel(Parcel source) {
            return new RecipeItem(source);
        }

        @Override
        public RecipeItem[] newArray(int size) {
            return new RecipeItem[size];
        }
    };
}

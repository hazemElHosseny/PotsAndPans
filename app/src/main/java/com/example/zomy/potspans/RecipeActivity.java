package com.example.zomy.potspans;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zomy.potspans.model.Recipe;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeActivity extends AppCompatActivity {

    @BindView(R.id.recipe_image)
    ImageView recipeImageView;
    @BindView(R.id.recipe_title)
    TextView recipeTitleTextView;
    @BindView(R.id.recipe_rank)
    TextView recipeRankTextView;
    @BindView(R.id.recipe_ingredient)
    TextView recipeIngredientTextView;

    private ShareActionProvider mShareActionProvider;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(RecipeActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recipe = getIntent().getParcelableExtra("Recipe");

        recipeTitleTextView.setText(recipe.getRecipe().getTitle());
        recipeRankTextView.setText(recipe.getRecipe().getSocialRank());
        Picasso.with(RecipeActivity.this).
                load(recipe.getRecipe().getImageUrl())
                .placeholder(R.drawable.recipe_placeholder)
                .error(R.drawable.recipe_error_placeholder)
                .into(recipeImageView);

        String recipeIngredientString = "";
        for(String ingredient: recipe.getRecipe().getIngredients()){
            recipeIngredientString = recipeIngredientString + ingredient + " \n";
        }
        recipeIngredientTextView.setText(recipeIngredientString);
    }

    @OnClick(R.id.recipe_details)
    public void details(){
        Intent intent = new Intent(RecipeActivity.this,RecipeWebViewActivity.class);
        intent.putExtra("URL",recipe.getRecipe().getSourceUrl());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);

        MenuItem item = menu.findItem(R.id.share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name_sent) + " " + recipe.getRecipe().getTitle() + " " + Uri.parse(recipe.getRecipe().getSourceUrl()));
        sendIntent.setType("text/plain");
        setShareIntent(sendIntent);

        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.converter){
            Intent intent = new Intent(RecipeActivity.this,ConverterActivity.class);
            startActivity(intent);
        }
        return true;

    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}

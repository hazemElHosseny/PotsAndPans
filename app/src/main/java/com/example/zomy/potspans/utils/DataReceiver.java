package com.example.zomy.potspans.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.zomy.potspans.HomeActivity;
import com.example.zomy.potspans.R;
import com.example.zomy.potspans.model.RecipesList;

public class DataReceiver extends BroadcastReceiver {
    HomeActivity homeActivity;
    public DataReceiver(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("hazem", "onReceive: ");
        RecipesList recipesList = intent.getParcelableExtra("RecipesList");
        boolean newTask = intent.getBooleanExtra("newTask",true);
        boolean error = intent.getBooleanExtra("Error",false);
        if(error){
            Toast.makeText(homeActivity, homeActivity.getString(R.string.no_results), Toast.LENGTH_SHORT).show();
            homeActivity.setLoading(true);
        }else {
            if (newTask) {
                homeActivity.getRecipeRecyclerViewAdapter().setValues(recipesList.getRecipes());
            } else {
                homeActivity.getRecipeRecyclerViewAdapter().addValues(recipesList.getRecipes());
            }
            homeActivity.setLoading(false);
        }
        homeActivity.getProgressWheel().setVisibility(View.GONE);

    }
}

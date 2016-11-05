package com.example.zomy.potspans.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zomy.potspans.HomeActivity;
import com.example.zomy.potspans.R;
import com.example.zomy.potspans.RecipeActivity;
import com.example.zomy.potspans.model.Recipe;
import com.example.zomy.potspans.model.RecipesList;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by
 * ZoMy on 11/2/2016.
 */

public class DataClass {
    public static void getRecipes(final String pageNo, final String sort, final Activity context) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Utilities.searchRecipes(null, sort, pageNo), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                RecipesList recipesList = gson.fromJson(response.toString(),RecipesList.class);
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("RecipesList",recipesList);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.showDialog(context, R.string.title_error, R.string.message_error, R.string.retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getRecipes(pageNo, sort, context);
                    }
                }, R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        context.finish();
                    }
                });
            }
        });
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

    public static void getRecipe(String recipeId,final Activity context) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Utilities.getRecipe(recipeId), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("hazem", "onResponse: " + response.toString());
                Gson gson = new Gson();
                Recipe recipe = gson.fromJson(response.toString(),Recipe.class);
                Log.d("hazem", "onResponse: " + recipe.toString());
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("Recipe",recipe);
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.showDialogOneButton(context, R.string.title_error, R.string.message_error,
                        R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(context instanceof HomeActivity) {
                            ((HomeActivity) context).getProgressWheel().setVisibility(View.GONE);
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }
}

package com.example.zomy.potspans.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.net.URI;

/**
 * Created by
 * ZoMy on 11/2/2016.
 */

public class Utilities {
    private static final String FOOD_2_FORK_API_KEY = "f5a181dce4cca5622f7ccdfc89b459b5";
    private static final String SEARCH_URL = "http://food2fork.com/api/search";
    private static final String RECIPE_URL = "http://food2fork.com/api/get";
    private static final String PAR_KEY = "key";
    private static final String PAR_Q = "q";
    private static final String PAR_SORT = "sort";
    private static final String PAR_PAGE = "page";
    private static final String PAR_RID = "rId";
    public static final String PREF_SORT = "pref_sort";
    public static final String PREF_APP = "pots_pans";
    public static final double TE_SPOON_TO_TE_SPOON = 1;
    public static final double TE_SPOON_TO_D_SPOON = 0.5;
    public static final double TE_SPOON_TO_TA_SPOON = 1/3f;
    public static final double TE_SPOON_TO_CUP = 0.02083;
    public static final double TE_SPOON_TO_M_LITER = 5;
    public static final double TE_SPOON_TO_D_LITER = 0.05;
    public static final double TE_SPOON_TO_LITER = 0.005;
    public static final double TE_SPOON_TO_GRAM = 2.1;
    public static final double TE_SPOON_TO_K_GRAM = 0.0021;
    public static final double D_SPOON_TO_TE_SPOON = 2;
    public static final double D_SPOON_TO_D_SPOON = 1;
    public static final double D_SPOON_TO_TA_SPOON = 2/3f;
    public static final double D_SPOON_TO_CUP = 0.04167;
    public static final double D_SPOON_TO_M_LITER = 10;
    public static final double D_SPOON_TO_D_LITER = 0.1;
    public static final double D_SPOON_TO_LITER = 0.01;
    public static final double D_SPOON_TO_GRAM = 4.2;
    public static final double D_SPOON_TO_K_GRAM = 0.0042;
    public static final double TA_SPOON_TO_TE_SPOON = 3;
    public static final double TA_SPOON_TO_D_SPOON = 1.5;
    public static final double TA_SPOON_TO_TA_SPOON = 1;
    public static final double TA_SPOON_TO_CUP = 0.0625;
    public static final double TA_SPOON_TO_M_LITER = 15;
    public static final double TA_SPOON_TO_D_LITER = 0.15;
    public static final double TA_SPOON_TO_LITER = 0.015;
    public static final double TA_SPOON_TO_GRAM = 6.3;
    public static final double TA_SPOON_TO_K_GRAM = 0.0063;
    public static final double CUP_TO_TE_SPOON = 48;
    public static final double CUP_TO_D_SPOON = 24;
    public static final double CUP_TO_TA_SPOON = 16;
    public static final double CUP_TO_CUP = 16;
    public static final double CUP_TO_M_LITER = 240;
    public static final double CUP_TO_D_LITER = 2.4;
    public static final double CUP_TO_LITER = 0.24;
    public static final double CUP_TO_GRAM = 100.8;
    public static final double CUP_TO_K_GRAM = 0.1008;
    public static final double M_LITER_TO_TE_SPOON = 0.2;
    public static final double M_LITER_TO_D_SPOON = 0.1;
    public static final double M_LITER_TO_TA_SPOON = 2/30f;
    public static final double M_LITER_TO_CUP = 0.004167;
    public static final double M_LITER_TO_M_LITER = 1;
    public static final double M_LITER_TO_D_LITER = 0.01;
    public static final double M_LITER_TO_LITER = 0.001;
    public static final double M_LITER_TO_GRAM = 0.42;
    public static final double M_LITER_TO_K_GRAM = 0.00042;
    public static final double D_LITER_TO_TE_SPOON = 20;
    public static final double D_LITER_TO_D_SPOON = 10;
    public static final double D_LITER_TO_TA_SPOON = 20/3f;
    public static final double D_LITER_TO_CUP = 0.4167;
    public static final double D_LITER_TO_M_LITER = 100;
    public static final double D_LITER_TO_D_LITER = 1;
    public static final double D_LITER_TO_LITER = 0.1;
    public static final double D_LITER_TO_GRAM = 42;
    public static final double D_LITER_TO_K_GRAM = 0.042;
    public static final double LITER_TO_TE_SPOON = 200;
    public static final double LITER_TO_D_SPOON = 100;
    public static final double LITER_TO_TA_SPOON = 200/3f;
    public static final double LITER_TO_CUP = 4.167;
    public static final double LITER_TO_M_LITER = 1000;
    public static final double LITER_TO_D_LITER = 10;
    public static final double LITER_TO_LITER = 1;
    public static final double LITER_TO_GRAM = 420;
    public static final double LITER_TO_K_GRAM = 0.42;
    public static final double GRAM_TO_TE_SPOON = 0.4762;
    public static final double GRAM_TO_D_SPOON = 0.2381;
    public static final double GRAM_TO_TA_SPOON = 0.1587;
    public static final double GRAM_TO_CUP = 0.009921;
    public static final double GRAM_TO_M_LITER = 2.381;
    public static final double GRAM_TO_D_LITER = 0.02381;
    public static final double GRAM_TO_LITER = 0.002381;
    public static final double GRAM_TO_GRAM = 1;
    public static final double GRAM_TO_K_GRAM = 0.001;
    public static final double K_GRAM_TO_TE_SPOON = 476.2;
    public static final double K_GRAM_TO_D_SPOON = 238.1;
    public static final double K_GRAM_TO_TA_SPOON = 158.7;
    public static final double K_GRAM_TO_CUP = 9.921;
    public static final double K_GRAM_TO_M_LITER = 2381;
    public static final double K_GRAM_TO_D_LITER = 23.81;
    public static final double K_GRAM_TO_LITER = 2.381;
    public static final double K_GRAM_TO_GRAM = 1000;
    public static final double K_GRAM_TO_K_GRAM = 1;

    public static String searchRecipes(String query, String sort, String page){
        Uri.Builder builder = Uri.parse(SEARCH_URL).buildUpon();
        builder.appendQueryParameter(PAR_KEY,FOOD_2_FORK_API_KEY);
        if (query!=null){
            builder.appendQueryParameter(PAR_Q,query);
        }
        if (sort != null){
            builder.appendQueryParameter(PAR_SORT,sort);
        }
        if (page != null){
            builder.appendQueryParameter(PAR_PAGE,page);
        }
        Log.d("hazem", "searchRecipes: " +builder.build().toString());
        return builder.build().toString();
    }

    public static String getRecipe(String recipeId){
        Uri.Builder builder = Uri.parse(RECIPE_URL).buildUpon();
        builder.appendQueryParameter(PAR_KEY,FOOD_2_FORK_API_KEY)
        .appendQueryParameter(PAR_RID,recipeId);
        return builder.build().toString();
    }

    public static void showDialog(final Activity currentActivity, int title, int message,
                                  int positiveText, DialogInterface.OnClickListener positiveListener,
                                  int negativeText, DialogInterface.OnClickListener  negativeListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton(negativeText, negativeListener);
        builder.setPositiveButton(positiveText, positiveListener);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public static void showDialogOneButton(final Activity currentActivity,int title, int message,
                                           int positiveText, DialogInterface.OnClickListener positiveListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, positiveListener);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}

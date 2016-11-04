package com.example.zomy.potspans.utils;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zomy.potspans.model.RecipesList;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class LiveDataService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_UPDATE = "com.example.zomy.potspans.utils.action.UPDATE";

    // TODO: Rename parameters
    private static final String EXTRA_QUERY = "com.example.zomy.potspans.utils.extra.QUERY";
    private static final String EXTRA_PAGE_NO = "com.example.zomy.potspans.utils.extra.PAGE_NO";
    private static final String EXTRA_SORT = "com.example.zomy.potspans.utils.extra.SORT";
    private static final String EXTRA_NEW_TASK = "com.example.zomy.potspans.utils.extra.NEW_TASK";
    private static int currentPage = 1;
    private static boolean last;

    public LiveDataService() {
        super("LiveDataService");
    }

    public static void startActionUpdate(Context context, String query, String sort, boolean newTask) {

        if(newTask){
            currentPage = 1;
            last = false;
        }else{
            currentPage++;
            if(last){
                return;
            }
        }
        Intent intent = new Intent(context, LiveDataService.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_QUERY, query);
        intent.putExtra(EXTRA_PAGE_NO, ""+currentPage);
        intent.putExtra(EXTRA_SORT, sort);
        intent.putExtra(EXTRA_NEW_TASK, newTask);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
             if (ACTION_UPDATE.equals(action)) {
                 final String query = intent.getStringExtra(EXTRA_QUERY);
                 final String pageNo = intent.getStringExtra(EXTRA_PAGE_NO);
                 final String sort = intent.getStringExtra(EXTRA_SORT);
                 final boolean newTask = intent.getBooleanExtra(EXTRA_NEW_TASK,true);
                 handleActionSearch(query, pageNo,sort,newTask);
            }
        }
    }

    private void handleActionSearch(String query,String pageNo, String sort, final boolean newTask) {

        Log.d("hazem", "handleActionSearch: search");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Utilities.searchRecipes(query, sort, pageNo), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Log.d("hazem", "onResponse: result " + response.toString());
                RecipesList recipesList = gson.fromJson(response.toString(),RecipesList.class);
                Log.d("hazem", "onResponse: result " + recipesList.toString());
                if(recipesList.getCount()!=0){
                    Log.d("hazem", "onResponse: count > 0");
                    Intent intent = new Intent();
                    intent.setAction("com.example.zomy.potspans.intent.action.RESULT");
                    intent.putExtra("RecipesList", recipesList);
                    intent.putExtra("newTask", newTask);
                    sendBroadcast(intent);
                }
                else{
                    Intent intent = new Intent();
                    intent.setAction("com.example.zomy.potspans.intent.action.RESULT");
                    intent.putExtra("Error", true);
                    sendBroadcast(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent();
                intent.setAction("com.example.zomy.potspans.intent.action.RESULT");
                intent.putExtra("Error", true);
                sendBroadcast(intent);
            }
        });
        Volley.newRequestQueue(LiveDataService.this).add(jsonObjectRequest);
    }
}

package com.example.zomy.potspans;


import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.menu.ExpandedMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.zomy.potspans.adapter.RecipeRecyclerViewAdapter;
import com.example.zomy.potspans.model.RecipeItem;
import com.example.zomy.potspans.model.RecipesList;
import com.example.zomy.potspans.utils.DataClass;
import com.example.zomy.potspans.utils.DataReceiver;
import com.example.zomy.potspans.utils.LiveDataService;
import com.example.zomy.potspans.utils.OnListItemInteractionListener;
import com.example.zomy.potspans.utils.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnListItemInteractionListener {

    @BindView(R.id.sort_spinner)
    Spinner sortSpinner;
    @BindView(R.id.progress_wheel)
    FrameLayout progressWheel;
    @BindView(R.id.recycler_home)
    RecyclerView recyclerView;
    @BindView(R.id.rel_sort)
    RelativeLayout sortLayout;

    private SharedPreferences sharedPrefs;
    private RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    private DataReceiver dataReceiver;
    private LinearLayoutManager linearLayoutManager;
    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private String sortingTech;
    private String searchQuery = null;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(HomeActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.sort_tech, R.layout.spinner_bg);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);

        sharedPrefs = getSharedPreferences(Utilities.PREF_APP,MODE_PRIVATE);
        if(sharedPrefs.getInt(Utilities.PREF_SORT,0)==0){
            sortSpinner.setSelection(0);
            sortingTech = "r";
        }else{
            sortSpinner.setSelection(1);
            sortingTech = "t";
        }

        RecipesList recipesList = getIntent().getParcelableExtra("RecipesList");
        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(recipesList.getRecipes(),HomeActivity.this,HomeActivity.this);
        recyclerView.setAdapter(recipeRecyclerViewAdapter);
        linearLayoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    // End has been reached
                    // Do something
                    if(searchQuery == null) {
                        LiveDataService.startActionUpdate(HomeActivity.this, null, sortingTech, false);
                    }
                    else {
                        LiveDataService.startActionUpdate(HomeActivity.this, searchQuery, "r", false);
                    }
                    loading = true;
                }
            }
        });

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //// TODO: change sorting
                if(++check>1) {
                    progressWheel.setVisibility(View.VISIBLE);
                    sharedPrefs.edit().putInt(Utilities.PREF_SORT,position).apply();
                    if(position == 0){
                        sortingTech = "r";
                    }
                    else {
                        sortingTech = "t";
                    }
                    LiveDataService.startActionUpdate(HomeActivity.this, null, sortingTech, true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public FrameLayout getProgressWheel() {
        return progressWheel;
    }

    public RecipeRecyclerViewAdapter getRecipeRecyclerViewAdapter() {
        return recipeRecyclerViewAdapter;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressWheel.setVisibility(View.GONE);
        dataReceiver = new DataReceiver(HomeActivity.this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(getResources().getString(R.string.result_receiver_action));
        registerReceiver(dataReceiver,filter);
        check = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(dataReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressWheel.setVisibility(View.VISIBLE);
                searchQuery = query;
                LiveDataService.startActionUpdate(HomeActivity.this,query,"r",true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("hazem", "onQueryTextChange: ");
                return false;
            }
        });

        return true;
    }

    @Override
    public void onListItemInteraction(RecipeItem item) {
        progressWheel.setVisibility(View.VISIBLE);
        DataClass.getRecipe(item.getRecipeId(),HomeActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("hazem", "onOptionsItemSelected: " + item.getTitle() + item.getItemId());
        if(item.getItemId() == R.id.search){
            item.getActionView().requestFocus();
            sortLayout.setVisibility(View.GONE);
            MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    Log.d("hazem", "onOptionsItemSelected: back");
                    sortLayout.setVisibility(View.VISIBLE);
                    searchQuery = null;
                    return true;
                }
            });
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.converter) {
            Intent intent = new Intent(HomeActivity.this,ConverterActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

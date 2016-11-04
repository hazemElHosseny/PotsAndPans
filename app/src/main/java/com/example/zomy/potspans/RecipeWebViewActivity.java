package com.example.zomy.potspans;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RecipeWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        WebViewClient webViewClient = new WebViewClient();

        WebView webView = (WebView) findViewById(R.id.content_recipe_web_view);
        webView.getSettings().setSupportZoom(true);//Zoom Control on web (You don't need this
        //if ROM supports Multi-Touch
        webView.getSettings().setBuiltInZoomControls(true); //Enable Multitouch if supported by ROM
        // Load URL
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(getIntent().getStringExtra("URL"));

    }

}

package net.k6.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class BrowserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        final WebView webView = (WebView) findViewById(R.id.webView);
        final EditText urlText = (EditText) findViewById(R.id.urlText);
        Button goButton = (Button) findViewById(R.id.goButton);
        final Button backButton = (Button) findViewById(R.id.backButton);
        final Button forwardButton = (Button) findViewById(R.id.forwardButton);
        Button historyButton = (Button) findViewById(R.id.historyButton);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (webView.canGoBack()) {
                    backButton.setEnabled(true);
                } else {
                    backButton.setEnabled(false);
                }
                if (webView.canGoForward()) {
                    forwardButton.setEnabled(true);
                } else {
                    forwardButton.setEnabled(false);
                }
            }
        });
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            String uri = intent.getData().toString();
            if (!TextUtils.isEmpty(uri)) {
                webView.loadUrl(uri);
            }
        }
        backButton.setEnabled(false);
        forwardButton.setEnabled(false);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlText.getText().toString();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (url != null) {
                    if (url.startsWith("http://")) {
                        History.addHistory(url);
                        webView.loadUrl(url);
                    } else {
                        History.addHistory(url);
                        webView.loadUrl("http://" + url);
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBackOrForward(-1);
            }
        });
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()) {
                    webView.goBackOrForward(1);
                }
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrowserActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}

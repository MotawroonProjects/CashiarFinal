package com.easycashiar.ui.activity_web_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.easycashiar.R;
import com.easycashiar.databinding.ActivityWebViewBinding;
import com.easycashiar.language.Language;

import io.paperdb.Paper;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding binding;
    private String url = "";
    private int id;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        url = intent.getStringExtra("data");
        id = intent.getIntExtra("id", 0);
    }

    private void initView() {
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        binding.webView.getSettings().setBuiltInZoomControls(false);
        binding.webView.loadUrl(url);
        binding.webView.setWebViewClient(new WebViewClient() {
                                             @Override
                                             public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                                 super.onPageStarted(view, url, favicon);
                                             }

                                             @Override
                                             public void onPageFinished(WebView view, String url) {
                                                 if (id != 0 && url.contains("success=true") || url.contains("issuccess=true")) {
                                                     Intent intent = getIntent();
                                                     intent.putExtra("url", url);
                                                     setResult(RESULT_OK, intent);
                                                     finish();
                                                     Toast.makeText(WebViewActivity.this, getString(R.string.suc), Toast.LENGTH_SHORT).show();

                                                 }

                                             }

                                             @Override
                                             public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                                                 super.onReceivedError(view, request, error);

                                                 //binding.webView.setVisibility(View.INVISIBLE);
                                             }


                                             @Override
                                             public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                                                 super.onReceivedHttpError(view, request, errorResponse);

                                                 //  binding.webView.setVisibility(View.INVISIBLE);
                                             }

                                             @Override
                                             public void onPageCommitVisible(WebView view, String url) {
                                                 super.onPageCommitVisible(view, url);
                                                 binding.progBar.setVisibility(View.GONE);
                                             }
                                         }

        );

    }


}
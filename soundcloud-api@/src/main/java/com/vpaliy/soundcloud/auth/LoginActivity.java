package com.vpaliy.soundcloud.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vpaliy.soundcloud.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

  static final String EXTRA_CONNECT_DATA = LoginActivity.class.getCanonicalName() + "Extra.Connect.Data";
  private Connect connect;

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);
    WebView webView = findViewById(R.id.web);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewAuthClient());
    connect = getIntent().getParcelableExtra(EXTRA_CONNECT_DATA);
    if (connect.url != null) {
      webView.loadUrl(connect.url);
    } else {
      finish();
    }
  }

  private class WebViewAuthClient extends WebViewClient {

    private final Intent resultIntent = new Intent();

    @Override
    public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);

      if (url.startsWith(connect.redirectUri)) {
        Uri data = Uri.parse(url);
        resultIntent.setData(data);
        if (url.contains("?code=")) {
          setResult(RESULT_OK, resultIntent);
        } else {
          setResult(RESULT_CANCELED, resultIntent);
        }
        finish();
      }
    }
  }
}

package de.wildbestand.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if ("https".equals(uri.getScheme()) && "appassets.androidplatform.net".equals(uri.getHost())) {
                    String path = uri.getPath();
                    if (path != null && path.startsWith("/assets/")) {
                        String assetName = path.substring("/assets/".length());
                        try {
                            InputStream input = getAssets().open(assetName);
                            String mime = "text/plain";
                            if (assetName.endsWith(".html")) mime = "text/html";
                            else if (assetName.endsWith(".js")) mime = "application/javascript";
                            else if (assetName.endsWith(".json")) mime = "application/json";
                            else if (assetName.endsWith(".png")) mime = "image/png";
                            return new WebResourceResponse(mime, "UTF-8", input);
                        } catch (IOException ignored) { }
                    }
                }
                return super.shouldInterceptRequest(view, request);
            }
        });
        webView.loadUrl("https://appassets.androidplatform.net/assets/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

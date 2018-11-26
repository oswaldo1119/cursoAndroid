package dev.oswaldo.primerospasos.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;

public class WebViewSampleActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.progressBar3)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_sample);
        ButterKnife.bind(this);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl("https://myanimelist.net/");
    }
}

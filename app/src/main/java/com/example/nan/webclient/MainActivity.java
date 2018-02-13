package com.example.nan.webclient;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    android.webkit.WebView wv;
    JavaScriptGrensesnitt grense = new JavaScriptGrensesnitt(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wv = new WebView(this);
        wv.addJavascriptInterface(grense,"javaobjekt");
        setContentView(wv);
        wv.setWebViewClient(new MyWebViewClient());

        if(savedInstanceState==null) {
            wv.getSettings().setJavaScriptEnabled(true);
            wv.addJavascriptInterface(grense,"javaobjekt");
            wv.loadUrl("file:///android_asset/index.html");
        }
        else{
            wv.addJavascriptInterface(grense,"javaobjekt");
            wv.restoreState(savedInstanceState);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

        if ( keyCode == android.view.KeyEvent.KEYCODE_BACK  && wv.canGoBack() ) {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        wv.saveState(outState);
    }
}

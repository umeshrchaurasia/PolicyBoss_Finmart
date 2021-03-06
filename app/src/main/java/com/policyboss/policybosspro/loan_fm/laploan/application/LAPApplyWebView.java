package com.policyboss.policybosspro.loan_fm.laploan.application;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.policyboss.policybosspro.R;
import com.policyboss.policybosspro.webviews.MyWebViewClient;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.QuoteEntity;

public class LAPApplyWebView extends AppCompatActivity {

    WebView webView;
    int quoteId;
    String url;
    QuoteEntity quoteEntity;
    LoginResponseEntity loginEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapapply_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loginEntity   = new DBPersistanceController(LAPApplyWebView.this).getUserData();
        webView = (WebView) findViewById(R.id.webView);
        //webView.clearCache(false);
        if (getIntent().getStringExtra("URL") != null) {
            quoteEntity = getIntent().getParcelableExtra("QUOTE_ENTITY");
            quoteId = getIntent().getIntExtra("QUOTE_ID", 0);
            url = getIntent().getStringExtra("URL");
        }
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);

        MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setBuiltInZoomControls(true);

        url = url + "?qoutid=" + quoteId + "&bankid=" + quoteEntity.getBank_Id()
                + "&productid=7"
                + "&refapp=0"
                + "&brokerid=" + loginEntity.getLoanId()
                + "&empcode=" + ""
                + "&loanamout=" + quoteEntity.getLoan_eligible()
                + "&idtype=" + quoteEntity.getRoi_type()
                + "&processingfee=" + quoteEntity.getProcessingfee()
                 +"&fbaid"+loginEntity.getFBAId()
                + "&Lead_Source="+"DC";
        Log.d("HOME_LOAN_URL", url);
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}

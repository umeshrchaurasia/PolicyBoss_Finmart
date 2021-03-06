package com.policyboss.policybosspro.loan_fm.laploan.addquote;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.policyboss.policybosspro.BaseActivity;
import com.policyboss.policybosspro.R;
import com.policyboss.policybosspro.home.HomeActivity;
import com.policyboss.policybosspro.utility.Constants;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.QuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.HomeLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetQuoteResponse;

public class LapLoanQuoteActivity extends BaseActivity {

    GetQuoteResponse getQuoteResponse;
    HomeLoanRequest homeLoanRequest;
    Toolbar toolbar;
    RecyclerView rvQuotes;
    LAPQuoteAdapter mAdapter;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_loan_quote);
        initialise_widget();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getQuoteResponse = getIntent().getParcelableExtra(Constants.QUOTES);
        homeLoanRequest = getIntent().getParcelableExtra(Constants.HL_REQUEST);

          rvQuotes.setAdapter(mAdapter);
    }
    public void redirectToApplyLoan(QuoteEntity entity) {

//        startActivity(new Intent(this, HomeLoanApplyActivity.class)
//                .putExtra("QUOTE_ENTITY", entity)
//                .putExtra("URL", getQuoteResponse.getUrl())
//                .putExtra("QUOTE_ID", getQuoteResponse.getQuote_id())
//        );
    }
    private void initialise_widget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvQuotes = (RecyclerView) findViewById(R.id.rvQuotes);
        rvQuotes.setLayoutManager(new LinearLayoutManager(LapLoanQuoteActivity.this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_home:

                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("MarkTYPE", "FROM_HOME");
                startActivity(intent);

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

package com.policyboss.policybosspro.loan_fm.personalloan;

import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.policyboss.policybosspro.BaseActivity;
import com.policyboss.policybosspro.R;
import com.policyboss.policybosspro.loan_fm.personalloan.addquote.PLMainActivity;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.mainloan.MainLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalMainEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmPersonalLoanResponse;

public class PersonalLoanDetailActivity extends BaseActivity implements IResponseSubcriberFM {
    Toolbar toolbar;
    ViewPager viewPager;
    ActivityTabsPagerAdapter_PL mAdapter;
    LoginResponseEntity loginEntity;
    boolean blnVerify = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.pager);
      //  TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
    //    tabLayout.setupWithViewPager(viewPager, true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

     //   tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        loginEntity = new DBPersistanceController(this).getUserData();

        //  mAdapter = new ActivityTabsPagerAdapter_PL(getSupportFragmentManager());
        //  viewPager.setAdapter(mAdapter);

       // viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!blnVerify) {
            fetchQuoteApplication();
        }
    }

    public void infoPopUpVerify()
    {
        blnVerify = true;
    }

    private void fetchQuoteApplication() {

        showDialog(getResources().getString(R.string.fetching_msg));

        new MainLoanController(this).getPLQuoteApplication(0,0,String.valueOf(loginEntity.getFBAId()), PersonalLoanDetailActivity.this);


    }

    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {
        cancelDialog();
        if (response instanceof FmPersonalLoanResponse) {
            if (((FmPersonalLoanResponse) response).getMasterData() != null) {

                PersonalMainEntity plQuoteApplicationEntity = ((FmPersonalLoanResponse) response).getMasterData();

                if (plQuoteApplicationEntity.getQuote().size() != 0
                        || plQuoteApplicationEntity.getApplication().size() != 0) {
                    mAdapter = new ActivityTabsPagerAdapter_PL(getSupportFragmentManager(), plQuoteApplicationEntity);
                    viewPager.setAdapter(mAdapter);
                } else {
                    finish();
                    startActivity(new Intent(this, PLMainActivity.class));
                }
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();

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
                onBackPressed();
//                Intent intent = new Intent(this, HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.putExtra("MarkTYPE", "FROM_HOME");
//                startActivity(intent);
//
//                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//
//        supportFinishAfterTransition();
//        super.onBackPressed();
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
        // return super.onSupportNavigateUp();
    }
}

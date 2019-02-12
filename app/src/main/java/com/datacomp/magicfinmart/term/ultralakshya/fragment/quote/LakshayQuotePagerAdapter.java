package com.datacomp.magicfinmart.term.ultralakshya.fragment.quote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.UltraLakshayScenarioOfBenefitsDeath;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.UltraLakshayUnmatchedBenefit;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.UltraLakshayBenefitStandAlone;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.UltraLakshayDeathBenefitToNominee;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.UltraLakshayBenefitILLustration;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.UltraLakshayLisJeevanVsLakshay;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.UltraLakshayProductCombo;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.coverPage1Fragment;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.coverPage2Fragment;
import com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.coverPage3Fragment;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;

/**
 * Created by Rajeev Ranjan on 31/01/2019.
 */

public class LakshayQuotePagerAdapter extends FragmentStatePagerAdapter {

   String strUserName;
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public LakshayQuotePagerAdapter(FragmentManager fm ,String strName) {
        super(fm);
        this.strUserName = strName;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                coverPage1Fragment objFrag = new coverPage1Fragment();
                Bundle bundle = new Bundle();           // use bundle for paasing the data
                bundle.putString(Utility.ULTRA_LAKSHYA_HDR_NAME,strUserName);
                objFrag.setArguments(bundle);
                return objFrag;

            case 1:

                coverPage2Fragment objFrag1 = new coverPage2Fragment();
                return objFrag1;

            case 2:

                coverPage3Fragment objFrag2 = new coverPage3Fragment();
                return objFrag2;

            case 3:
                UltraLakshayUnmatchedBenefit objFrag3 = new UltraLakshayUnmatchedBenefit();
                return objFrag3;


            case 4:


                UltraLakshayBenefitStandAlone objFrag4 = new UltraLakshayBenefitStandAlone();
                return objFrag4;

            case 5:


            UltraLakshayBenefitILLustration objFrag5 = new UltraLakshayBenefitILLustration();
            return objFrag5;


            case 6:

                return  new UltraLakshayLisJeevanVsLakshay();


            case 7:

                UltraLakshayDeathBenefitToNominee objFrag7 = new UltraLakshayDeathBenefitToNominee();
                return objFrag7;

            case 8:

                UltraLakshayProductCombo objFrag8 = new UltraLakshayProductCombo();
                return objFrag8;

            case 9:

                return  new UltraLakshayScenarioOfBenefitsDeath();




        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment) {
        mFragmentList.add(fragment);

    }
}

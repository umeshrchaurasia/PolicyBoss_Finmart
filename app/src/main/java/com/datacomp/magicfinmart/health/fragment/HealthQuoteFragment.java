package com.datacomp.magicfinmart.health.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.health.healthquotetabs.HealthQuoteBottomTabsActivity;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.health.HealthController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteExpResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteResponse;

/**
 * Created by Nilesh Birhade on 14/02/2018.
 */

public class HealthQuoteFragment extends BaseFragment implements IResponseSubcriber {

    private static final String FLOATER = "FLOATER STANDARD";
    private static final String INDIVIDUAL = "INDIVIDUAL STANDARD";
    TextView txtCoverType, txtCoverAmount;
    HealthQuote healthQuote;
    LinearLayout llMembers;
    ImageView webViewLoader;
    RecyclerView rvHealthQuote;
    HealthQuoteAdapter adapter;
    List<HealthQuoteEntity> listCompare;
    TextView tvCount, txtCompareCount;

    //    ExpandableListView elvHealthQuote;
//    HealthQuoteExpandableListAdapter mAdapter;
    String shareText = " results from www.policyboss.com";

    List<HealthQuoteEntity> listDataHeader;
    HashMap<Integer, List<HealthQuoteEntity>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_healthcontent_quote, container, false);
        initView(view);
        listCompare = new ArrayList<>();
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<Integer, List<HealthQuoteEntity>>();
        txtCompareCount.setVisibility(View.GONE);

        if (getArguments() != null) {
            if (getArguments().getParcelable(HealthQuoteBottomTabsActivity.QUOTE_DATA) != null) {
                healthQuote = getArguments().getParcelable(HealthQuoteBottomTabsActivity.QUOTE_DATA);
                bindHeaders();
                fetchQuotes();
            }
        }

        return view;
    }

    private void bindHeaders() {

        if (healthQuote.getHealthRequest().getMemberList().size() > 1) {
            txtCoverType.setText(FLOATER);
        } else {
            txtCoverType.setText(INDIVIDUAL);
        }

        tvCount.setText(shareText);
        String cover = "COVER :" + "<b>" + String.valueOf(healthQuote.getHealthRequest().getSumInsured()) + "</b>";
        txtCoverAmount.setText(Html.fromHtml(cover));
        bindImages(healthQuote.getHealthRequest().getMemberList());

    }

    private void bindImages(List<MemberListEntity> listmember) {

        for (int i = 0; i < listmember.size(); i++) {

            ImageView imageview = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

            if (listmember.get(i).getAge() > 18)
                imageview.setImageResource(R.mipmap.adult);
            else
                imageview.setImageResource(R.mipmap.child);

            imageview.setLayoutParams(params);
            llMembers.addView(imageview);
        }
    }

    private void initView(View view) {
        webViewLoader = (ImageView) view.findViewById(R.id.webViewLoader);
        txtCoverAmount = (TextView) view.findViewById(R.id.txtCoverAmount);
        txtCoverType = (TextView) view.findViewById(R.id.txtCoverType);
        llMembers = (LinearLayout) view.findViewById(R.id.llMembers);
        tvCount = (TextView) view.findViewById(R.id.tvCount);

        txtCompareCount = (TextView) view.findViewById(R.id.txtCompareCount);

        rvHealthQuote = (RecyclerView) view.findViewById(R.id.rvHealthQuote);
        rvHealthQuote.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHealthQuote.setLayoutManager(layoutManager);
    }

    public void redirectToBuy(HealthQuoteEntity entity) {

        Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
        intent.putExtra("URL", entity.getProposerPageUrl());
        intent.putExtra("TITLE", "HEALTH INSURENCE");
        intent.putExtra("NAME", "HEALTH INSURENCE");
        startActivity(intent);
    }


    public void fetchQuotes() {
        visibleLoader();
        //new HealthController(getActivity()).getHealthQuote(healthQuote, this);
        new HealthController(getActivity()).getHealthQuoteExp(healthQuote, this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        hideLoader();
        if (response instanceof HealthQuoteExpResponse) {
            if (((HealthQuoteExpResponse) response).getMasterData().getHealth_quote().getHeader() != null) {
                prepareChild(((HealthQuoteExpResponse) response).getMasterData()
                        .getHealth_quote().getHeader(), ((HealthQuoteExpResponse) response).getMasterData()
                        .getHealth_quote().getChild());
            }
        }

    }

    private void prepareChild(List<HealthQuoteEntity> listHeader, List<HealthQuoteEntity> listChild) {

        for (int i = 0; i < listHeader.size(); i++) {

            HealthQuoteEntity header = listHeader.get(i);
            List<HealthQuoteEntity> childList = new ArrayList<>();

            for (int j = 0; j < listChild.size(); j++) {
                HealthQuoteEntity child = listChild.get(j);
                if (header.getInsurerId() == child.getInsurerId()) {
                    childList.add(child);
                }
            }

            header.setTotalChilds(childList.size());

            listDataChild.put(header.getInsurerId(), childList);
            listDataHeader.add(header);

        }
        bindRecyclerView(listDataHeader);
        int total = listHeader.size() + listChild.size();
        tvCount.setText(total + shareText);
    }

    public void bindRecyclerView(List<HealthQuoteEntity> list) {
        adapter = new HealthQuoteAdapter(this, list);
        rvHealthQuote.setAdapter(adapter);

    }

    private void refreshAdapter(List<HealthQuoteEntity> list) {
        adapter.refreshNewQuote(list);
    }

    public void addMoreQuote(int insurerID) {

        List<HealthQuoteEntity> childList = listDataChild.get(insurerID);
        refreshAdapter(childList);
    }

    public void addRemoveCompare(HealthQuoteEntity entity, boolean isAdd) {
        if (isAdd) {
            listCompare.add(entity);
        } else {
            //remove item from list
            for (Iterator<HealthQuoteEntity> iter = listCompare.listIterator(); iter.hasNext(); ) {
                HealthQuoteEntity a = iter.next();
                if (a.getInsurerId() == entity.getInsurerId() && a.getPlanID() == entity.getPlanID()) {
                    iter.remove();
                }
            }
        }

        if (listCompare.size() == 0) {
            txtCompareCount.setVisibility(View.GONE);
        } else {
            txtCompareCount.setVisibility(View.VISIBLE);
            txtCompareCount.setText("" + listCompare.size());
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        hideLoader();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //region loader

    private void visibleLoader() {
        webViewLoader.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.preloader).into(webViewLoader);
    }

    private void hideLoader() {
        webViewLoader.setVisibility(View.GONE);
    }

    //endregion

}

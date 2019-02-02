package com.datacomp.magicfinmart.term.ultralakshya.fragment.quote.content.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.notification.NotificationActivity;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.DeathBenefitEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.NotificationEntity;

/**
 * Created by IN-RB on 22-02-2018.
 */

public class UltraLakshyaDeathNomineeAdapter extends RecyclerView.Adapter<UltraLakshyaDeathNomineeAdapter.DeathBenefitItem> {

    Fragment mContext;
    List<DeathBenefitEntity>  DeathBenefitLst;


    public UltraLakshyaDeathNomineeAdapter(Fragment mContext,  List<DeathBenefitEntity> deathBenefitLst) {
        this.mContext = mContext;
        this.DeathBenefitLst = deathBenefitLst;
    }


    public class DeathBenefitItem extends RecyclerView.ViewHolder
    {
        public TextView txtYear ,txtJeevanPremPaid ,txtJeevanBenefit , txtLakhshyaPremPaid ,txtLakhshyaBenefit;
        public LinearLayout lyParent;
        public DeathBenefitItem(View itemView) {
            super(itemView);
            txtYear = (TextView) itemView.findViewById(R.id.txtYear);
            txtJeevanPremPaid = (TextView)itemView.findViewById(R.id.txtJeevanPremPaid);
            txtJeevanBenefit = (TextView)itemView.findViewById(R.id.txtJeevanBenefit);
            txtLakhshyaPremPaid = (TextView)itemView.findViewById(R.id.txtLakhshyaPremPaid);
            txtLakhshyaBenefit = (TextView)itemView.findViewById(R.id.txtLakhshyaBenefit);
            lyParent = (LinearLayout) itemView.findViewById(R.id.lyParent);

        }
    }


    @Override
    public UltraLakshyaDeathNomineeAdapter.DeathBenefitItem onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.death_benefit_item, parent, false);

        return new UltraLakshyaDeathNomineeAdapter.DeathBenefitItem(itemView);

    }

    @Override
    public void onBindViewHolder(DeathBenefitItem holder, int position) {

        final DeathBenefitEntity deathBenefitEntity = DeathBenefitLst.get(position);
        holder.txtYear.setText( "" +deathBenefitEntity.getYear());
        holder.txtJeevanPremPaid.setText( "" +deathBenefitEntity.getJeevanPremiumPaid());
        holder.txtJeevanBenefit.setText( "" +deathBenefitEntity.getJeevanBenefitsPayable());

        holder.txtLakhshyaPremPaid.setText( "" +deathBenefitEntity.getLakshyaPremiumPaid());
        holder.txtLakhshyaBenefit.setText( "" +deathBenefitEntity.getLakshyaBenefitsPayable());




    }


    @Override
    public int getItemCount() {
        return DeathBenefitLst.size();
    }
}

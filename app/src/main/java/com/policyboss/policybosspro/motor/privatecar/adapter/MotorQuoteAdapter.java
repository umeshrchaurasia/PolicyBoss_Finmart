package com.policyboss.policybosspro.motor.privatecar.adapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.policyboss.policybosspro.R;
import com.policyboss.policybosspro.motor.privatecar.fragment.MotorQuoteFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CarMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.QuoteListEntity;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class MotorQuoteAdapter extends RecyclerView.Adapter<MotorQuoteAdapter.QuoteItem> implements View.OnClickListener, Filterable {
    Fragment mFrament;
    List<QuoteListEntity> mQuoteList;
    List<QuoteListEntity> mQuoteListFiltered;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public MotorQuoteAdapter(Fragment context, List<QuoteListEntity> list) {
        this.mFrament = context;
        mQuoteList = list;
        mQuoteListFiltered = list;

    }

    @Override

    public QuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_quote, parent, false);
        return new MotorQuoteAdapter.QuoteItem(itemView);


    }

    @Override
    public void onBindViewHolder(QuoteItem holder, int position) {

        if (holder instanceof QuoteItem) {
            final QuoteListEntity entity = mQuoteListFiltered.get(position);

            holder.txtPersonName.setText(entity.getMotorRequestEntity().getFirst_name()
                    + " " + entity.getMotorRequestEntity().getLast_name());
            try {
                CarMasterEntity carMasterEntity = null;
                if (entity.getMotorRequestEntity().getVehicle_id() == 0) {

                    carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                            .getVarientDetails(
                                    "" + entity.getMotorRequestEntity().getVarid());
                } else {

                    carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                            .getVarientDetails(
                                    "" + entity.getMotorRequestEntity().getVehicle_id());
                }
                holder.txtVehicleName.setText(carMasterEntity.getMake_Name() + "," + carMasterEntity.getModel_Name());

            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.txtQuoteDate.setText(entity.getMotorRequestEntity().getCreated_date());
            holder.txtCrnNo.setText("" + entity.getMotorRequestEntity().getCrn());

            holder.txtPersonName.setTag(R.id.txtPersonName, entity);
            holder.txtOverflowMenu.setTag(R.id.txtOverflowMenu, entity);
            holder.llDetails.setTag(R.id.llDetails, entity);

            holder.txtPersonName.setOnClickListener(this);
            holder.txtOverflowMenu.setOnClickListener(this);
            holder.llDetails.setOnClickListener(this);

        }
    }

    private void openPopUp(View v, final QuoteListEntity entity) {
        final PopupMenu popupMenu = new PopupMenu(mFrament.getActivity(), v);
        final Menu menu = popupMenu.getMenu();

        popupMenu.getMenuInflater().inflate(R.menu.recycler_menu_quote, menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuCall:
                        ((MotorQuoteFragment) mFrament).dialNumber(entity.getMotorRequestEntity().getMobile());
                        //Toast.makeText(mFrament.getActivity(), "WIP " + entity.getMotorRequestEntity().getMobile(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuSms:
                        ((MotorQuoteFragment) mFrament).sendSms(entity.getMotorRequestEntity().getMobile());
                        Toast.makeText(mFrament.getActivity(), "WIP SMS ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuDelete:
                        ((MotorQuoteFragment) mFrament).removeQuote(entity);
                        break;
                    case R.id.menuticket:
                        ((MotorQuoteFragment) mFrament).raiseticket(entity);
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        if (mQuoteListFiltered != null)
            return mQuoteListFiltered.size();
        else
            return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.llDetails:
            case R.id.txtPersonName:
                ((MotorQuoteFragment) mFrament).redirectToInputQuote((QuoteListEntity) view.getTag(view.getId()));
                break;
            case R.id.txtOverflowMenu:
                openPopUp(view, (QuoteListEntity) view.getTag(view.getId()));
                break;

        }
    }

    public class QuoteItem extends RecyclerView.ViewHolder {

        //  public ImageView ivTripleDot;
        public TextView txtQuoteDate, txtVehicleName, txtPersonName, txtCrnNo;
        LinearLayout llDetails;
        ImageView txtOverflowMenu;

        public QuoteItem(View itemView) {
            super(itemView);
            txtQuoteDate = (TextView) itemView.findViewById(R.id.txtQuoteDate);
            txtVehicleName = (TextView) itemView.findViewById(R.id.txtVehicleName);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtOverflowMenu = (ImageView) itemView.findViewById(R.id.txtOverflowMenu);
            txtCrnNo = (TextView) itemView.findViewById(R.id.txtCrnNo);
            llDetails = (LinearLayout) itemView.findViewById(R.id.llDetails);
        }
    }

    public void refreshAdapter(List<QuoteListEntity> list) {
        mQuoteListFiltered = list;
        mQuoteList = list;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mQuoteListFiltered = mQuoteList;
                } else {
                    List<QuoteListEntity> filteredList = new ArrayList<>();
                    for (QuoteListEntity row : mQuoteList) {
                        CarMasterEntity carMasterEntity = new CarMasterEntity();
                        carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                                .getVarientDetails(
                                        "" + row.getMotorRequestEntity().getVehicle_id());

                        if (row.getMotorRequestEntity().getFirst_name().toLowerCase().contains(charString.toLowerCase())
                                || row.getMotorRequestEntity().getLast_name().toLowerCase().contains(charString.toLowerCase())

                                || String.valueOf(row.getMotorRequestEntity().getCrn()).contains(charString.toLowerCase())) {

                            filteredList.add(row);
                        }

                        if (carMasterEntity != null) {
                            if (carMasterEntity.getMake_Name().toLowerCase().contains(charString.toLowerCase())
                                    || carMasterEntity.getModel_Name().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }

                    }

                    mQuoteListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mQuoteListFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mQuoteListFiltered = (ArrayList<QuoteListEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

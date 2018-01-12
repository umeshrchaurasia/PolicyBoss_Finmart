package magicfinmart.datacomp.com.finmartserviceapi.motor.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.motor.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestbuilder.MotorQuotesRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.BikePremiumRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.BikeRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.SaveAddOnRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikePremiumResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikeUniqueResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.SaveAddOnResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nilesh Birhade on 11/01/2018.
 */

public class MotorController implements IMotor {

    private static final long SLEEP_DELAY = 5000; // 5 seconds delay.
    private static final long NO_OF_SERVER_HITS = 10;
    MotorQuotesRequestBuilder.MotorQuotesNetworkService motorQuotesNetworkService;
    Context mContext;
    Handler handler;
    IResponseSubcriber iResponseSubcriber;

    public MotorController(Context context) {
        motorQuotesNetworkService = new MotorQuotesRequestBuilder().getService();
        mContext = context;
        handler = new Handler();
    }

    private class MotorRunnable implements Runnable {
        private int pID = 0;

        public MotorRunnable(int pID) {
            this.pID = pID;
        }

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };

        @Override
        public void run() {
            new MotorController(mContext).getMotorQuote(pID, iResponseSubcriber);
        }
    }


    @Override
    public void getMotorPremiumInitiate(final BikeRequestEntity bikeRequestEntity, final IResponseSubcriber iResponseSubcriber) {

        motorQuotesNetworkService.premiumInitiateUniqueID(bikeRequestEntity).enqueue(new Callback<BikeUniqueResponse>() {
            @Override
            public void onResponse(Call<BikeUniqueResponse> call, Response<BikeUniqueResponse> response) {
                if (response.body() != null) {

                    //for every new premium initiate counter should be 0
                    Utility.getSharedPreferenceEditor(mContext).remove(Utility.QUOTE_COUNTER).commit();

                    String UNIQUE = response.body().getSummary().getRequest_Unique_Id();

                    SharedPreferences.Editor edit = Utility.getSharedPreferenceEditor(mContext);

                    //car quote
                    if (bikeRequestEntity.getProduct_id() == 1) {
                        edit.putString(Utility.BIKEQUOTE_UNIQUEID,
                                UNIQUE);
                    } else if (bikeRequestEntity.getProduct_id() == 10) {
                        //bike quote
                        edit.putString(Utility.CARQUOTE_UNIQUEID,
                                UNIQUE);
                    }
                    edit.commit();

                    //callback of data
                    iResponseSubcriber.OnSuccess(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<BikeUniqueResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void getMotorQuote(final int productID, final IResponseSubcriber iResponseSubcriber) {
        this.iResponseSubcriber = iResponseSubcriber;
        BikePremiumRequestEntity entity = new BikePremiumRequestEntity();
        entity.setSecret_key(Utility.SECRET_KEY);
        entity.setClient_key(Utility.CLIENT_KEY);
        entity.setResponse_version(Utility.VERSION_CODE);
        //entity.setExecution_async("no");
        if (productID == 10) {
            entity.setSearch_reference_number(Utility.getSharedPreference(mContext).getString(Utility.BIKEQUOTE_UNIQUEID, ""));
        } else if (productID == 1) {
            entity.setSearch_reference_number(Utility.getSharedPreference(mContext).getString(Utility.CARQUOTE_UNIQUEID, ""));
        }
        if (Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) < NO_OF_SERVER_HITS) {
            Utility.getSharedPreferenceEditor(mContext).putInt(Utility.QUOTE_COUNTER,
                    Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) + 1)
                    .commit();
        }

        motorQuotesNetworkService.getPremiumList(entity).enqueue(new Callback<BikePremiumResponse>() {
            @Override
            public void onResponse(Call<BikePremiumResponse> call, Response<BikePremiumResponse> response) {
                if (response.body() != null) {

                    BikePremiumResponse bikePremiumResponse = new BikePremiumResponse();
                    if (response.body() != null) {
                        List<ResponseEntity> list = new ArrayList<ResponseEntity>();
                        for (int i = 0; i < response.body().getResponse().size(); i++) {
                            ResponseEntity responseEntity = response.body().getResponse().get(i);
                            if (responseEntity.getError_Code().equals("")) {
                                list.add(responseEntity);
                            }
                        }
                        bikePremiumResponse.setResponse(list);
                        bikePremiumResponse.setSummary(response.body().getSummary());
                    }

                    iResponseSubcriber.OnSuccess(bikePremiumResponse, response.body().getMessage());

                    MotorRunnable runnable = new MotorRunnable(productID);

                    if (!response.body().getSummary().getStatusX().equals("complete")) {

                        if (Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) < NO_OF_SERVER_HITS) {
                            //server request for pending quotes

                            handler.postDelayed(runnable, SLEEP_DELAY);
                        } else {
                            //remove handler
                            handler.removeCallbacks(runnable);
                            //remove stored counters
                            Utility.getSharedPreferenceEditor(mContext).remove(Utility.QUOTE_COUNTER).commit();
                        }
                    } else {
                        handler.removeCallbacks(runnable);
                    }

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("No Quote found"));
                }
            }

            @Override
            public void onFailure(Call<BikePremiumResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void saveAddOn(SaveAddOnRequestEntity saveAddOnRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        saveAddOnRequestEntity.setSecret_key(Utility.SECRET_KEY);
        saveAddOnRequestEntity.setClient_key(Utility.CLIENT_KEY);

        motorQuotesNetworkService.saveAddOn(saveAddOnRequestEntity).enqueue(new Callback<SaveAddOnResponse>() {
            @Override
            public void onResponse(Call<SaveAddOnResponse> call, Response<SaveAddOnResponse> response) {
                if (response.body() != null) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<SaveAddOnResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}
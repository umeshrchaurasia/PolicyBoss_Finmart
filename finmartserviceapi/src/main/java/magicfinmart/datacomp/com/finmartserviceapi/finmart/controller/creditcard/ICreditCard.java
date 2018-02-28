package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.creditcard;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.CCRblRequestEntity;

/**
 * Created by Nilesh Birhade 09/02/18
 */

public interface ICreditCard {

    void getAllCreditCards(IResponseSubcriber iResponseSubcriber);

    void applyRbl(CCRblRequestEntity rblRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getAppliedCreditCards(IResponseSubcriber iResponseSubcriber);

    void getRblCityMaster(IResponseSubcriber iResponseSubcriber);

}
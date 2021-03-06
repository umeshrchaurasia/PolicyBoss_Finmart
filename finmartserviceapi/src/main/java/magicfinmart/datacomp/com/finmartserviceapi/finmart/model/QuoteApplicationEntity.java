package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class QuoteApplicationEntity implements Parcelable  {

    private List<QuoteListEntity> quote;
    private List<ApplicationListEntity> application;
    private List<MotorMyLeadEntity> myleads;

    protected QuoteApplicationEntity(Parcel in) {
        quote = in.createTypedArrayList(QuoteListEntity.CREATOR);
        application = in.createTypedArrayList(ApplicationListEntity.CREATOR);
        myleads = in.createTypedArrayList(MotorMyLeadEntity.CREATOR);
    }

    public static final Creator<QuoteApplicationEntity> CREATOR = new Creator<QuoteApplicationEntity>() {
        @Override
        public QuoteApplicationEntity createFromParcel(Parcel in) {
            return new QuoteApplicationEntity(in);
        }

        @Override
        public QuoteApplicationEntity[] newArray(int size) {
            return new QuoteApplicationEntity[size];
        }
    };

    public List<QuoteListEntity> getQuote() {
        return quote;
    }

    public void setQuote(List<QuoteListEntity> quote) {
        this.quote = quote;
    }

    public List<ApplicationListEntity> getApplication() {
        return application;
    }

    public void setApplication(List<ApplicationListEntity> application) {
        this.application = application;
    }

    public List<MotorMyLeadEntity> getMyleads() {
        return myleads;
    }

    public void setMyleads(List<MotorMyLeadEntity> myleads) {
        this.myleads = myleads;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(quote);
        dest.writeTypedList(application);
        dest.writeTypedList(myleads);
    }


//    public List<QuoteListEntity> getQuote() {
//        return quote;
//    }
//
//    public void setQuote(List<QuoteListEntity> quote) {
//        this.quote = quote;
//    }
//
//    public List<ApplicationListEntity> getApplication() {
//        return application;
//    }
//
//    public void setApplication(List<ApplicationListEntity> application) {
//        this.application = application;
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeList(this.quote);
//        dest.writeList(this.application);
//    }
//
//    public QuoteApplicationEntity() {
//    }
//
//    protected QuoteApplicationEntity(Parcel in) {
//        this.quote = new ArrayList<QuoteListEntity>();
//        in.readList(this.quote, QuoteListEntity.class.getClassLoader());
//        this.application = new ArrayList<ApplicationListEntity>();
//        in.readList(this.application, ApplicationListEntity.class.getClassLoader());
//    }
//
//    public static final Parcelable.Creator<QuoteApplicationEntity> CREATOR = new Parcelable.Creator<QuoteApplicationEntity>() {
//        @Override
//        public QuoteApplicationEntity createFromParcel(Parcel source) {
//            return new QuoteApplicationEntity(source);
//        }
//
//        @Override
//        public QuoteApplicationEntity[] newArray(int size) {
//            return new QuoteApplicationEntity[size];
//        }
//    };
}

package com.scc.shake;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Hold data of device info with feedback to send to server
 */

public class Feedback implements Parcelable {


    public static final Creator<Feedback> CREATOR = new Creator<Feedback>() {
        @Override
        public Feedback createFromParcel(Parcel in) {
            return new Feedback(in);
        }

        @Override
        public Feedback[] newArray(int size) {
            return new Feedback[size];
        }
    };
    String text;
    String deviceOS;
    String deviceType;
    String deviceModel;
    String pageName;
    String manufacturer;
    Context context;


    public Feedback() {
    }

    protected Feedback(Parcel in) {
        text = in.readString();
        deviceOS = in.readString();
        deviceType = in.readString();
        deviceModel = in.readString();
        pageName = in.readString();
        manufacturer = in.readString();
//        context = (Context) in.readValue(ClassLoader.getSystemClassLoader());
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeString(deviceOS);
        parcel.writeString(deviceType);
        parcel.writeString(deviceModel);
        parcel.writeString(pageName);
        parcel.writeString(manufacturer);
//        parcel.write(context);
    }
}

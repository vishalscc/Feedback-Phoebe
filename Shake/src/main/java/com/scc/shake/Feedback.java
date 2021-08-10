package com.scc.shake;

import android.os.Parcel;
import android.os.Parcelable;

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


    public Feedback() {
    }

    protected Feedback(Parcel in) {
        text = in.readString();
        deviceOS = in.readString();
        deviceType = in.readString();
        deviceModel = in.readString();
        pageName = in.readString();
        manufacturer = in.readString();
    }

    public String getText() {
        return text;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setText(String text) {
        this.text = text;
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
    }
}

package com.raven.models;

public class Status {
    private static int generalStatus = 0;
    private static Boolean qrState = false;
    private static Boolean idCardState = false;
    private static Boolean photoState = false;

    private static Status status;
    private Status () {}
    public static Status getInstanceStatus () {
        if (status == null) {
            status = new Status();
        }

        return status;
    }
    public int getGeneralStatus() {
        return generalStatus;
    }

    public void setGeneralStatus(int generalStatus) {
        Status.generalStatus = generalStatus;
    }

    public Boolean getQrState() {
        return qrState;
    }

    public void setQrState(Boolean qrState) {
        Status.qrState = qrState;
    }

    public Boolean getIdCardState() {
        return idCardState;
    }

    public void setIdCardState(Boolean idCardState) {
        Status.idCardState = idCardState;
    }

    public Boolean getPhotoState() {
        return photoState;
    }

    public void setPhotoState(Boolean photoState) {
        Status.photoState = photoState;
    }
}

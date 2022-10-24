package com.raven.models;

public class Barcode {
    private static String Givenname;
    private static String Surname;
    private static String Fullname;
    private static String FromAddress;
    private static String FlightNo;
    private static String DestAddress;
    private static String CheckInSequenceNo;
    private static String ExpiryDate;
    private static String Composite;
    private static String SeatNo;
    private static Barcode barcode;

    private Barcode() {}

    public static Barcode getInstanceBarcode(){
        if(barcode == null)
            barcode = new Barcode();

        return barcode;
    }

    public String getGivenname() {
        return Givenname;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }

    public void setGivenname(String givenname) {
        Barcode.Givenname = givenname;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Barcode.Surname = surname;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Barcode.Fullname = fullname;
    }

    public String getFromAddress() {
        return FromAddress;
    }

    public void setFromAddress(String fromAddress) {
        Barcode.FromAddress = fromAddress;
    }

    public String getFlightNo() {
        return FlightNo;
    }

    public void setFlightNo(String flightNo) {
        Barcode.FlightNo = flightNo;
    }

    public String getDestAddress() {
        return DestAddress;
    }

    public void setDestAddress(String destAddress) {
        Barcode.DestAddress = destAddress;
    }

    public String getCheckInSequenceNo() {
        return CheckInSequenceNo;
    }

    public void setCheckInSequenceNo(String checkInSequenceNo) {
        Barcode.CheckInSequenceNo = checkInSequenceNo;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        Barcode.ExpiryDate = expiryDate;
    }

    public String getComposite() {
        return Composite;
    }

    public void setComposite(String composite) {
        Barcode.Composite = composite;
    }
}

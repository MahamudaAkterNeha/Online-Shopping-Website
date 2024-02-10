package com.example.clickcart.models;

public class PaymentModel {

    String userPaymentDetails;
    boolean isSelected;

    public PaymentModel() {
    }

    public String getUserPaymentDetails() {
        return userPaymentDetails;
    }

    public void setUserPaymentDetails(String userPaymentDetails) {
        this.userPaymentDetails = userPaymentDetails;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

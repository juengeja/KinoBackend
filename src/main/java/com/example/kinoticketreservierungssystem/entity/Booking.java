package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import java.util.List;

@Container(containerName = "Bookings", ru = "400")
public class Booking {
    @Id
    private String bookingID;
    private Customer customerInfo;
    private ShowEvent showEventInfo;
    private List<Seat> seatInfo;
    private Coupon couponInfo;
    @PartitionKey
    private String paymentMethod = "not paid yet";
    private double totalPrice;
    private boolean paid = false;

    public Booking(String bookingID, Customer customerInfo, ShowEvent showEventInfo, List<Seat> seatInfo, Coupon couponInfo, String paymentMethod, double totalPrice, boolean paid) {
        this.bookingID = bookingID;
        this.customerInfo = customerInfo;
        this.showEventInfo = showEventInfo;
        this.seatInfo = seatInfo;
        this.couponInfo = couponInfo;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.paid = paid;
    }

    public Booking(String bookingID, List<Seat> seats, ShowEvent showEvent) {
        this.bookingID = bookingID;
        this.seatInfo = seats;
        this.showEventInfo = showEvent;
    }

    public Booking() {
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, Booking.class);
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public Customer getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(Customer customerInfo) {
        this.customerInfo = customerInfo;
    }

    public ShowEvent getShowEventInfo() {
        return showEventInfo;
    }

    public void setShowEventInfo(ShowEvent showEventInfo) {
        this.showEventInfo = showEventInfo;
    }

    public List<Seat> getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(List<Seat> seatInfo) {
        this.seatInfo = seatInfo;
    }

    public Coupon getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(Coupon couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

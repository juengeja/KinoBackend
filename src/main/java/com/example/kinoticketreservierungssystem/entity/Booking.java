package com.example.kinoticketreservierungssystem.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.example.kinoticketreservierungssystem.blSupport.Reservation;
import com.google.gson.Gson;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Container(containerName = "Bookings", ru = "400")
public class Booking {
    @Id
    private String bookingID;
    private Customer customerInfo;
    @PartitionKey
    private Set<Reservation> reservations;
    private Set<String> tickets;
    private String bookingStatus;
    private Coupon couponInfo;
    private String paymentMethod = "not paid yet";
    private double totalPrice;

    public Booking(String bookingID, Customer customerInfo, Set<String> ticketInfo, Coupon couponInfo, String paymentMethod, double totalPrice) {
        this.bookingID = bookingID;
        this.customerInfo = customerInfo;
        this.tickets = ticketInfo;
        this.couponInfo = couponInfo;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    public Booking(String bookingID) {
        this.bookingID = bookingID;
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

    public Set<String> getTickets() {
        return tickets;
    }

    public void setTickets(Set<String> tickets) {
        this.tickets = this.tickets;
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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}

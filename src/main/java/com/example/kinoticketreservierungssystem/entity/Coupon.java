package com.example.kinoticketreservierungssystem.entity;


import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "Coupons", ru = "400")
public class Coupon {
    @Id
    private String couponID;
    private String couponCode;
    // Type a -> Flat; Type b -> Percent
    @PartitionKey
    private char couponType;
    private float flatAmount;
    private float percentAmount;

    public Coupon(String couponID, String couponCode, char couponType, float flatAmount, float percentAmount) {
        this.couponID = couponID;
        this.couponCode = couponCode;
        this.couponType = couponType;
        this.flatAmount = flatAmount;
        this.percentAmount = percentAmount;
    }

    public Coupon() {

    }

    public String getCouponID() {
        return couponID;
    }

    public void setCouponID(String couponID) {
        this.couponID = couponID;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public char getCouponType() {
        return couponType;
    }

    public void setCouponType(char couponType) {
        this.couponType = couponType;
    }

    public float getFlatAmount() {
        return flatAmount;
    }

    public void setFlatAmount(float flatAmount) {
        this.flatAmount = flatAmount;
    }

    public float getPercentAmount() {
        return percentAmount;
    }

    public void setPercentAmount(float percentAmount) {
        this.percentAmount = percentAmount;
    }
}

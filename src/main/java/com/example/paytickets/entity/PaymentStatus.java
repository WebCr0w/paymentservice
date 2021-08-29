package com.example.paytickets.entity;

import java.util.Random;

public enum PaymentStatus {

    ERROR,
    PROCESSED,
    COMPLETED;

    public static PaymentStatus generateRandomStatus(){
        PaymentStatus[] values = PaymentStatus.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);

        return values[randIndex];
    }

}

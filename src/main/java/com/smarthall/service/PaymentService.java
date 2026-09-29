package com.smarthall.service;

import com.smarthall.model.Payment;
import com.smarthall.repository.PaymentRepository;

public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService() {
        paymentRepository = new PaymentRepository();
    }

    // Create and save a new payment
    public Payment createPayment(String studentId,
                                 int semester,
                                 double amount) {

        if (studentId == null ||
            semester <= 0 ||
            amount <= 0) {

            return null;
        }

        Payment payment = new Payment(
                studentId,
                semester,
                amount
        );

        boolean saved =
                paymentRepository.savePayment(payment);

        if (!saved) {
            return null;
        }

        return payment;
    }

    // Make payment
    public boolean makePayment(Payment payment) {

        if (payment == null || !payment.isDue()) {
            return false;
        }

        payment.makePayment();

        return true;
    }
}
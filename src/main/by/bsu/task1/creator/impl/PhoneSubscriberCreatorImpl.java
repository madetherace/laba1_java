package bsu.task1.creator.impl;

import bsu.task1.entity.PhoneSubscriber;

public interface PhoneSubscriberCreatorImpl {
    PhoneSubscriber createSubscriber(
            int id, String lastName, String firstName, String middleName,
            String address, String creditCardNumber, double debit,
            double credit, int localCallMinutes, int internationalCallMinutes);

}
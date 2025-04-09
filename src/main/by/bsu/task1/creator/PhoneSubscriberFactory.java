package bsu.task1.creator;

import bsu.task1.entity.PhoneSubscriber;
import bsu.task1.validator.PhoneSubscriberValidator;

public abstract class PhoneSubscriberFactory implements PhoneSubscriberCreator {
    protected PhoneSubscriberValidator validator = new PhoneSubscriberValidator();

    @Override
    public abstract PhoneSubscriber createSubscriber(
            int id, String lastName, String firstName, String middleName,
            String address, String creditCardNumber, double debit,
            double credit, int localCallMinutes, int internationalCallMinutes);
}
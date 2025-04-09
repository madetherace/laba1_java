package bsu.task1.creator;

import bsu.task1.creator.impl.PhoneSubscriberCreatorImpl;
import bsu.task1.entity.PhoneSubscriber;
import bsu.task1.validator.PhoneSubscriberValidator;

public abstract class PhoneSubscriberFactory implements PhoneSubscriberCreatorImpl {
    protected PhoneSubscriberValidator validator = new PhoneSubscriberValidator();

    @Override
    public PhoneSubscriber createSubscriber(
            int id, String lastName, String firstName, String middleName,
            String address, String creditCardNumber, double debit,
            double credit, int localCallMinutes, int internationalCallMinutes) {

        PhoneSubscriber subscriber = new PhoneSubscriber();
        subscriber.setId(id);
        subscriber.setLastName(lastName);
        subscriber.setFirstName(firstName);
        subscriber.setMiddleName(middleName);
        subscriber.setAddress(address);
        subscriber.setCreditCardNumber(creditCardNumber);
        subscriber.setDebit(debit);
        subscriber.setCredit(credit);
        subscriber.setLocalCallMinutes(localCallMinutes);
        subscriber.setInternationalCallMinutes(internationalCallMinutes);
        return subscriber;
    }

}
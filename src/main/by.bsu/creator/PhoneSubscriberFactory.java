package creator;

import entity.PhoneSubscriber;
import validator.PhoneSubscriberValidator;

public abstract class PhoneSubscriberFactory {
    protected PhoneSubscriberValidator validator = new PhoneSubscriberValidator();

    public abstract PhoneSubscriber createSubscriber(
            int id, String lastName, String firstName, String middleName,
            String address, String creditCardNumber, double debit,
            double credit, int localCallMinutes, int internationalCallMinutes);
}
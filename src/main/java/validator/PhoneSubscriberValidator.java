package validator;

import entity.PhoneSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhoneSubscriberValidator {
    private static final Logger logger = LogManager.getLogger(PhoneSubscriberValidator.class);

    public boolean validate(PhoneSubscriber subscriber) {
        if (subscriber == null) {
            logger.error("Subscriber is null");
            return false;
        }

        if (subscriber.getId() <= 0) {
            logger.error("Invalid ID: {}", subscriber.getId());
            return false;
        }

        if (subscriber.getLastName() == null || subscriber.getLastName().isEmpty()) {
            logger.error("Last name is empty");
            return false;
        }

        if (subscriber.getFirstName() == null || subscriber.getFirstName().isEmpty()) {
            logger.error("First name is empty");
            return false;
        }

        if (subscriber.getCreditCardNumber() == null || !subscriber.getCreditCardNumber().matches("\\d{16}")) {
            logger.error("Invalid credit card number: {}", subscriber.getCreditCardNumber());
            return false;
        }

        if (subscriber.getDebit() < 0) {
            logger.error("Debit cannot be negative: {}", subscriber.getDebit());
            return false;
        }

        if (subscriber.getCredit() < 0) {
            logger.error("Credit cannot be negative: {}", subscriber.getCredit());
            return false;
        }

        if (subscriber.getLocalCallMinutes() < 0) {
            logger.error("Local call minutes cannot be negative: {}", subscriber.getLocalCallMinutes());
            return false;
        }

        if (subscriber.getInternationalCallMinutes() < 0) {
            logger.error("International call minutes cannot be negative: {}", subscriber.getInternationalCallMinutes());
            return false;
        }

        return true;
    }
}
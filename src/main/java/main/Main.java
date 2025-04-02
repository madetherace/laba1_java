package main;

import entity.PhoneSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.PhoneSubscriberService;
import service.impl.PhoneSubscriberServiceImpl;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        PhoneSubscriberService service = new PhoneSubscriberServiceImpl();

        // Add some test subscribers
        service.addSubscriber(createSubscriber(1, "Smith", "John", "A.", "123 Main St",
                "1234567890123456", 100.0, 0.0, 120, 0));
        service.addSubscriber(createSubscriber(2, "Johnson", "Alice", "B.", "456 Oak Ave",
                "2345678901234567", 50.0, 10.0, 60, 30));
        service.addSubscriber(createSubscriber(3, "Williams", "Bob", "C.", "789 Pine Rd",
                "3456789012345678", 75.0, 5.0, 90, 0));

        // Test the service methods
        logger.info("Subscribers with local calls > 80 minutes:");
        service.getSubscribersWithExcessiveLocalCalls(80).forEach(subscriber ->
                logger.info(subscriber.toString()));

        logger.info("\nSubscribers with international calls:");
        service.getSubscribersWithInternationalCalls().forEach(subscriber ->
                logger.info(subscriber.toString()));

        logger.info("\nSubscribers in alphabetical order:");
        service.getSubscribersInAlphabeticalOrder().forEach(subscriber ->
                logger.info(subscriber.toString()));
    }

    private static PhoneSubscriber createSubscriber(int id, String lastName, String firstName,
                                                    String middleName, String address, String creditCardNumber, double debit,
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

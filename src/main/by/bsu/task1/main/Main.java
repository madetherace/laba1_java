package bsu.task1.main;

import bsu.task1.creator.impl.PhoneSubscriberFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import bsu.task1.service.PhoneSubscriberService;
import bsu.task1.service.impl.PhoneSubscriberServiceImpl;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        PhoneSubscriberService service = new PhoneSubscriberServiceImpl();
        PhoneSubscriberFactoryImpl factory = new PhoneSubscriberFactoryImpl() {

        };



        service.addSubscriber(factory.createSubscriber(1, "Smith", "John", "A.", "123 Main St",
                "1234567890123456", 100.0, 0.0, 120, 0));
        service.addSubscriber(factory.createSubscriber(2, "Johnson", "Alice", "B.", "456 Oak Ave",
                "2345678901234567", 50.0, 10.0, 60, 30));
        service.addSubscriber(factory.createSubscriber(3, "Williams", "Bob", "C.", "789 Pine Rd",
                "3456789012345678", 75.0, 5.0, 90, 0));

        logger.info("\nSubscribers with local calls > 80 minutes:");
        service.findSubscribersWithExcessiveLocalCalls(80).forEach(subscriber ->
                logger.info(subscriber.toString()));

        logger.info("\nSubscribers with international calls:");
        service.findSubscribersWithInternationalCalls().forEach(subscriber ->
                logger.info(subscriber.toString()));

        logger.info("\nSubscribers in alphabetical order:");
        service.findSubscribersInAlphabeticalOrder().forEach(subscriber ->
                logger.info(subscriber.toString()));
    }

}
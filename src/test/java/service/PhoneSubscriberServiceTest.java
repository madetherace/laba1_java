package service;

import entity.PhoneSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.impl.PhoneSubscriberServiceImpl;

import java.util.List;

public class PhoneSubscriberServiceTest {
    private static final Logger logger = LogManager.getLogger(PhoneSubscriberServiceTest.class);
    private PhoneSubscriberService service;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        logger.info("Initializing test setup...");
        service = new PhoneSubscriberServiceImpl();
        softAssert = new SoftAssert();

        logger.debug("Adding test subscribers...");
        service.addSubscriber(createSubscriber(1, "Smith", "John", "A.", "123 Main St",
                "1234567890123456", 100.0, 0.0, 120, 0));
        service.addSubscriber(createSubscriber(2, "Johnson", "Alice", "B.", "456 Oak Ave",
                "2345678901234567", 50.0, 10.0, 60, 30));
        service.addSubscriber(createSubscriber(3, "Williams", "Bob", "C.", "789 Pine Rd",
                "3456789012345678", 75.0, 5.0, 90, 0));
        logger.info("Test setup completed");
    }

    @Test
    public void testGetSubscribersWithExcessiveLocalCalls() {
        logger.info("Running testGetSubscribersWithExcessiveLocalCalls...");
        List<PhoneSubscriber> result = service.getSubscribersWithExcessiveLocalCalls(80);

        logger.debug("Verifying results for excessive local calls...");
        softAssert.assertEquals(result.size(), 2,
                "Should return 2 subscribers with local calls > 80 minutes");

        if (result.size() == 2) {
            logger.debug("First subscriber: {}", result.get(0));
            logger.debug("Second subscriber: {}", result.get(1));
            softAssert.assertEquals(result.get(0).getLastName(), "Smith",
                    "First subscriber should be Smith");
            softAssert.assertEquals(result.get(1).getLastName(), "Williams",
                    "Second subscriber should be Williams");
        }

        softAssert.assertAll();
        logger.info("testGetSubscribersWithExcessiveLocalCalls completed");
    }

    @Test
    public void testGetSubscribersWithInternationalCalls() {
        logger.info("Running testGetSubscribersWithInternationalCalls...");
        List<PhoneSubscriber> result = service.getSubscribersWithInternationalCalls();

        logger.debug("Verifying results for international calls...");
        softAssert.assertEquals(result.size(), 1,
                "Should return 1 subscriber with international calls");

        if (!result.isEmpty()) {
            logger.debug("Subscriber with international calls: {}", result.get(0));
            softAssert.assertEquals(result.get(0).getLastName(), "Johnson",
                    "Subscriber with international calls should be Johnson");
            softAssert.assertTrue(result.get(0).getInternationalCallMinutes() > 0,
                    "International call minutes should be positive");
        }

        softAssert.assertAll();
        logger.info("testGetSubscribersWithInternationalCalls completed");
    }

    @Test
    public void testGetSubscribersInAlphabeticalOrder() {
        logger.info("Running testGetSubscribersInAlphabeticalOrder...");
        List<PhoneSubscriber> result = service.getSubscribersInAlphabeticalOrder();

        logger.debug("Verifying alphabetical order...");
        softAssert.assertEquals(result.size(), 3,
                "Should return all 3 subscribers");

        if (result.size() == 3) {
            logger.debug("Subscribers in order: 1) {} 2) {} 3) {}",
                    result.get(0).getLastName(),
                    result.get(1).getLastName(),
                    result.get(2).getLastName());

            softAssert.assertEquals(result.get(0).getLastName(), "Johnson",
                    "First subscriber should be Johnson");
            softAssert.assertEquals(result.get(1).getLastName(), "Smith",
                    "Second subscriber should be Smith");
            softAssert.assertEquals(result.get(2).getLastName(), "Williams",
                    "Third subscriber should be Williams");
        }

        softAssert.assertAll();
        logger.info("testGetSubscribersInAlphabeticalOrder completed");
    }

    @Test
    public void testAddSubscriber() {
        logger.info("Running testAddSubscriber...");
        PhoneSubscriber newSubscriber = createSubscriber(4, "Brown", "David", "D.",
                "101 Elm St", "4567890123456789", 200.0, 0.0, 30, 5);

        int initialSize = service.getSubscribersInAlphabeticalOrder().size();
        logger.debug("Initial subscriber count: {}", initialSize);

        service.addSubscriber(newSubscriber);
        logger.debug("Added new subscriber: {}", newSubscriber);

        int newSize = service.getSubscribersInAlphabeticalOrder().size();
        logger.debug("New subscriber count: {}", newSize);

        softAssert.assertEquals(newSize, initialSize + 1,
                "Subscriber list size should increase by 1");

        List<PhoneSubscriber> subscribers = service.getSubscribersInAlphabeticalOrder();
        softAssert.assertTrue(subscribers.contains(newSubscriber),
                "New subscriber should be in the list");

        softAssert.assertAll();
        logger.info("testAddSubscriber completed");
    }

    private PhoneSubscriber createSubscriber(int id, String lastName, String firstName,
                                             String middleName, String address, String creditCardNumber, double debit,
                                             double credit, int localCallMinutes, int internationalCallMinutes) {
        logger.trace("Creating subscriber: {} {}", firstName, lastName);
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
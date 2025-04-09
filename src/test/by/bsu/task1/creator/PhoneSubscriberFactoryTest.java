package bsu.task1.creator;

import bsu.task1.entity.PhoneSubscriber;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertNull;

public class PhoneSubscriberFactoryTest {

    private static class TestPhoneSubscriberFactory extends PhoneSubscriberFactory {
        @Override
        public PhoneSubscriber createSubscriber(int id, String lastName, String firstName, String middleName,
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

            if (!validator.validate(subscriber)) {
                return null;
            }
            return subscriber;
        }
    }

    private final PhoneSubscriberFactory factory = new TestPhoneSubscriberFactory();
    private final SoftAssert softAssert = new SoftAssert();


    @Test
    public void testCreateSubscriberWithInvalidCreditCard() {
        PhoneSubscriber subscriber = factory.createSubscriber(
                2, "Johnson", "Alice", "B.", "456 Oak Ave",
                "invalid", 50.0, 10.0, 60, 30);

        softAssert.assertNull(subscriber, "Subscriber with invalid credit card should be null");
        softAssert.assertAll();
    }

    @Test
    public void testCreateSubscriberWithNegativeDebit() {
        PhoneSubscriber subscriber = factory.createSubscriber(
                3, "Williams", "Bob", "C.", "789 Pine Rd",
                "3456789012345678", -75.0, 5.0, 90, 0);

        softAssert.assertNull(subscriber, "Subscriber with negative debit should be null");
        softAssert.assertAll();
    }

    @Test
    public void testCreateSubscriberWithEmptyName() {
        PhoneSubscriber subscriber = factory.createSubscriber(
                4, "", "Bob", "C.", "789 Pine Rd",
                "3456789012345678", 75.0, 5.0, 90, 0);

        softAssert.assertNull(subscriber, "Subscriber with empty last name should be null");
        softAssert.assertAll();
    }

    @Test
    public void testMultipleValidationsInOneTest() {
        // Проверка нескольких случаев в одном тесте
        PhoneSubscriber validSubscriber = factory.createSubscriber(
                5, "Brown", "David", "E.", "101 Maple St",
                "4567890123456789", 200.0, 0.0, 30, 10);

        PhoneSubscriber invalidSubscriber = factory.createSubscriber(
                6, null, "Eve", "F.", "202 Oak St",
                "5678901234567890", 150.0, 0.0, 45, 5);

        softAssert.assertNotNull(validSubscriber, "Valid subscriber should not be null");
        softAssert.assertNull(invalidSubscriber, "Subscriber with null last name should be null");

        softAssert.assertAll();
    }
}
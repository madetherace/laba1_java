package bsu.task1.validator;

import bsu.task1.entity.PhoneSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PhoneSubscriberValidatorTest {
    private static final Logger logger = LogManager.getLogger(PhoneSubscriberValidatorTest.class);
    private final PhoneSubscriberValidator validator = new PhoneSubscriberValidator();

    @Test
    public void testValidateWithValidSubscriber() {
        logger.info("Starting testValidateWithValidSubscriber");
        SoftAssert softAssert = new SoftAssert();
        PhoneSubscriber subscriber = createValidSubscriber();

        logger.debug("Testing valid subscriber: {}", subscriber);
        boolean isValid = validator.validate(subscriber);
        softAssert.assertTrue(isValid, "Valid subscriber should pass validation");

        if (isValid) {
            logger.debug("Validation passed as expected");
        } else {
            logger.error("Validation failed for valid subscriber!");
        }

        softAssert.assertAll();
        logger.info("Completed testValidateWithValidSubscriber");
    }

    @Test
    public void testValidateWithNullSubscriber() {
        logger.info("Starting testValidateWithNullSubscriber");
        SoftAssert softAssert = new SoftAssert();

        logger.debug("Testing null subscriber");
        boolean isValid = validator.validate(null);
        softAssert.assertFalse(isValid, "Null subscriber should fail validation");

        if (!isValid) {
            logger.debug("Null subscriber rejected as expected");
        } else {
            logger.error("Null subscriber was incorrectly accepted!");
        }

        softAssert.assertAll();
        logger.info("Completed testValidateWithNullSubscriber");
    }

    @Test
    public void testValidateWithInvalidId() {
        logger.info("Starting testValidateWithInvalidId");
        SoftAssert softAssert = new SoftAssert();
        PhoneSubscriber subscriber = createValidSubscriber();
        subscriber.setId(0);

        logger.debug("Testing subscriber with invalid ID (0): {}", subscriber);
        softAssert.assertFalse(validator.validate(subscriber),
                "Subscriber with invalid ID (0) should fail validation");

        subscriber.setId(-1);
        logger.debug("Testing subscriber with negative ID (-1): {}", subscriber);
        softAssert.assertFalse(validator.validate(subscriber),
                "Subscriber with invalid ID (-1) should fail validation");

        softAssert.assertAll();
        logger.info("Completed testValidateWithInvalidId");
    }

    @Test
    public void testValidateWithEmptyLastName() {
        logger.info("Starting testValidateWithEmptyLastName");
        SoftAssert softAssert = new SoftAssert();
        PhoneSubscriber subscriber = createValidSubscriber();
        subscriber.setLastName("");

        logger.debug("Testing subscriber with empty last name: {}", subscriber);
        softAssert.assertFalse(validator.validate(subscriber),
                "Subscriber with empty last name should fail validation");

        subscriber.setLastName(null);
        logger.debug("Testing subscriber with null last name: {}", subscriber);
        softAssert.assertFalse(validator.validate(subscriber),
                "Subscriber with null last name should fail validation");

        softAssert.assertAll();
        logger.info("Completed testValidateWithEmptyLastName");
    }

    @Test
    public void testValidateWithInvalidCreditCard() {
        logger.info("Starting testValidateWithInvalidCreditCard");
        SoftAssert softAssert = new SoftAssert();
        PhoneSubscriber subscriber = createValidSubscriber();

        subscriber.setCreditCardNumber("1234");
        logger.debug("Testing subscriber with short credit card: {}", subscriber);
        softAssert.assertFalse(validator.validate(subscriber),
                "Subscriber with short credit card number should fail validation");

        subscriber.setCreditCardNumber("123456789012345a");
        logger.debug("Testing subscriber with non-digit credit card: {}", subscriber);
        softAssert.assertFalse(validator.validate(subscriber),
                "Subscriber with non-digit characters in credit card should fail validation");

        subscriber.setCreditCardNumber(null);
        logger.debug("Testing subscriber with null credit card: {}", subscriber);
        softAssert.assertFalse(validator.validate(subscriber),
                "Subscriber with null credit card should fail validation");

        softAssert.assertAll();
        logger.info("Completed testValidateWithInvalidCreditCard");
    }

    @Test
    public void testMultipleValidationsInOneTest() {
        logger.info("Starting testMultipleValidationsInOneTest");
        SoftAssert softAssert = new SoftAssert();

        // Valid case
        PhoneSubscriber validSubscriber = createValidSubscriber();
        logger.debug("Testing valid subscriber: {}", validSubscriber);
        softAssert.assertTrue(validator.validate(validSubscriber),
                "Valid subscriber should pass validation");

        // Invalid ID case
        PhoneSubscriber invalidSubscriber = createValidSubscriber();
        invalidSubscriber.setId(0);
        logger.debug("Testing subscriber with invalid ID: {}", invalidSubscriber);
        softAssert.assertFalse(validator.validate(invalidSubscriber),
                "Subscriber with invalid ID should fail validation");

        // Empty last name case
        invalidSubscriber = createValidSubscriber();
        invalidSubscriber.setLastName("");
        logger.debug("Testing subscriber with empty last name: {}", invalidSubscriber);
        softAssert.assertFalse(validator.validate(invalidSubscriber),
                "Subscriber with empty last name should fail validation");

        softAssert.assertAll();
        logger.info("Completed testMultipleValidationsInOneTest");
    }

    private PhoneSubscriber createValidSubscriber() {
        logger.trace("Creating valid subscriber for testing");
        PhoneSubscriber subscriber = new PhoneSubscriber();
        subscriber.setId(1);
        subscriber.setLastName("Doe");
        subscriber.setFirstName("John");
        subscriber.setMiddleName("A.");
        subscriber.setAddress("123 Main St");
        subscriber.setCreditCardNumber("1234567890123456");
        subscriber.setDebit(100.0);
        subscriber.setCredit(0.0);
        subscriber.setLocalCallMinutes(60);
        subscriber.setInternationalCallMinutes(0);
        return subscriber;
    }
}
package service;

import entity.PhoneSubscriber;

import java.util.List;

public interface PhoneSubscriberService {
    List<PhoneSubscriber> getSubscribersWithExcessiveLocalCalls(int minutesThreshold);
    List<PhoneSubscriber> getSubscribersWithInternationalCalls();
    List<PhoneSubscriber> getSubscribersInAlphabeticalOrder();
    void addSubscriber(PhoneSubscriber subscriber);
}
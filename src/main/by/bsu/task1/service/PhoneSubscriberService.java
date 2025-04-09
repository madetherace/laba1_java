package bsu.task1.service;

import bsu.task1.entity.PhoneSubscriber;

import java.util.List;

public interface PhoneSubscriberService {
    List<PhoneSubscriber> findSubscribersWithExcessiveLocalCalls(int minutesThreshold);
    List<PhoneSubscriber> findSubscribersWithInternationalCalls();
    List<PhoneSubscriber> findSubscribersInAlphabeticalOrder();
    void addSubscriber(PhoneSubscriber subscriber);
}
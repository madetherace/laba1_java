package bsu.task1.service.impl;

import bsu.task1.entity.PhoneSubscriber;
import bsu.task1.service.PhoneSubscriberService;
import bsu.task1.validator.PhoneSubscriberValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PhoneSubscriberServiceImpl implements PhoneSubscriberService {
    private List<PhoneSubscriber> subscribers = new ArrayList<>();
    private PhoneSubscriberValidator validator = new PhoneSubscriberValidator();

    @Override
    public List<PhoneSubscriber> findSubscribersWithExcessiveLocalCalls(int minutesThreshold) {
        return subscribers.stream()
                .filter(s -> s.getLocalCallMinutes() > minutesThreshold)
                .toList();
    }

    @Override
    public List<PhoneSubscriber> findSubscribersWithInternationalCalls() {
        return subscribers.stream()
                .filter(s -> s.getInternationalCallMinutes() > 0)
                .toList();
    }

    @Override
    public List<PhoneSubscriber> findSubscribersInAlphabeticalOrder() {
        return subscribers.stream()
                .sorted(Comparator.comparing(PhoneSubscriber::getLastName))
                .toList();
    }

    @Override
    public void addSubscriber(PhoneSubscriber subscriber) {
        if (validator.validate(subscriber)) {
            subscribers.add(subscriber);
        }
    }
}
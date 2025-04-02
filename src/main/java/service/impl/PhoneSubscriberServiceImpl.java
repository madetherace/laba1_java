package service.impl;

import entity.PhoneSubscriber;
import service.PhoneSubscriberService;
import validator.PhoneSubscriberValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneSubscriberServiceImpl implements PhoneSubscriberService {
    private List<PhoneSubscriber> subscribers = new ArrayList<>();
    private PhoneSubscriberValidator validator = new PhoneSubscriberValidator();

    @Override
    public List<PhoneSubscriber> getSubscribersWithExcessiveLocalCalls(int minutesThreshold) {
        return subscribers.stream()
                .filter(s -> s.getLocalCallMinutes() > minutesThreshold)
                .collect(Collectors.toList());
    }

    @Override
    public List<PhoneSubscriber> getSubscribersWithInternationalCalls() {
        return subscribers.stream()
                .filter(s -> s.getInternationalCallMinutes() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<PhoneSubscriber> getSubscribersInAlphabeticalOrder() {
        return subscribers.stream()
                .sorted(Comparator.comparing(PhoneSubscriber::getLastName))
                .collect(Collectors.toList());
    }

    @Override
    public void addSubscriber(PhoneSubscriber subscriber) {
        if (validator.validate(subscriber)) {
            subscribers.add(subscriber);
        }
    }
}
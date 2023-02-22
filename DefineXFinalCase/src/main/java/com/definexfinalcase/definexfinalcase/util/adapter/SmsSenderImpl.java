package com.definexfinalcase.definexfinalcase.util.adapter;

import com.definexfinalcase.definexfinalcase.configuration.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderImpl implements SmsSender{
    @Override
    public void sendSms(String phoneNumber, String message) {
        TwilioConfiguration twilioConfiguration = new TwilioConfiguration();
        MessageCreator messageCreator = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioConfiguration.getTrialNumber()),
                message
        );
        messageCreator.create();
    }
}

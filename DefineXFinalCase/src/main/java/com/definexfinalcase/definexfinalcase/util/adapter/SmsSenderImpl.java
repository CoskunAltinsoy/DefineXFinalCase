package com.definexfinalcase.definexfinalcase.util.adapter;

import com.definexfinalcase.definexfinalcase.configuration.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("twilio")
@Slf4j
public class SmsSenderImpl implements SmsSender{

    private final TwilioConfiguration twilioConfiguration;

    public SmsSenderImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }
    @Override
    public void sendSms(String phoneNumber, String message) {
        MessageCreator messageCreator = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioConfiguration.getTrialNumber()),
                message
        );
        messageCreator.create();
        log.info(String.format("Send sms %s", phoneNumber));
    }

}

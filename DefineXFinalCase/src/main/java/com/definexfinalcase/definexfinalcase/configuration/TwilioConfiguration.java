package com.definexfinalcase.definexfinalcase.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfiguration {
    //@Value("${twilio.account_sid}")
    private String accountSid;
    //@Value("${twilio.auth_token}")
    private String authToken;
    //@Value("${twilio.trial_number}")
    private String trialNumber;

}

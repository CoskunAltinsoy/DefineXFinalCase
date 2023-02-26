package com.definexfinalcase.definexfinalcase.util.externalService;

public class CustomerCreditScoreGenerator {

    public int CustomerCreditScore(String nationalIdentity) {
        int max= 1900;
        int min= 1;

        int score = (int) Math.floor(Math.random()*(max-min+1)+min);
        System.out.println(score);
        return score;
    }
}

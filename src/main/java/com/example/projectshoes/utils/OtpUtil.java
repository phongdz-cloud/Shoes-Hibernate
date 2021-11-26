package com.example.projectshoes.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class OtpUtil {

  public static String generateOtp(final int lengthOfOTP) {
    StringBuilder generateOTP = new StringBuilder();
    SecureRandom secureRandom = new SecureRandom();
    try {
      secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());
      for (int i = 0; i < lengthOfOTP; i++) {
        generateOTP.append(secureRandom.nextInt(9));
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generateOTP.toString();
  }

  public static void main(String[] args) {
    String price = "4.36$";
    String price2 = "$182.01";

    System.out.println(price2.substring(1));
    System.out.println(price.substring(0,price.length()-1));

    price = price.substring(price.length()-1);

  }

}


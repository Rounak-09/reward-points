package com.walmart.rewardPoints.utility;

import com.walmart.rewardPoints.exception.UserException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Converter {

    private Converter() {
    }

    public static LocalDate convertToLocalDate(String date) {
        if (date == null || date.trim().isEmpty()) return null;
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new UserException("Invalid date format!");
        }
    }
}

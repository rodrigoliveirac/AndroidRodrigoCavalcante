package com.rodcollab.androidrodrigocavalcante;

import java.util.Calendar;

public class DateUtils {

    public String getCurrentDateAndTime() {
        Calendar calendar = Calendar.getInstance();

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String dayText = String.format("%02d", dayOfMonth);
        String monthText = String.format("%02d", month);
        String yearText = String.valueOf(year);
        String hourText = String.format("%02d", hour);
        String minuteText = String.format("%02d", minute);

        StringBuilder result = new StringBuilder();
        result.append(dayText).append("/").append(monthText).append("/").append(yearText)
                .append(" ").append(hourText).append(":").append(minuteText);

        return result.toString();
    }
}

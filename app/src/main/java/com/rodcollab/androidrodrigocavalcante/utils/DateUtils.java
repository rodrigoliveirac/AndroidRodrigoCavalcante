package com.rodcollab.androidrodrigocavalcante.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

    public String getDateOrderFormatted(String date) {

        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dateFormatted = "";

        try {
            Date data = formatoOriginal.parse(date);

            Calendar currentCalendar = Calendar.getInstance();
            int currentDayOfMonth = currentCalendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
            int currentYear = currentCalendar.get(Calendar.YEAR);

            StringBuilder result = new StringBuilder();
            result.append(currentDayOfMonth).append("/").append(currentMonth).append("/").append(currentYear);

            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(data);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);

            StringBuilder result2 = new StringBuilder();
            result.append(dayOfMonth).append("/").append(month).append("/").append(year);


            if(result == result2) {
                SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm");
                newFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                assert data != null;
                dateFormatted = newFormat.format(data);
            } else if(currentYear == year){
                SimpleDateFormat newFormat = new SimpleDateFormat("dd MMM");
                newFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                assert data != null;
                dateFormatted = newFormat.format(data);
            } else {
                SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
                newFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                assert data != null;
                dateFormatted = newFormat.format(data);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFormatted;
    }
}

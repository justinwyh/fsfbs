package Util;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Fsfbs.SimulationMode;

public class UtilTime {

    private static UtilTime timeInstance = new UtilTime();

    public static UtilTime getTimeInstance() {
        return timeInstance;
    }

    public String getCurrentDate() throws IOException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.now();
        return (dtf.format(localDate));
    }

    public String getCurrentTime() throws IOException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return (dtf.format(now));
    }

    public boolean isTimeLaterThanCurrentTime(String inputTime) throws IOException {
        int currentHour;
        int inputHour;
        String currentTime = getCurrentTime();
        currentHour = Integer.parseInt(currentTime.substring(0, 2));
        inputHour = Integer.parseInt(inputTime.substring(0, 2));

        //checking
        return currentHour <= inputHour;
    }

    public int isTimeRangeExceed(int timeslot){
        int startTime = timeslot/100;
        int endTime = timeslot%100;
        if (endTime - startTime <= 0 || startTime > 23 || endTime > 24){
            return -1; //incorrect time range
        }
        else if (startTime - endTime > 1) {
            return -2; //each booking can not bookmore than an hour
        }
        else if(startTime < 10){
            return -3; //sport centres open from 10am to 12am
        }
        else {
            return 0;
        }
    }

    private int convertTimeToInt(String time){
        return Integer.parseInt(time.substring(0,2));
    }

    public String getTimeWithFormat(int timeslot){
        String sTime = Integer.toString(timeslot);
        String startTime = sTime.substring(0,2) + ":00";
        String endTime = sTime.substring(2,4) + ":00";
        return (startTime +" - " + endTime);
    }

    public String getNextAvailableTimeSlot(int hour){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:00:00");
        LocalDateTime now = LocalDateTime.now().plusHours(hour);
        return (dtf.format(now));
    }

    public int getTimeSlot(String startTime, String endTime){
        int startTimeHour = convertTimeToInt(startTime);
        int endTimeHour = convertTimeToInt(endTime);
        if (endTimeHour == 0) {
            endTimeHour = 24;
        }
        return startTimeHour * 100 + endTimeHour;
    }
}

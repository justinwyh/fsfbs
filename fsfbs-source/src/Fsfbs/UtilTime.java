package Fsfbs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class UtilTime {

    private static UtilTime timeInstance = new UtilTime();

    public static UtilTime getTimeInstance() {
        return timeInstance;
    }

    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.now();
        return (dtf.format(localDate));
    }

    public String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return (dtf.format(now));
    }

    public boolean isTimeLaterThanCurrentTime(String inputTime) {
        int currentHour;
        int inputHour;
        String currentTime = getCurrentTime();
        currentHour = Integer.parseInt(currentTime.substring(0, 2));
        inputHour = Integer.parseInt(inputTime.substring(0, 2));

        //checking
        if (currentHour > inputHour) {
            return false;
        } else {
            return true;
        }
    }

    public int isTimeRangeExceed(int timeslot){
        int startTime = timeslot/100;
        int endTime = timeslot%100;
        if (startTime - endTime <= 0 && startTime > 23 && endTime > 24){
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
}

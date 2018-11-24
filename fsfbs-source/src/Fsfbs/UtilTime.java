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
        int currentHour, currentMin, currentSec;
        int inputHour, inputMin, inputSec;
        String currentTime = getCurrentTime();
        currentHour = Integer.parseInt(currentTime.substring(0, 2));
        currentMin = Integer.parseInt(currentTime.substring(3, 5));
        currentSec = Integer.parseInt(currentTime.substring(6, 8));
        inputHour = Integer.parseInt(inputTime.substring(0, 2));
        inputMin = Integer.parseInt(inputTime.substring(3, 5));
        inputSec = Integer.parseInt(inputTime.substring(6, 8));

        //checking
        if (currentHour > inputHour) {
            return false;
        } else if (currentMin > inputMin) {
            return false;
        } else if (currentSec > inputSec) {
            return false;
        } else {
            return true;
        }
    }

    public int isTimeRangeExceed(int timeslot){
        int startTime = timeslot/100;
        int endTime = timeslot%100;
        if (startTime - endTime <= 0){
            return -1; //incorrect time range
        }
        else if (startTime - endTime > 1){
            return -2; //each booking can
        }
        else {
            return 0;
        }
    }
}

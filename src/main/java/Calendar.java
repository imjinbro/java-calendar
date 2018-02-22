import io.Input;
import io.Output;
import util.CalendarFactory;
import util.MaxDate;

public class Calendar {
    public static void main(String[] args) {
        Calendar.start();
    }

    /* interface */
    public static void start(){
        int month = getMonth();

        while(!isFinishInput(month)){
            String weekDay = getWeekDay();
            printCalendar(month, weekDay);
            month = getMonth();
        }
        printMessage("Have a nice day!");
    }
    /* end interface */


    /* func */
    private static void printMaxDateInfo(int month){
        if(isInvalidMonth(month)){
            printMessage("존재하지않는 달(월) 입니다.");
            return;
        }
        String infoMessage = MaxDate.getMaxDateInfo(month);
        Output.printMessage(infoMessage);
    }

    private static void printCalendar(int month, String weekDay){
        if(isInvalidMonth(month) || isInvalidWeekDay(weekDay)){
            printMessage("존재하지않는 달(월) 또는 요일 입니다.");
            return;
        }

        int maxDate = MaxDate.getMaxDate(month);
        int defaultDay = WeekDay.valueOf(weekDay).getDefaultDate();
        String calendar = CalendarFactory.createCalendar(maxDate, defaultDay);
        printMessage(calendar);
    }
    /* end func */


    /* util */
    private static int getMonth(){
        return Input.getMonth();
    }

    private static String getWeekDay(){
        return Input.getWeekDay();
    }

    private static void printMessage(String message){
        Output.printMessage(message);
    }

    private static boolean isInvalidWeekDay(String weekDay){
        try{
            WeekDay.valueOf(weekDay);
            return false;
        }catch(IllegalArgumentException e){
            return true;
        }
    }

    private static boolean isInvalidMonth(int month){
        return month < 1 || month > 12;
    }

    private static boolean isFinishInput(int input){
        return input == -1;
    }
    /* end util */
}


enum WeekDay{
    일(0), 월(1), 화(2), 수(3), 목(4), 금(5), 토(6);

    private int defaultDate;

    WeekDay(int defaultDate){
        this.defaultDate = defaultDate;
    }

    public int getDefaultDate(){
        return defaultDate;
    }
}

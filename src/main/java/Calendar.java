import io.Input;
import io.Output;
import util.CalendarFactory;

public class Calendar {
    public static void main(String[] args) {
        Calendar.start();
    }

    /* interface */
    public static void start(){
        int year = getYear();

        while(!isFinishInput(year)){
            int month = getMonth();
            printCalendar(year, month);
            year = getYear();
        }
        printMessage("Have a nice day!");
    }
    /* end interface */


    /* func */
    private static void printCalendar(int year, int month){
        if(isInvalidYear(year)){
            printMessage("지원하지 않는 년(1900~)도 입니다.");
            return;
        }

        if(isInvalidMonth(month)){
            printMessage("올바르지 않는 월(달)입니다.");
            return;
        }
        printMessage(CalendarFactory.createCalendar(year, month));
    }
    /* end func */


    /* util */
    private static int getYear(){
        return Input.getYear();
    }

    private static int getMonth(){
        return Input.getMonth();
    }

    private static boolean isInvalidYear(int year){
        return year < 1900;
    }

    private static boolean isInvalidMonth(int month){
        return month < 1 || month > 12;
    }

    private static boolean isFinishInput(int input){
        return input == -1;
    }

    private static void printMessage(String message){
        Output.printMessage(message);
    }
    /* end util */
}

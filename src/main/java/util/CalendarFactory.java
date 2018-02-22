package util;

import java.util.Arrays;

public class CalendarFactory {
    private static String[][] calendar;

    public static String createCalendar(int year, int month){
        setCalendar(getCalendarForm());
        int startWeekDayIdx = getStartWeekDayIdx(year, month);
        int maxDate = CalendarInfo.getMonthDate(year, month);
        fillDate(maxDate, startWeekDayIdx);
        fillEmpty();

        return buildCalendarMessage(year, month, getCalendar());
    }

    private static void setCalendar(String[][] calendarForm){
        calendar = calendarForm;
    }

    private static String[][] getCalendar(){
        return calendar;
    }

    private static String[][] getCalendarForm(){
        return new String[5][7];
    }


    private static int getStartWeekDayIdx(int year, int month){
        int totalDiffDate = getTotalDiffDate(year, month);
        return (totalDiffDate + getStandardValue(Standard.WEEKDAY))%7;
    }

    private static int getTotalDiffDate(int year, int month){
        return getTotalYearDate(year) + getTotalMonthdate(year, month);
    }

    private static int getTotalYearDate(int year){
        int sum = 0;
        int STANDARD_YEAR = getStandardValue(Standard.YEAR);
        for(int i=STANDARD_YEAR; i<year; i++){
            sum += CalendarInfo.getYearDate(i);
        }
        return sum;
    }

    private static int getTotalMonthdate(int year, int month){
        int sum = 0;
        int STANDARD_MONTH = getStandardValue(Standard.MONTH);
        for(int i=STANDARD_MONTH; i<month; i++){
            sum += CalendarInfo.getMonthDate(year, i);
        }
        return sum;
    }

    private static void fillDate(int maxDate, int startWeekDayIdx){
        for(int date=1; date<=maxDate; date++){
            String[][] calendar = getCalendar();
            int row = getWeekIdx(startWeekDayIdx, date);
            int col = getWeekDayIdx(startWeekDayIdx, date);
            String dateTxt = convertRegularTxt(intToString(date));

            doFill(calendar, row, col, dateTxt);
        }
    }

    private static int getWeekIdx(int startWeekDayIdx, int targetDate){
        return (startWeekDayIdx + getDiffDate(targetDate))/7;
    }

    private static int getWeekDayIdx(int startWeekDayIdx, int targetDate){
        return (startWeekDayIdx + getDiffDate(targetDate))%7;
    }

    private static int getDiffDate(int targetDate){
        return targetDate - getStandardValue(Standard.DATE);
    }

    private static String convertRegularTxt(String date){
        char[] fillArea = getArea();
        int fillIdx = fillArea.length-1;
        int targetIdx = date.length()-1;

        for(int i=fillIdx; (i>=0 && targetIdx >= 0); i--){
            fillArea[i] = date.charAt(targetIdx--);
        }
        return String.valueOf(fillArea);
    }

    private static char[] getArea(){
        char[] area = new char[2];
        Arrays.fill(area, ' ');
        return area;
    }

    private static String intToString(int date){
        return String.valueOf(date);
    }

    private static void doFill(String[][] calendar, int row, int col, String date){
        try{
            calendar[row][col] = date;
        }catch(ArrayIndexOutOfBoundsException e){
            setCalendar(extendCalendarForm(calendar, row, col, date));
        }
    }

    private static String[][] extendCalendarForm(String[][] beforeCalendar, int row, int col, String date){
        String[][] extendCalendar = new String[6][7];
        for(int i=0; i<beforeCalendar.length; i++){
            extendCalendar[i] = beforeCalendar[i];
        }
        extendCalendar[row][col] = date;
        return extendCalendar;
    }


    private static void fillEmpty(){
        String[][] calendar = getCalendar();

        for(String[] week : calendar) {
            fillEmptryWeek(week);
        }
    }

    private static void fillEmptryWeek(String[] week){
        for(int i=0; i<week.length; i++){
            replaceEmpty(week, i, week[i]);
        }
    }

    private static void replaceEmpty(String[] week, int targetIdx, String date){
        if(isNullDate(date)){
            week[targetIdx] = String.valueOf(getArea());
        }
    }

    private static boolean isNullDate(String date){
        return date == null;
    }


    private static String buildCalendarMessage(int year, int month, String[][] calendar){
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(Format.TITLE.getFormat(), year, month))
               .append(Format.MONTH.getFormat())
               .append(Format.LINE.getFormat());

        for(String[] week : calendar){
            builder.append(String.format(Format.DATE.getFormat(), week));
        }
        return builder.toString();
    }


    private static int getStandardValue(Standard standard){
        return standard.getValue();
    }
}


enum Format{
    TITLE("     %d년 %d월\n"),
    MONTH("SU MO TU WE TH FR SA\n"),
    LINE("--------------------\n"),
    DATE("%s %s %s %s %s %s %s\n");

    private String format;

    Format(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}

enum Standard{
    YEAR(1900),
    MONTH(1),
    DATE(1),
    WEEKDAY(WeekDay.월.getWeekDayIdx());

    private int value;

    Standard(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

enum WeekDay {
    일(0), 월(1), 화(2), 수(3), 목(4), 금(5), 토(6);

    private int weekDayIdx;

    WeekDay(int weekDayIdx){
        this.weekDayIdx = weekDayIdx;
    }

    public int getWeekDayIdx(){
        return weekDayIdx;
    }
}



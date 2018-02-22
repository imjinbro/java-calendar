package util;

import java.util.Arrays;

public class CalendarFactory {
    public static void main(String[] args) {
        String[][] calendarForm = getCalendarForm();
        fillDate(calendarForm, 28, 4);
        fillEmpty(calendarForm);
        System.out.println(buildCalendar(calendarForm));
    }

    private static final int STANDARD_DATE = 1;

    public static String createCalendar(int maxDate, int defaultDate){
        String[][] calendarForm = getCalendarForm();
        fillDate(calendarForm, maxDate, defaultDate);
        fillEmpty(calendarForm);
        return buildCalendar(calendarForm);
    }

    private static String buildCalendar(String[][] calendar){
        StringBuilder builder = new StringBuilder();

        builder.append(Format.TOP.getFormat())
               .append(Format.LINE.getFormat());

        for(String[] week : calendar){
            builder.append(String.format(Format.DATE.getFormat(), week));
        }

        return builder.toString();
    }


    private static String[][] getCalendarForm(){
        return new String[5][7];
    }


    private static void fillDate(String[][] calendarForm, int maxDate, int defaultDate){
        for(int date=1; date<=maxDate; date++){
            int row = getWeekIdx(defaultDate, date);
            int col = getWeekDayIdx(defaultDate, date);

            // TODO : 추가할 때 해당 인덱스가 없다면(한달 6주인 경우) 캘린더 row 늘리기 -> 조회부터 해보고 존재하지않는다면
            calendarForm[row][col] = fillRegularTxt(intToString(date));
        }
    }

    private static int getWeekIdx(int defaultDate, int targetDate){
        return (defaultDate + getDiffDate(targetDate))/7;
    }

    private static int getWeekDayIdx(int defaultDate, int targetDate){
        return (defaultDate + getDiffDate(targetDate))%7;
    }

    private static int getDiffDate(int targetDate){
        return targetDate - STANDARD_DATE;
    }

    private static String fillRegularTxt(String date){
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



    private static void fillEmpty(String[][] calendar){
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
}


enum Format{
    TOP("SU MO TU WE TH FR SA\n"),
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


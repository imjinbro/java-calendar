package util;

public class CalendarInfo {
    public static int getYearDate(int year){
        if(isLeapYear(year)){
            return YearMode.LEAP_YEAR.getYearDate();
        }
        return YearMode.NORMAL_YEAR.getYearDate();
    }

    public static int getMonthDate(int year, int month){
        int[] maxDateList = getMaxDateList(year);
        return maxDateList[getMonthIdx(month)];
    }

    private static int[] getMaxDateList(int year){
        int[] maxDateList;
        if(isLeapYear(year)){
            maxDateList = YearMode.LEAP_YEAR.getMaxDateList();
        }
        return maxDateList = YearMode.NORMAL_YEAR.getMaxDateList();
    }

    private static int getMonthIdx(int month){
        return month-1;
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && ((year % 100 != 0) || ((year % 100 == 0) && (year % 400 == 0)));
    }
}

enum YearMode{
    LEAP_YEAR(new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, 366),
    NORMAL_YEAR(new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, 365);

    private int[] maxDateList;
    private int totalYearDate;

    YearMode(int[] maxDateList, int totalYearDate) {
        this.maxDateList = maxDateList;
        this.totalYearDate = totalYearDate;
    }

    public int[] getMaxDateList() {
        return maxDateList;
    }

    public int getYearDate() {
        return totalYearDate;
    }
}



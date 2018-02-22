package util;

public class MaxDate {
    private static int[] maxDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static String getMaxDateInfo(int month){
        int maxDate = getMaxDate(month);
        return buildInfoMessage(month, maxDate);
    }

    public static int getMaxDate(int month){
        return maxDate[getMonthIdx(month)];
    }

    private static int getMonthIdx(int month){
        return month-1;
    }

    private static String buildInfoMessage(int month, int maxDate) {
        String format = "%s월은 %s일까지 있습니다.";
        return String.format(format, month, maxDate);
    }
}

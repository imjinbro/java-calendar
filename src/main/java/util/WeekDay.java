package util;

public enum WeekDay {
    일(0), 월(1), 화(2), 수(3), 목(4), 금(5), 토(6);

    private int weekDayIdx;

    WeekDay(int weekDayIdx){
        this.weekDayIdx = weekDayIdx;
    }

    public int getWeekDayIdx(){
        return weekDayIdx;
    }
}

package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner scanner = new Scanner(System.in);

    public static int getMonth(){
        Output.printMessage("달을 입력해주세요.");
        try {
            return strToInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
    }

    private static int strToInt(String input) throws NumberFormatException{
        return Integer.parseInt(input);
    }

    public static String getWeekDay(){
        Output.printMessage("1일은 무슨 요일인가요?");
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public static void close(){
        try {
            reader.close();
        } catch (IOException e) { System.exit(1); }
    }
}

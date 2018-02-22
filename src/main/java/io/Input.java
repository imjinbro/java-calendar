package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int getYear(){
        Output.printMessage("년도를 입력하세요.");
        return getInt();
    }

    public static int getMonth(){
        Output.printMessage("달을 입력해주세요.");
        return getInt();
    }

    private static int getInt(){
        try {
            return strToInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
    }

    private static int strToInt(String input) throws NumberFormatException{
        return Integer.parseInt(input);
    }

    public static void close(){
        try {
            reader.close();
        } catch (IOException e) { System.exit(1); }
    }
}

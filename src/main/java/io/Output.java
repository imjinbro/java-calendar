package io;

public class Output {
    public static void printMessage(String message){
        if(isInvliadMessage(message)){
            return;
        }
        System.out.println(message);
    }

    private static boolean isInvliadMessage(String message){
        return message == null || message.isEmpty();
    }
}

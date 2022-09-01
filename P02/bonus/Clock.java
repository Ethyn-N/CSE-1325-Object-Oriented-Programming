package cse1325.P02.bonus;

import java.util.Scanner;

class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    public Clock (int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return twoDigit(hours) + ":" + twoDigit(minutes) + ":" + twoDigit(seconds);
    }

    private String twoDigit(int i){
        return String.format("%02d", i);
    }

    private void rationalize() {
        //If the seconds field is less than 0, add 60 seconds and then decrement minutes
        while (seconds < 0) { seconds+=60; minutes-=1; }

        //if the seconds field is greater than 59, subtract 60 seconds and then increment minutes
        while (seconds > 59) { seconds-=60; minutes+=1; }

        //If the minutes field is less than 0, add 60 minutes and then decrement hours
        while (minutes < 0) { minutes+=60; hours-=1; }

        //if the minutes field is greater than 59, subtract 60 minutes and then increment hours
        while (minutes > 59) { minutes-=60; hours+=1; }

        //If the hours field is less than 0, add 24 hours
        while (hours < 0) { hours+=24; }

        //If the hours field is greater than 23, subtract 24 hours
        while (hours > 23) { hours-=24; }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("What is the hour? : ");
        int hours = input.nextInt();

        System.out.print("What is the minute? : ");
        int minutes = input.nextInt();

        System.out.print("What is the second? : ");
        int seconds = input.nextInt();

        Clock clock = new Clock(hours, minutes, seconds);
        clock.rationalize();

        System.out.println("The time is " + clock);

        input.close();
    }
    
}
import java.util.Scanner;

class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    public Clock (int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        rationalize();
    }

    public Clock add(int seconds) {
        Clock clock = new Clock(hours, minutes, this.seconds + seconds);
        clock.rationalize();
        return clock;
    }

    public Clock add(Clock clock) {
        Clock newClock = new Clock(this.hours + clock.hours, this.minutes + clock.minutes, this.seconds + clock.seconds);
        newClock.rationalize();
        return newClock;
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

        System.out.println("The time is " + clock);

        System.out.print("\nSeconds to tic? : ");
        int ticks = input.nextInt();

        Clock newClock = new Clock(hours, minutes, seconds);

        for (int i = 0; i < ticks; i++) {
            newClock = newClock.add(1);
            System.out.println(newClock);
        }

        int addSeconds = 0;

        do {
            System.out.print("Seconds to add (0 to continue)? : ");
            addSeconds = input.nextInt();
            newClock = newClock.add(addSeconds);
            System.out.println(newClock);
        } while (addSeconds != 0);

        System.out.println("\nAddding original time with new time...");
        System.out.println(clock + " + " + newClock + " = " + newClock.add(clock));

        input.close();
    }
    
}
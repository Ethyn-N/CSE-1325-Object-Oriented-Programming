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

        input.close();
    }

}
package cse1325.P01.bonus;

import java.util.Scanner;

class Hello {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("What is your name? ");
        String name = input.nextLine();
        System.out.println("Hello, " + name + "!");
        
        input.close();
        
    }
}
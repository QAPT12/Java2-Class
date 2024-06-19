package ca.nl.cna.quintin.java2.Tests.Test01.Q01;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BicycleRegistration {

    public static void main(String[] args) {

        String regex = "[A-Z]{3}\\d{4}(D1|D2|D3|D4)_\\d{5}";
        Pattern licensePattern = Pattern.compile(regex);

        while(true){
            Scanner input = new Scanner(System.in);
            System.out.print("Enter license number: ");
            String license = input.nextLine();

            if(licensePattern.matcher(license).find()){
                System.out.println("You are registered for the race");
                break;
            } else {
                System.out.println("Invalid license, please try again");
            }
        }

    }

}

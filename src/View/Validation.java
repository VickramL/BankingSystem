package View;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static int inputInt(){
        int number = 0;
        try{
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter the option : ");
            number = input.nextInt();
            return number;
        }catch (Exception e){
            return 0;
        }
    }

    public static String isValidMobileNumber(){
        Scanner input = new Scanner(System.in);
        String mobileNumber;
        while (true) {
            System.out.print("Enter Mobile Number : ");
            mobileNumber = input.next();
            Pattern pattern = Pattern.compile("^\\d{10}$");
            Matcher matcher = pattern.matcher(mobileNumber);
            if(matcher.find())
                break;
            System.out.println("Invalid mobile Number : ");
        }
        return mobileNumber;
    }

    public static int validInt(String string){
        int number = 0;
        try{
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter the " +string+" : ");
            number = input.nextInt();
            return number;
        }catch (Exception e){
            System.out.println("Invalid Input");
            return 0;
        }
    }

    public static long validLong(String string){
        long number = 0;
        try{
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter the " +string+" : ");
            number = input.nextInt();
            return number;
        }catch (Exception e){
            System.out.println("Invalid Input");
            return 0;
        }
    }
}

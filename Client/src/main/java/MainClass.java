import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.HashMap;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        String date = readData();

        if (validation(date)) {
            System.out.println("Corect");
        }
        else {
            System.out.println("Non");
        }
    }

    public static String readData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Data: ");
        return scanner.nextLine();
    }

    public static HashMap<Integer, Integer> getMonthDays() {
        HashMap<Integer, Integer> month_days = new HashMap<>();
        month_days.put(1, 31);
        month_days.put(2, 28);
        month_days.put(3, 31);
        month_days.put(4, 30);
        month_days.put(5, 31);
        month_days.put(6, 30);
        month_days.put(7, 31);
        month_days.put(8, 31);
        month_days.put(9, 30);
        month_days.put(10, 31);
        month_days.put(11, 30);
        month_days.put(12, 31);
        return month_days;
    }

    public static boolean validation(String date) {

        if ((date.charAt(1) == '/' && date.charAt(3) == '/') ||
                (date.charAt(1) == '/' && date.charAt(4) == '/')) {

            String[] numbers = date.split("/");

            int month = Integer.parseInt(numbers[0]);
            int day = Integer.parseInt(numbers[1]);
            int year = Integer.parseInt(numbers[2]);

            if (1 > month || month > 12) {
                return false;
            }

            if (month == 2 && year % 4 == 0) {
                if (day > getMonthDays().get(month) + 1) {
                    return false;
                }
            }
            if (day > getMonthDays().get(month)) {
                return false;
            }

            if (day < 1) {
                return false;
            }

            return year >= 0;
        }

        if (date.charAt(2) == '/' && date.charAt(4) == '/') {

            String[] numbers = date.split("/");

            String monthStr = numbers[0];
            String dayStr = numbers[1];
            int year = Integer.parseInt(numbers[2]);

            int month;
            if (monthStr.charAt(0) == '0') {
                month = monthStr.charAt(1);
            }
            else {
                month = Integer.parseInt(monthStr);
            }

            int day;
            if (dayStr.charAt(0) == '0') {
                day = dayStr.charAt(1);
            }
            else {
                day = Integer.parseInt(dayStr);
            }

            if (1 > month || month > 12) {
                return false;
            }

            if (month == 2 && year % 4 == 0) {
                if (day > getMonthDays().get(month) + 1) {
                    return false;
                }
            }
            if (day > getMonthDays().get(month)) {
                return false;
            }

            if (day < 1) {
                return false;
            }

            return year >= 0;
        }

        return true;
    }
}

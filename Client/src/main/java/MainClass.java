import autumn.AutumnGrpc;
import autumn.AutumnOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import spring.SpringGrpc;
import spring.SpringOuterClass;
import summer.Summe;
import summer.SummerGrpc;
import winter.WinterGrpc;
import winter.WinterOuterClass;

import java.util.HashMap;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        while (true) {
            String date = readData();

            if (validation(date)) {
                ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 3000).usePlaintext().build();

                int month = Integer.parseInt(date.split("/")[0]);
                int day = Integer.parseInt(date.split("/")[1]);
                int year = Integer.parseInt(date.split("/")[2]);
                switch (month) {
                    case 12:
                    case 1:
                    case 2:
                        WinterGrpc.WinterBlockingStub winterBlockingStub = WinterGrpc
                                .newBlockingStub(channel);
                        WinterOuterClass.ZodiacResponse zodiacWinter =  winterBlockingStub
                                .getZodiacSign(
                                        WinterOuterClass.ZodiacRequest
                                                .newBuilder()
                                                .setMonth(Integer.toString(month))
                                                .setDay(Integer.toString(day))
                                                .setYear(Integer.toString(year)).build());
                        System.out.println(zodiacWinter.getZodiacSign());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        SpringGrpc.SpringBlockingStub springBlockingStub = SpringGrpc
                                .newBlockingStub(channel);
                        SpringOuterClass.ZodiacResponse zodiacSpring =  springBlockingStub
                                .getZodiacSign(
                                        SpringOuterClass.ZodiacRequest
                                                .newBuilder()
                                                .setMonth(Integer.toString(month))
                                                .setDay(Integer.toString(day))
                                                .setYear(Integer.toString(year)).build());
                        System.out.println(zodiacSpring.getZodiacSign());
                        break;
                    case 6:
                    case 7:
                    case 8:
                        SummerGrpc.SummerBlockingStub summerBlockingStub = SummerGrpc
                                .newBlockingStub(channel);
                        Summe.ZodiacResponse zodiacSummer = summerBlockingStub
                                .getZodiacSign(
                                        Summe.ZodiacRequest
                                                .newBuilder()
                                                .setMonth(Integer.toString(month))
                                                .setDay(Integer.toString(day))
                                                .setYear(Integer.toString(year)).build());
                        System.out.println(zodiacSummer.getZodiacSign());
                        break;
                    case 9:
                    case 10:
                    case 11:
                        AutumnGrpc.AutumnBlockingStub autumnBlockingStub = AutumnGrpc
                                .newBlockingStub(channel);
                        AutumnOuterClass.ZodiacResponse zodiacAutumn = autumnBlockingStub
                                .getZodiacSign(
                                        AutumnOuterClass.ZodiacRequest
                                                .newBuilder()
                                                .setMonth(Integer.toString(month))
                                                .setDay(Integer.toString(day))
                                                .setYear(Integer.toString(year)).build());
                        System.out.println(zodiacAutumn.getZodiacSign());
                        break;
                }

                Scanner read = new Scanner(System.in);
                System.out.println("Exit?");
                String exit = read.next();
                if (exit.equals("yes")) {
                    System.exit(0);
                }
            }
            else {
                System.out.println("Invalid data");
            }
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
        String[] dateNumbers = date.split("/");

        if (dateNumbers.length < 3) {
            return false;
        }
        int month = Integer.parseInt(dateNumbers[0]);
        int day = Integer.parseInt(dateNumbers[1]);
        int year = Integer.parseInt(dateNumbers[2]);

        if (month >= 1 && month <= 12
                && day <= getMonthDays().get(month)) {
            return true;
        }
        return month == 2
                && day <= getMonthDays().get(month) + 1
                && year % 4 == 0;
    }
}
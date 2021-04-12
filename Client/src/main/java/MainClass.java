import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Data: ");
        String date = scanner.nextLine();

        Pattern patternDate = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
        Matcher matcher = patternDate.matcher(date);
        if (matcher.find()) {
            System.out.println("Correct");
        }
        else {
            System.out.println("Incorrect");
        }
    }
}

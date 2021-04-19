package microservices;

import io.grpc.stub.StreamObserver;
import summer.Summe;
import summer.SummerGrpc;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SummerImpl extends SummerGrpc.SummerImplBase {
    @Override
    public void getZodiacSign(Summe.ZodiacRequest request, StreamObserver<Summe.ZodiacResponse> responseObserver) {
        String[][] intervals = new String[4][5];
        try (Scanner scanner = new Scanner(new FileReader("/text/Summer.txt"))) {
            int index = 0;
            while (index < 5) {
                intervals[index] = scanner.nextLine().split(" ");
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package microservices;

import io.grpc.stub.StreamObserver;
import winter.WinterGrpc;
import winter.WinterOuterClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WinterImpl extends WinterGrpc.WinterImplBase {
    @Override
    public void getZodiacSign(WinterOuterClass.ZodiacRequest request, StreamObserver<WinterOuterClass.ZodiacResponse> responseObserver) {
        String[][] intervals = new String[4][3];
        try (Scanner scanner = new Scanner(new FileReader("/text/Winter.txt"))) {
            int index = 0;
            while (index < 4) {
                intervals[index] = scanner.nextLine().split(" ");
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package microservices;

import io.grpc.stub.StreamObserver;
import spring.SpringGrpc;
import spring.SpringOuterClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SpringImpl extends SpringGrpc.SpringImplBase {
    @Override
    public void getZodiacSign(SpringOuterClass.ZodiacRequest request, StreamObserver<SpringOuterClass.ZodiacResponse> responseObserver) {
        String[][] intervals = new String[4][3];
        try (Scanner scanner = new Scanner(new FileReader("/text/Spring.txt"))) {
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

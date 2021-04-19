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
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/text/Summe.txt"))) {
            int index = 0;
            while (scanner.hasNextLine()) {
                intervals[index] = scanner.nextLine().split(" ");
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] interval : intervals) {
            if (Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) >= Integer.parseInt(interval[1])
                    && Integer.parseInt(request.getDay()) <= Integer.parseInt(interval[3])) {
                Summe.ZodiacResponse zodiacResponse = Summe.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) >= Integer.parseInt(interval[1])) {
                Summe.ZodiacResponse zodiacResponse = Summe.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) <= Integer.parseInt(interval[3])) {
                Summe.ZodiacResponse zodiacResponse = Summe.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
        }
    }
}

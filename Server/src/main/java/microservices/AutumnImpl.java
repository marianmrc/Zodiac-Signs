package microservices;

import autumn.AutumnGrpc;
import autumn.AutumnOuterClass;
import io.grpc.stub.StreamObserver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AutumnImpl extends AutumnGrpc.AutumnImplBase {
    @Override
    public void getZodiacSign(AutumnOuterClass.ZodiacRequest request, StreamObserver<AutumnOuterClass.ZodiacResponse> responseObserver) {
        String[][] intervals = new String[4][5];
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/text/Autumn.txt"))) {
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
                AutumnOuterClass.ZodiacResponse zodiacResponse = AutumnOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) >= Integer.parseInt(interval[1])) {
                AutumnOuterClass.ZodiacResponse zodiacResponse = AutumnOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) <= Integer.parseInt(interval[3])) {
                AutumnOuterClass.ZodiacResponse zodiacResponse = AutumnOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
        }
    }
}

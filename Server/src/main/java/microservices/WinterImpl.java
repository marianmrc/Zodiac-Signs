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
        String[][] intervals = new String[4][5];
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/text/Winter.txt"))) {
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
                WinterOuterClass.ZodiacResponse zodiacResponse = WinterOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) >= Integer.parseInt(interval[1])) {
                WinterOuterClass.ZodiacResponse zodiacResponse = WinterOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) <= Integer.parseInt(interval[3])) {
                WinterOuterClass.ZodiacResponse zodiacResponse = WinterOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
        }
    }
}
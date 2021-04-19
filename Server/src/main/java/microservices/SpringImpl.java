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
        String[][] intervals = new String[4][5];
        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/text/Spring.txt"))) {
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
                SpringOuterClass.ZodiacResponse zodiacResponse = SpringOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) >= Integer.parseInt(interval[1])) {
                SpringOuterClass.ZodiacResponse zodiacResponse = SpringOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
            if (Integer.parseInt(request.getMonth()) != Integer.parseInt(interval[0])
                    && Integer.parseInt(request.getMonth()) == Integer.parseInt(interval[2])
                    && Integer.parseInt(request.getDay()) <= Integer.parseInt(interval[3])) {
                SpringOuterClass.ZodiacResponse zodiacResponse = SpringOuterClass.ZodiacResponse
                        .newBuilder()
                        .setZodiacSign(interval[4])
                        .build();
                responseObserver.onNext(zodiacResponse);
                responseObserver.onCompleted();
            }
        }
    }
}

import io.grpc.Server;
import io.grpc.ServerBuilder;
import microservices.AutumnImpl;
import microservices.SpringImpl;
import microservices.SummerImpl;
import microservices.WinterImpl;

public class MainClass {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(3000)
                .addService(new AutumnImpl())
                .addService(new SpringImpl())
                .addService(new SummerImpl())
                .addService(new WinterImpl())
                .build();

        try {
            server.start();
            System.out.println("Server started at " + server.getPort());
            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
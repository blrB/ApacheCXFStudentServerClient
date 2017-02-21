package by.bsuir.aipos.cxfserver;

import by.bsuir.aipos.cxflib.StudentWebServiceImpl;
import by.bsuir.aipos.service.StudentGroupServiceImpl;
import org.apache.log4j.Logger;

import javax.xml.ws.Endpoint;
import java.util.List;
import java.util.logging.Handler;

public class StudentServer {

    private static Logger logger = Logger.getLogger(StudentServer.class);
    private static final int DEFAULT_PORT = 8080;
    private static int port = DEFAULT_PORT;

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.error("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        StudentServer studentServer = new StudentServer();
        studentServer.start();
    }

    private void start() {
        try {
            System.out.println("Starting Server");
            StudentWebServiceImpl implementor = new StudentWebServiceImpl();
            String address = "http://192.168.38.29:" + port + "/student";
            Endpoint.publish(address, implementor);
            startServer();
            logger.info("Start on port : " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }


    private void startServer(){
        logger.info("Start server\n" +
                "       _         __   __  __          _______ \n" +
                "      | |  /\\    \\ \\ / /  \\ \\        / / ____|\n" +
                "      | | /  \\    \\ V /____\\ \\  /\\  / / (___  \n" +
                "  _   | |/ /\\ \\    > <______\\ \\/  \\/ / \\___ \\ \n" +
                " | |__| / ____ \\  / . \\      \\  /\\  /  ____) |\n" +
                "  \\____/_/    \\_\\/_/ \\_\\      \\/  \\/  |_____/ \n" +
                "                                              \n" +
                "                                              ");
    }
}

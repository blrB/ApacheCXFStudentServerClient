package by.bsuir.aipos.cxfserver;

import by.bsuir.aipos.cxflib.StudentWebServiceImpl;
import org.apache.log4j.Logger;

import javax.xml.ws.Endpoint;

public class StudentServer {
    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(StudentServer.class);
    /**
     * Constant for default server's port
     */
    private static final int DEFAULT_PORT = 8080;
    /**
     * Initialize port by default value
     */
    private static int port = DEFAULT_PORT;

    /**
     * Server runner
     *
     * @param args the only sent argument is server's port
     */
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

    /**
     * Run server
     */
    private void start() {
        try {
            StudentWebServiceImpl implementor = new StudentWebServiceImpl();
            String address = "http://0.0.0.0:" + port + "/student";
            Endpoint.publish(address, implementor);
            startServer();
            logger.info("Start on port : " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns logger
     * @return logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Write amazing text in a console
     */
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

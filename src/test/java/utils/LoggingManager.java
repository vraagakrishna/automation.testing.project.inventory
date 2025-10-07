package utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LoggingManager {

    // <editor-fold desc="Class Fields / Constants">
    private static final Logger rootLogger = Logger.getLogger("");

    private static final String reportDir = System.getProperty("user.dir") + File.separator + "Reports";

    private static boolean initialized = false;
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public static Logger getLogger(String className) {
        if (!initialized)
            setupLogger();
        return Logger.getLogger(className);
    }
    // </editor-fold>

    // <editor-fold desc="Private Methods">
    private static void setupLogger() {
        try {
            // Create report directory
            File dir = new File(reportDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String logFileName = reportDir + File.separator + "logs_" + System.currentTimeMillis() + ".log";

            // Remove default console handlers
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter());

            // File handler
            FileHandler fileHandler = new FileHandler(logFileName, true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());

            // Add handlers
            rootLogger.addHandler(consoleHandler);
            rootLogger.addHandler(fileHandler);

            // Set global log level
            rootLogger.setLevel(Level.INFO);

            // Turn off noisy Selenium logs
            Logger.getLogger("org.openqa.selenium").setLevel(Level.WARNING);
            Logger.getLogger("org.apache.http").setLevel(Level.WARNING);
            Logger.getLogger("io.netty").setLevel(Level.WARNING);

            initialized = true;
            rootLogger.info("Logger initialized. Logs saved to: " + logFileName);
        } catch (IOException ex) {
            System.out.println("Failed to initialize logger: " + ex.getMessage());
        }
    }
    // </editor-fold>

}

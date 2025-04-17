/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;
import com.mysql.cj.log.Log;
import java.util.logging.*;

/**
 *
 * @author pc
 */
public class log {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Log.class.getName());

        // Remove the default console handler
        Handler consoleHandler = null;
        for (Handler handler : logger.getParent().getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                consoleHandler = handler;
                break;
            }
        }
        if (consoleHandler != null) {
            logger.getParent().removeHandler(consoleHandler);
        }

        // Add a new console handler
        consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        // Log a message
        logger.info("Hello, World!");
    }
    
}

package ePortfolio;

/**
 * Endprogram.java is a class file meant for the quit option in the commands menu
 * @author  Khushi Rajput
 * @version 3.0
 * @since   2021-11-06
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EndProgram implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

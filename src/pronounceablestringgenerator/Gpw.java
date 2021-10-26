package pronounceablestringgenerator;

/** GPW - Generate pronounceable passwords
 *  This program uses statistics on the frequency of three-letter sequences
 *  in English to generate passwords.  The statistics are
 *  generated from your dictionary by the program loadtris.
 *
 * See www.multicians.org/thvv/gpw.html for history and info.
 * @author  Tom Van Vleck
 *
 *  THVV 06/01/94 Coded
 *  THVV 04/14/96 converted to Java
 *  THVV 07/30/97 fixed for Netscape 4.0
 */

import java.awt.*;
import java.applet.*;
import java.util.Random;

public class Gpw extends Applet {
private GpwWindow GpwWindow = null;
private Button gpw_button = null;

public Gpw() { // constructor
 this.setLayout(new BorderLayout());
 this.setFont(new Font("Helvetica", Font.BOLD, 14));
 this.add("Center", gpw_button = new Button("Generate Passwords"));
} // constructor

// Invoked when "Generate" button is pushed
public boolean action(Event evt, Object arg) {
 if (evt.target == gpw_button) {
   // create a new password window every time.
   GpwWindow = new GpwWindow (this.getParameter("npw"),
				 this.getParameter("pwlength"));
   GpwWindow.show(); // show the password box.
 }
 return true;
} // action()

// Boiler plate for HotJava
public String getAppletInfo () {
 return "Generate Passwords 96-04-14 THVV";
} // getAppletInfo()
public String [][] getParameterInfo () {
 String [][] info = {
   {"pwlength", "int", "length of passwords"},
   {"npw", "int", "number of passwords"}
 };
 return info;
} // getParameterInfo()
// Main program for testing only.
public static void main(String args[]) {
 Frame f = new Frame("Gpw");
 Gpw gpw = new Gpw();
 gpw.init();
 gpw.start();
 f.add("Center", gpw);
 f.resize(600, 300);
 f.show();
} // main()
} // Gpw

//================================================================
//Box showing the generated passwords






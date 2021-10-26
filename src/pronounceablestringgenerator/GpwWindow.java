package pronounceablestringgenerator;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.Random;

class GpwWindow extends Frame {
protected Button ok_button = null;
protected Panel pw_panel_north = null;
int npw = 10;
int pwl = 8;
static GpwData data = null;

GpwWindow (String bvnpw, String bvpwl) { // constructor
 if (data == null) {
   data = new GpwData();
 }
 try {npw = Integer.parseInt(bvnpw, 10);}
 catch (NumberFormatException ew) {npw = 10;}
 try {pwl = Integer.parseInt(bvpwl, 10);}
 catch (NumberFormatException eh) {pwl = 8;}
 // set up a window with a text box and an OK button
 this.setFont(new Font("Courier", Font.PLAIN, 14));
 this.setLayout(new BorderLayout(5, 5));

 pw_panel_north = new Panel();
 pw_panel_north.setLayout(new GridLayout(0, 1));
 this.generate(pw_panel_north, npw, pwl);
 this.add("North", pw_panel_north);

 Panel pw_panel_south = new Panel();
 pw_panel_south.setLayout(new FlowLayout(FlowLayout.CENTER));
 pw_panel_south.add(ok_button = new Button("OK"));
 ok_button.setFont(new Font("Helvetica", Font.BOLD, 14));
 this.add("South", pw_panel_south);
 this.pack();
} // constructor

public boolean action(Event e, Object arg) {
 if (e.target == ok_button) {
   this.hide();
   this.dispose();
   return true;
 }
 return false;
} // action()

public boolean gotFocus(Event e, Object arg) {
 ok_button.requestFocus();
 return true;
} // gotFocus()

final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

private void generate (Panel pan, int npw, int pwl) {
 int c1, c2, c3;
 long sum = 0;
 int nchar;
 long ranno;
 int pwnum;
 double pik;
 StringBuffer password;
 Random ran = new Random(); // new random source seeded by clock

 // Pick a random starting point.
 for (pwnum=0; pwnum < npw; pwnum++) {
   password = new StringBuffer(pwl);
   pik = ran.nextDouble(); // random number [0,1]
   ranno = (long)(pik * data.getSigma()); // weight by sum of frequencies
   sum = 0;
   for (c1=0; c1 < 26; c1++) {
	for (c2=0; c2 < 26; c2++) {
	  for (c3=0; c3 < 26; c3++) {
	    sum += data.get(c1, c2, c3);
	    if (sum > ranno) {
	      password.append(alphabet.charAt(c1));
	      password.append(alphabet.charAt(c2));
	      password.append(alphabet.charAt(c3));
	      c1 = 26; // Found start. Break all 3 loops.
	      c2 = 26;
	      c3 = 26;
	    } // if sum
	  } // for c3
	} // for c2
   } // for c1

   // Now do a random walk.
   nchar = 3;
   while (nchar < pwl) {
	c1 = alphabet.indexOf(password.charAt(nchar-2));
	c2 = alphabet.indexOf(password.charAt(nchar-1));
	sum = 0;
	for (c3=0; c3 < 26; c3++)
	  sum += data.get(c1, c2, c3);
	if (sum == 0) {
	  break;	// exit while loop
	}
	pik = ran.nextDouble();
	ranno = (long)(pik * sum);
	sum = 0;
	for (c3=0; c3 < 26; c3++) {
	  sum += data.get(c1, c2, c3);
	  if (sum > ranno) {
	    password.append(alphabet.charAt(c3));
	    c3 = 26; // break for loop
	  } // if sum
	} // for c3
	nchar ++;
   } // while nchar
   pan.add(new Label(password.toString())); // Password generated
 } // for pwnum
} // generate()
} // GpwWindow

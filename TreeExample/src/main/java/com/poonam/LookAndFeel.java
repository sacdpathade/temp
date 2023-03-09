package com.poonam;
import java.applet.Applet;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;



public class LookAndFeel {

    
    public static void setLookAndFeel(JFrame frame, int lookAndFeelCount) {
        lookAndFeelCount++;
        lookAndFeelCount = (short) (lookAndFeelCount % 3);
        switch(lookAndFeelCount) {
            case 0:
                LookAndFeel.setNativeLookAndFeel(frame);
                break;
            case 1:
                LookAndFeel.setJavaLookAndFeel(frame);
                break;
            default:
                LookAndFeel.setMotifLookAndFeel(frame);
        }
    }
    
    
    
  private static void setNativeLookAndFeel(Frame frame) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch(Exception e) {
      System.out.println("Error setting native LAF: " + e);
    }
    SwingUtilities.updateComponentTreeUI(frame);

  }

  private static void setJavaLookAndFeel(Frame frame) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch(Exception e) {
      System.out.println("Error setting Java LAF: " + e);
    }
    SwingUtilities.updateComponentTreeUI(frame);
  }

  private static void setMotifLookAndFeel(Frame frame) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    } catch(Exception e) {
      System.out.println("Error setting Motif LAF: " + e);
    }
    SwingUtilities.updateComponentTreeUI(frame);
  }
   
  private static void setGTKLookAndFeel(Frame frame) {
	    try {
	      UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
	    } catch(Exception e) {
	        System.out.println("Error setting GTK LAF: " + e);
	      }
	      SwingUtilities.updateComponentTreeUI(frame);
	    }

}

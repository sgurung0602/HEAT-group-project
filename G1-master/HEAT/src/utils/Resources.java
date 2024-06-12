/**
 *
 * Copyright (c) 2005 University of Kent
 * Computing Laboratory, Canterbury, Kent, CT2 7NP, U.K
 *
 * This software is the confidential and proprietary information of the
 * Computing Laboratory of the University of Kent ("Confidential Information").
 * You shall not disclose such confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * the University.
 *
 * @author Dean Ashton
 *
 */

package utils;

import java.util.logging.Logger;


import javax.swing.ImageIcon;


public class Resources {
  private static Logger log = Logger.getLogger("heat");

  /**
   * Used to create an ImageIcon from a local graphic
   * file. Note that this method will automatically add a .png file extension
   * @param iconName the basename of the file
   * @return the created ImageIcon
   */
  public static ImageIcon getIcon(String iconName) {
    if (iconName.equals(""))
      return null;
    String imgLocation = "icons/" + iconName + ".png";
    java.net.URL imageURL = Resources.class.getClassLoader().getResource(imgLocation);
    if (imageURL == null) {
      log.warning("[Resources] - Resource not found:" + imgLocation);
      return null;
    } else
      return new ImageIcon(imageURL);
  }
  
  /**
   * Used to create an ImageIcon from a local graphic
   * file. Note that this method will automatically add a .png file extension
   * @param iconName the basename of the file, scale for how large the icon should be
   * @return the created ImageIcon
   */
  public static ImageIcon getIconLarge(String iconName, int scale) {
    if (iconName.equals(""))
      return null;
    String imgLocation = "iconsLarge/" + iconName + ".png";
    java.net.URL imageURL = Resources.class.getClassLoader().getResource(imgLocation);
    if (imageURL == null) {
      log.warning("[Resources] - Resource not found:" + imgLocation);
      return null;
    } else {
    	ImageIcon icon = new ImageIcon(imageURL);
    	return new ImageIcon(imageURL);
    }
  }
	
	/**
	 * Scale the given ImageIcon based on the provided scaling factor
	 *
	 * @param icon  the ImageIcon to be scaled
	 * @param scale the scaling factor
	 * @return the scaled ImageIcon
	 */
	private static ImageIcon scaleIcon(ImageIcon icon, int scale) {
	    if (scale <= 0) {
	        log.warning("[Resources] - Invalid scale factor:" + scale);
	        return icon;
	    }
	    int newWidth = (int) (icon.getIconWidth() * scale / 100.0);
	    int newHeight = (int) (icon.getIconHeight() * scale / 100.0);
	    return new ImageIcon(icon.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH));
	}

  
  /**
   * Return the location of the help files location
   * @param filebaseName The name of the file (minus extension)
   * @return The path to the file
   */
  public static String getHelpFilePath(String filebaseName) {
    if (filebaseName.equals(""))
      return null;
    String fileLocation = "html/" + filebaseName + ".html";
    java.net.URL fileURL = Resources.class.getClassLoader().getResource(fileLocation);
    if (fileURL == null) {
      log.warning("[Resources] - Help not found:" + fileURL);
      return null;
    } else
      return fileURL.toString();
  }

}

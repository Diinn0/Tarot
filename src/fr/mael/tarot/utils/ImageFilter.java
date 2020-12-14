package fr.mael.tarot.utils;
 
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
 
/**
 * Filter for images
 * @author Mael
 *
 */
public class ImageFilter extends FileFilter {
 
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
 
        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.gif) ||
                extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg) ||
                extension.equals(Utils.png)) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
 
    public String getDescription() {
        return "Images(.jpeg, .jpg, .png, .gif)";
    }
}
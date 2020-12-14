package fr.mael.tarot.utils;
 
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
 
public class JsonFilter extends FileFilter {
 
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
 
        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.json)) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
 
    public String getDescription() {
        return "Json(.json)";
    }
}
package gui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ColorSetup {
    private static final String COLOR_FILE_NAME = "Color.properties";
    private Properties colorProperties = new Properties();


    public String getColor(String property) throws IOException{
        InputStream input = ColorSetup.class.getClassLoader().getResourceAsStream(COLOR_FILE_NAME);
        if(input!=null){
            colorProperties.load(input);
        }else{
            throw new FileNotFoundException(COLOR_FILE_NAME);
        }
       String color =  colorProperties.getProperty(property);
       input.close();
       System.out.println(" Color: " + color);
       return color;
    }
    
    public void updateColor(String key, String value){
        colorProperties.setProperty(key, value);
    }
}

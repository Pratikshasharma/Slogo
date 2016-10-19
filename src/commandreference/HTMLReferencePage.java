package commandreference;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class HTMLReferencePage {

	public static void getPage(){
		//		try {
		/* The following gets the HTML content
		 * 
		 */
		//			String a = "http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php";
		//			URL url = new URL(a);
		//			URLConnection conn = url.openConnection();
		//
		//			// open the stream and put it into BufferedReader
		//			BufferedReader br = new BufferedReader(
		//					new InputStreamReader(conn.getInputStream()));
		//
		//			String inputLine;
		//			while ((inputLine = br.readLine()) != null) {
		//				System.out.println(inputLine);
		//			}
		//			br.close();
		
		/*
		 * This part opens a particular URL in the computer's default browser
		 */
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		getPage();
	}
}

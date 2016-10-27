package commandreference;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class HTMLReferencePage {

	public void getPage(){
		//		try {
		/* The following gets the HTML content
		 * 
		 */
		File file;
		try {
			file = new File("src/commandreference/commandReference.html");
			StringBuilder html = new StringBuilder();
			Scanner in = new Scanner(file);
			while(in.hasNextLine()){
				html.append(in.nextLine());
			}
			in.close();
			Files.write(file.toPath(), html.toString().getBytes());
		    Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		//			String a = "http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands2_J2W.php";
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
		//		}

		/*
		 * This part opens a particular URL in the computer's default browser
		 */
		//			Desktop d = Desktop.getDesktop();
		//			try {
		//				d.browse(new URI("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php"));
		//		catch (URISyntaxException e) {
		//			e.printStackTrace();
		//		} 
		//		catch (MalformedURLException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
	}
}

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by re258m on 11/27/2017.
 */
public class RunAllServices {

    public static void main(String[] args) throws IOException {


        String url = "http://egjenkins-he.dtveng.net/login?from=%2F";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        // con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        //Write the response to file
        BufferedWriter output = null;
        try {
            File file = new File("C:\\Users\\re258m\\Documents\\RunAllServicesResults.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write("Starting the test: " + timeStamp);
            output.write(response.toString());
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }
}

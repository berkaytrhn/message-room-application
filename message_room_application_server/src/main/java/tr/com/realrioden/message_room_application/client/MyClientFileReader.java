package tr.com.realrioden.message_room_application.client;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyClientFileReader {
    public static String fileReader(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String content = "";
        String line = "";
        while( (line = reader.readLine()) !=null ){
            content += line+"\n";
        }
        reader.close();
        return content;
    }
}

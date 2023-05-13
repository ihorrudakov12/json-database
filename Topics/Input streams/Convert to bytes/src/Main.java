import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        int charAsNumber = inputStream.read();
        while(charAsNumber != -1) {
            System.out.print(charAsNumber);
            charAsNumber = inputStream.read();
        }
        inputStream.close();
    }
}
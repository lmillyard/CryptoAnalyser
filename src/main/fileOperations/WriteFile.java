package main.fileOperations;

import java.io.*;
import java.nio.charset.StandardCharsets;
//writes and saves a file.

public class WriteFile {

    public void writeToFile(String fileName, String lines){
        File file = new File(fileName);
        String content = lines;

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(content);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

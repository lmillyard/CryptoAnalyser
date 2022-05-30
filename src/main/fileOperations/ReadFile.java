package main.fileOperations;

import java.io.*;
//Reads a given file.
public class ReadFile {

    String fileName;

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public String readFile() {
        String result = null;

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            result = sb.toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}

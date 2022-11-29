import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        String [] words;
        int wordCount = 0;
        int lineCount = 0;
        int charCount = 0;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                while (reader.ready())
                {
                    rec = reader.readLine(); //reads the line
                    lines.add(rec);
                    lineCount++;

                    charCount += rec.length();

                    words = rec.split(" ");

                    wordCount += words.length;

                    System.out.printf("\nLine %4d %-60s ", lineCount, rec);
                }
//                for (String l : lines)
//                {
//                    System.out.println(l);
//                }
//
//                String fields[] = lines.split(" ");
//                for (String f : fields) {
//                    System.out.println(f);
//                }
                reader.close(); // must close the file to seal it and flush buffer

                System.out.println("\n\nName of file chosen: " + selectedFile.getName());
                System.out.println("Number of lines in file: " + lineCount);
                System.out.println("Number of words in file: " + wordCount);
                System.out.println("Number of characters in file: " + charCount);

                System.out.println("\n\nData file read!");
            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }// end of try
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
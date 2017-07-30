// --------------------------------------------------------------------------
// FileReverse.java
// Matthew Tan
// mxtan
// lab2
// CMPS 12B/M
// --------------------------------------------------------------------------

import java.io.*;
import java.util.*;

public class FileReverse
{
    public static String stringReverse(String s, int n)
    {
        if (n == 1)
        {
            return s;
        }
        else
        {
            s = s.substring(1, n) + s.charAt(0) + s.substring(n);
            return stringReverse(s, n - 1);
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner scan = null;
        PrintWriter write = null;
        String line = null;
        String[] tokenizer = null;
        int i, n, lineNumber = 0;
        
        if (args.length < 2)
        {
            System.out.println("Usage: FileReverse <input file> <output file>");
            System.exit(1);
        }
        
        scan = new Scanner(new File(args[0]));
        write = new PrintWriter(new FileWriter(args[1]));

        while (scan.hasNextLine())
        {
            lineNumber++;
            line = scan.nextLine().trim() + " ";
            tokenizer = line.split("\\s+");
            n = tokenizer.length;
            for (i = 0; i < n; i++)
            {
                write.println(stringReverse(tokenizer[i], tokenizer[i].length()));
            }
        }

        scan.close();
        write.close();
    }
}

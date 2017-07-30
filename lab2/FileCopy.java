//-----------------------------------------------------------------------------
// FileCopy.java
// Illustrates file IO
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class FileCopy
{
    public static void main(String[] args) throws IOException
    {
      
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        int n;

        // check number of command line arguments is at least 2
        if (args.length < 2)
        {
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }

        // open files
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));

        // read lines from in, write lines to out
        while (in.hasNextLine())
        {
            line = in.nextLine();
            out.println(line);
        }

        // close files
        in.close();
        out.close();
    }
}

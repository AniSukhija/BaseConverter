import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author 23sukhija
 * @version 12/15/22
 * Base converter that takes a number and switches its base
 */

public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in frombase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
      */
    public int strToInt(String num, String fromBase) {
        // FF3A base 16 == ??
        int value = 0, exp = 0, base = Integer.parseInt(fromBase);
        for(int i = num.length()-1; i >= 0; i--)    {
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }

        // A == 10 * 16^0 ==> 10
        // 3 == 3 * 16^1 ==> 48 + 10 == 58
        // F == 15 * 16^2 ==> 3840 == 3898
        // F == 15 * 16^3 == 61440 == 65338
        return value;
    }

    /**
     * Convert a base-10 int to a String number of base toBase.
     * @param num the original number
     * @param toBase the base you want to convert to
     * @return the converted number or 0
     */
    public String intToStr(int num, int toBase) {
        String toNum = new String();
        int index = -1;
        while(num>0) {
            toNum = DIGITS.charAt(num % toBase) + toNum;
            num /= toBase;
        }
        return (toNum.equals("")) ? "0" : toNum;
    }


    // Opens the file stream, inputs data one line at a time, converts, prints
    // the result to the console window and writes data to the output stream.

    /**
     * Takes the inputted number and converts it to a number with the new base
     * Prints the new number and writes it to a file
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter pw = null;
        try {
            in = new Scanner(new File("datafiles/values30.dat"));
            pw = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line;
            while(in.hasNext()) {
                line = in.nextLine().split("\t");
                // to write to the file:
                // write the original number -STRING
                // tab
                // write the original base -STRING
                // tab
                // write the converted number
                // tab
                // write the toBase -STRING
                if(Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                    System.out.println("Invalid input base " + line[1]);
                else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid input base " + line[2]);
                else{
                    pw.println(line[0] + "\t" + line[1] + "\t" +
                           intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) +
                            "\t" + line[2]);
                    System.out.println(line[0] + " base " + line[1] + " = " +
                            intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) +
                            " base " + line[2]);
                   // System.out.println("DEBUG: " + strToInt(line[0], line[1]));
                }

                //for(String item : line)
               // System.out.println(line[0]);
                    // System.out.println(line[1]);
               // System.out.println(line[2]);
            }
            if(pw != null)
                pw.close();
            if(in != null)
                in.close();
            //System.out.println("The revolution will not be televised.");

        }
        catch(Exception e)  {
            System.out.println("There was an error, see here: " + e.toString());
        }


    }

    /**
     * Main method for class BaseConverter.
     * @param args command line arguments, if needed
     */
    public static void main(String[] args) {
        BaseConverter app = new BaseConverter();
        app.inputConvertPrintWrite();
    }
}

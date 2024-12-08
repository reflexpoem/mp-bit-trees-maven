package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BrailleAsciiTables;
import java.io.PrintWriter;

/**
 * A program to convert between ASCII, Braille, and Unicode.
 *
 * <p>Needs two inputs: 1. The type of conversion: "ascii", "braille", or "unicode". 2. The string
 * to convert.
 *
 * <p>Examples: - `ascii 100000` converts Braille bits to ASCII. - `braille Hello` converts ASCII to
 * Braille. - `unicode A` converts ASCII to Unicode.
 */
public class BrailleASCII {

  /**
   * Main method to handle the input and process conversions.
   *
   * @param parameters Command line arguments.
   */
  public static void main(String[] parameters) {
    // Check if the right number of arguments is given.
    if (parameters.length != 2) {
      System.err.println("Error: Need two inputs (type and string).");
      System.err.println("Example: braille A");
      return;
    } // if

    // Get the inputs.
    String translationType = parameters[0].toLowerCase();
    String inputString = parameters[1];
    String brailleBits;
    PrintWriter output = new PrintWriter(System.out, true);

    // Do the right conversion based on the first input.
    switch (translationType) {
      case "ascii":
        // Convert Braille to ASCII.
        brailleBits = inputString;
        if (brailleBits.length() % 6 != 0) {
          System.err.println("Error: Braille bits must be a multiple of 6.");
        } else {
          try {
            translateBrailleToAscii(brailleBits, output);
          } catch (Exception exception) {
            output.println();
            System.err.println("Error: " + exception.getMessage());
          } // try-catch
        } // if-else
        break;

      case "braille":
        // Convert ASCII to Braille.
        try {
          translateAsciiToBraille(inputString, output);
        } catch (Exception exception) {
          output.println();
          System.err.println("Error: " + exception.getMessage());
        } // try-catch
        break;

      case "unicode":
        // Convert ASCII to Unicode using Braille as a step.
        try {
          translateAsciiToUnicode(inputString, output);
        } catch (Exception exception) {
          output.println();
          System.err.println("Error: " + exception.getMessage());
        } // try-catch
        break;

      default:
        System.err.println("Error: Type must be 'ascii', 'braille', or 'unicode'.");
        break;
    } // switch

    // Close the writer.
    output.close();
  } // main(String[])

  /**
   * Convert Braille bits to ASCII and print the result.
   *
   * @param brailleBits The Braille bits to convert.
   * @param output Where to print the result.
   */
  private static void translateBrailleToAscii(String brailleBits, PrintWriter output) {
    while (!brailleBits.isEmpty()) {
      String segment = brailleBits.substring(0, 6);
      output.print(BrailleAsciiTables.convertToAscii(segment));
      brailleBits = brailleBits.substring(6);
    } // while
    output.println();
  } // translateBrailleToAscii(String, PrintWriter)

  /**
   * Convert ASCII to Braille and print the result.
   *
   * @param inputString The ASCII text to convert.
   * @param output Where to print the result.
   */
  private static void translateAsciiToBraille(String inputString, PrintWriter output) {
    for (char character : inputString.toCharArray()) {
      output.print(BrailleAsciiTables.convertToBraille(character));
    } // for
    output.println();
  } // translateAsciiToBraille(String, PrintWriter)

  /**
   * Convert ASCII to Unicode using Braille and print the result.
   *
   * @param inputString The ASCII text to convert.
   * @param output Where to print the result.
   */
  private static void translateAsciiToUnicode(String inputString, PrintWriter output) {
    for (char character : inputString.toCharArray()) {
      String brailleBits = BrailleAsciiTables.convertToBraille(character);
      String unicodeCharacter = BrailleAsciiTables.convertToUnicode(brailleBits);
      output.print(unicodeCharacter);
    } // for
    output.println();
  } // translateAsciiToUnicode(String, PrintWriter)
} // class BrailleASCII

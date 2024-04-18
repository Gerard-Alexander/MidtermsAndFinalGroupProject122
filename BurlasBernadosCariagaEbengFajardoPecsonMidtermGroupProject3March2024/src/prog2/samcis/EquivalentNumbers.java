/**
 Name/s of programmer:
 Class Code and Schedule:
 Date:
 */
package prog2.samcis;
/**
 *A template for an object representing a whole number with its decimal, binary, octal and hexadecimal form.
 **/
public class EquivalentNumbers implements NumberConverter {
    private double decimalNumber; // decimal number form
    private String hexadecimalString; // hexadecimal number form
    private String binaryString; // binary number form
    private String octalString; // octal number form
    /**
     *Constructs an object representing the number 0
     **/
    public EquivalentNumbers(){
        decimalNumber=0;
        hexadecimalString="0";
        binaryString="0";
        octalString="0";
    }

    /**
     *Constructs an object representing a q whole number a
     **/
    public EquivalentNumbers(int a, String b, String c, String d){
        decimalNumber=a;
        hexadecimalString=b;
        binaryString=c;
        octalString=d;
    }
    /**
     *Returns the decimal number form of an object of EquivalentNumbers
     **/
    public double getDecimalNumber(){
        return decimalNumber;
    }

    /**
     *Returns the hexadecimal number form of an object of EquivalentNumbers
     **/
    public String getHexadecimalString(){
        return hexadecimalString;
    }
    /**
     *Returns the binary number form of an object of EquivalentNumbers
     **/
    public String getBinaryString(){
        return binaryString;
    }
    /**
     *Returns the octal number form of an object of EquivalentNumbers
     **/
    public String getOctalString(){
        return octalString;
    }

    /**
     *Sets the hexadecimal number string to n, then the decimal number, binary number and octal number
     *are set based on the hexadecimal number.
     */
    public void setHexadecimalString(String n){
        hexadecimalString = n;
        decimalNumber = hexadecimalToDecimal(hexadecimalString);
/* 2. Assign the decimal string, binary string, and octal string that is equivalent to the hexadecimal string n
by invoking/calling the methods that you must have created */
        decimalNumber =hexadecimalToDecimal(hexadecimalString);
        binaryString = hexadecimalToBinary(hexadecimalString);
        octalString = hexadecimalToOctal(Integer.parseInt(hexadecimalString));

    }

    /**
     *Sets the octal number string to n, then the decimal number, binary number and hexadecimal number
     *are set based on the octal number.
     */
    public void setOctalString(String n){
        octalString = n;
/* 3. Assign the decimal string, binary string, and hexadecimal string that is equivalent to the octal string n
by invoking/calling the methods that you must have created */
        decimalNumber = octalToDecimal(octalString);
        binaryString = octalToBinary(octalString);
        hexadecimalString = octalToHexadecimal(Integer.parseInt(octalString));
    }

    /**
     *Sets the binary number string to n, then the decimal number, octal number and hexadecimal number
     *are set based on the binary number.
     */
    public void setBinaryString(String b) throws Exception{
        binaryString = b;
/* 4. Assign the decimal string, octal string, and hexadecimal string that is equivalent to the binary string b
by invoking/calling the methods that you must have created */
        decimalNumber = binaryToDecimal(binaryString);
        octalString = binaryToOctal(binaryString);
        hexadecimalString = binaryToHexadecimal(binaryString);
    }

    /**
     *Sets the decimal number to d, then the hexadecimal number, binary number and octal number
     *are set based on the decimal number.
     */
    public void setDecimalNumber(double d){
        decimalNumber = d;
/* 5. Assign the decimal string, octal string, and hexadecimal string that is equivalent to the binary string b
by invoking/calling the methods that you must have created */
        binaryString = decimalToBinary(decimalNumber);
        octalString = decimalToOctal(decimalNumber);
        hexadecimalString = decimalToHexadecimal(decimalNumber);
    }
    /**
     *Returns the hexadecimal digit corresponding to a given number in the range 0 to 15
     **/
    public char hexDigit(byte digit){
        char r='0';
        switch (digit){
            case 0: r = '0'; break;
            case 1: r = '1'; break;
            case 2: r = '2'; break;
            case 3: r = '3'; break;
            case 4: r = '4'; break;
            case 5: r = '5'; break;
            case 6: r = '6'; break;
            case 7: r = '7'; break;
            case 8: r = '8'; break;
            case 9: r = '9'; break;
            case 10: r = 'A'; break;

            case 11: r = 'B'; break;
            case 12: r = 'C'; break;
            case 13: r = 'D'; break;
            case 14: r = 'E'; break;
            case 15: r = 'F'; break;
        }
        return r;
    }

    /**
     *Returns the decimal number corresponding to a given binary number
     **/
    /**
     * This method converts binary to decimal
     *
     *
     * @param binary The Binary converted to decimal
     * @return the decimal equivalent of the binary
     * @author Jeoffrie Ross Burlas
     */
    public double binaryToDecimal(String binary) {
        String[] parts = binary.split("\\."); // Split the binary number into whole and fractional parts

        // Convert the whole number part to decimal
        int wholeNumber = Integer.parseInt(parts[0], 2); // Convert whole number from binary to decimal

        // Convert the fractional part to decimal
        double fractionalPart = 0.0;
        if (parts.length > 1) {
            String fractionPartBinary = parts[1];
            for (int i = 0; i < fractionPartBinary.length(); i++) {
                fractionalPart += Double.parseDouble(String.valueOf(fractionPartBinary.charAt(i))) * Math.pow(2, -i - 1);
            }
        }

        return wholeNumber + fractionalPart;
    }//end of  binaryToDecimal method

    /**
     * This method converts a decimal number to its binary representation.
     *
     * @param decimal Decimal number to convert to binary
     * @return The binary equivalent of the decimal number
     * @author Jeoffrie Ross Burlas
     */
    public static String decimalToBinary(double decimal) {
        int wholeNumber = (int) decimal;
        double fractionPart = decimal - wholeNumber;

        StringBuilder binaryRepresentation = new StringBuilder();

        // Convert whole number part to binary
        while (wholeNumber != 0) {
            binaryRepresentation.insert(0, wholeNumber % 2); // Insert binary digit at the beginning
            wholeNumber /= 2; // Update whole number
        }

        // Check if fractional part is not zero
        if (fractionPart != 0) {
            binaryRepresentation.append(".");
            int decimalPlaces = 0;
            while (fractionPart != 0 && decimalPlaces < 8) { // Considering up to 8 decimal places for fractional part
                fractionPart *= 2;
                binaryRepresentation.append((int) fractionPart); // Append the binary digit
                fractionPart %= 1;
                decimalPlaces++;
            }
        }

        return binaryRepresentation.toString();
    }//end of decimalToBinary method

    /**
     *Returns the decimal number corresponding to a given hexadecimal number
     **/
    /**
     * This method converts hexadecimal to decimal
     *
     * @param hexadecimal The hexadecimal to be converted
     * @author Gerard Bernados
     */
    public double hexadecimalToDecimal(String hexadecimal) {

        //initialize the length of digits assuming that it has no fractional part
        int length = hexadecimal.length();
        int wholeNumber = 0;
        double fractionPart = 0;

        // to execute specific statements if the actual parameter has a fractional part
        boolean isFractional = false;

        //to determine where to split the whole number and fraction part
        int decimalPosition = hexadecimal.indexOf('.');
        // when the index of '.' is not to -1, then it has a fractional part.
        if (decimalPosition != -1) {
            isFractional = true;
            length = decimalPosition; // declaring the length of whole number part only
        }

             /*
            to convert decimal to hexadecimal, we must multiply the whole number part
            by 16 to the increasing power from 1 and so on.
             */

        for (int i = length - 1; i >= 0; i--) {
            char c = hexadecimal.charAt(i);
            int digitValue;
            if (Character.isDigit(c)) { //this method determines of that character in that index is a digit or not
                digitValue = c - '0'; // allows the digit to be a string
            } else {
                // in here, we minus it to a character A, which is in ASCII value is 65
                // we add it to 10 to represent the Hexadecimal letters (i.e A = 10, B = 11, and so on)
                digitValue = Character.toUpperCase(c) - 'A' + 10;
            }
            wholeNumber += digitValue * Math.pow(16, length - 1 - i); // this will start from the power of 0
        }

             /*
            to convert the fractional part of decimal to hexadecimal, we must multiply the whole number part
            by 16 to the decreasing power from -1 and so on.
             */

        if (isFractional) {
            for (int i = decimalPosition + 1; i < hexadecimal.length(); i++) {
                char c = hexadecimal.charAt(i);
                int digitValue;
                if (Character.isDigit(c)) {
                    digitValue = c - '0';
                } else {
                    digitValue = Character.toUpperCase(c) - 'A' + 10;
                }
                fractionPart += digitValue * Math.pow(16, decimalPosition - i); //this will start from the power of -1
            }
        }

        return wholeNumber + fractionPart;
    }//end of hexadecimalToDecimal method


    /**
     * This method converts decimal to hexadeimal.
     * Since we utilized arrays in this method, it is better to have the return type void
     * to easily print it already inside the method.
     *
     * @param decimal The decimal to be converted
     * @return
     * @author Gerard Bernados
     */

    public static String decimalToHexadecimal(double decimal) {
        int wholeNumber = (int) decimal;
        double fractionalPart = decimal - wholeNumber;

        // Converts the whole number part to hexadecimal
        String wholeNumberHexadecimal = Integer.toHexString(wholeNumber);

        // Converts the fractional part to hexadecimal
        StringBuilder fractionalHexadecimal = new StringBuilder(".");
        while (fractionalPart != 0 && fractionalHexadecimal.length() < 10) { // Limit fractional part to 8 hexadecimal digits
            fractionalPart *= 16; // Multiply by 16 for hexadecimal conversion
            int digit = (int) fractionalPart; // Extract the integer part as the hexadecimal digit
            fractionalHexadecimal.append(Integer.toHexString(digit)); // Append hexadecimal digit
            fractionalPart -= digit; // Update fractional part
        }

        String hexadecimalString = wholeNumberHexadecimal + fractionalHexadecimal.toString().toUpperCase(); // Combine whole and fractional parts

        System.out.println(hexadecimalString); // Print the hexadecimal string
        return hexadecimalString; // Return the hexadecimal string
    }






    /**
     * Converts a decimal number to its octal representation.
     *
     * @param d The decimal number to be converted.
     * @author Mike Fajardo
     */
    public static String decimalToOctal(double d) {
        /*
        Splits the given decimal into its whole number and
        fractional parts.

        The given decimal, when negative, can lead to incorrect
        results to be incorrect if not handled. For instance,
        the decimal number d is equal to -12.3. In this case:

            decWholeNumPart would be -12
            decFractionalPart would be -12.3 - (-12) = -0.3

        As a result, neither part would be converted to their
        octal representations because the while loops
        require the parts to be positive.

        To fix this, the absolute value of d is used. So:

            decWholeNumPart becomes 12
            decFractionalPart becomes 12.3 - 12 = 0.3

        This way, both parts can be correctly converted to their
        octal representations.
         */
        long decWholeNumPart = Math.abs((long) d);
        double decFractionalPart = Math.abs(d) - decWholeNumPart;

        /*
        Converts the whole number part of the given decimal to
        the whole number part of its octal representation.
         */
        StringBuilder octWholeNumPart = new StringBuilder();
        while (decWholeNumPart > 0) {
            int remainder = (int) (decWholeNumPart % 8);
            decWholeNumPart /= 8;
            octWholeNumPart.append(remainder);
        }
        octWholeNumPart.reverse();

        /*
        Converts the fractional part of the given decimal to the
        fractional part of its octal representation.
         */
        StringBuilder octFractionalPart = new StringBuilder(".");
        while (decFractionalPart > 0) {
            decFractionalPart *= 8;
            int digit = (int) decFractionalPart;
            decFractionalPart -= digit;
            octFractionalPart.append(digit);
        }

        /*
        Combines the whole number and fractional parts of the octal
        representation. If the given decimal does not have a
        fractional part, its octal representation will only include
        a whole number and no decimal point or any digits after it.
         */
        String octalString = (d % 1 == 0) ? "" + octWholeNumPart :
                "" + octWholeNumPart + octFractionalPart;

        /*
        If the given decimal is negative, its octal representation
        is returned with a negative sign. Otherwise, the octal
        representation is returned as a positive number.
         */
        return (d < 0) ? "-" + octalString : octalString;
    }//end of decimalToOctal method

    /**
     *Returns the decimal number corresponding to a given octal number
     **/
    /**
     * Converts an octal number to its decimal representation.
     *
     * @param o The octal number to be converted.
     * @author Mike Fajardo
     */
    public double octalToDecimal(String o) throws NumberFormatException {
        /*
        Checks if each character of the given string, ignoring its
        sign and/or decimal point if present, is a valid octal digit.
        If any character is not a valid octal digit, it throws a
        NumberFormatException.
         */
        for (char c : String.valueOf(Math.abs(
                Double.parseDouble(o))).replace(".", "").toCharArray()) {
            if (c < '0' || c > '7') {
                throw new NumberFormatException("Not a valid octal number.");
            }
        }

        /*
        Splits the given octal number into its whole number and
        fractional parts. If the octal number only has a whole
        number part, an empty string is assigned to its fractional
        part.
         */
        String[] parts = o.split("\\.");
        String octWholeNumPart =
                (String.valueOf(Math.abs(Integer.parseInt(parts[0]))));
        String octFractionalPart = (parts.length > 1) ? parts[1] : "";

        /*
        Converts the whole number part of the given octal number
        to the whole number part of its decimal representation.
         */
        int decWholeNumPart = 0;
        int i = 0;
        for (char digit : octWholeNumPart.toCharArray()) {
            decWholeNumPart += (int) (Character.getNumericValue(digit) *
                    Math.pow(8, octWholeNumPart.length() - 1 - i++));
        }

        /*
        Converts the fractional part of the given octal number
        to the fractional part of its decimal representation.
         */
        double decFractionalPart = 0;
        int j = -1;
        for (char digit : octFractionalPart.toCharArray()) {
            decFractionalPart +=
                    Character.getNumericValue(digit) * Math.pow(8, j--);
        }

        /*
        Combines the whole number and fractional parts of the decimal
        representation. If the given octal does not have a
        fractional part, its decimal representation will only include
        a whole number and no decimal point or any digits after it.
         */
        double decimal = (parts.length < 2) ? decWholeNumPart :
                decWholeNumPart + decFractionalPart;

        /*
        If the given octal is negative, its decimal representation
        is returned as a negative number. Otherwise, the decimal
        representation is returned as a positive number.
         */
        boolean isNegative = Double.parseDouble(o) < 0;
        return (isNegative) ? -decimal : decimal;
    }//end of octalToDecimal method

    /**
     * This method converts a binary number to its octal representation.
     *
     * @param binary Binary number to convert to octal
     * @return The octal equivalent of the binary number
     * @author Ravone Ebeng
     */
    public static String binaryToOctal(String binary) {
        String[] parts = binary.split("\\."); // Split the binary number into whole and fractional parts

        // Convert the whole number part to octal
        int wholeNumber = Integer.parseInt(parts[0], 2); // Convert whole number from binary to decimal
        String wholeNumberOctal = Integer.toOctalString(wholeNumber); // Convert whole number from decimal to octal

        // Convert the fractional part to octal
        StringBuilder fractionalPartOctal = new StringBuilder();
        if (parts.length > 1) {
            String fractionPartBinary = parts[1];
            while (fractionPartBinary.length() % 3 != 0) { // Pad with zeros to make length a multiple of 3
                fractionPartBinary += "0";
            }
            for (int i = 0; i < fractionPartBinary.length(); i += 3) {
                String threeBits = fractionPartBinary.substring(i, i + 3);
                int decimal = Integer.parseInt(threeBits, 2); // Convert three bits from binary to decimal
                fractionalPartOctal.append(Integer.toOctalString(decimal)); // Convert decimal to octal
            }
        }

        return wholeNumberOctal + (!fractionalPartOctal.isEmpty() ? "." + fractionalPartOctal : "");
    }//end of binaryToOctal method

    /**
     * This method converts an octal number to its binary representation.
     *
     * @param octal Octal number to convert to binary
     * @return The binary equivalent of the octal number
     * @author Ravone Ebeng
     */
    public static String octalToBinary(String octal) {
        String[] parts = octal.split("\\."); // Split the octal number into whole and fractional parts

        // Convert the whole number part to binary
        int wholeNumber = Integer.parseInt(parts[0], 8); // Convert whole number from octal to decimal
        String wholeNumberBinary = Integer.toBinaryString(wholeNumber); // Convert whole number from decimal to binary

        // Convert the fractional part to binary
        StringBuilder fractionalPartBinary = new StringBuilder();
        if (parts.length > 1) {
            String fractionPartOctal = parts[1];
            for (int i = 0; i < fractionPartOctal.length(); i++) {
                int decimal = Character.getNumericValue(fractionPartOctal.charAt(i)); // Convert octal digit to decimal
                String threeBits = Integer.toBinaryString(decimal); // Convert decimal to binary
                while (threeBits.length() < 3) { // Pad with zeros to make length 3
                    threeBits = "0" + threeBits;
                }
                fractionalPartBinary.append(threeBits);
            }
        }

        return wholeNumberBinary + (!fractionalPartBinary.isEmpty() ? "." + fractionalPartBinary : "");
    }//end of octalToBinary method

    /**
     *
     * This method converts binary numbers into their corresponding hexadecimal equivalent
     *
     * @param binary The binary string to be converted.
     * @return The hexadecimal representation of the binary string.
     * @author Lance Kenneth G. Cariaga
     */
    public String binaryToHexadecimal(String binary) {
        // Define an array to store hexadecimal digits
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

        // Initializes an empty string to store the result
        String result = "";

        // Splits binary into integer and fractional parts, if present
        String[] parts = binary.split("\\.");

        // Converts the integer part to hexadecimal
        String integerPart = parts[0];
        /* Pads integer part with leading zeros to make its length a multiple of 4
        *  (i.e. 101100 -> 10 1100 -> 0010 1100)
        *  (i.e. 1111100 -> 111 1100 -> 0111 1100)
        *
        * */
        while (integerPart.length() % 4 != 0) {
            integerPart = "0" + integerPart;
        }
        // Convert each 4-bit chunk of the integer part to hexadecimal
        for (int i = 0; i < integerPart.length(); i += 4) {
            String chunk = integerPart.substring(i, i + 4);
            int decimal = Integer.parseInt(chunk, 2);
            result += hex[decimal];
        }

        /*Similar to the integer portion of the binary string, the fractional portion of the binary string would be
        * converted to its hexadecimal equivalent.
        *
        * */
        if (parts.length > 1) {
            result += ".";
            String fractionalPart = parts[1];
            // Pads fractional part with trailing zeros to make its length a multiple of 4
            while (fractionalPart.length() % 4 != 0) {
                fractionalPart += "0";
            }
            // Converts each 4-bit chunk of the fractional part to hexadecimal
            for (int i = 0; i < fractionalPart.length(); i += 4) {
                String chunk = fractionalPart.substring(i, i + 4);
                int decimal = Integer.parseInt(chunk, 2);
                result += hex[decimal];
            }
        }

        return result;
    }//end of binaryToHexadecimal method


    /**
     * this method converts hexadecimal string to its binary equivalent
     *
     * @param hex The hexadecimal string to be converted.
     * @return The binary representation of the hexadecimal string.
     * @author Lance Kenneth G. Cariaga
     */
    public String hexadecimalToBinary(String hex) {
        // Create a mapping of hexadecimal characters to their decimal values
        String hexChars = "0123456789ABCDEF";

        // Initialize the result for both integer and fractional parts
        StringBuilder integerResult = new StringBuilder();
        StringBuilder fractionalResult = new StringBuilder();

        // if the hexadecimal string has both integer and fractional portions, they are split
        String[] parts = hex.split("\\.");

        // Converts the integer portion to binary
        String integerPart = parts[0];
        for (char c : integerPart.toCharArray()) {
            // Converts the character to uppercase so that even if the hexadecimal string inputted is in lowercase, it would be recognized properly.
            char upperC = Character.toUpperCase(c);

            // Finds the decimal value of the character by searching in the hexadecimal character mapping
            int decimalValue = hexChars.indexOf(upperC);

            // Converts the decimal value to a 4-bit binary chunk
            String binaryChunk = Integer.toBinaryString(decimalValue);

            /* Pads the binary chunk with leading zeros to ensure it has exactly 4 bits
            Functions the same way as the binaryToHex method pads 0s before the 4-bit chunks if the chunk
            is missing a bit
            */
            while (binaryChunk.length() < 4) {
                binaryChunk = "0" + binaryChunk; // Adding leading zeros
            }

            // Appends the binary chunk to the integer result
            integerResult.append(binaryChunk);
        }

        // If there's a fractional part, it is converted as well
        if (parts.length > 1) {
            String fractionalPart = parts[1];
            for (char c : fractionalPart.toCharArray()) {
                // Converts the character to uppercase so that even if the input hexadecimal is in lowercase, it would be recognized properly.
                char upperC = Character.toUpperCase(c);

                // Finds the decimal value of the character by searching in the hexadecimal character mapping
                int decimalValue = hexChars.indexOf(upperC);

                // Converts the decimal value to a 4-bit binary chunk
                String binaryChunk = Integer.toBinaryString(decimalValue);

                // Pads the binary chunk with leading zeros to ensure it has exactly 4 bits
                while (binaryChunk.length() < 4) {
                    binaryChunk = "0" + binaryChunk; // Adding leading zeros
                }

                // Appends the binary chunk to the fractional result
                fractionalResult.append(binaryChunk);
            }
        }

        // Combines integer and fractional parts with a dot in between, if there's a fractional part
        if (!fractionalResult.isEmpty()) {
            return integerResult + "." + fractionalResult;
        } else {
            return integerResult.toString();
        }
    }//end of hexadecimalToBinary

    /**
     * The following two methods converts an Octal number to a hexadecimal representation
     *
     * @param oct Octal number to Hexadecimal number
     * @return The octal equivalent of hexadecimal number
     * @author Charles Lohan V. Pecson
     */

    public static String octalToHexadecimal(int oct){// convert the octal to decimal
        int dec = 0, i = 0;
        while (oct != 0) {
            dec = dec + (oct % 10) * (int) Math.pow(8, i);
            i++;
            oct = oct / 10;
        }
        String hex = hexadecimal(dec);// print the hexadecimal representation
        System.out.println(hex);
        return hex;
    }//end of octalToHexadecimal

    //part of octalToHexadecimal
    static String hexadecimal(int q){//convert the decimal to octal
        char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int rem;
        String hexdec = "";
        while (q != 0) {
            rem = q % 16;
            hexdec = a[rem] + hexdec;
            q = q / 16;}
        return hexdec;
    }
/**
 * The following two methods converts a hexadecimal number to its Octal representation.
 *
 * @param hex Hexadecimal number to Octal number
 * @return The hexadecimal equivalent of octal number
 * @author Charles Lohan V. Pecson
 */

public static String hexadecimalToOctal(int hex){

    long decimal = hexToDecimal(hex);
    String octal = decimalToOctal(decimal);

    System.out.println("Octal number is: " + octal);
    return octal;
}

    static long hexToDecimal(int hex) {//Convert the Hexadecimal to Decimal
        return Long.parseLong(String.valueOf(hex), 16);
    }

    static String decimalToOctal(long decimal) {//Convert the Decimal to Octal
        StringBuilder octal = new StringBuilder();
        while (decimal != 0) {
            long remainder = decimal % 8;
            octal.insert(0, remainder);
            decimal /= 8;
        }
        return octal.toString();
    }




    /**
     *Returns a string showing a whole number with its decimal, binary, ocatal and hexadecimal form.
     **/
    public String toString(){
        return ("Decimal Value = "+decimalNumber+"\nBinary Value = " + binaryString +"\nOctal Value = " +
                octalString + "\nHexadecimal Value = " + hexadecimalString);
    }//end of toStringmethod


    /**
     *Returns true if this number is equivalent to another number.
     **/
    public boolean equals(EquivalentNumbers another){
        return (this.decimalNumber == another.getDecimalNumber());
    }
    private boolean isValidBinaryString(String s){
        boolean result = true;
        for (int index=0; index< s.length() && result; index++ ){
            if (s.charAt(index) != '0' && s.charAt(index) != '1') {
                result = false;

            }
        }
        return result;
    }// end of method


}//end of class
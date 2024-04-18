/** 
 *
 * This method converts binary numbers into hexadecimal numbers by separating the binary number into 4-bit chunks (i.e 10001111 -> 1000 1111) and converts each
 *4-bit chunk into their corresponding hexadecimal equivalent before stringing them together to create the hexadecimal number. 
 (i.e 10001111 -> 1000 1111 -> 8 F -> 8F).
 * @param binary The binary string to be converted.
 * @return The hexadecimal representation of the binary string.
 */
public String binaryToHexadecimal(String binary) {
    // Define an array to store hexadecimal digits
    String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
   
    // Initializes an empty string to store the result
    String result = "";

     // Splitting binary into integer and fractional parts, if present
    String[] parts = binary.split("\\.");

    // Convert integer part to hexadecimal
    String integerPart = parts[0];
    // Pad integer part with leading zeros to make its length a multiple of 4
    while (integerPart.length() % 4 != 0) {
        integerPart = "0" + integerPart;
    }
    // Convert each 4-bit chunk of the integer part to hexadecimal
    for (int i = 0; i < integerPart.length(); i += 4) {
        String chunk = integerPart.substring(i, i + 4);
        int decimal = Integer.parseInt(chunk, 2);
        result += hex[decimal];
    }

    // If there's a fractional part, convert it as well
    if (parts.length > 1) {
        result += ".";
        String fractionalPart = parts[1];
        // Pad fractional part with trailing zeros to make its length a multiple of 4
        while (fractionalPart.length() % 4 != 0) {
            fractionalPart += "0";
        }
        // Convert each 4-bit chunk of the fractional part to hexadecimal
        for (int i = 0; i < fractionalPart.length(); i += 4) {
            String chunk = fractionalPart.substring(i, i + 4);
            int decimal = Integer.parseInt(chunk, 2);
            result += hex[decimal];
        }
    }

    return result;
}


/**
 * Converts a hexadecimal string to its binary representation.
 *
 * @param hex The hexadecimal string to be converted.
 * @return The binary representation of the hexadecimal string.
 */
public String hexadecimalToBinary(String hex) {
    // Create a mapping of hexadecimal characters to their decimal values
    String hexChars = "0123456789ABCDEF";

    // Initialize the result for both integer and fractional parts
    StringBuilder integerResult = new StringBuilder();
    StringBuilder fractionalResult = new StringBuilder();

    // Splitting hexadecimal into integer and fractional parts, if present
    String[] parts = hex.split("\\.");

    // Convert integer part to binary
    String integerPart = parts[0];
    for (char c : integerPart.toCharArray()) {
        // Convert the character to uppercase so that even if the input hexadecimal is in lowercase, it would be recognized properly.
        char upperC = Character.toUpperCase(c);

        // Find the decimal value of the character by searching in the hexadecimal character mapping
        int decimalValue = hexChars.indexOf(upperC);

        // Convert the decimal value to a 4-bit binary chunk
        String binaryChunk = Integer.toBinaryString(decimalValue);

        // Pad the binary chunk with leading zeros to ensure it has exactly 4 bits
        while (binaryChunk.length() < 4) {
            binaryChunk = "0" + binaryChunk; // Adding leading zeros
        }

        // Append the binary chunk to the integer result
        integerResult.append(binaryChunk);
    }

    // If there's a fractional part, convert it as well
    if (parts.length > 1) {
        String fractionalPart = parts[1];
        for (char c : fractionalPart.toCharArray()) {
            // Convert the character to uppercase so that even if the input hexadecimal is in lowercase, it would be recognized properly.
            char upperC = Character.toUpperCase(c);

            // Find the decimal value of the character by searching in the hexadecimal character mapping
            int decimalValue = hexChars.indexOf(upperC);

            // Convert the decimal value to a 4-bit binary chunk
            String binaryChunk = Integer.toBinaryString(decimalValue);

            // Pad the binary chunk with leading zeros to ensure it has exactly 4 bits
            while (binaryChunk.length() < 4) {
                binaryChunk = "0" + binaryChunk; // Adding leading zeros
            }

            // Append the binary chunk to the fractional result
            fractionalResult.append(binaryChunk);
        }
    }

    // Combine integer and fractional parts with a dot in between, if there's a fractional part
    if (fractionalResult.length() > 0) {
        return integerResult.toString() + "." + fractionalResult.toString();
    } else {
        return integerResult.toString();
    }
}



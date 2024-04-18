package prog2.samcis;

public class OctalDecimalConversion {
    public static void main(String[] args) {
        // Testers
        System.out.println(decimalToOctal(18));
        System.out.println(octalToDecimal("1762.357372"));
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
    }

    /**
     * Converts an octal number to its decimal representation.
     *
     * @param o The octal number to be converted.
     * @author Mike Fajardo
     */
    public static double octalToDecimal(String o) throws NumberFormatException {
        /*
        Checks if each character of the given string, ignoring its
        sign and/or decimal point if present, to ensure it is a
        valid octal number. If any character is not a valid octal
        digit, it throws a NumberFormatException.
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
    }
}

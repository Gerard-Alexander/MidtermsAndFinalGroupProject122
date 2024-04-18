package prog2.samcis;

/**
 Name/s of programmer: Gerard Alexander C. Bernados
 Class Code and Schedule: 9308 TF 1:30 - 3:00
 Date: April 8, 2024
 */
          // 1. Let NumberConverter be an interface. What must replace the blank?
    public interface NumberConverter {
        public double binaryToDecimal(String b) throws Exception;
        public double hexadecimalToDecimal(String h);
        public double octalToDecimal(String n);
    }


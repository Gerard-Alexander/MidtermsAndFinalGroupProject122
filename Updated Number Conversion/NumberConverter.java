package prog2.samcis;

public interface NumberConverter {
    public double binaryToDecimal(String b) throws Exception;
    public double hexadecimalToDecimal(String h);
    public double octalToDecimal(String n);
}

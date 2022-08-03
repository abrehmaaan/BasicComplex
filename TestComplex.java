import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Complex {
	private double real, imag;
    private static int decPlaces = 2;
    public static int getDecPlaces() { return decPlaces; }
    public static void setDecPlaces(int decPlaces) { Complex.decPlaces = decPlaces; }

    public Complex () { real = imag = 0; }
    public Complex (double r, double i) { real = r; imag = i; }
    public double getReal () { return real; }
    public double getImag () { return imag; }
    public void setReal (double r) { real = r; }
    public void setImag (double i) { imag = i; }
    public Complex add (Complex other) {
        return new Complex (real + other.real, imag + other.imag);
    }
    public Complex subtract (Complex other) {
        return new Complex (real - other.real, imag - other.imag);
    }
    public Complex multiply (Complex o) {
        return new Complex (real * o.real - imag * o.imag, imag * o.real + real * o.imag);
    }
    public Complex divide (Complex o) {
        double a = real, b = imag, c = o.real, d = o.imag;
        return new Complex ((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d));
    }
    public void conjugate () { imag = -1*imag; }
    public void negative () { real = -1 * real; imag = -1 * imag; }
    public double modulus () { return Math.sqrt (real * real + imag * imag); }
    public String toString () {
        String fmt = "%." + String.valueOf (decPlaces) + "f";
        String s = String.format (fmt, real);
        if (imag > 0) s += "+";
        s += String.format (fmt, imag) + "i";
        return s;
    }
}
public class TestComplex{
    public static void main(String[] args){
    	Map<String, Complex> n = new HashMap<String, Complex>();
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()){
            List<String> tokens = new ArrayList<String>();
            Scanner lineScanner = new Scanner(scanner.nextLine());
 
            while (lineScanner.hasNext()) {
                tokens.add(lineScanner.next());
            }
            if(tokens.isEmpty()) {
            	break;
            }
            else if(tokens.get(0).equals("define")&&tokens.size()==4) {
            	n.put(tokens.get(1), new Complex(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3))));
            }
            else if(tokens.get(0).equals("define")&&tokens.size()==2) {
            	n.put(tokens.get(1), new Complex());
            }
            else if(tokens.get(0).equals("set")) {
            	n.get(tokens.get(1)).setReal(Double.parseDouble(tokens.get(2)));
            	n.get(tokens.get(1)).setImag(Double.parseDouble(tokens.get(3)));
            }
            else if(tokens.get(0).equals("show")) {
            	System.out.println(n.get(tokens.get(1)).toString());
            }
            else if(tokens.get(0).equals("add")) {
            	n.put(tokens.get(1), n.get(tokens.get(2)).add(n.get(tokens.get(3))));
            }
            else if(tokens.get(0).equals("subtract")) {
            	n.put(tokens.get(1), n.get(tokens.get(2)).subtract(n.get(tokens.get(3))));
            }
            else if(tokens.get(0).equals("multiply")) {
            	n.put(tokens.get(1), n.get(tokens.get(2)).multiply(n.get(tokens.get(3))));
            }
            else if(tokens.get(0).equals("divide")) {
            	n.put(tokens.get(1), n.get(tokens.get(2)).divide(n.get(tokens.get(3))));
            }
            else if(tokens.get(0).equals("negative")) {
            	n.get(tokens.get(1)).negative();
            }
            else if(tokens.get(0).equals("conjugate")) {
            	n.get(tokens.get(1)).conjugate();
            }
            else if(tokens.get(0).equals("decimal")) {
            	Complex.setDecPlaces(Integer.parseInt(tokens.get(1)));
            }
            lineScanner.close();
            
        }
        scanner.close();
    }
}

public class Statistic {
    public String name;
    public double value;

    public Statistic( String initialName, double initialValue ) {
        name = initialName;
        value = initialValue;
    }

    public void addValue( double newValue ) {
        value += newValue;
    }

    public void divideValue( double newValue ) {
        value /= newValue;
    }
}
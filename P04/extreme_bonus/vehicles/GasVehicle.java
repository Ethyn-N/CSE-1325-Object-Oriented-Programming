package vehicles;

/**
 * Defines a gasoline-fueled ground vehicle such as a car, truck, or SUV.
 *
 * @author             Ethyn Nguyen
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class GasVehicle implements Vehicle {
    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle, double milesPerGallon, double gallonsInTank) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.bodyStyle = bodyStyle;
        this.milesPerGallon = milesPerGallon;
        this.gallonsInTank = gallonsInTank;
    }

/**
 * Cost of gasoline in dollars per gallon.
 */
    public static double dollarsPerGallonOfGas = Double.NaN;

    public double range() {
        return gallonsInTank * milesPerGallon;
    }

    public double fuelConsumed(double miles) {
        double fuelConsumed = miles/milesPerGallon;
        if (fuelConsumed > gallonsInTank)
            throw new ArithmeticException("Error: more fuel consumed than available gallons");
        else 
            return fuelConsumed;
    }

/**
 * Calculate the cost of traveling a given distance.
 * @param miles        The distance given in miles     
 * @return             Returns cost (dollars) to travel.
 * {@link              dollarsPerGallonOfGas}
 * @since              1.0
 * 
 */
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model + " " + bodyStyle;
    }

    private int year;
    private String make;
    private String model;
    private BodyStyle bodyStyle;

    private double milesPerGallon;
    private double gallonsInTank;
}
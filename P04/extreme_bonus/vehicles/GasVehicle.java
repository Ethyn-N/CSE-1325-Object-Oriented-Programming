package vehicles;

/**
 * Defines a gasoline-fueled ground vehicle such as a car, truck, or SUV.
 *
 * @author             Ethyn Nguyen
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class GasVehicle extends Vehicle {
    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle, double milesPerGallon, double gallonsInTank) {
        super(year, make, model, bodyStyle);
        this.milesPerGallon = milesPerGallon;
        this.gallonsInTank = gallonsInTank;
    }

/**
 * Cost of gasoline in dollars per gallon.
 */
    public static double dollarsPerGallonOfGas = Double.NaN;

    @Override
    public double range() {
        return gallonsInTank * milesPerGallon;
    }

    @Override
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
    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }

    private double milesPerGallon;
    private double gallonsInTank;
}
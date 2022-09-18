package vehicles;

/**
 * Defines a battery and electric motor-propelled ground vehicle such as a car, truck, or SUV.
 *
 * @author             Ethyn Nguyen
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class ElectricVehicle extends Vehicle {
    public ElectricVehicle(int year, String make, String model, BodyStyle bodyStyle, double whPerMile, double kwhInBattery) {
        super(year, make, model, bodyStyle);
        this.whPerMile = whPerMile;
        this.kwhInBattery = kwhInBattery;
    }

/**
 * Cost of Electricity in cents per KWh.
 */
    public static double centsPerKwhOfElectricty = Double.NaN;

    @Override
    public double range() {
        return kwhInBattery/(whPerMile/1000);
    }

    @Override
    public double fuelConsumed(double miles) {
        double fuelConsumed = miles*(whPerMile/1000);
        if (fuelConsumed > kwhInBattery)
            throw new ArithmeticException("Error: more energy consumed than available in battery");
        else 
            return fuelConsumed;
    }

/**
 * Calculate the cost of traveling a given distance.
 * @param miles        The distance given in miles     
 * @return             Returns cost (dollars) to travel.
 * {@link              centsPerKwhOfElectricty}
 * @since              1.0
 * 
 */
    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * (centsPerKwhOfElectricty/100);
    }

    private double whPerMile;
    private double kwhInBattery;
}
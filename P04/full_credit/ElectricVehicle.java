public class ElectricVehicle extends Vehicle {
    public ElectricVehicle(int year, String make, String model, BodyStyle bodyStyle, double whPerMile, double kwhInBattery) {
        super(year, make, model, bodyStyle);
        this.whPerMile = whPerMile;
        this.kwhInBattery = kwhInBattery;
    }

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

    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * (centsPerKwhOfElectricty/100);
    }

    private double whPerMile;
    private double kwhInBattery;
}
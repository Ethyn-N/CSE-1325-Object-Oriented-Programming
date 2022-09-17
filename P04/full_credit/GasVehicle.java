public class GasVehicle extends Vehicle {
    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle, double milesPerGallon, double gallonsInTank) {
        super(year, make, model, bodyStyle);
        this.milesPerGallon = milesPerGallon;
        this.gallonsInTank = gallonsInTank;
    }

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

    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }

    private double milesPerGallon;
    private double gallonsInTank;
}
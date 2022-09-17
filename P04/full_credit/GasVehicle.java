public class GasVehicle extends Vehicle {
    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle) {
        super(year, make, model, bodyStyle);
    }

    public static double dollarsPerGallonOfGas = Double.NaN;

    @Override
    public double range() {
        return gallonsinTank * milesPerGallon;
    }

    @Override
    public double fuelConsumed(double miles) {
        double fuelConsumed = miles/milesPerGallon;
        if (fuelConsumed > gallonsinTank)
            throw new ArithmeticException("Error: more fuel consumed than available gallons");
        else 
            return fuelConsumed;
    }

    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }

    private double milesPerGallon;
    private double gallonsinTank;
}
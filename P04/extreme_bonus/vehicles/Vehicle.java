package vehicles;

/**
 * Defines a self-propelled ground vehicle such as a car, truck, or SUV
 *
 * @author             Ethyn Nguyen
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public interface Vehicle {
    
/**
 * Calculate how far the vehicle can travel without refueling.
 *
 * @return             Returns the range
 * @since              1.0
 * 
 */
    public double range();

/**
 * Calculate how much fuel would be consumed to travel a given distance.
 *
 * @param miles        The distance given in miles     
 * @return             Returns how much fuel is consumed.
 * @since              1.0
 * 
 */
    public double fuelConsumed(double miles);

/**
 * Calculate the cost of traveling a given distance.
 * @param miles        The distance given in miles     
 * @return             Returns cost (dollars) to travel.
 * @since              1.0
 * 
 */
    public double dollarsToTravel(double miles);
}
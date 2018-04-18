import data.DataAccessorDatabase;
import logic.Controller;
import logic.PriceCalculator;

/**
 *
 * @author RODA
 */
public class Main {
    
    public static void main(String[] args) {
        new Controller(new DataAccessorDatabase(), new PriceCalculator()).go();
    }
}

package logic;

import presentation.UI;
import data.DataAccessor;
import data.DataAccessorDatabase;
import presentation.TUI;

/**
 *
 * @author RODA
 */
public class Controller {


    public Controller(DataAccessor data, PriceCalculator pc){
        logic = pc;
        this.data = data;
    }


    public static final boolean DEBUG = true;
    private UI ui = new TUI();
//    private DataAccessor data = new DataAccessorFile();
    private DataAccessor data;
    private PriceCalculator logic;


    
    public void go() {
        // Get input
        double height = ui.getFrameHeight();
        //System.out.println("Height = " + height);
        double width = ui.getFrameWidth();
        //System.out.println("Width = " + width);
        FrameType type = ui.getFrameType();
        //System.out.println("Type = " + type.toString());
        
        // Calculate price
        double price = logic.calculatePrice(height, width, type, data);
        
        // Display result
        ui.displayPrice(price);
    }
    
}

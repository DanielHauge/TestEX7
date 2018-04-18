package logic;

import data.DataAccessorHardCodedValues;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ControllerTest {

    @Test
    void go() throws Exception {

        ByteArrayInputStream in = new ByteArrayInputStream("25\n25\n2\n".getBytes());
        System.setIn(in);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        Thread.sleep(1000);
        ctthread cts = new ctthread(new Controller(new DataAccessorHardCodedValues(), new PriceCalculator()));
        cts.start();
        Thread.sleep(1000);

        final String standardOutput = myOut.toString();

        String[]price = standardOutput.split("\r\n")[5].split("kr. ")[1].split(",");
        String finalprice = price[0]+"."+price[1];
        Double dprice = Double.parseDouble(finalprice);

        assertThat(dprice, is(218.75d));
    }


}

class ctthread extends Thread{
    Controller ct;
    public ctthread(Controller ct){
        this.ct = ct;
    }

    public void run(){
        System.out.println("I STARTED!");
        ct.go();
    }
}

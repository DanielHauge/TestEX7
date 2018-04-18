import data.DBConnector;
import data.DataAccessorDatabase;
import data.DataAccessorHardCodedValues;
import logic.Controller;
import logic.PriceCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MainTest {

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();

        String query = "CREATE TABLE `glarmester`.`prices` (\n" +
                "  `name` VARCHAR(25) NOT NULL,\n" +
                "  `price` FLOAT NULL,\n" +
                "  PRIMARY KEY (`name`));";
        String query1 = "INSERT INTO `glarmester`.`prices` (`name`, `price`) VALUES \n" +
                "('glass', '300'), \n" +
                "('simple', '100'), \n" +
                "('ornate', '200'), \n" +
                "('lavish', '350');";

        stmt.execute(query);
        stmt.execute(query1);
        connection.close();


    }

    @AfterEach
    void tearDown() throws Exception {

        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();
        String query2 = "DROP table `prices`;";
        stmt.execute(query2);
        connection.close();

    }

    @Test
    void main() throws InterruptedException {


        ByteArrayInputStream in = new ByteArrayInputStream("25\n25\n2\n".getBytes());
        System.setIn(in);
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));



        ctthread cts = new ctthread(new Controller(new DataAccessorDatabase(), new PriceCalculator()));
        cts.start();
        Thread.sleep(100);

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

package data;

import logic.FrameType;

import java.sql.Connection;
import java.sql.Statement;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


class DataAccessorDatabaseTest {

    @org.junit.jupiter.api.BeforeEach
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

    @org.junit.jupiter.api.AfterEach
    void tearDown() throws Exception {
        Connection connection = new DBConnector().getConnection();
        Statement stmt = connection.createStatement();
        String query2 = "DROP table `prices`;";
        stmt.execute(query2);
        connection.close();
    }

    @org.junit.jupiter.api.Test
    void getGlassprice() {
        DataAccessorDatabase da = new DataAccessorDatabase();
        long time = System.currentTimeMillis();
        double glassprice = da.getGlassprice();
        long end = System.currentTimeMillis();
        int exetime = (int)(end-time);
        System.out.println(exetime);
        assertThat(exetime, is(lessThan(100)));
        assertThat(glassprice, is(300.0));


    }

    @org.junit.jupiter.api.Test
    void getFramePrice() {
        DataAccessorDatabase da = new DataAccessorDatabase();
        long time = System.currentTimeMillis();
        double framePrice = da.getFramePrice(FrameType.Lavish);
        long end = System.currentTimeMillis();
        int exetime = (int)(end-time);
        System.out.println(exetime);
        assertThat(exetime, is(lessThan(100)));
        assertThat(framePrice, is(350.0));

    }
}
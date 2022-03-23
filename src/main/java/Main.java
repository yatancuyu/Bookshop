
import models.Customer;
import services.CustomerService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CustomerService customerService = new CustomerService();
        System.out.println(customerService.readCustomerByLogin("admin").toString());
    }
}
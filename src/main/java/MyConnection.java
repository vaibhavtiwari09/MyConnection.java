import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Customer{
    int id;
    String name;

    public Customer() {
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +      //comnnectio ..prepar
                '}';
    }
}
public class MyConnection {
    public static Connection getConnection()  {
        Connection c = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cm", "root", "vaibhav");
            System.out.println("Connection ho gyo");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    public static List<Customer> readCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection c = MyConnection.getConnection();
        PreparedStatement ps = c.prepareStatement("select customernumber, customername from customers");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            String name =rs.getString(2);
            customers.add(new Customer(id, name));
        }
        return customers;
    }

    public static void main(String[] args) throws  SQLException {
        List<Customer> customers = MyConnection.readCustomers();
        customers.forEach(c -> System.out.println(c));
    }
}
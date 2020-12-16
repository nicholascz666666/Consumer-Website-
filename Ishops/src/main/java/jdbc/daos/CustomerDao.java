package jdbc.daos;
import jdbc.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import jdbc.models.Address;
import jdbc.models.Customer;
import jdbc.utils.DBUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.sql.Connection;
@Component
public class CustomerDao {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String HOST = "localhost:3306";
    static final String SCHEMA = "test";
    static final String CONFIG = "useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    static final String DB_URL
            = "jdbc:mysql://"+HOST+"/"+SCHEMA+"?"+CONFIG;

    static final String USER = "root";
    static final String PASS = "111321704";

    static Connection connection = null;
    static PreparedStatement statement = null;
    Integer status = -1;

    public static Connection getConnection() {
        if(connection != null) return connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager
                    .getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void closeConnection() {
        try {
            if(connection != null) {
                connection.close();
            }
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static final String FIND_ALL_Customer
            = "SELECT * FROM customers";

    private static String FIND_Phones_FOR_Customer
            = "SELECT * FROM customers c, telephones t WHERE c.id = t.user_id AND c.id=?";


    private static String FIND_Orders_FOR_Customers
            = "SELECT * FROM customers c, orders o WHERE c.id = o.user_id AND c.id=?";

    public List<Customer> findAllCustomers() {
        List<Customer> users = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(FIND_ALL_Customer);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                statement = connection.prepareStatement(FIND_Phones_FOR_Customer);
                statement.setInt(1,id);
                List<Telephone> telephones = new ArrayList<>();
                ResultSet telephonesResultSet = statement.executeQuery();
                while(telephonesResultSet.next()) {
                    Integer tid = resultSet.getInt("id");
                    Integer phone = resultSet.getInt("phone");
                    Boolean defau = resultSet.getBoolean("defau");
                    String type = resultSet.getString("type");
                    TelephoneType telephoneType;
                    switch (type){
                        case "Home":
                            telephoneType = TelephoneType.Home;
                            break;
                        case "Work":
                            telephoneType = TelephoneType.Work;
                            break;
                        case "Tel":
                            telephoneType = TelephoneType.Tel;
                            break;
                        default:
                            telephoneType = TelephoneType.Other;
                            break;
                    }
                    Telephone telephone = new Telephone(tid,phone,defau,telephoneType);
                    telephones.add(telephone);
                }
                statement = connection.prepareStatement(FIND_Orders_FOR_Customers);
                statement.setInt(1,id);
                List<Order> orders = new ArrayList<>();
                ResultSet ordersResultSet = statement.executeQuery();
                while(ordersResultSet.next()) {
                    Integer oid = resultSet.getInt("id");
                    String role = resultSet.getString("role");
                    Priviledge p;
                    switch (role){
                        case "EMPLOYEE_BENEFIT":
                            p=Priviledge.EMPLOYEE_BENEFIT;
                            break;
                        case "SELL":
                            p=Priviledge.SELL;
                            break;
                        case "UPDATE":
                            p=Priviledge.UPDATE;
                            break;
                        case "DELETE":
                            p=Priviledge.DELETE;
                            break;
                        default:
                            p=Priviledge.BUY;
                            break;
                    }

                    Integer itemNum = resultSet.getInt("itemNum");
                    Integer sum = resultSet.getInt("sum");
                    Integer item_id = resultSet.getInt("item_id");
                    OrderDao orderDao =new OrderDao();
                    Item item = orderDao.findItemByOrderId(item_id);
                    Order order = new Order(id,p,itemNum,item);
                    orders.add(order);
                }
                String keywords = resultSet.getString("keywords");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String username = resultSet.getString("username");
                Customer customer = new Customer(id,keywords,lastname,firstname,username);
                customer.setTelephones(telephones);
                users.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }


    static final String FIND_Customer_BY_ID =
            "SELECT * FROM customers WHERE id=?";
    public Customer findCustomerById(Integer userId) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_Customer_BY_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String keywords = resultSet.getString("keywords");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String username = resultSet.getString("username");
                Customer customer = new Customer(userId,keywords,lastname,firstname,username);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    static final String UPDATE_Customer =
            "UPDATE customers SET keywords=?, lastname =?, firstname=?, username=?, WHERE id=?";
    public Integer updateCustomer(String keywords,String lastname,String firstname,String username,Customer customer) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_Customer);
            statement.setString(1,customer.getKeywords() );
            statement.setString(2, customer.getLastname());
            statement.setString(3, customer.getFirstname());
            statement.setString(4, customer.getUsername());
            statement.setInt(5, customer.getId());
            status = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    static final String CREATE_Customer
            = "INSERT INTO customers VALUES (?,?,?,?,?)";
    public Integer createCustomer(Customer customer) {
        status = -1;
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(CREATE_Customer);
            statement.setInt(1, customer.getId());
            statement.setString(2,customer.getKeywords() );
            statement.setString(3, customer.getLastname());
            statement.setString(4, customer.getFirstname());
            statement.setString(5, customer.getUsername());
            status = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }
    public void add(Customer customer){
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into customers values(0,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,customer.getKeywords());
            pst.setString(2,customer.getLastname());
            pst.setString(3,customer.getFirstname());
            pst.setString(4,customer.getUsername());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,null);
        }
    }

    public Customer login(String username, String password) {
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from customers where username=? and keywords=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            rs = pst.executeQuery();
            if(rs.next()){
                Customer customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                return customer;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return null;
    }
}

package jdbc.daos;

import jdbc.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import jdbc.models.Address;
import jdbc.models.BasicUser;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BasicUserDao {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String HOST = "localhost:3306";
    static final String SCHEMA = "test";
    static final String CONFIG = "serverTimezone=UTC";
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


    static final String FIND_ALL_Users
            = "SELECT * FROM users";

    private static String FIND_Phones_FOR_User
            = "SELECT * FROM users u, telephones t WHERE u.id = t.user_id AND u.id=?";

    private static String FIND_Orders_FOR_User
            = "SELECT * FROM users u, orders o WHERE u.id = o.user_id AND u.id=?";



    public List<BasicUser> findAllUsers() {
        List<BasicUser> users = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(FIND_ALL_Users);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                statement = connection.prepareStatement(FIND_Phones_FOR_User);
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

                statement = connection.prepareStatement(FIND_Orders_FOR_User);
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
                BasicUser basicUser = new BasicUser(id,keywords,lastname,firstname,username);
                basicUser.setTelephones(telephones);
                users.add(basicUser);
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


    static final String FIND_User_BY_ID =
            "SELECT * FROM users WHERE id=?";
    public BasicUser findUserById(Integer userId) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_User_BY_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String keywords = resultSet.getString("keywords");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String username = resultSet.getString("username");
                BasicUser basicUser = new BasicUser(userId,keywords,lastname,firstname,username);
                return basicUser;
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


    static final String UPDATE_User =
            "UPDATE users SET keywords=?, lastname =?, firstname=?, username=? WHERE id=?";
    public Integer updateUser(String keywords,String lastname,String firstname,String username,BasicUser basicUser) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_User);
            statement.setString(1,basicUser.getKeywords() );
            statement.setString(2, basicUser.getLastname());
            statement.setString(3, basicUser.getFirstname());
            statement.setString(4, basicUser.getUsername());
            statement.setInt(5, basicUser.getId());
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

    static final String CREATE_User
            = "INSERT INTO users VALUES (?,?,?,?,?)";
    public Integer createUser(BasicUser user) {
        status = -1;
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(CREATE_User);
            statement.setInt(1, user.getId());
            statement.setString(2,user.getKeywords() );
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getFirstname());
            statement.setString(5, user.getUsername());
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

}
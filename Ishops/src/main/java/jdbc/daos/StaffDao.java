package jdbc.daos;

import jdbc.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
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


    static final String FIND_ALL_Staff
            = "SELECT * FROM staffs";

    private static String FIND_Phones_FOR_Staffs
            = "SELECT * FROM staffs s, telephones t WHERE s.id = t.user_id AND s.id=?";

    private static String FIND_Orders_FOR_Staffs
            = "SELECT * FROM staffs u, orders o WHERE u.id = o.user_id AND u.id=?";

    public List<Staff> findAllStaffs() {
        List<Staff> users = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(FIND_ALL_Staff);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                statement = connection.prepareStatement(FIND_Phones_FOR_Staffs);
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




                statement = connection.prepareStatement(FIND_Orders_FOR_Staffs);
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
                Integer start = resultSet.getInt("start");
                Integer end = resultSet.getInt("end");
                Staff basicUser = new Staff(id,keywords,lastname,firstname,username,start,end);
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


    static final String FIND_Staff_BY_ID =
            "SELECT * FROM staffs WHERE id=?";
    public BasicUser findStaffById(Integer userId) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_Staff_BY_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String keywords = resultSet.getString("keywords");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String username = resultSet.getString("username");
                Integer start = resultSet.getInt("start");
                Integer end = resultSet.getInt("end");
                Staff basicUser = new Staff(userId,keywords,lastname,firstname,username,start,end);
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


    static final String UPDATE_Staff =
            "UPDATE users SET keywords=?, lastname =?, firstname=?, username=?,start=?,end=?   WHERE id=?";
    public Integer updateUser(String keywords,String lastname,String firstname,String username,Staff staff) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_Staff);
            statement.setString(1,staff.getKeywords() );
            statement.setString(2, staff.getLastname());
            statement.setString(3, staff.getFirstname());
            statement.setString(4, staff.getUsername());
            statement.setInt(5, staff.getStart());
            statement.setInt(6, staff.getEnd());
            statement.setInt(7, staff.getId());
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

    static final String CREATE_Staff
            = "INSERT INTO staffs VALUES (?,?,?,?,?,?,?)";
    public Integer createStaff(Staff staff) {
        status = -1;
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(CREATE_Staff);
            statement.setInt(1, staff.getId());
            statement.setString(2,staff.getKeywords() );
            statement.setString(3, staff.getLastname());
            statement.setString(4, staff.getFirstname());
            statement.setString(5, staff.getUsername());
            statement.setInt(6, staff.getStart());
            statement.setInt(7, staff.getEnd());
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

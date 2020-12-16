package jdbc.daos;
import jdbc.models.*;
import jdbc.utils.DBUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
@Component
public class SellerDao {
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


    static final String FIND_ALL_Sellers
            = "SELECT * FROM sellers";

    private static String FIND_Phones_FOR_Sellers
            = "SELECT * FROM sellers s, telephones t WHERE s.id = t.user_id AND s.id=?";
    private static String FIND_Items_FOR_Sellers
            = "SELECT * FROM sellers s, items i WHERE s.id = i.seller_id AND s.id=?";

    private static String FIND_Orders_FOR_Seller
            = "SELECT * FROM sellers u, orders o WHERE u.id = o.user_id AND u.id=?";
    public List<Seller> findAllCustomers() {
        List<Seller> users = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(FIND_ALL_Sellers);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                statement = connection.prepareStatement(FIND_Phones_FOR_Sellers);
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

                statement = connection.prepareStatement(FIND_Items_FOR_Sellers);
                statement.setInt(1,id);
                List<Item> items = new ArrayList<>();
                ResultSet itemsResultSet = statement.executeQuery();
                while(itemsResultSet.next()) {
                    Integer iid = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String discription = resultSet.getString("discription");
                    Integer price = resultSet.getInt("price");
                    Integer instock = resultSet.getInt("instock");
                    Integer sellerId = resultSet.getInt("seller_id");
                    Item item = new Item(iid,name,discription,price,instock,sellerId);
                    items.add(item);
                }
                statement = connection.prepareStatement(FIND_Orders_FOR_Seller);
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
                Integer deals = resultSet.getInt("number_deals");
                Seller seller = new Seller(id,keywords,lastname,firstname,username,deals);
                seller.setTelephones(telephones);
                seller.setItems(items);
                users.add(seller);
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


    static final String FIND_Seller_BY_ID =
            "SELECT * FROM sellers WHERE id=?";
    public Seller findSellerById(Integer userId) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_Seller_BY_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String keywords = resultSet.getString("keywords");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String username = resultSet.getString("username");
                Integer number_deals = resultSet.getInt("number_deals");
                Seller seller = new Seller(userId,keywords,lastname,firstname,username,number_deals);
                return seller;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    static final String UPDATE_Seller =
            "UPDATE customers SET keywords=?, lastname =?, firstname=?, username=?,number_deals=? WHERE id=?";
    public Integer updateSeller(String keywords,String lastname,String firstname,String username,Seller seller) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_Seller);
            statement.setString(1,seller.getKeywords() );
            statement.setString(2, seller.getLastname());
            statement.setString(3, seller.getFirstname());
            statement.setString(4, seller.getUsername());
            statement.setInt(5, seller.getNumber_deals());
            statement.setInt(6, seller.getId());

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

    static final String CREATE_Seller
            = "INSERT INTO sellers VALUES (?,?,?,?,?,?)";
    public Integer createSeller(Seller seller) {
        status = -1;
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(CREATE_Seller);
            statement.setInt(1, seller.getId());
            statement.setString(2,seller.getKeywords() );
            statement.setString(3, seller.getLastname());
            statement.setString(4, seller.getFirstname());
            statement.setString(5, seller.getUsername());
            statement.setInt(6, seller.getNumber_deals());
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

    public Seller getSellerById(int id){
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from sellers where id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while(rs.next()){
                Seller seller = new Seller(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                return seller;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return null;
    }
    public Seller login(String uername,String password){
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from sellers where username=? and keywords=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,uername);
            pst.setString(2,password);
            rs = pst.executeQuery();
            if(rs.next()){
                Seller seller = new Seller(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                return seller;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return null;
    }
    public void add(Seller seller){
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into sellers values(0,?,?,?,?,0)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,seller.getKeywords());
            pst.setString(2,seller.getLastname());
            pst.setString(3,seller.getFirstname());
            pst.setString(4,seller.getUsername());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,null);
        }
    }

}

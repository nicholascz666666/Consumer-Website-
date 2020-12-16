package jdbc.daos;

import jdbc.models.*;
import jdbc.utils.DBUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class OrderDao {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String HOST = "localhost:3306";
    static final String SCHEMA = "market";
    static final String CONFIG = "useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    static final String DB_URL
            = "jdbc:mysql://"+HOST+"/"+SCHEMA+"?"+CONFIG;

    static final String USER = "root";
    static final String PASS = "root";

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


    static final String FIND_ALL_Orders
            = "SELECT * FROM orders";

    private static String FIND_Item_FOR_Order
            = "SELECT * FROM orders o, items i WHERE i.id = o.item_id AND o.id=?";

    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(FIND_ALL_Orders);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                statement = connection.prepareStatement(FIND_Item_FOR_Order);
                statement.setInt(1,id);
                ResultSet itemsResultSet = statement.executeQuery();
                Item item = new Item();
                while(itemsResultSet.next()) {
                    Integer oid = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String discription = resultSet.getString("discription");
                    Integer price = resultSet.getInt("price");
                    Integer instock = resultSet.getInt("instock");
                    Integer sellerId = resultSet.getInt("seller_id");
                    item = new Item(oid,name,discription,price,instock,sellerId);
                }

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
                Order order = new Order(id,p,itemNum,item);
                orders.add(order);
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
        return orders;
    }


    static final String FIND_Order_BY_ID =
            "SELECT * FROM orders WHERE id=?";
    public Order findOrderById(Integer orderId) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_Order_BY_ID);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {

                Integer id = resultSet.getInt("id");
                statement = connection.prepareStatement(FIND_Item_FOR_Order);
                statement.setInt(1,id);
                ResultSet itemsResultSet = statement.executeQuery();
                Item item = new Item();
                while(itemsResultSet.next()) {
                    Integer oid = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String discription = resultSet.getString("discription");
                    Integer price = resultSet.getInt("price");
                    Integer instock = resultSet.getInt("instock");
                    Integer sellerId = resultSet.getInt("seller_id");
                    item = new Item(oid,name,discription,price,instock,sellerId);
                }

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
                Order order = new Order(id,p,itemNum,item);

                return order;
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





    public Item findItemByOrderId(Integer userId) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_Item_FOR_Order);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {

                Integer id = resultSet.getInt("id");
                statement = connection.prepareStatement(FIND_Item_FOR_Order);
                statement.setInt(1,id);
                ResultSet itemsResultSet = statement.executeQuery();
                    Integer oid = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String discription = resultSet.getString("discription");
                    Integer price = resultSet.getInt("price");
                    Integer instock = resultSet.getInt("instock");
                Integer sellerId = resultSet.getInt("seller_id");
                Item item = new Item(oid,name,discription,price,instock,sellerId);
                return item;
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






    static final String UPDATE_Order =
            "UPDATE users SET role=?, itemNum =?, sum=? WHERE id=?";
    public Integer updateOrder(Integer itemNum,Order order) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_Order);
            statement.setString(1,order.getRole().toString());
            statement.setInt(2,order.getItemNum());
            statement.setInt(3, order.getSum());
            statement.setInt(4, order.getId());
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

    static final String CREATE_Order
            = "INSERT INTO orders VALUES (?,?,?,?,?,?)";
    public Integer createOrder(Order order,Priviledge priviledge,Item item,BasicUser user) {
        status = -1;
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(CREATE_Order);
            statement.setInt(1, order.getId());
            statement.setString(2,priviledge.toString());
            statement.setInt(3, item.getId());
            statement.setInt(4, order.getItemNum());
            statement.setInt(5, 0);
            statement.setInt(6, user.getId());
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

    public void addOrder(Item item,int userId){
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into orders values(0,?,?,1,?,?)";
        System.out.println(item.getId());
        System.out.println(item.getPrice());
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,Priviledge.BUY.toString());
            pst.setInt(2,item.getId());
            pst.setInt(3,item.getPrice());
            pst.setInt(4,userId);
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,null);
        }
    }

    public List<Order> getAllByUserId(Integer id) {
        List<Order> list = new ArrayList<Order>();
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from orders where user_id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setSum(rs.getInt(5));
                Item item = new ItemDao().getItemById(rs.getInt(3));
                order.setItem(item);
                list.add(order);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return list;
    }
}

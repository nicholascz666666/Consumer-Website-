package jdbc.daos;


import jdbc.models.*;
import jdbc.utils.DBUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
@Component
public class AddressDao {
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


    static final String FIND_ALL_Addresses
            = "SELECT * FROM addresses";

    public List<Address> findAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(FIND_ALL_Addresses);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Boolean defau = resultSet.getBoolean("defau");
                String address1 = resultSet.getString("address1");
                String address2 = resultSet.getString("address2");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String country = resultSet.getString("country");
                Integer zipcode = resultSet.getInt("zipcode");
                Address address = new Address(id, defau,address1,address2,city,state,country,zipcode);
                addresses.add(address);
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
        return addresses;
    }


    static final String FIND_Address_BY_ID =
            "SELECT * FROM addresses WHERE id=?";
    public Address findAddressById(Integer addressId) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_Address_BY_ID);
            statement.setInt(1, addressId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Boolean defau = resultSet.getBoolean("defau");
                String address1 = resultSet.getString("address1");
                String address2 = resultSet.getString("address2");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String country = resultSet.getString("country");
                Integer zipcode = resultSet.getInt("zipcode");
                Address address = new Address(addressId, defau,address1,address2,city,state,country,zipcode);
                return address;
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


    static final String UPDATE_Address =
            "UPDATE addresses SET address1=?, address2 =?, city=?, state=?, country=?, zipcode=?, defau= ? WHERE id=?";
    public Integer updateAddress(String address1,String address2,String city,String state,String country,Integer zipcode,Boolean defau, Address address) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_Address);
            statement.setString(1,address.getAddress1() );
            statement.setString(2, address.getAddress2());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setString(5, address.getCountry());
            statement.setInt(6, address.getZipcode());
            statement.setBoolean(7, address.isDefau());
            statement.setInt(8, address.getId());
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

    static final String CREATE_Address_For_Customer
            = "INSERT INTO addresses VALUES (?,?,?,?,?,?,?,?) where addresses.cid = customers.id and customers.id = ? ";
    public Integer createAddressForCustomer(Address address, Customer customer) {
        status = -1;
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(CREATE_Address_For_Customer);
            statement.setInt(1, address.getId());
            statement.setBoolean(2, address.isDefau());
            statement.setString(3,address.getAddress1() );
            statement.setString(4, address.getAddress2());
            statement.setString(5, address.getCity());
            statement.setString(6, address.getState());
            statement.setString(7, address.getCountry());
            statement.setInt(8, address.getZipcode());
            statement.setInt(9, customer.getId());
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

    public void addAddress(Address address, Integer id) {
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into addresses values(0,?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setBoolean(1, false);
            pst.setString(2,address.getAddress1());
            pst.setString(3,address.getAddress2());
            pst.setString(4,address.getCity());
            pst.setString(5,address.getState());
            pst.setString(6,address.getCountry());
            pst.setInt(7,address.getZipcode());
            pst.setInt(8,id);
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,null);
        }
    }
    public List<Address> getAddressByCid(int cid){
        List<Address> list = new ArrayList<Address>();
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from addresses where cid=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,cid);
            rs = pst.executeQuery();
            while(rs.next()){
                Address address = new Address(rs.getInt(1),rs.getBoolean(2),
                        rs.getString(3),rs.getString(4),rs.getString(6),
                        rs.getString(6),rs.getString(7),rs.getInt(8));
                list.add(address);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return list;
    }
}

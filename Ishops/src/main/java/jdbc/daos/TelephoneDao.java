package jdbc.daos;

import jdbc.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelephoneDao {
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


    static final String FIND_ALL_Telephones
            = "SELECT * FROM telephones";

    public List<Telephone> findAllTelephones() {
        List<Telephone> telephones = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(FIND_ALL_Telephones);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt("id");
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
                Telephone telephone = new Telephone(id,phone,defau,telephoneType);
                telephones.add(telephone);
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
        return telephones;
    }


    static final String FIND_Telephone_BY_ID =
            "SELECT * FROM telephones WHERE id=?";
    public Telephone findTelephonesById(Integer id) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(FIND_Telephone_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
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
                Telephone telephone = new Telephone(id,phone,defau,telephoneType);
                return telephone;
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


    static final String UPDATE_Telephone =
            "UPDATE addresses SET phone=?, defau =?, type=? WHERE id=?";
    public Integer updateAddress(Integer phone, Boolean defau, String type, Telephone telephone) {
        connection = getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_Telephone);

            statement.setInt(1, telephone.getPhone());
            statement.setBoolean(2, telephone.isDefau());

            statement.setString(3, telephone.getType().toString());
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

    static final String CREATE_Telephone_For_User
            = "INSERT INTO telephones VALUES (?,?,?,?) where telephone.user_id = users.id and user.id = ? ";
    public Integer createForCustomer(Telephone telephone, BasicUser user) {
        status = -1;
        connection = getConnection();
        try {
            statement = connection
                    .prepareStatement(CREATE_Telephone_For_User);
            statement.setInt(1, telephone.getId());
            statement.setInt(2, telephone.getPhone());
            statement.setBoolean(3, telephone.isDefau());
            statement.setString(4, telephone.getType().toString());
            statement.setInt(5, user.getId());
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

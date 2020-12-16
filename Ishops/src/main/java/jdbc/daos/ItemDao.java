package jdbc.daos;

import jdbc.models.Customer;
import jdbc.models.Item;
import jdbc.utils.DBUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDao {

    public List<Item> getItemsByKeywords(String keywords){
        List<Item> list = new ArrayList<Item>();
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from items where name=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,keywords);
            rs = pst.executeQuery();
            while(rs.next()){
               Item item = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
               list.add(item);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return list;
    }


    public Item getItemById(int id) {
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from items where id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while(rs.next()){
                Item item = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
                return item;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return null;
    }

    public List<Item> getItemsByIdSellerId(Integer id) {
        List<Item> list = new ArrayList<Item>();
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from items where seller_id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while(rs.next()){
                Item item = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
                list.add(item);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
        return list;
    }

    public void addItem(Item item) {
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "insert into items values(0,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,item.getName());
            pst.setString(2,item.getDiscription());
            pst.setInt(3,item.getPrice());
            pst.setInt(4,item.getInstock());
            pst.setInt(5,item.getSellerId());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }

    }

    public void deleteById(int id) {
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "delete from items where id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
    }

    public void updateItem(Item item) {
        Connection conn =  DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "update items set name=?, discription=?,price=?,instock=? where id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,item.getName());
            pst.setString(2,item.getDiscription());
            pst.setInt(3,item.getPrice());
            pst.setInt(4,item.getInstock());
            pst.setInt(5,item.getId());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,pst,rs);
        }
    }
}

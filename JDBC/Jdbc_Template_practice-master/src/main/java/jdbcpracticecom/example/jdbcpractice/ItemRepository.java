package jdbcpracticecom.example.jdbcpractice;


import jdbcpracticecom.example.jdbcpractice.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @Autowired
    JdbcTemplate template;

    /*Getting all Items from table*/
    public List<Item> getAllItems() {
        List<Item> items = template.query("select id, name,category from item", (result, rowNum) -> new Item(result.getInt("id"),
                result.getString("name"), result.getString("category")));
        return items;
    }

    //get one item
    public Item getItem(int itemId) {
        String query = "SELECT * FROM item WHERE ID=?";
        Item item = template.queryForObject(query, new Object[]{itemId}, new
                BeanPropertyRowMapper<>(Item.class));

        return item;
    }

    //add item to database
    public int addItem(int id, String name, String category) {
        String query = "INSERT INTO item VALUES(?,?,?)";
        return template.update(query, id, name, category);
    }

    //delete item from database
    public int deleteItem(int itemId){
        String query = "DELETE FROM ITEM WHERE ID =?";
        return template.update(query,itemId);
    }

    //update item in database
    public void update(Integer id,String name,String category){
        String query= "update item set name = ?,category = ? where id = ?";
        template.update(query, name,category, id);
        System.out.println("Updated Record with ID = " + id );
        return;
    }





}
package com.prototype.protoapp.stock.repository;

import com.prototype.protoapp.stock.entity.Stock;
import com.prototype.protoapp.stock.form.GetForm;
import com.prototype.protoapp.stock.form.PostForm;
import com.prototype.protoapp.stock.form.PutForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockDao implements IStockDao{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    public StockDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stock> findList(GetForm form) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT s.id, s.category, s.title, s.quantity, DATE_FORMAT(s.ymd, '%Y/%m/%d') AS ymd, update_datetime, c.name "
                + "FROM stocks AS s INNER JOIN category_code AS c ON s.category = c.cd "
                );



        Map<String, String> param = new HashMap<>();
        if(form.getCategory() != null && form.getCategory() != "") {
            sqlBuilder.append(" AND c.cd = :cd");
            param.put("cd", form.getCategory());
        }
        /*
        if(form.getYmd() != null && form.getYmd() != "") {
            sqlBuilder.append(" AND TO_CHAR(s.ymd, 'YYYY/MM') = :ymd");
            param.put("ymd", form.getYmd());
        }
         */

        String sql = sqlBuilder.toString();

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
        List<Stock> list = new ArrayList<Stock>();

        for(Map<String, Object> result : resultList) {
            Stock stock = new Stock();
            stock.setId((int) result.get("id"));
            stock.setCategory((String) result.get("category"));
            stock.setTitle((String) result.get("title"));
            stock.setQuantity((int) result.get("quantity"));
            stock.setYmd((String) result.get("ymd"));
            stock.setUpdate_datetime((Timestamp) result.get("update_datetime"));
            stock.setName((String) result.get("name"));
            list.add(stock);
        }

        return list;
    }

    @Override
    public int insert(PostForm form) {
        int count = 0;
        String sql = "INSERT INTO stocks(category, title, quantity, ymd, update_datetime) "
                + "VALUES(:category, :title, :quantity, :ymd, :update_datetime)";

        Map<String, Object> param = new HashMap<>();
        param.put("category", form.getCategoryForm());
        param.put("title", form.getTitleForm());
        param.put("quantity", form.getQuantityForm());
        param.put("ymd", form.getYmdForm());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        param.put("update_datetime", timestamp);
        count = jdbcTemplate.update(sql, param);
        return count;
    }

    @Override
    public Stock findById(int id) throws IncorrectResultSizeDataAccessException {
        String sql = "SELECT s.id, s.category, s.title, s.quantity, DATE_FORMAT(s.ymd, '%Y/%m/%d') AS ymd, s.update_datetime, c.name "
                + "FROM stocks AS s INNER JOIN category_code AS c ON s.category = c.cd "
                + "WHERE s.id = :id";

        Map<String, Object> param = new HashMap<>();
        param.put("id", id);

        Map<String, Object> result = jdbcTemplate.queryForMap(sql, param);
        Stock stock = new Stock();
        stock.setId((int) result.get("id"));
        stock.setCategory((String) result.get("category"));
        stock.setTitle((String) result.get("title"));
        stock.setQuantity((int) result.get("quantity"));
        stock.setYmd((String) result.get("ymd"));
        stock.setUpdate_datetime((Timestamp)result.get("update_datetime"));
        stock.setName((String) result.get("name"));

        return stock;
    }

    @Override
    public int update(PutForm form) {
        int count = 0;
        String sql = "UPDATE stocks "
                + "SET category=:category, title=:title, quantity=:quantity, ymd=:ymd, update_datetime=:update_datetime "
                + "WHERE id=:id";

        Map<String, Object> param = new HashMap<>();
        param.put("id", form.getId());
        param.put("category", form.getCategoryForm());
        param.put("title", form.getTitleForm());
        param.put("quantity", form.getQuantityForm());
        param.put("ymd", form.getYmdForm());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        param.put("update_datetime", timestamp);
        count = jdbcTemplate.update(sql, param);
        return count;
    }

    @Override
    public int delete(int id) {
        int count = 0;
        String sql = " DELETE FROM stocks " + " WHERE id=:id ";

        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        count = jdbcTemplate.update(sql, param);
        return count;
    }
}
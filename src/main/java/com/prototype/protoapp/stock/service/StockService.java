package com.prototype.protoapp.stock.service;

import com.prototype.protoapp.stock.entity.Stock;
import com.prototype.protoapp.stock.form.GetForm;
import com.prototype.protoapp.stock.form.PostForm;
import com.prototype.protoapp.stock.form.PutForm;
import com.prototype.protoapp.stock.repository.IStockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StockService {
    private final IStockDao dao;

    @Autowired
    public StockService(IStockDao dao) {
        this.dao = dao;
    }
    public List<Stock> findList(GetForm form) {
        return dao.findList(form);
    }

    public int insert(PostForm form) {
        return dao.insert(form);
    }

    public Stock findById(int id) {
        try {
            return dao.findById(id);
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    public int update(PutForm form) {
        return dao.update(form);
    }

    public int delete(int id) {
        return dao.delete(id);
    }

}

package com.prototype.protoapp.stock.repository;

import com.prototype.protoapp.stock.entity.Stock;
import com.prototype.protoapp.stock.form.GetForm;
import com.prototype.protoapp.stock.form.PostForm;
import com.prototype.protoapp.stock.form.PutForm;

import java.util.List;

public interface IStockDao {
    List<Stock> findList(GetForm form);

    int insert(PostForm form);

    Stock findById(int id);

    int update(PutForm form);

    int delete(int id);

}
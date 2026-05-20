package com.example.dao;

import com.example.entity.BeerOrder;

import java.util.List;

public interface BeerOrderDAO {
    List<BeerOrder> getAll();
    public BeerOrder findById(int id);
    public void insert(BeerOrder order);
    public void update(BeerOrder order);
    public void delete(int id);
}

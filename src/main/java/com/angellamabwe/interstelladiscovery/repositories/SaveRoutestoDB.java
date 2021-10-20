package com.angellamabwe.interstelladiscovery.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angellamabwe.interstelladiscovery.entities.Route;

@Repository
public interface SaveRoutestoDB extends CrudRepository<Route, Integer> {

}

package com.angellamabwe.interstelladiscovery.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angellamabwe.interstelladiscovery.entities.Planet;

@Repository
public interface SavePlanetstoDB extends CrudRepository<Planet, String> {

}
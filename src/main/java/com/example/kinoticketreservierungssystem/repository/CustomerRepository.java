package com.example.kinoticketreservierungssystem.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.kinoticketreservierungssystem.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCosmosRepository<Customer, String> {

}

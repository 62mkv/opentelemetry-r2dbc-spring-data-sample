package org.example.repository;

import org.example.entity.Address;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends R2dbcRepository<Address, UUID> {
}

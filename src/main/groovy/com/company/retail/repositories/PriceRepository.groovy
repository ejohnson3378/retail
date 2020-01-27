package com.company.retail.repositories

import com.company.retail.entities.Price
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
public interface PriceRepository extends CrudRepository<Price, String> {
}

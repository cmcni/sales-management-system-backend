package com.cmcni.sales_management_system_backend.domain.buyer.repository;

import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}

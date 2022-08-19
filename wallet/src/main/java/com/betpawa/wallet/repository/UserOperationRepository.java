package com.betpawa.wallet.repository;

import com.betpawa.wallet.entity.UserOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOperationRepository extends JpaRepository<UserOperation,Long> {
    List<UserOperation> findAllById(Long id);
}

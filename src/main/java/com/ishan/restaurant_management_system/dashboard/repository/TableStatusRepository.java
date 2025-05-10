package com.ishan.restaurant_management_system.dashboard.repository;



import com.ishan.restaurant_management_system.dashboard.model.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TableStatusRepository extends JpaRepository<TableStatus, Integer> {
    
    List<TableStatus> findByStatus(TableStatus.Status status);
    
    @Query("SELECT COUNT(t) FROM TableStatus t WHERE t.status = :status")
    long countByStatus(TableStatus.Status status);
    
    @Query("SELECT t.status as status, COUNT(t) as count FROM TableStatus t GROUP BY t.status")
    List<Map<String, Object>> getTableStatusCounts();
}
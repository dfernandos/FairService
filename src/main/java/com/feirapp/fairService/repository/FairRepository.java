package com.feirapp.fairService.repository;

import com.feirapp.fairService.entity.Fair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FairRepository extends JpaRepository<Fair, Integer> {

    public List<Fair> getByweekday(String weekDay);
}

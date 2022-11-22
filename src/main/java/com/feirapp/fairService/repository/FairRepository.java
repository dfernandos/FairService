package com.feirapp.fairService.repository;

import com.feirapp.fairService.entity.Fair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FairRepository extends JpaRepository<Fair, Integer> {

    public List<Fair> getByweekday(String weekDay);
}

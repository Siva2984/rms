package com.dtw.rms.repository;

import com.dtw.rms.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RmsRepository extends JpaRepository<Rate, Long> {
}

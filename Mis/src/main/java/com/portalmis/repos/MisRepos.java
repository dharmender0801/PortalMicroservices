package com.portalmis.repos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.aspectj.weaver.tools.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.portalmis.Model.DailyMis;

public interface MisRepos extends JpaRepository<DailyMis, Long> {
	@Query(value = "SELECT * FROM tbl_mis_etl WHERE DATE(date_time) BETWEEN :start AND :end",nativeQuery = true) 
	List<DailyMis> findByDateTimeBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);


}

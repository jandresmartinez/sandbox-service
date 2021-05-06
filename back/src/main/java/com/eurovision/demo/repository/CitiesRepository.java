package com.eurovision.demo.repository;

import com.eurovision.demo.domain.Cities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository  extends PagingAndSortingRepository<Cities, Long> {
    Page<Cities> findByNameContaining(String name, Pageable pageable);

}

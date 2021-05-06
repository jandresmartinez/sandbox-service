package com.eurovision.demo.service;

import com.eurovision.demo.domain.Cities;
import com.eurovision.demo.exceptions.ObjectNotFoundException;
import com.eurovision.demo.repository.CitiesRepository;
import com.eurovision.demo.service.impl.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CitiesService extends AbstractService<Cities>   {

    private CitiesRepository citiesRepository;


    @Autowired
    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }


    public Cities findById(long id) {
        Optional<Cities> opt = citiesRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            throw new ObjectNotFoundException("City with id:"+id+ " not found.");
    }

    public Page<Cities> findByNameContaining(String name,final int page, final int size,String direction,String orderBy) {
        return citiesRepository.findByNameContaining(name, PageRequest.of(page, size, Sort.Direction.fromString(direction),orderBy));
    }

    @Override
    protected PagingAndSortingRepository<Cities, Long> getRepository() {
        return citiesRepository;
    }
}

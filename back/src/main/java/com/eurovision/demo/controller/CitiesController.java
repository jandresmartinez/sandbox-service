package com.eurovision.demo.controller;


import com.eurovision.demo.domain.Cities;
import com.eurovision.demo.dto.CitiesDTO;
import com.eurovision.demo.dto.RestCitiesDTOResponseBuilder;
import com.eurovision.demo.exceptions.ObjectNotFoundException;
import com.eurovision.demo.response.JsonResponseCreator;
import com.eurovision.demo.service.CitiesService;
import com.eurovision.demo.utils.CitiesUtil;
import com.eurovision.demo.utils.Consts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/cities")
public class CitiesController {


    private CitiesService citiesService;

    @Autowired
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @ApiOperation(value = "One city", nickname ="oneCity", tags = { "Cities" })
    @GetMapping(path = "/{id}")
    public @ResponseBody
    HttpEntity<CitiesDTO> getSingleCity(@PathVariable Long id ) {
        Cities city = citiesService.findOne(id);
        if (city==null)
            throw new ObjectNotFoundException("City with id:"+id+ " not found.");
        return new ResponseEntity<>(
                CitiesDTO
                        .builder().withCities(city).build(),  HttpStatus.OK);
    }

    @ApiOperation(value = "List of cities", nickname ="listCities", tags = { "Cities" })
    @GetMapping(path = "/queryByPage")
    public @ResponseBody
    HttpEntity<JsonResponseCreator<CitiesDTO>> getAllCities(
            @ApiParam(value = "Filter by the name of the city.", example = "Madrid")
            @RequestParam(required = false) String name,
            @ApiParam(value = "Number of page.", example = Consts.CONTROLLERPAGEDEFAULTVALUE)
            @RequestParam(value = "page", required = false, defaultValue = Consts.CONTROLLERPAGEDEFAULTVALUE) Integer pageNo,
            @ApiParam(value = "Size of page.", example = Consts.CONTROLLERSIZEDEFAULTVALUE)
            @RequestParam(value = "size", required = false, defaultValue = Consts.CONTROLLERSIZEDEFAULTVALUE) Integer pageSize,
            @ApiParam(value = "Cities direction order.", example = Consts.CONTROLLERDIRECTIONDEFAULTVALUE)
            @RequestParam(value = "direction", required = false, defaultValue = Consts.CONTROLLERDIRECTIONDEFAULTVALUE) String direction) {

        Page<Cities> page;
        if (name==null)
            page = citiesService.findPaginated(pageNo, pageSize,direction,Consts.CONTROLLERORDERBYDEFAULTVALUE);
        else
            page = citiesService.findByNameContaining(name,pageNo, pageSize,direction,Consts.CONTROLLERORDERBYDEFAULTVALUE);
        int totalPages=(int)Math.ceil(((Number)page.getTotalElements()).doubleValue()/pageSize);

        List<CitiesDTO> citiesList = RestCitiesDTOResponseBuilder.createCitiesDTOResponseList(page.getContent());
        JsonResponseCreator<CitiesDTO> jsonResponseCreator = new JsonResponseCreator<>(page.getTotalElements(), totalPages,pageNo, pageSize,citiesList);
        return new ResponseEntity<>(jsonResponseCreator,  HttpStatus.OK);
    }

    @ApiOperation(value = "Order the cities (table eurovisiondb.cities) alphabetically, then obtain the longest possible sequence of city Id's which \n" +
            "form an ascending list of numbers (note: the sequence of id's do not have to be adjacent entries in the table).", nickname ="longestSequence", tags = { "Cities" })
    @GetMapping(path = "/longest-sequence")
    public @ResponseBody
    HttpEntity<String> getLongestSequence(
            @ApiParam(value = "Size of page.", example = Consts.CONTROLLERSIZEDEFAULTVALUE)
            @RequestParam(value = "size", required = false, defaultValue = Consts.CONTROLLERSIZEDEFAULTVALUE) Integer pageSize
          ) {

        Page<Cities> page = citiesService.findPaginated(
                Integer.parseInt(Consts.CONTROLLERPAGEDEFAULTVALUE),
                pageSize,
                Consts.CONTROLLERDIRECTIONDEFAULTVALUE,
                Consts.CONTROLLERORDERBYDEFAULTVALUE);
        return new ResponseEntity<>(CitiesUtil.getLongestSequence(page.getContent()),  HttpStatus.OK);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> onObjectNotFoundException(ObjectNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("%s - %s",
                HttpStatus.NOT_FOUND, ex.getMessage()));
    }


}

package com.yellowstone.cleantogather.api.marker;

import com.yellowstone.cleantogather.api.common.exception.NotFoundException;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController // This means this class is a rest controller
@RequestMapping(path="/markers") // This means URL's start with /events (after Application path)

public class MarkerController {

	private final MarkerRepository markerRepository;

    @Autowired
    public MarkerController(MarkerRepository markerRepository, ModelMapper mapper) {
        this.markerRepository = markerRepository;
    }
    
    @ApiOperation("Create a new marker")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Marker postMarker(@RequestBody Marker marker) {
        return markerRepository.save(marker);
    }
    
    @ApiOperation("Get an marker by his id")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Marker getMarker(@PathVariable("id") Long id) {
        return markerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ApiOperation("Get all the markers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Marker> getAllMarkers() {
        return (List<Marker>) markerRepository.findAll();
    }

    @ApiOperation("Delete an marker")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")	
    public Marker deleteMarker(@PathVariable("id") Long id) {
        Marker deletedMarker = markerRepository.findById(id).orElseThrow(NotFoundException::new);
        markerRepository.deleteById(id);
        return deletedMarker;
    }
}
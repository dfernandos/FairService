package com.feirapp.fairService.service;

import com.feirapp.fairService.entity.Fair;
import com.feirapp.fairService.exceptions.FairException;
import com.feirapp.fairService.repository.FairRepository;
import com.feirapp.fairService.utils.Marker;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FairService {

    @Autowired
    private FairRepository fairRepository;

    public Fair saveFair(Fair fair){
       return fairRepository.save(fair);
    }

    public List<Fair> saveFairs(List<Fair> fairs){
        return fairRepository.saveAll(fairs);
    }

    public List<Fair> getFairs(){
        return fairRepository.findAll();
    }

    public boolean deleteFair(int id) throws FairException{
        try{
            fairRepository.deleteById(id);
            return true;
        }
        catch (Exception ex){
            throw new FairException();
        }
    }

    public List<Fair> getFairByWeekDay(String weekday){
        return fairRepository.getByweekday(weekday);
    }

    public List<Marker> getMarkers(){
        List<Marker> markers = new ArrayList<>();
        getFairs().stream().
                forEach((Fair fair) -> {
                    Marker marker = new Marker(fair.getName(), fair.getLatitude(), fair.getLongitude());
                    markers.add(marker);
                });

        return markers;
    }

    public Optional<Fair> getFair(int id){
        return fairRepository.findById(id);
    }

}

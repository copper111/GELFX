package ru.gelfx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aer on 30.11.16.
 */
@Service
public class gitService {

    @Autowired
    private BashComandService bashComandService;

    public String openExistingRepository(String url){

        return bashComandService.executeCommand("cd " + url);
    }
}

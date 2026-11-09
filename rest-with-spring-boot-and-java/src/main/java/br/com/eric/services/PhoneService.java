package br.com.eric.services;

import br.com.eric.model.Phone;
import br.com.eric.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PhoneService {

    private final Logger logger = Logger.getLogger(PhoneService.class.getName());

    @Autowired
    PhoneRepository repository;

//    @Query("SELECT p FROM Phone p LEFT JOIN FETCH p.person")
    public List<Phone> findAll(){
        logger.info("Finding all Phones!");
        return repository.findAll();
    }
}

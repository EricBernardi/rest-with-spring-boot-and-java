package br.com.eric.services;

import br.com.eric.model.Phone;
import br.com.eric.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PhoneService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PhoneRepository repository;

    @Query("SELECT u FROM phone u LEFT JOIN FETCH u.person objPerson WHERE u.person_id = objPerson.id")
    public List<Phone> findAll(){
        logger.info("Finding all Phones!");
        List<Phone> phones = repository.findAll();
        return phones;
    }
}

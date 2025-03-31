package br.com.eric.services;

import br.com.eric.data.dto.PersonDTO;
import br.com.eric.exception.ResourceNotFoundException;
import static br.com.eric.mapper.ObjectMapper.parseListObject;
import static br.com.eric.mapper.ObjectMapper.parseObject;
import br.com.eric.model.Person;
import br.com.eric.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all Person!");
        return parseListObject(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID")
        );
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Register a new person!");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO updatePerson(PersonDTO person){
        logger.info("Updating a person!");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting a person!");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}

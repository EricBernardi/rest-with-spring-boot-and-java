package br.com.eric.services;

import br.com.eric.data.dto.PersonDTO;
import br.com.eric.exception.ResourceNotFoundException;
import static br.com.eric.mapper.ObjectMapper.parseListObject;
import static br.com.eric.mapper.ObjectMapper.parseObject;

import br.com.eric.mapper.PersonMapper;
import br.com.eric.model.Person;
import br.com.eric.model.Phone;
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
    private PersonMapper personMapper;

    public PersonMapper getPersonMapper(){
        if (personMapper == null) {
            personMapper = new PersonMapper();
        }
        return personMapper;
    }

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all Person!");

        List<Person> peoples = repository.findAll();
        List<PersonDTO> peoplesDTO = new ArrayList<>();

        if (peoples != null && !peoples.isEmpty()){
            peoples.forEach(person -> {
                peoplesDTO.add(getPersonMapper().transformToDTO(person));
            });
        }

        return peoplesDTO;
    }

    public List<PersonDTO> findAllWithPhone() {
        logger.info("Finding all Person with our phone number!");

        List<Person> peoples = repository.findAllWithPhones();
        List<PersonDTO> peoplesDTO = new ArrayList<>();

        if (peoples != null && !peoples.isEmpty()){
            peoples.forEach(person -> {
                peoplesDTO.add(getPersonMapper().transformToDTO(person));
            });
        }

        return peoplesDTO;
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

        PersonMapper personMapper = new PersonMapper();
        Person entity = personMapper.transformToEntity(person);

        for (Phone phone : entity.getPhones()) {
            phone.setPerson(entity);
        }

        return personMapper.transformToDTO(repository.save(entity));
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

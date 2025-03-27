package br.com.eric.services;

import br.com.eric.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all Person!");

        List<Person> people = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            people.add(person);
        }
        return people;
    }

    public Person findById(String id) {
        logger.info("Finding one Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Eric");
        person.setLastName("Almeida");
        person.setAddress("Porto Alegre - RS - Brasil");
        person.setGender("Male");
        return person;
    }

    public Person register(Person person){
        logger.info("Register a new person!");
        return person;
    }

    public Person updatePerson(Person person){
        logger.info("Updating a person!");
        return person;
    }

    public void delete(String id){
        logger.info("Deleting a person!");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Address " + i);
        person.setGender("Male");
        return person;
    }
}

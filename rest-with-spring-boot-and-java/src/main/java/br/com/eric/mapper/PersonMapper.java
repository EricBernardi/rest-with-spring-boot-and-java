package br.com.eric.mapper;

import br.com.eric.data.dto.PersonDTO;
import br.com.eric.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person transformToEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setGender(personDTO.getGender());
        person.setAddress(personDTO.getAddress());
        PhoneMapper phoneMapper = new PhoneMapper();
        personDTO.getPhones().forEach(phone -> person.getPhones().add(phoneMapper.transformToEntity(phone)));

        return person;
    }

    public PersonDTO transformToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setGender(person.getGender());
        personDTO.setAddress(person.getAddress());
        if (person.getPhones() != null && !person.getPhones().isEmpty()) {
            PhoneMapper phoneMapper = new PhoneMapper();
            person.getPhones().forEach(phone -> personDTO.getPhones().add(phoneMapper.transformToDTO(phone)));
        }

        return personDTO;
    }
}

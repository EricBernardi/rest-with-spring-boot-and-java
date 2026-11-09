package br.com.eric.services;

import br.com.eric.model.Person;
import br.com.eric.model.Phone;
import br.com.eric.repository.PhoneRepository;
import br.com.eric.utils.enums.TypePhone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneService phoneService;

    private Phone criarTelefone(Long id) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName("Eric");
        person.setLastName("Bernardi");
        person.setAddress("Rua A, 123");
        person.setGender("Male");

        Phone phone = new Phone();
        phone.setId(id);
        phone.setNumber("48997654125");
        phone.setType(TypePhone.CELLPHONE);
        phone.setPerson(person);

        return phone;
    }

    @Test
    @DisplayName("findAll: deve retornar a lista de telefones quando existir")
    void findAllDeveRetornarLista(){
        when(phoneRepository.findAll()).thenReturn(List.of(criarTelefone(1L), criarTelefone(2L)));

        List<Phone> resultado = phoneService.findAll();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.getFirst().getId()).isEqualTo(1L);
        assertThat(resultado.getFirst().getNumber()).isEqualTo("48997654125");

        verify(phoneRepository).findAll();
    }

    @Test
    @DisplayName("findAllEmpty: deve retornar lista de telefones vazia quando não existir")
    void findAllDeveRetornarListaVazia(){
        when(phoneRepository.findAll()).thenReturn(List.of());

        assertThat(phoneService.findAll()).isEmpty();
    }



}

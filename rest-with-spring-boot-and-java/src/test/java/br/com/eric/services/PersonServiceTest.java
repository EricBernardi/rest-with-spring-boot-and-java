package br.com.eric.services;

import br.com.eric.data.dto.PersonDTO;
import br.com.eric.exception.ResourceNotFoundException;
import br.com.eric.model.Person;
import br.com.eric.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    private Person criarPerson(Long id) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName("Eric");
        person.setLastName("Bernardi");
        person.setAddress("Rua A, 123");
        person.setGender("Male");
        return person;
    }

    @Test
    @DisplayName("findAll: deve retornar lista de DTOs quando existirem pessoas")
    void findAllDeveRetornarListaDeDTOs() {
        when(repository.findAll()).thenReturn(List.of(criarPerson(1L), criarPerson(2L)));

        List<PersonDTO> resultado = service.findAll();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.getFirst().getId()).isEqualTo(1L);
        assertThat(resultado.getFirst().getFirstName()).isEqualTo("Eric");
    }

    @Test
    @DisplayName("findAll: deve retornar lista vazia quando não houver pessoas")
    void findAllDeveRetornarListaVazia() {
        when(repository.findAll()).thenReturn(List.of());

        assertThat(service.findAll()).isEmpty();
    }

    @Test
    @DisplayName("findAll: deve retornar lista vazia quando o repository devolver null")
    void findAllDeveTratarNull() {
        when(repository.findAll()).thenReturn(null);

        assertThat(service.findAll()).isEmpty();
    }

    @Test
    @DisplayName("findAllWithPhone: deve retornar DTOs usando a query com telefones")
    void findAllWithPhoneDeveRetornarDTOs() {
        when(repository.findAllWithPhones()).thenReturn(List.of(criarPerson(1L)));

        List<PersonDTO> resultado = service.findAllWithPhone();

        assertThat(resultado).hasSize(1);
        verify(repository).findAllWithPhones();
        verify(repository, never()).findAll();
    }

    @Test
    @DisplayName("findById: deve retornar o DTO quando o id existir")
    void findByIdDeveRetornarDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(criarPerson(1L)));

        PersonDTO resultado = service.findById(1L);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getFirstName()).isEqualTo("Eric");
        assertThat(resultado.getLastName()).isEqualTo("Bernardi");
    }

    @Test
    @DisplayName("findById: deve lançar ResourceNotFoundException quando o id não existir")
    void findByIdDeveLancarExcecao() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("No records found for this ID");
    }

    @Test
    @DisplayName("create: deve salvar a entidade e retornar o DTO com o id gerado")
    void createDeveSalvarERetornarDTO() {
        PersonDTO entrada = new PersonDTO();
        entrada.setFirstName("Eric");
        entrada.setLastName("Bernardi");
        entrada.setAddress("Rua A, 123");
        entrada.setGender("Male");

        when(repository.save(any(Person.class))).thenAnswer(invocation -> {
            Person p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        PersonDTO resultado = service.create(entrada);

        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getFirstName()).isEqualTo("Eric");

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getLastName()).isEqualTo("Bernardi");
    }

    @Test
    @DisplayName("updatePerson: deve atualizar os campos e salvar")
    void updateDeveAtualizarCampos() {
        Person existente = criarPerson(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any(Person.class))).thenAnswer(inv -> inv.getArgument(0));

        PersonDTO novo = new PersonDTO();
        novo.setId(1L);
        novo.setFirstName("Carlos");
        novo.setLastName("Silva");
        novo.setAddress("Rua B, 456");
        novo.setGender("Male");

        PersonDTO resultado = service.updatePerson(novo);

        assertThat(resultado.getFirstName()).isEqualTo("Carlos");
        assertThat(resultado.getLastName()).isEqualTo("Silva");
        assertThat(resultado.getAddress()).isEqualTo("Rua B, 456");
        verify(repository).save(existente);
    }

    @Test
    @DisplayName("updatePerson: deve lançar exceção e não salvar quando o id não existir")
    void updateDeveLancarExcecao() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        PersonDTO dto = new PersonDTO();
        dto.setId(99L);

        assertThatThrownBy(() -> service.updatePerson(dto))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(repository, never()).save(any());
    }
    
    @Test
    @DisplayName("delete: deve remover a entidade quando o id existir")
    void deleteDeveRemover() {
        Person existente = criarPerson(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(existente));

        service.delete(1L);

        verify(repository).delete(existente);
    }

    @Test
    @DisplayName("delete: deve lançar exceção e não remover quando o id não existir")
    void deleteDeveLancarExcecao() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(99L))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(repository, never()).delete(any());
    }
}
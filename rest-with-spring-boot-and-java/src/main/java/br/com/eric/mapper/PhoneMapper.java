package br.com.eric.mapper;

import br.com.eric.data.dto.PhoneDTO;
import br.com.eric.model.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {

    public Phone transformToEntity(PhoneDTO phoneDTO){
        Phone phone = new Phone();
        phone.setType(phoneDTO.getType());
        phone.setNumber(phoneDTO.getNumber());
        return phone;
    }

    public PhoneDTO transformToDTO(Phone phone){
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setType(phone.getType());
        phoneDTO.setNumber(phone.getNumber());
        return phoneDTO;
    }
}

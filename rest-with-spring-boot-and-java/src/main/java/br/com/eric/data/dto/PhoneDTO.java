package br.com.eric.data.dto;

import br.com.eric.utils.enums.TypePhone;

import java.io.Serializable;
import java.util.Objects;

public class PhoneDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String number;
    private TypePhone type;

    public PhoneDTO() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TypePhone getType() {
        return type;
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhoneDTO phoneDTO)) return false;
        return Objects.equals(getNumber(), phoneDTO.getNumber()) && getType() == phoneDTO.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getType());
    }
}

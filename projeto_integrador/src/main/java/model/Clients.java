package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "client")
public class Clients {

    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Nome não pode estar vazio!")
    @Size(min = 1, max = 25, message = "O nome do usuário deve ser no máximo 30 caracteres")
    @Pattern(regexp = "[^0-9]*", message = "O nome de usuário não pode conter digitos.")
    private String name;


    @NotNull(message = "Telefone Vazio!")
    @NotEmpty(message = "Telefone Vazio!")
    private String phone;

    @Size(min = 1, max = 25, message = "Deve ter no máximo 25 caracteres")
    @NotNull(message = "Cidade não pode estar vazia!")
    @NotEmpty(message = "Cidade não pode estar vazia!")
    private String city;

    @Size(min = 1, max = 25, message = "Deve ter no máximo 25 caracteres")
    private String street;

    @Size(min = 1, max = 10, message = "Deve ter no máximo 25 caracteres")
    private String neighborhood;

    @Size(min = 1, max = 10, message = "Deve ter no máximo 25 caracteres")
    private String number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}

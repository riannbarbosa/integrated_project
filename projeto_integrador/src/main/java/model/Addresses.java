package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Addresses {

    @Id
    @Column(name = "client_id")
    private Integer id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Clients clients;

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
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

    public Integer getId() {
        return id;
    }





}

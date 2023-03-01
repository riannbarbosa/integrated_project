package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @Size(min = 1, max = 30, message = "Deve ter no máximo 30 caracteres!")
    @NotNull(message = "Não pode estar vazia!")
    private String name;


    @NotNull(message = "Não pode estar vazia!")
    private Date order_date;


    private Boolean status;

    @NotNull(message = "Não pode estar vazia!")
    private Double value;

    @Size(min = 1, max = 255, message = "Deve ter no máximo 255 caracteres!")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clients getClients() {
        return client;
    }

    public void setClients(Clients clients) {
        this.client = clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
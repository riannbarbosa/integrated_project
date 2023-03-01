package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "resources")

public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Integer id;

    @Size(min = 1, max = 30, message = "Deve ter no máximo 30 caracteres")
    @NotNull(message = "Não pode estar vazia!")
    @NotEmpty(message = "Não pode estar vazia!")
    private String name;

    @NotNull(message = "Não pode estar vazia!")
    private Date datebuy;


    @NotNull(message = "Não pode estar vazia!")
    private double value;

    @Size(min = 1, max = 255, message = "Deve ter no máximo 255 caracteres!")
    private String desc_type;

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

    public Date getDatebuy() {
        return datebuy;
    }

    public void setDatebuy(Date datebuy) {
        this.datebuy = datebuy;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDesc_type() {
        return desc_type;
    }

    public void setDesc_type(String desc_type) {
        this.desc_type = desc_type;
    }
}
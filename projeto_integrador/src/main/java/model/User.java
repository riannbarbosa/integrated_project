package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @NotNull(message = "Nome não pode estar vazio!")
    @Size(min = 1, max = 25, message = "O nome do usuário deve ser no máximo 30 caracteres")
    @Pattern(regexp = "[^0-9]*", message = "O nome de usuário não pode conter digitos.")
    private String usuario;


    @NotNull(message = "Email não pode estar vazio!")
    @NotEmpty(message = "Email Vazio!")
    @Email(message = "Não é um endereço de e-mail válido")
    private String email;


    @NotNull(message = "Coloque a senha!")
    @NotEmpty(message = "Coloque a senha!")
    private String senha;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

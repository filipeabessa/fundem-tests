package ifpe.edu.user;

import ifpe.edu.user.dtos.CreateUserDto;

public class User {
    Long id;
    String nomeCompleto;
    String cpf;
    String email;
    String dataNascimento;
    String numeroTelefone;
    String senha;

    public User(CreateUserDto createUserDto) {
        this.nomeCompleto = createUserDto.nomeCompleto();
        this.cpf = createUserDto.cpf();
        this.email = createUserDto.email();
        this.dataNascimento = createUserDto.dataNascimento();
        this.numeroTelefone = createUserDto.numeroTelefone();
        this.senha = createUserDto.senha();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getSenha() {
        return senha;
    }
}

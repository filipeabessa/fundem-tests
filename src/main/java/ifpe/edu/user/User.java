package ifpe.edu.user;

import ifpe.edu.authentication.ReturnedSocialMediaInfos;
import ifpe.edu.user.dtos.CreateUserDto;
import ifpe.edu.user.dtos.UpdateUserDto;

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

    public User(UpdateUserDto updateUserDto) {
        this.id = updateUserDto.id();
        this.nomeCompleto = updateUserDto.nomeCompleto();
        this.cpf = updateUserDto.cpf();
        this.email = updateUserDto.email();
        this.dataNascimento = updateUserDto.dataNascimento();
        this.numeroTelefone = updateUserDto.numeroTelefone();
        this.senha = updateUserDto.senha();
    }

    public User() {

    }

    public User(ReturnedSocialMediaInfos socialMediaInfos) {
        this.nomeCompleto = socialMediaInfos.nomeCompleto();
        this.email = socialMediaInfos.email();
        this.dataNascimento = socialMediaInfos.dataNascimento();
        this.numeroTelefone = socialMediaInfos.numeroTelefone();
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

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

package ifpe.edu.user.dtos;

public record CreateUserDto(
        String nomeCompleto,
        String cpf,
        String email,
        String dataNascimento,
        String numeroTelefone,
        String senha
) {
}

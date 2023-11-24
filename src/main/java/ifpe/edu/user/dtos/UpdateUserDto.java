package ifpe.edu.user.dtos;

public record UpdateUserDto(
        Long id,
        String nomeCompleto,
        String cpf,
        String email,
        String dataNascimento,
        String numeroTelefone,
        String senha
) {
}

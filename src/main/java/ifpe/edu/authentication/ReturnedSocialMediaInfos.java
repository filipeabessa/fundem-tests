package ifpe.edu.authentication;

public record ReturnedSocialMediaInfos(
        String nomeCompleto,
        String email,
        String dataNascimento,
        String numeroTelefone,
        String token
) {
}

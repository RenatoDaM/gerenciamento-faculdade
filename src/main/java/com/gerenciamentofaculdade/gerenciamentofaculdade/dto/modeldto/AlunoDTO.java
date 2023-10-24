package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class AlunoDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "RA não pode estar em branco")
    @Pattern(regexp = "^\\d{25}$", message = "RA deve ser um valor numérico contendo 25 dígitos")
    private String ra;

    @NotBlank(message = "Nome não pode estar em branco")
    @NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "E-mail não pode estar em branco")
    @NotNull(message = "E-mail não pode ser nulo")
    @Email(message = "Campo e-mail possui formato inválido")
    String email;

    @NotBlank(message = "O primeiro telefone não pode estar em branco")
    @NotNull(message = "O primeiro telefone não pode ser nulo")
    @NotEmpty(message = "O primeiro telefone não pode estar vazio")
    @Pattern(regexp = "^\\d{11}$", message = "Telefone deve ser válido. Verifique se foi digitado um número a mais ou a menos")
    private String telefone1;

    @Pattern(regexp = "^\\d{11}$", message = "Telefone deve ser válido")
    private String telefone2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
}


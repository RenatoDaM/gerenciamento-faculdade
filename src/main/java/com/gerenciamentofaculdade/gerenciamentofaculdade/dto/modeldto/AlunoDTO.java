package com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.Objects;

public class AlunoDTO {
    @Schema(accessMode = Schema.AccessMode.AUTO)
    @Positive(message = "Valor de ID deve ser numérico e positivo")
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

    public AlunoDTO(String ra, String nome, String email, String telefone1, String telefone2) {
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    public AlunoDTO() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoDTO alunoDTO = (AlunoDTO) o;
        return Objects.equals(id, alunoDTO.id) && Objects.equals(ra, alunoDTO.ra) && Objects.equals(nome, alunoDTO.nome) && Objects.equals(email, alunoDTO.email) && Objects.equals(telefone1, alunoDTO.telefone1) && Objects.equals(telefone2, alunoDTO.telefone2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ra, nome, email, telefone1, telefone2);
    }
}


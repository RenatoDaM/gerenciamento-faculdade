package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.search.param.AlunoParametroFiltro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
    Optional<AlunoModel> findByRa(String ra);

    @Query("SELECT new com.gerenciamentofaculdade.gerenciamentofaculdade.model.AlunoModel(" +
            "a.id, a.ra, a.nome, a.email, a.telefone1, a.telefone2) " +
            "FROM aluno a " +
            "WHERE (:ra IS NULL OR LOWER(a.ra) LIKE %:ra%) " +
            "AND (:nome IS NULL OR LOWER(a.nome) LIKE %:nome%) " +
            "AND (:email IS NULL OR LOWER(a.email) LIKE %:email%)")
    List<AlunoModel> getAllWithFilter(@Param("ra") String ra, @Param("nome") String nome, @Param("email") String email);
    Optional<AlunoModel> findByEmail(String email);
}

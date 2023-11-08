package com.gerenciamentofaculdade.gerenciamentofaculdade.repository;

import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaDisciplinaKey;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaDisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.response.ProfessorLecionaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorLecionaDisciplinaRepository extends JpaRepository<ProfessorLecionaDisciplinaModel, Long>  {
    @Query("SELECT new com.gerenciamentofaculdade.gerenciamentofaculdade.response.ProfessorLecionaResponse(pd.key, d, h.horarioAulaModel) " +
            "FROM professor_leciona_disciplina pd " +
            "JOIN pd.professorModel p " +
            "JOIN pd.disciplinaModel d " +
            "JOIN pd.professorLecionaHorarioList h " +
            "WHERE pd.key.professorId = :professorId")
    List<ProfessorLecionaResponse> returnHorariosAndDisciplinas(@Param("professorId") Long professorId);

    @Query("SELECT d FROM professor_leciona_disciplina pd " +
            "JOIN pd.professorModel p " +
            "JOIN pd.disciplinaModel d " +
            "JOIN pd.professorLecionaHorarioList h " +
            "WHERE pd.key.professorId = :professorId")
    List<DisciplinaModel> findDisciplinasByProfessorId(@Param("professorId") Long professorId);

    @Modifying
    @Query("DELETE FROM professor_leciona_disciplina pl WHERE pl.key.professorId = :professorId AND pl.key.disciplinaId = :disciplinaId")
    void deleteRelation(@Param("professorId")Long professorId, @Param("disciplinaId")Long disciplinaId);
    Optional<ProfessorLecionaDisciplinaModel> findProfessorLecionaDisciplinaModelsByKey(ProfessorLecionaDisciplinaKey key);
}

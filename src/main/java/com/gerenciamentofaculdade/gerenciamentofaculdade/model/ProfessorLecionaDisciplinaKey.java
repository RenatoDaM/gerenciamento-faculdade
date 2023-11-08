package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProfessorLecionaDisciplinaKey implements Serializable {
    @Column(name = "professor_id")
    Long professorId;

    @Column(name = "disciplina_id")
    Long disciplinaId;

    public ProfessorLecionaDisciplinaKey() {
    }

    public ProfessorLecionaDisciplinaKey(Long professorId, Long disciplinaId) {
        this.professorId = professorId;
        this.disciplinaId = disciplinaId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorLecionaDisciplinaKey that = (ProfessorLecionaDisciplinaKey) o;
        return Objects.equals(professorId, that.professorId) && Objects.equals(disciplinaId, that.disciplinaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, disciplinaId);
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}

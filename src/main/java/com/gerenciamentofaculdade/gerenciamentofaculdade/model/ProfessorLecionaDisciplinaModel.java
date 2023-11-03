package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "professor_leciona_disciplina")
@Table(name = "professor_leciona_disciplina")
public class ProfessorLecionaDisciplinaModel {

    @EmbeddedId
    ProfessorLecionaDisciplinaKey key;

    @ManyToOne
    @MapsId("professorId")
    @JoinColumn(name = "professor_id")
    ProfessorModel professorModel;

    @ManyToOne
    @MapsId("disciplinaId")
    @JoinColumn(name = "disciplina_id")
    DisciplinaModel disciplinaModel;

    @Column
    Integer diaLecionado;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false, columnDefinition = "date")
    LocalTime horario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorLecionaDisciplinaModel that = (ProfessorLecionaDisciplinaModel) o;
        return Objects.equals(key, that.key) && Objects.equals(professorModel, that.professorModel) && Objects.equals(disciplinaModel, that.disciplinaModel) && Objects.equals(diaLecionado, that.diaLecionado) && Objects.equals(horario, that.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, professorModel, disciplinaModel, diaLecionado, horario);
    }

    public ProfessorLecionaDisciplinaKey getKey() {
        return key;
    }

    public void setKey(ProfessorLecionaDisciplinaKey key) {
        this.key = key;
    }

    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public void setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
    }

    public DisciplinaModel getDisciplinaModel() {
        return disciplinaModel;
    }

    public void setDisciplinaModel(DisciplinaModel disciplinaModel) {
        this.disciplinaModel = disciplinaModel;
    }

    public Integer getDiaLecionado() {
        return diaLecionado;
    }

    public void setDiaLecionado(Integer diaLecionado) {
        this.diaLecionado = diaLecionado;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}

package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.DiaDaSemanaEnum;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
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

    @OneToMany(mappedBy = "professorLecionaDisciplinaModel", cascade = CascadeType.ALL)
    List<ProfessorLecionaHorarioModel> professorLecionaHorarioList;


    public ProfessorLecionaDisciplinaModel() {
    }

    public ProfessorLecionaDisciplinaModel(ProfessorLecionaDisciplinaKey key, ProfessorModel professorModel, DisciplinaModel disciplinaModel) {
        this.key = key;
        this.professorModel = professorModel;
        this.disciplinaModel = disciplinaModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorLecionaDisciplinaModel that = (ProfessorLecionaDisciplinaModel) o;
        return Objects.equals(key, that.key) && Objects.equals(professorModel, that.professorModel) && Objects.equals(disciplinaModel, that.disciplinaModel) && Objects.equals(professorLecionaHorarioList, that.professorLecionaHorarioList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, professorModel, disciplinaModel, professorLecionaHorarioList);
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


    public List<ProfessorLecionaHorarioModel> getProfessorLecionaHorarioList() {
        return professorLecionaHorarioList;
    }

    public void setProfessorLecionaHorarioList(List<ProfessorLecionaHorarioModel> professorLecionaHorarioList) {
        this.professorLecionaHorarioList = professorLecionaHorarioList;
    }
}

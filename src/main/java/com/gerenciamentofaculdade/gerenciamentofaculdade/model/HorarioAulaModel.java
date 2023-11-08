package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.DiaDaSemanaEnum;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity(name = "horario_aula")
@Table(name = "horario_aula")
public class HorarioAulaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaDaSemanaEnum diaSemana;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    LocalTime horarioInicio;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    LocalTime horarioFim;

    @OneToMany(mappedBy = "horarioAulaModel")
    List<ProfessorLecionaHorarioModel> professorLecionaHorarioList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiaDaSemanaEnum getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaDaSemanaEnum diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public List<ProfessorLecionaHorarioModel> getProfessorLecionaHorarioList() {
        return professorLecionaHorarioList;
    }

    public void setProfessorLecionaHorarioList(List<ProfessorLecionaHorarioModel> professorLecionaHorarioList) {
        this.professorLecionaHorarioList = professorLecionaHorarioList;
    }
}

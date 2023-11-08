package com.gerenciamentofaculdade.gerenciamentofaculdade.model;

import jakarta.persistence.*;

@Entity(name = "professor_leciona_horario")
@Table(name = "professor_leciona_horario")
public class ProfessorLecionaHorarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "professor_id", referencedColumnName = "professor_id"),
            @JoinColumn(name = "disciplina_id", referencedColumnName = "disciplina_id")
    })
    private ProfessorLecionaDisciplinaModel professorLecionaDisciplinaModel;

    @ManyToOne
    @JoinColumn(name="horario_aula_id", nullable = false)
    private HorarioAulaModel horarioAulaModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfessorLecionaDisciplinaModel getProfessorLecionaDisciplinaModel() {
        return professorLecionaDisciplinaModel;
    }

    public void setProfessorLecionaDisciplinaModel(ProfessorLecionaDisciplinaModel professorLecionaDisciplinaModel) {
        this.professorLecionaDisciplinaModel = professorLecionaDisciplinaModel;
    }

    public HorarioAulaModel getHorarioAulaModel() {
        return horarioAulaModel;
    }

    public void setHorarioAulaModel(HorarioAulaModel horarioAulaModel) {
        this.horarioAulaModel = horarioAulaModel;
    }
}

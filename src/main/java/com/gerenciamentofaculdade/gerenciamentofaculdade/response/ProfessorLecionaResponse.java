package com.gerenciamentofaculdade.gerenciamentofaculdade.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamentofaculdade.gerenciamentofaculdade.util.mapper.CursoMapper;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.CursoDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.dto.modeldto.DisciplinaDTO;
import com.gerenciamentofaculdade.gerenciamentofaculdade.enumeration.DiaDaSemanaEnum;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.DisciplinaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.HorarioAulaModel;
import com.gerenciamentofaculdade.gerenciamentofaculdade.model.ProfessorLecionaDisciplinaKey;

import java.time.LocalTime;
import java.util.List;

public class ProfessorLecionaResponse {
    ProfessorLecionaDisciplinaKey relacao;
    Long disciplinaId;
    String nomeDisciplina;
    Integer cargaHoraria;
    CursoDTO curso;
    DiaDaSemanaEnum diaDaSemana;

    /*DisciplinaDTO disciplinaDTO;

    HorarioAulaDTO horarioAulaDTO;*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalTime horarioInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalTime horarioFim;

    public ProfessorLecionaResponse() {
    }

    public ProfessorLecionaResponse(Long id, String nome, String registroConselho, List<DisciplinaDTO> disciplinasList) {

    }

    public ProfessorLecionaResponse(ProfessorLecionaDisciplinaKey relacao, DisciplinaModel disciplinaModel, HorarioAulaModel horarioAulaModel) {
        this.relacao = relacao;
        this.nomeDisciplina = disciplinaModel.getNome();
        this.cargaHoraria = disciplinaModel.getCargaHoraria();
        this.curso = CursoMapper.INSTANCE.modelToDTO(disciplinaModel.getCursoModel());
        this.horarioInicio = horarioAulaModel.getHorarioInicio();
        this.horarioFim = horarioAulaModel.getHorarioFim();
        this.diaDaSemana = horarioAulaModel.getDiaSemana();
        this.disciplinaId = disciplinaModel.getId();
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    public DiaDaSemanaEnum getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemanaEnum diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
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

    public ProfessorLecionaDisciplinaKey getRelacao() {
        return relacao;
    }

    public void setRelacao(ProfessorLecionaDisciplinaKey relacao) {
        this.relacao = relacao;
    }
}

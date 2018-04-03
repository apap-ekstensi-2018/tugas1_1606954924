package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;
import com.example.model.ProgramStudiModel;

public interface ProgramStudiService {
	List<ProgramStudiModel> selectAllProdi();
	
	ProgramStudiModel selectProdi(Integer id_prodi);
	
	List<ProgramStudiModel> selectAllProdibyFakultas(Integer id_univ);
	
}

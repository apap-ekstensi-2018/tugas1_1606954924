package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;

public interface FakultasService {
	List<FakultasModel> selectAllFakultas();
		
	FakultasModel selectFakultas(Integer id_fakultas);
	
	List<FakultasModel> selectFakultasbyUniv(Integer id_univ);
	
}

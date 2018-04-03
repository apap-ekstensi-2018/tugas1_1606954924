package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;
import com.example.model.FakultasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceDatabase implements FakultasService {
	
	@Autowired 
	private FakultasMapper fakultasMapper;
	
	@Override
	public List<FakultasModel> selectAllFakultas() {
		log.info ("select semua fakultas");
		return fakultasMapper.selectAllFakultas();
	}

	@Override
	public FakultasModel selectFakultas(Integer id_fakultas) {
		log.info ("select fakultas dengan id {}", id_fakultas);
		return fakultasMapper.selectFakultas(id_fakultas);
	}

	@Override
	public List<FakultasModel> selectFakultasbyUniv(Integer id_univ) {
		log.info ("select fakultas dengan univ {}", id_univ);
		return fakultasMapper.selectFakultasbyUniv(id_univ);
	}

}

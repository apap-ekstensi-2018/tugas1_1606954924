package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProgramStudiMapper;
import com.example.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService{
	@Autowired
	ProgramStudiMapper prodiMapper;

	@Override
	public List<ProgramStudiModel> selectAllProdi() {
		log.info ("select semua prodi");
		return prodiMapper.selectAllProdis();
	}

	@Override
	public ProgramStudiModel selectProdi(Integer id_prodi) {
		log.info ("select prodi dengan id {}", id_prodi);
		return prodiMapper.selectProdi(id_prodi);
	}

	@Override
	public List<ProgramStudiModel> selectAllProdibyFakultas(Integer id_fakultas) {
		log.info ("select fakultas dengan univ {}", id_fakultas);
		return prodiMapper.selectAllProdibyFakultas(id_fakultas);
	}
	
}

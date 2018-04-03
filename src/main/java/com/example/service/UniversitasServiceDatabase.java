package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UniversitasMapper;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class UniversitasServiceDatabase implements UniversitasService {
	@Autowired
	private UniversitasMapper univMapper;

	@Override
	public List<UniversitasModel> selectAllUniversitas() {
		log.info ("select semua universitas");
		return univMapper.selectAllUniversitas();
	}

	@Override
	public UniversitasModel selectUniversitas(Integer id_univ) {
		log.info ("select universitas dengan id {}", id_univ);
		return univMapper.selectUniversitas(id_univ);
	}
	
}

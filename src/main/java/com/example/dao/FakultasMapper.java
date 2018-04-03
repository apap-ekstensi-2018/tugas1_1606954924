package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.FakultasModel;

@Mapper
public interface FakultasMapper {
	@Select("select id, kode_fakultas, nama_fakultas, id_univ from fakultas where id = #{id_fakultas}")
    FakultasModel selectFakultas (@Param("id_fakultas") Integer id_fakultas);
	
	@Select("select id, kode_fakultas, nama_fakultas, id_univ from fakultas")
    List<FakultasModel> selectAllFakultas();
	
	@Select("select id, kode_fakultas, nama_fakultas, id_univ from fakultas where id_univ = #{id_univ}")
	List<FakultasModel> selectFakultasbyUniv (@Param("id_univ") Integer id_univ);
	
}

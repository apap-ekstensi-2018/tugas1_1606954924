package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.UniversitasModel;

@Mapper
public interface UniversitasMapper {
	@Select("select id, kode_univ, nama_univ from universitas where id = #{id_univ}")
    UniversitasModel selectUniversitas (@Param("id_univ") Integer id_univ);
	
	@Select("select id, kode_univ, nama_univ from universitas")
    List<UniversitasModel> selectAllUniversitas();
	
}

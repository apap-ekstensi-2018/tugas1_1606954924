package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

@Mapper
public interface MahasiswaMapper
{
    @Select("select id, npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, tahun_masuk, jalur_masuk, status, id_prodi from mahasiswa where npm = #{npm}")
    MahasiswaModel selectMahasiswa (@Param("npm") String npm);

    @Select("select npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, tahun_masuk, jalur_masuk, status from mahasiswa")
    List<MahasiswaModel> selectAllStudents ();

    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, tahun_masuk, jalur_masuk, status) "
    		+ "VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, #{tahun_masuk}, #{jalur_masuk}, #{status})")
    void addStudent (MahasiswaModel student);
    
    @Update("UPDATE mahasiswa SET npm = #{npm}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, jenis_kelamin = #{jenis_kelamin}, agama = #{agama}"
    		+ ", golongan_darah = #{golongan_darah}, tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{id_prodi}"
    		+ " where id = #{id}")
    void updateMahasiswa(MahasiswaModel mahasiswa);
    
    @Select("select id, kode_prodi, nama_prodi, id_fakultas from program_studi where id = #{id_prodi}")
    ProgramStudiModel selectProdi (@Param("id_prodi") Integer id_prodi);
    
    @Select("select id, kode_fakultas, nama_fakultas, id_univ from fakultas where id = #{id_fakultas}")
    FakultasModel selectFakultas (@Param("id_fakultas") Integer id_fakultas);
    
    @Select("select id, kode_univ, nama_univ from universitas where id = #{id_univ}")
    UniversitasModel selectUniversitas (@Param("id_univ") Integer id_univ);
    
    @Select("select max(npm) from mahasiswa where npm like CONCAT(#{npm},'%') limit 1")
    String selectNpm (@Param("npm") String npm);
    
    @Select("SELECT COUNT(mahasiswa.npm) as jlh_mahasiswa FROM mahasiswa WHERE status = 'Lulus'"
    		+ "and tahun_masuk = #{tahun_masuk} AND id_prodi = #{id_prodi}")
    Integer getJumlahMhsLulus(@Param("tahun_masuk") String tahun_masuk, @Param("id_prodi") Integer id_prodi);
    
    @Select("SELECT COUNT(mahasiswa.npm) as jlh_mahasiswa FROM mahasiswa WHERE "
    		+ "tahun_masuk = #{tahun_masuk} AND id_prodi = #{id_prodi}")
    Integer getTotalMahasiswa(@Param("tahun_masuk") String tahun_masuk, @Param("id_prodi") Integer id_prodi);
    
    @Select("select id, kode_univ, nama_univ from universitas")
    List<UniversitasModel> selectAllUniversitas();
}

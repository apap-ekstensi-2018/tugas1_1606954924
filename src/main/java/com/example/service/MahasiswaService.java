package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

public interface MahasiswaService
{
    MahasiswaModel selectMahasiswa (String npm);

    List<MahasiswaModel> selectAllStudents ();

    void addStudent (MahasiswaModel student);
    
    void deleteStudent (String npm);
    
    void updateMahasiswa (MahasiswaModel mahasiswa);
   
    ProgramStudiModel selectProdi(Integer id_prodi);
    
    FakultasModel selectFakultas(Integer id_fakultas);
    
    UniversitasModel selectUniversitas(Integer id_univ);
    
    public String selectNpm (String npm);
    
    public Integer jumlahMahasiwaLulus(String tahun_masuk, Integer id_prodi);
    
    public Integer totalMahasiswa(String tahun_masuk, Integer id_prodi);
    
    List<UniversitasModel> selectAllUniversitas();
}

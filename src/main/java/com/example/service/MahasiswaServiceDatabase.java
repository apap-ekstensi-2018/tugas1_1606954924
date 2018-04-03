package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaMapper;
import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService
{
    @Autowired
    private MahasiswaMapper mahasiswaMapper;

    @Override
    public MahasiswaModel selectMahasiswa(String npm)
    {
        log.info ("select mahasiswa dengan npm {}", npm);
        return mahasiswaMapper.selectMahasiswa (npm);
    }


    @Override
    public List<MahasiswaModel> selectAllStudents ()
    {
        log.info ("select semua mahasiswa");
        return mahasiswaMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (MahasiswaModel student)
    {
        mahasiswaMapper.addStudent (student);
    }


    @Override
    public void deleteStudent (String npm)
    {
    }


	@Override
	public void updateMahasiswa(MahasiswaModel mahasiswa) 
	{
		log.info("student " + mahasiswa.getNpm() + " updated");
		mahasiswaMapper.updateMahasiswa(mahasiswa);
	}
	
	@Override
	public ProgramStudiModel selectProdi(Integer id_prodi) {
		log.info ("select program studi dengan kode {}", id_prodi);
        return mahasiswaMapper.selectProdi(id_prodi);
	}


	@Override
	public FakultasModel selectFakultas(Integer id_fakultas) {
		log.info ("select fakultas dengan kode {}", id_fakultas);
		return mahasiswaMapper.selectFakultas(id_fakultas);
	}

	@Override
	public UniversitasModel selectUniversitas(Integer id_univ) {
		log.info ("select universitas dengan kode {}", id_univ);
		return mahasiswaMapper.selectUniversitas(id_univ);
	}
	
	@Override
    public String selectNpm (String npm){
    	log.info("select NPM like (to calculate)");
    	return mahasiswaMapper.selectNpm(npm);
    }


	@Override
	public Integer jumlahMahasiwaLulus(String tahun_masuk, Integer id_prodi) {
		log.info("select count() mahasiswa lulus");
		return mahasiswaMapper.getJumlahMhsLulus(tahun_masuk, id_prodi);
	}


	@Override
	public Integer totalMahasiswa(String tahun_masuk, Integer id_prodi) {
		log.info("select count() seluruh mahasiwa");
		return mahasiswaMapper.getTotalMahasiswa(tahun_masuk, id_prodi);
	}


	@Override
	public List<UniversitasModel> selectAllUniversitas() {
		log.info ("select semua prodi");
        return mahasiswaMapper.selectAllUniversitas();
	} 
	
}

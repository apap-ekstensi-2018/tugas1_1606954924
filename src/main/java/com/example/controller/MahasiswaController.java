package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;
import com.example.service.FakultasService;
import com.example.service.MahasiswaService;
import com.example.service.ProgramStudiService;
import com.example.service.UniversitasService;
@Controller
public class MahasiswaController
{
    @Autowired
    MahasiswaService mahasiswaDAO;
    
    @Autowired
    UniversitasService univDAO;
    
    @Autowired
    FakultasService fakultasDAO;
    
    @Autowired
    ProgramStudiService prodiDAO;

    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<MahasiswaModel> students = mahasiswaDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        return "viewall";
    }
    
    @RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("title", "Home Page");
        return "index";
    }
    
    @RequestMapping("/mahasiswa")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        MahasiswaModel student = mahasiswaDAO.selectMahasiswa (npm);
        ProgramStudiModel prodi = prodiDAO.selectProdi(student.getId_prodi());
        FakultasModel fakultas = fakultasDAO.selectFakultas(prodi.getId_fakultas());
        UniversitasModel universitas = univDAO.selectUniversitas(fakultas.getId_univ());
        
        model.addAttribute("title", "View Mahasiswa");
        model.addAttribute("prodi",prodi);
        model.addAttribute("fakultas", fakultas);
        model.addAttribute("universitas", universitas);
        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }
    
    @RequestMapping("/mahasiswa/tambah")
    public String tambah (Model model)
    {
    	MahasiswaModel mahasiswa = new MahasiswaModel();
    	model.addAttribute(mahasiswa);
    	model.addAttribute("title", "Add Mahasiswa");
        return "form-tambah";
    }
    
    @RequestMapping("/mahasiswa/tambah/submit")
    public String tambahSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa) {
    	String thn = mahasiswa.getTahun_masuk().substring(2,4);
    	ProgramStudiModel prodi = mahasiswaDAO.selectProdi(mahasiswa.getId_prodi());
    	FakultasModel fakultas = mahasiswaDAO.selectFakultas(prodi.getId_fakultas());
    	UniversitasModel universitas = mahasiswaDAO.selectUniversitas(fakultas.getId_univ());
    	String kodeUniv = universitas.getKode_univ();
    	String prod = prodi.getKode_prodi();
    	String jalur = "";
    	String Finalnpm="";
    	String nomor_urut ="";
    	if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Undangan Olimpiade")) {
    		jalur = "53";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Undangan Reguler/SNMPTN")) {
    		jalur = "54";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Undangan Paralel/PPKB")){
    		jalur = "55";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Ujian Tulis Bersama/SBMPTN")) {
    		jalur = "57";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Ujian Tulis Mandiri")) {
    		jalur = "62";
    	}
    	String npm = thn + kodeUniv + prod + jalur;
    	
    	String max_npm = mahasiswaDAO.selectNpm(npm);
    	if(max_npm == null) {
    		Finalnpm = npm + "001";
    	}
    	else
    	{
    		nomor_urut = getNomorUrut(max_npm);
    		Finalnpm = npm + nomor_urut;
    	}
    	model.addAttribute("title", "Add submit");
    	mahasiswa.setStatus("Aktif");
    	mahasiswa.setNpm(Finalnpm);
    	
    	mahasiswaDAO.addStudent(mahasiswa);
    	return "success-add";
    }
    
    public String getNomorUrut(String max_npm) {
    	String result = "0";
    	if(max_npm == null) {
    		result = max_npm+"001";
    	}
    	else
    	{
    		String nomor_urut = max_npm.substring(9);
        	String new_nomor_urut = String.valueOf(Integer.parseInt(nomor_urut) + 1) ;
        	if(new_nomor_urut.length()==1) {
        		result = "00"+new_nomor_urut;
        	}else if(new_nomor_urut.length()==2) {
        		result = "0"+new_nomor_urut;
        	}else {
        		result = new_nomor_urut;
        	}
    	}
    	
    	return result;
    }
    

    @RequestMapping("/mahasiswa/ubah/{npm}")
    public String update (Model model,  @PathVariable(value = "npm") String npm)
    {
    	MahasiswaModel student = mahasiswaDAO.selectMahasiswa (npm);
    	model.addAttribute ("student", student);
    	model.addAttribute("title", "Ubah Data Mahasiswa");
    	return "form-update";
    }
    
    @RequestMapping(value="/mahasiswa/ubah/submit", method = RequestMethod.POST)
    public String updateSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa)
    {  
    	model.addAttribute("title", "Submit Ubah Data Mahasiswa");
    	String npm_lama = mahasiswa.getNpm().substring(9,12);
    	String thn = mahasiswa.getTahun_masuk().substring(2,4);
    	ProgramStudiModel prodi = mahasiswaDAO.selectProdi(mahasiswa.getId_prodi());
    	FakultasModel fakultas = mahasiswaDAO.selectFakultas(prodi.getId_fakultas());
    	UniversitasModel universitas = mahasiswaDAO.selectUniversitas(fakultas.getId_univ());
    	String kodeUniv = universitas.getKode_univ();
    	String prod = prodi.getKode_prodi();
    	String jalur = "";
    	if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Undangan Olimpiade")) {
    		jalur = "53";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Undangan Reguler/SNMPTN")) {
    		jalur = "54";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Undangan Paralel/PPKB")){
    		jalur = "55";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Ujian Tulis Bersama/SBMPTN")) {
    		jalur = "57";
    	}else if(mahasiswa.getJalur_masuk().equalsIgnoreCase("Ujian Tulis Mandiri")) {
    		jalur = "62";
    	}
    	String npm = thn + kodeUniv + prod + jalur;
    	String npmFinal = npm + npm_lama;
    	mahasiswa.setNpm(npmFinal);
    	mahasiswaDAO.updateMahasiswa(mahasiswa);
        return "success-update";
    }
    
    @RequestMapping("/kelulusan")
    public String kelulusan () 
    {
    	return "kelulusan";
    }
    
    @RequestMapping("/kelulusan/view")
    public String kelulusanView(Model model,
            @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
            @RequestParam(value = "id_prodi", required = false) Integer id_prodi)
    {
    	model.addAttribute("title", "Kelulusan Mahasiswa");
    	ProgramStudiModel prodi = mahasiswaDAO.selectProdi(id_prodi);
    	FakultasModel fakultas = mahasiswaDAO.selectFakultas(prodi.getId_fakultas());
    	UniversitasModel universitas = mahasiswaDAO.selectUniversitas(fakultas.getId_univ());
    	
    	Integer jlhMahasiswa = mahasiswaDAO.jumlahMahasiwaLulus(tahun_masuk, id_prodi);
    	Integer totalMahasiwa = mahasiswaDAO.totalMahasiswa(tahun_masuk, id_prodi);
    	String presentaseLulus = "0";

		if (totalMahasiwa > 0) {
			presentaseLulus = String.format("%.2f", ((float)jlhMahasiswa/(float)totalMahasiwa) * 100);
		}
    	model.addAttribute("tahun_masuk", tahun_masuk);
    	model.addAttribute("program_studi", prodi.getNama_prodi());
    	model.addAttribute("fakultas", fakultas.getNama_fakultas());
    	model.addAttribute("universitas", universitas.getNama_univ());
    	model.addAttribute("jlhMahasiswa", jlhMahasiswa);
    	model.addAttribute("totalMahasiwa", totalMahasiwa);
    	model.addAttribute("persen", presentaseLulus);
    	
    	return "kelulusan-view";
    }
    
    @RequestMapping(value = "/mahasiswa/cari", method = RequestMethod.GET)
    public String cari(Model model) {
    	model.addAttribute("title", "Cari Mahasiswa");
    	List<UniversitasModel> universitas = univDAO.selectAllUniversitas();
    	model.addAttribute("universitas", universitas);
   
    	return "cari";
    }
    
    
    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
        mahasiswaDAO.deleteStudent (npm);
        return "delete";
    }
    
    @RequestMapping(value = "/getFakultasbyUniv", method = RequestMethod.GET)
	public @ResponseBody
	List<FakultasModel> findAllFakultasbyUniv(
	        @RequestParam(value = "id_univ", required = true) Integer id_univ) {
	    return fakultasDAO.selectFakultasbyUniv(id_univ);
	}
	
	@RequestMapping(value = "/getProdibyFakultas", method = RequestMethod.GET)
	public @ResponseBody
	List<ProgramStudiModel> findAllProdibyFakultas(
	        @RequestParam(value = "id_fakultas", required = true) Integer id_fakultas) {
		return prodiDAO.selectAllProdibyFakultas(id_fakultas);
	}
    
}

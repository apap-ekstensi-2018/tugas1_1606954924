package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramStudiModel {
	public String kode_prodi;
	public String nama_prodi;
	public int id_fakultas;
	public FakultasModel fakultas;
}



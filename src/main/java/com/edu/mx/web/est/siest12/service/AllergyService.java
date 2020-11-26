package com.edu.mx.web.est.siest12.service;

import java.util.List;

import com.edu.mx.web.est.siest12.model.Allergy;

public interface AllergyService {

	public List<Allergy> getAll();

	public Allergy getAllergy(int idAllergy);

	public void save(Allergy objAllergy);
	
	public void delete(int idAllergy);

}

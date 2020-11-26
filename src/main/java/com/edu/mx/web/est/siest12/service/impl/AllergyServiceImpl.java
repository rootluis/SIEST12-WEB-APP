package com.edu.mx.web.est.siest12.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edu.mx.web.est.siest12.model.Allergy;
import com.edu.mx.web.est.siest12.service.AllergyService;
import com.edu.mx.web.est.siest12.utils.Constants;

@Service
public class AllergyServiceImpl implements AllergyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AllergyServiceImpl.class);
	private static final String MGS_1 = "Consumiendo el servicio REST - SIEST-REST-API";

	private RestTemplate restTemplate;

	private String urlRest;

	@Autowired
	public AllergyServiceImpl(RestTemplate restTemplate, @Value("${allergy.rest.url}") String urlRest) {
		this.restTemplate = restTemplate;
		this.urlRest = urlRest;
	}

	@Override
	public List<Allergy> getAll() {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, MGS_1,"GET - /allergies").getMessage());
		ResponseEntity<List<Allergy>> responseEntity = restTemplate.exchange(urlRest, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Allergy>>() {
				});

		List<Allergy> allergies = responseEntity.getBody();

		return allergies;
	}

	@Override
	public Allergy getAllergy(int idAllergy) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, MGS_1,"POST - /allergies").getMessage());
		Allergy theAllergy = restTemplate.getForObject(urlRest + "/" + idAllergy, Allergy.class);
		return theAllergy;
	}

	@Override
	public void save(Allergy objAllergy) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, MGS_1,"PUT - /allergies").getMessage());
		int allergyId = objAllergy.getCdAlergia();
		
		if (allergyId == 0) {
			// add allergy
			restTemplate.postForEntity(urlRest, objAllergy, String.class);
		}else {
			// update allergy
			restTemplate.put(urlRest, objAllergy);
		}
	}

	@Override
	public void delete(int idAllergy) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, MGS_1,"DELETE - /allergies/'idAllergy'").getMessage());
		restTemplate.delete(urlRest + "/" + idAllergy);
		
	}

}

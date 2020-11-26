package com.edu.mx.web.est.siest12.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.mx.web.est.siest12.model.Allergy;
import com.edu.mx.web.est.siest12.service.AllergyService;
import com.edu.mx.web.est.siest12.utils.Constants;

@Controller
@RequestMapping("/allergy")
public class AllergyController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AllergyController.class);
	private static final String CLASS_NAME = "Ejecutando " + AllergyController.class.getName();
	
	private AllergyService allergyService;
		
	@Autowired
	public AllergyController(AllergyService allergyService) {
		this.allergyService = allergyService;
	}

	@GetMapping("/list")
	public String listAllergies(Model theModel) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, CLASS_NAME,"listAllergies()").getMessage());
		List<Allergy> listAllergy= allergyService.getAll();
		theModel.addAttribute("allergies", listAllergy);
		return "allergy/allergy-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String addAllergy(Model theModel) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, CLASS_NAME,"addAllergy()").getMessage());
		Allergy theAllergy = new Allergy();
		theModel.addAttribute("allergy", theAllergy);		
		return "allergy/allergy-form";
	}
	
	@PostMapping("/save")
	public String saveAllergy(@ModelAttribute("allergy") Allergy objAllergy) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, CLASS_NAME,"saveAllergy()").getMessage());
		allergyService.save(objAllergy);
		return "redirect:/allergy/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateAllergy(@RequestParam("allergyId") int idAllergy, Model theModel) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, CLASS_NAME,"updateAllergy()").getMessage());
		Allergy objAllergy = allergyService.getAllergy(idAllergy);
		theModel.addAttribute("allergy", objAllergy);
		return "allergy/allergy-form";
	}
	
	@GetMapping("/delete")
	public String deleteAllergy(@RequestParam("allergyId") int idAllergy) {
		LOGGER.info(MessageFormatter.format(Constants.LOG_FULL, CLASS_NAME,"deleteAllergy()").getMessage());
		allergyService.delete(idAllergy);
		return "redirect:/allergy/list";
	}
	
}

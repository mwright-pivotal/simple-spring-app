package com.pivotal.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pivotal.demo.data.Toy;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static String envJSON = null;
	private static JsonNode rootEnvNode = null;
	private CrudRepository<Toy, Long> toyRepository;
	 
	@PersistenceContext(unitName="SimpleSpringApp")
	private EntityManager em;
	
//	@Autowired
//	MongoOperations mongoOperation;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		if (this.getEnv()!=null) {
			model.addAttribute("instance_id",this.getEnv().path("instance_id").getTextValue());
			model.addAttribute("instance_index",this.getEnv().path("instance_index").getBigIntegerValue());
			model.addAttribute("application_uris",this.getEnv().path("application_uris").getElements().next().asText());
		}
		
		Iterable<Toy> xmasList = this.toyRepository.findAll();
		
		model.addAttribute("xmasList", xmasList.iterator().next());
		return "home";
	}
	
	/**
	 * @TODO implement save to MongoDB
	 * @param model
	 * @return
	 */
	
	public String saveToy(Model model) {
		return null;
	}
	
	private JsonNode getEnv() {
		if (toyRepository==null)
			this.setUp();
		if (envJSON==null) {
			envJSON = System.getenv("VCAP_APPLICATION");
			if (envJSON==null)
					return null;
			
			ObjectMapper mapper = new ObjectMapper();
		
		 	try {
				rootEnvNode = mapper.readTree(envJSON);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rootEnvNode;
	}
	
	private void setUp() {

        toyRepository = new SimpleJpaRepository<Toy, Long>(Toy.class, em);

	}
	
}

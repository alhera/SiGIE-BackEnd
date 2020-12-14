package cr.ac.ucr.ie.sigie.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.NewsCategoryBusiness;
import cr.ac.ucr.ie.sigie.domain.NewsCategory;


@RestController
@RequestMapping(value = "/api/newscategory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "*")
public class NewsCategoryController {
	
	@Autowired
    NewsCategoryBusiness NewsCategoryBusiness;
	@GetMapping(path = "/findById/{searchNewsCategoryId}")
	public ResponseEntity<NewsCategory> findById(
			@PathVariable("searchNewsCategoryId") int searchNewsCategoryId){
		NewsCategory NewsCategory=new NewsCategory();
		try {
			NewsCategory = NewsCategoryBusiness.findById(searchNewsCategoryId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<NewsCategory>(NewsCategory, HttpStatus.OK);
	}
	
	@GetMapping("/findNewsCategory")
	public ResponseEntity<List<NewsCategory>> findNewsCategory(){
		List<NewsCategory> newsCategory = NewsCategoryBusiness.findAll();
		return new ResponseEntity<List<NewsCategory>>(newsCategory, HttpStatus.OK);
	}
}

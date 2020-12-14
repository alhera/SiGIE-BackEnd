package cr.ac.ucr.ie.sigie.controller;

import java.sql.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.NewsBusiness;
import cr.ac.ucr.ie.sigie.domain.News;

@RestController
@RequestMapping(value = "/api/news", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "*")
public class NewsController {
	
	

	@Autowired
	NewsBusiness newsBusiness;

	@GetMapping("/")
	public ResponseEntity<ArrayList<News>> findAll()  {
		ArrayList<News> news=new ArrayList<>();
		try {
			news = newsBusiness.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<News>>(news, HttpStatus.OK);
	}
	@GetMapping(path = "/filterNews/{startDate}/{endDate}/{searchUniversityBranchId}/{searchTitle}")
	public ResponseEntity<ArrayList<News>> filterNews(
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate,
			@PathVariable("searchUniversityBranchId") int searchUniversityBranchId,
			@PathVariable("searchTitle") String searchTitle
			) {
		ArrayList<News> news=new ArrayList<>();
		try {
			if (searchTitle.equals("null")) {
				searchTitle=null;
			}
			news = newsBusiness.filterNews(startDate, endDate,searchUniversityBranchId,searchTitle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<News>>(news, HttpStatus.OK);
	}
	@GetMapping(path = "/findByDateRange/{startDate}/{endDate}")
	public ResponseEntity<ArrayList<News>> findByDateRange(
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) {
		ArrayList<News> news=new ArrayList<>();
		try {
			news = newsBusiness.findByDateRange(startDate, endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<News>>(news, HttpStatus.OK);
	}
	@GetMapping(path = "/findByUniversityBranch/{searchUniversityBranchId}")
	public ResponseEntity<ArrayList<News>> findByUniversityBranch(
			@PathVariable("searchUniversityBranchId") int searchUniversityBranchId){
		ArrayList<News> news=new ArrayList<>();
		try {
			news = newsBusiness.findByUniversityBranch(searchUniversityBranchId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<News>>(news, HttpStatus.OK);
	}
	@GetMapping(path = "/findByNewsCategory/{searchNewsCategoryId}")
	public ResponseEntity<ArrayList<News>> findByNewsCategory(
			@PathVariable("searchNewsCategoryId") int searchNewsCategoryId){
		ArrayList<News> news=new ArrayList<>();
		try {
			news = newsBusiness.findByNewsCategory(searchNewsCategoryId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<News>>(news, HttpStatus.OK);
	}
	@GetMapping(path = "/findById/{searchNewsId}")
	public ResponseEntity<News> findById(
			@PathVariable("searchNewsId") int searchNewsId){
		News news=new News();
		try {
			news = newsBusiness.findById(searchNewsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<News>(news, HttpStatus.OK);
	}
	
	
	///
	@PostMapping("/save")
	public ResponseEntity<News> saveNews(@RequestBody News news) {
		News newsPending = newsBusiness.save(news);
		return new ResponseEntity<News>(newsPending, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/findNewsPendingApprove")
	public ResponseEntity<List<News>> chargeNews(){
		List<News> news = newsBusiness.findAllNewsPending();
		return new ResponseEntity<List<News>>(news, HttpStatus.OK);
	}
	
	
	@GetMapping("/findNewsPendingApproveByTitle/{title}")
	public ResponseEntity<List<News>> chargeNewsByTitle(@PathVariable("title") String title){
		List<News> news = newsBusiness.findByTitle(title);
		return new ResponseEntity<List<News>>(news, HttpStatus.OK);
	}
	
	@GetMapping("/findNewsPendingApproveByAuthorName/{authorName}")
	public ResponseEntity<List<News>> chargeNewsById(@PathVariable("authorName") String authorName){
		List<News> news = newsBusiness.findByAuthorName(authorName);
		return new ResponseEntity<List<News>>(news, HttpStatus.OK);
	}

}

package cr.ac.ucr.ie.sigie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.EmphasisData;
import cr.ac.ucr.ie.sigie.domain.Emphasis;

@Service
public class EmphasisBusiness {
	
	@Autowired
	private EmphasisData emphasisData;
	
	public List<Emphasis> listAllEmphasis (){
		return emphasisData.listAllEmphasis();
	}
	
}
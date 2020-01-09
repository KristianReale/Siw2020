package pensistence.dao;

import java.util.List;

import model.CorsoDiLaurea;
import model.Dipartimento;

public interface CorsoDiLaureaDao {
	public void save(CorsoDiLaurea corsoDiLaurea);  // Create
	public CorsoDiLaurea findByPrimaryKey(Long codice);     // Retrieve
	public List<CorsoDiLaurea> findAll();
	
//	public CorsoDiLaurea findByPrimaryKeyJoin(Long id);
	
//	public List<CorsoDiLaurea> findByDipartimento(Dipartimento dipartimento);
	
	public void update(CorsoDiLaurea corsoDiLaurea); //Update
	public void delete(CorsoDiLaurea corsoDiLaurea); //Delete	
}

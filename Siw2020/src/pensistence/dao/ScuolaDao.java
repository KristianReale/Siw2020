package pensistence.dao;

import java.util.List;

import model.Scuola;

public interface ScuolaDao {
	
	public void save(Scuola scuola);  // Create
	public Scuola findByPrimaryKey(Long id);     // Retrieve
	public List<Scuola> findAll();       
	public void update(Scuola gruppo); //Update
	public void delete(Scuola gruppo); //Delete
}

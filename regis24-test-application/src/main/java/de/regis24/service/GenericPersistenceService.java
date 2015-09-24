package de.regis24.service;

import java.util.List;

/**
 * Created by vbourdine on 23.09.2015.
 */

public interface GenericPersistenceService<T> {
	
	public List<T> saveAll(List<T> models);
	
	public T get(Long id);

}

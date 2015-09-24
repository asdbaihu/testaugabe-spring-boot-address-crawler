package de.regis24.persistence.dao;

import java.util.List;

/**
 * Created by vbourdine on 23.09.2015.
 */

/**
 *
 * @param <T> Model type param
 * defines basic dao operations
 */
public interface GenericDao<T> {
	
	public List<T> saveAll(List<T> models);
	
	public T get(Long id);
}

package org.app.model.dao;

import java.util.List;

import org.app.model.entity.Address;

public interface AddressDAO {

	public Address create(Address xentity);

	public Address update(Address xentity);

	public void remove(Integer id);

	public Address findByID(Integer id);

}
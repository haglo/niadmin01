package org.app.model.dao;

import java.util.List;

import org.app.model.entity.ElytronMailSetting;
import org.app.model.entity.ElytronUser;

public interface ElytronMailSettingDAO {

	public ElytronMailSetting create(ElytronMailSetting xentity);

	public ElytronMailSetting update(ElytronMailSetting xentity);

	public void remove(Integer id);	
	
	public ElytronMailSetting findByID(Integer id);

	public List<ElytronMailSetting> findAll();

	public List<ElytronMailSetting> findAllExpanded();
	
	public ElytronMailSetting findByElytronUser(ElytronUser in);

}
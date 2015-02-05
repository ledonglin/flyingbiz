package com.flyingbiz.module.pic.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.flyingbiz.module.login.dao.BaseHibernateDAO;
import com.flyingbiz.module.pic.dao.PictureDao;
import com.flyingbiz.module.pic.model.Picture;

public class PictureDaoImpl extends BaseHibernateDAO implements PictureDao {

	@Override
	public void addPicture(Picture picture) {
		getSession().save(picture);
	}

	@Override
	public Picture getPictureById(String pictureId) {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.add(Restrictions.eq("pictureId", pictureId)).list();
		if (pictureList == null || pictureList.size() == 0)
			return null;
		else
			return pictureList.get(0);
	}
	@Override
	public List<Picture> getPicturesByUserId(String userId) {
		return getPicturesByUserId(userId, "desc");
	}

	@Override
	public List<Picture> getPicturesByUserId(String userId, String order) {
		Order orderType = "desc".equalsIgnoreCase(order) ? Order.desc("date") : Order.asc("date");

		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.add(Restrictions.eq("userId", userId)).addOrder(orderType).list();
		
		if (pictureList == null )
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@Override
	public List<Picture> getPicturesByFavourite() {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.addOrder(Order.desc("agree")).list();
		
		if (pictureList == null )
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@Override
	public List<Picture> getPicturesByFavourite(int limit) {
		return getPicturesByFavourite(0,limit);
	}

	@Override
	public List<Picture> getPicturesByFavourite(int start, int limit) {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.addOrder(Order.desc("agree")).setFirstResult(start).setFetchSize(limit).list();
		
		if (pictureList == null )
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@Override
	public List<Picture> getPictures() {
		List<Picture> pictureList = getSession().createCriteria(Picture.class).addOrder(Order.desc("date")).list();
		if (pictureList == null )
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

	@Override
	public List<Picture> getPictures(int start, int limit) {
		List<Picture> pictureList = getSession().createCriteria(Picture.class)
				.addOrder(Order.desc("date"))
				.setFirstResult(start).setFetchSize(limit).list();
		if (pictureList == null )
			return new ArrayList<Picture>();
		else
			return pictureList;
	}

}

package com.flyingbiz.module.pic.dao;

import java.util.List;

import com.flyingbiz.module.pic.model.Picture;

public interface PictureDao {
	public void addPicture(Picture picture);
	public Picture getPictureById(String pictureId);
	public List<Picture> getPicturesByUserId(String userId);
	public List<Picture> getPicturesByUserId(String userId, String order);
	public List<Picture> getPicturesByFavourite();
	public List<Picture> getPicturesByFavourite(int limit);
	public List<Picture> getPicturesByFavourite(int start, int limit);
	public List<Picture> getPictures();
	public List<Picture> getPictures(int start, int limit);
}

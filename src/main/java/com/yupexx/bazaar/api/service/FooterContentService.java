package com.yupexx.bazaar.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yupexx.bazaar.api.model.FooterContentModel;
import com.yupexx.bazaar.api.repository.FooterContentRepository;
import com.yupexx.bazaar.api.service.interfaces.FooterContentInterface;

@Service
public class FooterContentService implements FooterContentInterface {
	
	@Autowired
	FooterContentRepository dao;

	@Override
	public List<FooterContentModel> getAllFooterContent() {
		// TODO Auto-generated method stub
//		return dao.findByStatus();
		return dao.findAll();
	}

	@Override
	public FooterContentModel getFooterContentBySlug(String slug) {
		// TODO Auto-generated method stub
//		return dao.findBySlugsAndStatus(slug, true);
		return dao.findBySlugs(slug);
	}

	@Override
	public FooterContentModel addNewFooterContent(FooterContentModel object) {
		// TODO Auto-generated method stub
		object.setStatus(true);
		return dao.save(object);
	}

	@Override
	public FooterContentModel updateFooterContentBySlug(String slug, FooterContentModel object) {
		// TODO Auto-generated method stub
		FooterContentModel blog = dao.findBySlugs(slug);
		blog.setDescription(object.getDescription());
		blog.setTitle(object.getTitle());
		return dao.save(blog);
	}
	
	@Override
	public FooterContentModel updateFooterContentById(int id, FooterContentModel object) {
		// TODO Auto-generated method stub
		FooterContentModel blog = dao.findById(id);
		blog.setDescription(object.getDescription());
		blog.setTitle(object.getTitle());
		blog.setSlugs(object.getSlugs());
		return dao.save(blog);
	}

	@Override
	public FooterContentModel deleteFooterContentBySlug(String slug) {
		// TODO Auto-generated method stub
		FooterContentModel object = dao.findBySlugs(slug);
		object.setStatus(false);
		return dao.save(object);

	}

	@Override
	public FooterContentModel getFooterContentById(int id) {
		// TODO Auto-generated method stub
//		return dao.findByIdAndStatus(id);
		return dao.findById(id);
	}

	@Override
	public FooterContentModel deleteFooterContentById(int id) {
		// TODO Auto-generated method stub
		FooterContentModel object = dao.findById(id);
		object.setStatus(false);
		return dao.save(object);
	}

}

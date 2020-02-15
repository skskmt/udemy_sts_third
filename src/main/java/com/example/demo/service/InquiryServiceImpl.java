package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inquiry;
import com.example.demo.repository.InquiryDao;

/*
 * Add an annotation here
 */
@Service //これでDIコンテナの方で自動的にsingletonとされる
public class InquiryServiceImpl implements InquiryService{

	private final InquiryDao dao; //interface名の型にしておく
	
	@Autowired InquiryServiceImpl(InquiryDao dao) { //デフォルトコンストラクタ
		this.dao = dao;
	}
	
	@Override
	public void save(Inquiry inquiry) {
		//hands-on
		dao.insertInquiry(inquiry);
	}

	
//  This method is used in the latter chapter
//	@Override
//	public void update(Inquiry inquiry) {
//		
//		//return dao.updateInquiry(inquiry);
//		if(dao.updateInquiry(inquiry) == 0) {
//			throw new InquiryNotFoundException("can't find the same ID");
//		}
//	}
	
	@Override
	public List<Inquiry> getAll() {
		return dao.getAll();
	}
}

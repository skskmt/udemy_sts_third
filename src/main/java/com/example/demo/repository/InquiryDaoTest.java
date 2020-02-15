package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Inquiry;

public interface InquiryDaoTest {
	void insertInquiry(Inquiry inquiry);
	
	List<Inquiry> getAll();
}

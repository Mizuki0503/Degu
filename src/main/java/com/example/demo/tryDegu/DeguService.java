package com.example.demo.tryDegu;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class DeguService {
@Autowired
HelloRepository helloRepository;
public boolean insert(Degu degu) {
	int rowNumber=helloRepository.insertOne(degu);
	boolean result=false;
	if(rowNumber>0) {
		result=true;
	}
	return result;
}
public List<Degu>selectMany(){
	return helloRepository.selectMany();
}
public Degu selectOne(int Id) {
	return helloRepository.selectOne(Id);
	
	
}
public boolean deleteOne(int Id) {
	int rowNumber=helloRepository.deleteOne(Id);
	boolean result=false;
	if(rowNumber>0) {
		result=true;
	}
	return result;
}
public boolean updateOne(Degu degu) {
	int rowNumber=helloRepository.updateOne(degu);
	boolean result=false;
	if(rowNumber>0) {
		result=true;
	}
	return result;
}
public List<Degu> selectByYearMonth(int year,int month) {
	return helloRepository.selectByYearMonth(year,month);
}
public List<String> selectDistinctYearMonth()  {
	
	return helloRepository.selectDistinctYearMonth();
	
}
}


   
      
    
   
  

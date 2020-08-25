package com.example.demo.login.domain.model;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class Article {
	
	
		private int Id;
		private String comment;
		private int delate_flag;
	    private Timestamp create_date_time;
		private Timestamp update_date_time;
	
		

	}


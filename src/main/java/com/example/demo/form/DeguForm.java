package com.example.demo.form;



import java.util.Date;

import lombok.Data;

@Data
public class DeguForm {
	private int Id;
	private String comment;
	private String photo;
   private Date updated_at;
}


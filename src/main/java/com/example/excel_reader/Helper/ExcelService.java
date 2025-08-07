package com.example.excel_reader.Helper;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.excel_reader.model.Tutorial;
import com.example.excel_reader.repository.TutorialRepository;

@Service
public class ExcelService {
	
	@Autowired
	private TutorialRepository tutorialRepository;
	
	
	public void save(MultipartFile file) {
		try {
			List<Tutorial> tutorials = ExcelHelper.excelToTurial(file.getInputStream());
			tutorialRepository.saveAll(tutorials);
		}catch(IOException e) {
            throw new RuntimeException("Fail to store excel data: " + e.getMessage());
		}
	}
	
	public List<Tutorial> getAllTutorials(){
		return tutorialRepository.findAll();
	}
	
}

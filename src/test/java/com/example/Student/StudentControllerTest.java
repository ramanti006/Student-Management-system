package com.example.Student;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Student.Controller.StudentController;
import com.example.Student.Model.Student;
import com.example.Student.Repositery.StudentRepositery;
import com.example.Student.Service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
	
	private MockMvc mockMvc;
	
	ObjectMapper objmapper = new ObjectMapper();
	ObjectWriter objwriter = objmapper.writer();
	
	@Mock
	private StudentRepositery stuRepo;
	
	@Mock
	private StudentService stuService;
	
	@InjectMocks
	private StudentController stuController;
	
	Student stu1 = new Student(1,"Ramanti Shaw","TIU",25,"ram@gmail.com","1456498","CSE");
    
	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stuController).build();
	}
	
	@Test
	public void getAllRecord() throws Exception
	{
		
		List<Student> li = new ArrayList<>();
		li.add(stu1);
		
		Mockito.when(stuService.showAll()).thenReturn(li);
		
		

        mockMvc.perform(MockMvcRequestBuilders.get("/showAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Ramanti Shaw")) 
                .andExpect(jsonPath("$.message").value("SUCCESSFUL!")); 
        
		
    
	}
	
    @Test
    public void testShowStuInfoNoStudents() throws Exception {
        // Mock the behavior of the StudentService.showAll() method to return an empty list
    	Mockito.when(stuService.showAll()).thenReturn(new ArrayList<>());

        // Perform a GET request to the /showAll endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/showAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // Expect HTTP 404 status
                .andExpect(jsonPath("$.message").value("Not Found!")); // Expect the not found message
    }

}

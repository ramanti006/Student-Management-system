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
	Student stu2 = new Student(4,"Rani Shaw","TIU",12,"rani@gmail.com","13456468","ECE");
	
    
	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stuController).build();
	}
	
	@Test
	public void getAllRecord() throws Exception
	{
		
		List<Student> li = new ArrayList<>();
		li.add(stu1);
		li.add(stu2);
		
		Mockito.when(stuService.showAll()).thenReturn(li);
		
		

        mockMvc.perform(MockMvcRequestBuilders.get("/showAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("Ramanti Shaw"))
                .andExpect(jsonPath("$.data[1].name").value("Rani Shaw"))
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
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Test
    public void createMultipleStudentRecord() throws Exception
    {
    	List<Student> li = new ArrayList<Student>();
		li.add(stu1);
		li.add(stu2);
        
        Mockito.when(stuService.addMultiple((Mockito.anyList()))).thenReturn(li);
    	
        String content =  objwriter.writeValueAsString(li);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/addMultipleStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void createOneStudentRecord() throws Exception
    {
    	Student stu = new Student();
    	stu.setId(2);
        stu.setName("John Doe");
        stu.setCollgname("TIU");
        stu.setRollno(101);
        stu.setEmail("john.doe@gmail.com");
        stu.setPhoneno("1234567890");
        stu.setDepartment("CSE");
        
        Mockito.when(stuService.addOne(Mockito.any(Student.class))).thenReturn(stu);
    	
        String content =  objwriter.writeValueAsString(stu);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/addOneStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());
    }
    
	@Test
    public void testcreateOneStudentRecord() throws Exception {
        // Mock the behavior of the StudentService.showAll() method to return an empty list
		Student stu3 = new Student(5,"Ritu Shaw",null,22,"ritu@gmail.com","1351238","ECE");
		
		Mockito.when(stuService.addOne(Mockito.any(Student.class))).thenThrow(new IllegalArgumentException("Invalid input"));

        // Perform a GET request to the /showAll endpoint
		 mockMvc.perform(MockMvcRequestBuilders.post("/addOneStudent")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content("{}")) // Empty JSON or invalid content
		            .andExpect(status().isBadRequest())
		            .andExpect(jsonPath("$.message").value("Invalid input")); // Adjust message as per actual implementation
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	@Test
	public void getRecordById() throws Exception
	{
		
		Mockito.when(stuService.showOne(stu1.getId())).thenReturn(stu1);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/showOne/{id}",stu1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(stu1.getId()))
                .andExpect(jsonPath("$.data.name").value(stu1.getName()))
                .andExpect(jsonPath("$.data.collgname").value(stu1.getCollgname()))
                .andExpect(jsonPath("$.data.rollno").value(stu1.getRollno()))
                .andExpect(jsonPath("$.data.email").value(stu1.getEmail()))
                .andExpect(jsonPath("$.data.phoneno").value(stu1.getPhoneno()))
                .andExpect(jsonPath("$.data.department").value(stu1.getDepartment()))
                .andExpect(jsonPath("$.message").value("SUCCESSFUL!"));
		
	}
	
	@Test
    public void testShowStuInfoByIdNoStudents() throws Exception {
        // Mock the behavior of the StudentService.showAll() method to return an empty list
    	Mockito.when(stuService.showOne(5)).thenReturn(null);

        // Perform a GET request to the /showAll endpoint
    	mockMvc.perform(MockMvcRequestBuilders.get("/showOne/{id}",5)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // Expect HTTP 404 status
                .andExpect(jsonPath("$.message").value("Not Found!")); // Expect the not found message
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void deleteRecordById() throws Exception
	{
		
		Mockito.when(stuService.deleteById(stu2.getId())).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteById/{id}",stu2.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) 
				.andExpect(jsonPath("$.message").value("SUCCESSFUL!"));

		
	}
	
	@Test
	public void testdeleteRecordById() throws Exception
	{
		
		Mockito.when(stuService.deleteById(10)).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteById/{id}",10)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()) 
				.andExpect(jsonPath("$.message").value("Not Found!"));

		
	}

}

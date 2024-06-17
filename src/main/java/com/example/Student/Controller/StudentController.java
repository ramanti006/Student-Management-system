package com.example.Student.Controller;


import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Student.Model.Student;
import com.example.Student.Response.ResponseHandler;
import com.example.Student.Service.StudentService;

@CrossOrigin("http://localhost:3000/")
@RestController
public class StudentController {
	
	
	@Autowired
	private StudentService stuser;
	
//	@Autowired
//	private ResponseHandler rp;
	
	//this api will add 1 student info and return id of that student
	@PostMapping("/addOneStudent")
	public ResponseEntity<Object> addOneStudent(@RequestBody Student stu)
	{
		
	   //if any details is null then it will not save any that details
       if(stu.getCollgname() == null || stu.getDepartment() == null || stu.getEmail() == null || 
    		   stu.getPhoneno() == null || stu.getName() == null || stu.getRollno() == 0)
       {
    	   return ResponseHandler.generateResponse("Invalid input", HttpStatus.BAD_REQUEST, null);
       }
       else
       {
    	   Student s = stuser.addOne(stu);
    	   System.out.println(s.toString());
    	   return ResponseHandler.generateResponse("SUCCESSFUL!", HttpStatus.CREATED, s);
       }
		
		
		
	  //return new  ResponseEntity(stuser.addOne(stu),HttpStatus.OK);
	  
	  //it will return student id which generated atomatically .
	  //return stu.getId();
	}
	
	
	//this api will add multiple student info.return name and id of that student in map 
	@PostMapping("/addMultipleStudent")
	public ResponseEntity<Object> addMultipleStudent(@RequestBody List<Student> stu)
	{
		
		System.out.println(stu);
     
		for( Student i : stu)
		{
			if(i.getCollgname() == null || i.getDepartment() == null || i.getEmail() == null || 
		    		   i.getPhoneno() == null || i.getName() == null || i.getRollno() == 0)
		       {
		    	   return ResponseHandler.generateResponse("Invalid input", HttpStatus.BAD_REQUEST, null);
		       }
			
		}
		
	   List<Student> se = stuser.addMultiple(stu);
       HashMap<Integer,Student > record= new HashMap<Integer,Student>(); 
		
		for(Student i : se)
		{
			record.put(i.getId(),i);
		}
		
		return ResponseHandler.generateResponse("SUCCESSFUL!", HttpStatus.CREATED, record);
		
		
	}
	
	////it will show each student record from table
	@GetMapping("/showAll")
	public ResponseEntity<Object> showStuInfo()
	{
		List<Student> li = stuser.showAll();
		//return stuser.showAll();
		if(li.isEmpty())
		{
			return ResponseHandler.generateResponse("Not Found!",HttpStatus.NOT_FOUND,null);
		}
		else {
			
		return ResponseHandler.generateResponse("SUCCESSFUL!",HttpStatus.OK,li);
		}
		
	}
	
	//it will show one record need to implement if no record for perticular scenario
	@GetMapping("/showOne/{id}")
	public ResponseEntity<Object> showoneStuInfo(@PathVariable int id)
	{
		Student str = stuser.showOne(id);
		System.out.println(str);
		if(str == null)
		{
			return ResponseHandler.generateResponse("Not Found!",HttpStatus.NOT_FOUND,null);
		}
		else
		{
			return ResponseHandler.generateResponse("SUCCESSFUL!",HttpStatus.OK,str);	
		}
		
		
	}
	
	@GetMapping("/showByName")
	public ResponseEntity<Object> showByName(@RequestParam String name)
	{
		Student str = stuser.showByName(name);
		
		System.out.println(str);
		
		if(str == null)
		{
			return ResponseHandler.generateResponse("Not Found!",HttpStatus.NOT_FOUND,null);
		}
		else
		{
			return ResponseHandler.generateResponse("SUCCESSFUL!",HttpStatus.OK,str);	
		}
	}
	
	
	//@PutMapping("/updateStudent/{id}")
	@PatchMapping("/updateStudent/{id}")
	public ResponseEntity<Object> updateOneStudent(@PathVariable int id ,@RequestBody Student stu)
	{
		Student str = stuser.update(id,stu);
		if(str == null)
		{
			return ResponseHandler.generateResponse("Not Found!",HttpStatus.NOT_FOUND,null);
		}
		else
		{
			return ResponseHandler.generateResponse("SUCCESSFUL!",HttpStatus.OK,str);	
		}
		
	}
	
	@PutMapping("/updateEmailAndRoll/{id}")
	public ResponseEntity<Object> updateEmailAnddRoll(@RequestBody Student stu,@PathVariable int id)
	{
		int i = stuser.updateEmailAndRoll(stu,id);
		if(i == 0)
	{
			return ResponseHandler.generateResponse("Id Not Found!",HttpStatus.NOT_FOUND,null);
		}
	else
		{
		return new ResponseEntity("SUCCESSFUL!",HttpStatus.OK);	
	}
		//return new ResponseEntity("SUCCESSFUL!",HttpStatus.OK);
	}
	
	@GetMapping("/showByDepartment")
	public ResponseEntity<Object> showByDepart(@RequestParam String department)
	{
		List<Student> str = stuser.showByDepartment(department);
		
		System.out.println(str);
		
		if(str == null)
		{
			return ResponseHandler.generateResponse("Not Found!",HttpStatus.NOT_FOUND,null);
		}
		else
		{
			return ResponseHandler.generateResponse("SUCCESSFUL!",HttpStatus.OK,str);	
		}
	}
	
	@GetMapping("/showByRollno")
	public ResponseEntity<Object> showByRollNo(@RequestParam int a,@RequestParam int b)
	{
		List<Student> str = stuser.showByRoll(a,b);
		
		System.out.println(str);
		
		if(str == null)
		{
			return ResponseHandler.generateResponse("Not Found!",HttpStatus.NOT_FOUND,null);
		}
		else
		{
			return ResponseHandler.generateResponse("SUCCESSFUL!",HttpStatus.OK,str);	
		}
	}
	
	
	
	
	
	//it will delete all the info at one time
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Object> deleteAll()
	{

		List<Student> li = stuser.showAll();
		//return stuser.showAll();
		if(li.isEmpty())
		{
			return ResponseHandler.generateResponse("Not Found!",HttpStatus.NOT_FOUND,null);
		}
		else {
			stuser.deletedAll();
			
		return new ResponseEntity("SUCCESSFUL!",HttpStatus.OK);
		}
		
	}
	
	
	//it will delete one info
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Map<String, String>> deleteById(@PathVariable int id)
	{
		boolean tr = stuser.deleteById(id);
		Map<String, String> response = new HashMap<>();
		if(tr == false)
		{
			
			response.put("message", "Not Found!");
		     return new ResponseEntity<>( response, HttpStatus.NOT_FOUND);
		}
		else
		{
			response.put("message", "SUCCESSFUL!");
	        return new ResponseEntity<>(response, HttpStatus.OK);	
		}
		
	}
	
	///Pagination an sorting
		@GetMapping("/pagination/{pageno}/{pagesize}")
		public ResponseEntity<Object> getprouctByPagination(@PathVariable int pageno,@PathVariable int pagesize)
		{
			Page<Student> pageUser = stuser.pagination(pageno, pagesize);
			
			return ResponseHandler.generateResponse("SUCCESSFUL",HttpStatus.OK,pageUser);
		}
		
		@GetMapping("/sorting/{id}")
		public ResponseEntity<Object> getprouctBySorting(@PathVariable String id)
		{
			List<Student> li = stuser.sorting(id);
			return ResponseHandler.generateResponse("SUCCESSFUL",HttpStatus.OK,li);
		}
		
		
        //paging and sorting both
		@GetMapping("/paginationAndSorting/{pageno}/{pagesize}/{id}")
		public ResponseEntity<Object> getprouctByPaginationAndSorting(@PathVariable int pageno,@PathVariable int pagesize,@PathVariable String id)
		{
			Page<Student> pageUser = stuser.pagingAndsorting(pageno, pagesize,id);
			
			return ResponseHandler.generateResponse("SUCCESSFUL",HttpStatus.OK,pageUser);
		}	
       
		//this will find student based on Department
		@GetMapping("/get/StudentDepartment/{d}")
		public ResponseEntity<Object> getStudentByDepartment(@PathVariable String d)
				
		{
			List<Student> li1 = stuser.getBydepart(d);
			if(li1.isEmpty())
			{
				return ResponseHandler.generateResponse("NOT FOUND!",HttpStatus.NOT_FOUND,null);
			}
			else
			{
			return ResponseHandler.generateResponse("SUCCESSFUL",HttpStatus.OK,li1);   
			}
		}
}

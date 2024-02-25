package com.example.Student.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Student.Model.Student;
import com.example.Student.Repositery.StudentRepositery;
import com.example.Student.Service.StudentService;

@Service
public class StudentServiceImpl extends StudentService {
	
	@Autowired
	private StudentRepositery repo;
	
	
	public Student addOne(Student stu)
	{
		return repo.save(stu);
	}
	
	public List<Student> addMultiple(List<Student> stuli)
	{
		return repo.saveAll(stuli);
	}
	
	
	public List<Student> showAll()
	{
		return repo.findAll();
	}
	
	public Student showOne(int id)
	{
		Student s = repo.findById(id).orElse(null);
		
		return s;
	}
   
	public Student showByName(String n)
	{
		Student s = repo.findByname(n);
		
		return s;
	}
	
	public Student update(int id, Student stu)
	{
		Student str = repo.findById(id).orElse(null);
		
		if(str == null)
		{
			return null;
			//if we are using patchMapping
//			str.setPhoneno(stu.getPhoneno());
//			str.setRollno(stu.getRollno());
			
			
		}
		else
		{
			repo.save(stu);
			return stu;
		}
		
		
	}
	
	public void deletedAll()
	{
		repo.deleteAll();
	}
	
	public boolean deleteById(int id)
	{
		Student str = repo.findById(id).orElse(null);
		if(str == null)
		{
			return false;
	
		}
		else
		{
			repo.deleteById(id);
			return true;
		}
	}
	
	public List<Student> showByDepartment(String n)
	{
		List<Student> s = (List<Student>) repo.findAllByDepartment(n);
		
		return s;
	}
	
	public List<Student> showByRoll(int a , int b)
	{
      List<Student> s = (List<Student>) repo.findByRollnoBetween(a ,b);
		
		return s;
		
	}
	

	//pagination an sorting
	public Page<Student> pagination(int pageNo ,int pageSize)
	{
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		Page<Student> pagingUser = repo.findAll(pageRequest);
		
		return pagingUser;
	}
	
	public List<Student> sorting(String id)
	{
		return repo.findAll(Sort.by(Sort.Direction.ASC,id));
	}
}

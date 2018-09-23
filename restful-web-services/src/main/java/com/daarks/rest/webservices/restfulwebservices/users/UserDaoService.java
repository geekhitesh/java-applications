package com.daarks.rest.webservices.restfulwebservices.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	
	private static int usersCount=3;
	
	static {
		users.add( new User(1,"Dheeraj", new Date()));
		users.add( new User(2,"Karan", new Date()));
		users.add( new User(3,"Akash", new Date()));
	}
	
	public List<User> findAll()
	{
	
		return users;
	}
	
	public User save(User user)
	{
		//if(users.g)
		
		if(user.getId() == 0) {
			usersCount++;
			user.setId(usersCount);
		}
		
		users.add(user);
		
		return user;
	}
	
	public User findById(int id)
	{
		for(User user: users)
		{
			if(user.getId() == id)
			{
				return user;
			}
		}
		
		return null;
	}
	
	public User deleteById(int id) {
		
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id ) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
	}
	
	
}

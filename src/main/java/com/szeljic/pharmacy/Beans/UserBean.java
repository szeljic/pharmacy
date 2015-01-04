package com.szeljic.pharmacy.Beans;

public class UserBean implements Comparable<UserBean>
{

	private String username, password, firstName, lastName, umcn;
	private int type, id;
	private boolean valid;
	
	public UserBean(){

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUmcn()
	{
		return umcn;
	}

	public void setUmcn(String umcn)
	{
		this.umcn = umcn;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int compareTo(UserBean o)
	{
		String compare = o.getUsername();
		return this.username.compareToIgnoreCase(compare);
	}

}

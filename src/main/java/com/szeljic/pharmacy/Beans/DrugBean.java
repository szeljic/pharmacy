package com.szeljic.pharmacy.Beans;

public class DrugBean implements Comparable<DrugBean>
{

	private int id, price, store;
	private String name, producer, uses, howToUse, sideEffects;
	
	public DrugBean(){

	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getStore()
	{
		return store;
	}

	public void setStore(int store)
	{
		this.store = store;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getProducer()
	{
		return producer;
	}

	public void setProducer(String producer)
	{
		this.producer = producer;
	}

	public String getUses()
	{
		return uses;
	}

	public void setUses(String uses)
	{
		this.uses = uses;
	}

	public String getHowToUse()
	{
		return howToUse;
	}

	public void setHowToUse(String howToUse)
	{
		this.howToUse = howToUse;
	}

	public String getSideEffects()
	{
		return sideEffects;
	}

	public void setSideEffects(String sideEffects)
	{
		this.sideEffects = sideEffects;
	}

	public int compareTo(DrugBean o)
	{
		String compare = o.getName();
		return this.name.compareToIgnoreCase(compare);
	}

}

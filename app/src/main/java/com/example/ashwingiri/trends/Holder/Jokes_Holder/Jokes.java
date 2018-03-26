package com.example.ashwingiri.trends.Holder.Jokes_Holder;

public class Jokes{
	private int id;
	private String url;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Jokes{" + 
			"id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}

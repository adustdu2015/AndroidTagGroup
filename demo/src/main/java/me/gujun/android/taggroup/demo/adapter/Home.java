package me.gujun.android.taggroup.demo.adapter;

import java.util.List;

public class Home {
	public List<HomeItem> list;

   public void setList( final List< HomeItem > list ) {
	  this.list = list;
   }

   public List< HomeItem > getList() {
	  return list;
   }

   public static class HomeItem{
	   public String name;

	  public HomeItem( final String name ) {
		 this.name = name;
	  }

	  public String getName() {
		  return name;
	   }

	   public void setName( final String name ) {
		  this.name = name;
	   }
	}
}

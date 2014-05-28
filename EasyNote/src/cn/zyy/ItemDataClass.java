package cn.zyy;

import java.text.SimpleDateFormat;

import android.provider.ContactsContract.Contacts.Data;

public class ItemDataClass {
	public String date;
	public String itemOne;
	public String itemTwo;
	public ItemDataClass(String _itemOne, String _itemTwo)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");    
		date=sdf.format(new java.util.Date());   
		itemOne = _itemOne;
		itemTwo = _itemTwo;
	}
}

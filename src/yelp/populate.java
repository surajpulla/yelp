package yelp;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

	public class populate {
		
		private static Connection con;
		private static HashSet<String> hm;
		
		public static void main(String[] args) {
		
		con = getConnection();
		hm = new HashSet<String>();
		
		hm.add("Active Life");
		
		      
		     
		  hm.add("Arts & Entertainment");
		      hm.add("Automotive");
		      hm.add("Car Rental");
		      hm.add("Cafes");
		      hm.add("Beauty & Spas");
		      hm.add("Convenience Stores");
		      hm.add("Dentists");
		      hm.add("Doctors");
		      hm.add("Drugstores");
		      hm.add("Department Stores");
		      hm.add("Education");
		      hm.add("Event Planning & Services");
		      hm.add("Flowers & Gifts");
		      hm.add("Food");
		      hm.add("Health & Medical");
		      hm.add("Home Services");
		      hm.add("Home & Garden");
		      hm.add("Hospitals");
		      hm.add("Hotels & Travel");
		      hm.add("Hardware Stores");
		      hm.add("Grocery");
		      hm.add("Medical Centers");
		      hm.add("Nurseries & Gardening");
		      hm.add("Nightlife");
		      hm.add("Restaurants");
		      hm.add("Shopping");
		      hm.add("Transportation");
		      
		      HashSet<String> hs = new HashSet<String>();
		
		hs.add("Monday");
		
		      
		     
		  hs.add("Tuesday");
		      hs.add("Wednesday");
		      hs.add("Thursday");
		      hs.add("Friday");
		      hs.add("Saturday");
		      hs.add("Sunday");
		      
		
		
		 String FileName="D:/SCU STUFF/Fall 2015/Database/hw3/YelpDataset-CptS451/yelp_business.json";
		    try {
		    	createbusiness();
		    	createSubCategory();
		        ArrayList<JSONObject> jsons=ReadJSON(new File(FileName),"UTF-8");
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		
		
		
		}
		
		
		private static ArrayList<String> flatValue(String key, Object object) {
		ArrayList<String> values = new ArrayList<String>();
		if (object instanceof JSONObject) {
		for (Object key2: ((JSONObject) object).keySet()) {
		if (key.length() > 0) {
		  values.addAll(flatValue(key+"_"+key2.toString(), ((JSONObject) object).get(key2)));
		} else {
		  values.addAll(flatValue(key2.toString(), ((JSONObject) object).get(key2)));
		}
		}
		} else {
		values.add(key+"_"+object.toString());
		}
		return values;
		}
		
		public static Connection getConnection() {
			String host = "127.0.0.1";
			String port = "1521";
			String dbname = "dbnewnew";
			String sid="orcl";
			
			String JURL="jdbc:oracle:thin:@" + host + ":" + port+ ":"+ dbname;
			String username = "hr";
			String passwd = "hr";
			try {
		      DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		      System.out.println("establish connection");
			  return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","hr","hr");
			} catch (Exception e) {
				return null;
			}
		}
		
		
		public static synchronized ArrayList<JSONObject> ReadJSON(File MyFile,String Encoding) throws FileNotFoundException, ParseException {
		    Scanner scn=new Scanner(MyFile,Encoding);
		    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
		
		    while(scn.hasNext()){
		        JSONObject obj= (JSONObject) new JSONParser().parse(scn.nextLine());
		        json.add(obj);
		    }
			    int count=0;
			  
			  String insertBusinessSQL = "INSERT INTO Business (BUSINESS_ID,CATEGORIES,NAME,STATE,ATTRIBUTES,MONDAY_OPEN,MONDAY_CLOSE,TUESDAY_OPEN,TUESDAY_CLOSE,WEDNESDAY_OPEN,WEDNESDAY_CLOSE,THURSDAY_OPEN,THURSDAY_CLOSE,FRIDAY_OPEN,FRIDAY_CLOSE,SATURDAY_OPEN,SATURDAY_CLOSE,SUNDAY_OPEN,SUNDAY_CLOSE,CITY,STARS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
			  
			  String insertSubcategorySQL = "INSERT INTO Category (CATEGORY, SUBCATEGORY, ATTRIBUTE) VALUES (?,?,?)";
			  
			 // String insertTimeSQL = "INSERT INTO Time (BUSINESS_ID,NAME,MONDAY_OPEN,MONDAY_CLOSE,TUESDAY_OPEN,TUESDAY_CLOSE,WEDNESDAY_OPEN,WEDNESDAY_CLOSE,THURSDAY_OPEN,THURSDAY_CLOSE,FRIDAY_OPEN,FRIDAY_CLOSE,SATURDAY_OPEN,SATURDAY_CLOSE,SUNDAY_OPEN,SUNDAY_CLOSE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			    
			  
			  PreparedStatement preparedBusinessStatement = null;
			  PreparedStatement preparedCategoryStatement = null;
			 // PreparedStatement preparedTimeStatement = null;
			 
			try {
				preparedBusinessStatement = con.prepareStatement(insertBusinessSQL);
				preparedCategoryStatement = con.prepareStatement(insertSubcategorySQL);
			
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
			  //Here Printing Json Objects
			    for(JSONObject obj : json){
			       
			    	String businessID=(String)(obj.get("business_id"));
			    	String name=(String)(obj.get("name"));
			    	String categories = ((JSONArray)(obj.get("categories"))).toJSONString();
			    	//System.out.println(categories);
			    	//String attributes = ((JSONObject)(obj.get("attributes"))).toString();
			    	String state=(String)(obj.get("state"));
			    	
			    	
			    	
//			    	System.out.println(city);
//			    	System.out.println(fulladdress);
			    	String monday_open="closed";
			    	String monday_close="closed";
			    	
			    	
			    	int i1=0;
			    	int j1=0;
			    	 HashMap htemp = null;
			    	if(((HashMap) obj.get("hours")).get("Monday")!=null){
			    		htemp = (HashMap) ((HashMap) obj.get("hours")).get("Monday");
			    		monday_open=(String) (htemp.get("open"));
			       		i1=Integer.parseInt(monday_open.substring(0,2));
			       		monday_close=(String) (htemp.get("close"));
			       		 j1=Integer.parseInt(monday_close.substring(0,2));
			    	//monday=(String)(((HashMap) obj.get("hours")).get("Monday")).toString();
			    	}
			    	else{
			    		i1=25;
			    		j1=25;
			    	}
			    	String tuesday_open="closed";
			    	String tuesday_close="closed";
			    	
			    	
			    	int i2=0;
			    	int j2=0;
			    	HashMap htemp2 = null;
			    	if(((HashMap) obj.get("hours")).get("Tuesday")!=null){
			    		htemp2 = (HashMap) ((HashMap) obj.get("hours")).get("Tuesday");
			    		tuesday_open=(String) (htemp2.get("open"));
			       		i2=Integer.parseInt(tuesday_open.substring(0,2));
			       		tuesday_close=(String) (htemp2.get("close"));
			       		 j2=Integer.parseInt(tuesday_close.substring(0,2));
			    	//monday=(String)(((HashMap) obj.get("hours")).get("Monday")).toString();
			    	}
			    	else{
			    		i2=25;
			    		j2=25;
			    	}
			    	String wednesday_open="closed";
			    	String wednesday_close="closed";
			    	
			    	
			    	int i3=0;
			    	int j3=0;
			    	 HashMap htemp3 = null;
			    	if(((HashMap) obj.get("hours")).get("Wednesday")!=null){
			    		htemp3 = (HashMap) ((HashMap) obj.get("hours")).get("Wednesday");
			    		wednesday_open=(String) (htemp3.get("open"));
			       		i3=Integer.parseInt(wednesday_open.substring(0,2));
			       		wednesday_close=(String) (htemp3.get("close"));
			       		 j3=Integer.parseInt(wednesday_close.substring(0,2));
			    	//monday=(String)(((HashMap) obj.get("hours")).get("Monday")).toString();
			    	}
			    	else{
			    		i3=25;
			    		j3=25;
			    	}
			    	String thursday_open="closed";
			    	String thursday_close="closed";
			    	
			    	
			    	int i4=0;
			    	int j4=0;
			    	 HashMap htemp4 = null;
			    	if(((HashMap) obj.get("hours")).get("Thursday")!=null){
			    		htemp4 = (HashMap) ((HashMap) obj.get("hours")).get("Thursday");
			    		thursday_open=(String) (htemp4.get("open"));
			       		i4=Integer.parseInt(thursday_open.substring(0,2));
			       		thursday_close=(String) (htemp4.get("close"));
			       		 j4=Integer.parseInt(thursday_close.substring(0,2));
			    	//monday=(String)(((HashMap) obj.get("hours")).get("Monday")).toString();
			    	}
			    	else{
			    		i4=25;
			    		j4=25;
			    	}
			    	String friday_open="closed";
			    	String friday_close="closed";
			    	
			    	
			    	int i5=0;
			    	int j5=0;
			    	 HashMap htemp5 = null;
			    	if(((HashMap) obj.get("hours")).get("Friday")!=null){
			    		htemp5 = (HashMap) ((HashMap) obj.get("hours")).get("Friday");
			    		friday_open=(String) (htemp5.get("open"));
			       		i5=Integer.parseInt(friday_open.substring(0,2));
			       		friday_close=(String) (htemp5.get("close"));
			       		 j5=Integer.parseInt(friday_close.substring(0,2));
			    	//monday=(String)(((HashMap) obj.get("hours")).get("Monday")).toString();
			    	}
			    	else{
			    		i5=25;
			    		j5=25;
			    	}
			    	String saturday_open="closed";
			    	String saturday_close="closed";
			    	
			    	
			    	int i6=0;
			    	int j6=0;
			    	 HashMap htemp6 = null;
			    	if(((HashMap) obj.get("hours")).get("Saturday")!=null){
			    		htemp6 = (HashMap) ((HashMap) obj.get("hours")).get("Saturday");
			    		saturday_open=(String) (htemp6.get("open"));
			       		i6=Integer.parseInt(saturday_open.substring(0,2));
			       		saturday_close=(String) (htemp6.get("close"));
			       		 j6=Integer.parseInt(saturday_close.substring(0,2));
			    	//monday=(String)(((HashMap) obj.get("hours")).get("Monday")).toString();
			    	}
			    	else{
			    		i6=25;
			    		j6=25;
			    	}
			    	String sunday_open="closed";
			    	String sunday_close="closed";
			    	
			    	
			    	int i7=0;
			    	int j7=0;
			    	 HashMap htemp7 = null;
			    	if(((HashMap) obj.get("hours")).get("Sunday")!=null){
			    		htemp7 = (HashMap) ((HashMap) obj.get("hours")).get("Sunday");
			    		sunday_open=(String) (htemp7.get("open"));
			       		i7=Integer.parseInt(sunday_open.substring(0,2));
			       		sunday_close=(String) (htemp7.get("close"));
			       		 j7=Integer.parseInt(sunday_close.substring(0,2));
			    	//monday=(String)(((HashMap) obj.get("hours")).get("Monday")).toString();
			    	}
			    	else{
			    		i7=25;
			    		j7=25;
			    	}
			    	
//			    	String tuesday="closed";
//			    			if(((HashMap) obj.get("hours")).get("Tuesday")!=null){
//			    	 tuesday=(String)(((HashMap) obj.get("hours")).get("Tuesday").toString());
//			    			}
//			    			String wednesday="closed";
//			    			if(((HashMap) obj.get("hours")).get("Wednesday")!=null){
//			    	 wednesday=(String)(((HashMap) obj.get("hours")).get("Wednesday").toString());
//			    			}
//			    			String thursday="closed";
//			    			if(((HashMap) obj.get("hours")).get("Thursday")!=null){
//			    	 thursday=(String)(((HashMap) obj.get("hours")).get("Thursday").toString());
//			    			}
////
////			    		String wednesday="closed";
////			    	if(((HashMap) obj.get("hours")).get("Wednesday")!=null);{
////			    	 wednesday=(String)(((HashMap) obj.get("hours")).get("Wednesday")).toString();
////			    	}
//////			    	
////			    	 String thursday="closed";
////			    	if(((HashMap) obj.get("hours")).get("Thursday")!=null){
////			    	thursday=(String)(((HashMap) obj.get("hours")).get("Thursday")).toString();
////			    	}
//			    	String friday="closed";
//			    	if(((HashMap) obj.get("hours")).get("Friday")!=null){
//			    	friday=(String)(((HashMap) obj.get("hours")).get("Friday")).toString();
//			    	}
//			    	String saturday="closed";
//			    		if(((HashMap) obj.get("hours")).get("Saturday")!=null){
//			    	 saturday=(String)(((HashMap) obj.get("hours")).get("Saturday")).toString();
//			    		}
//			    		String sunday="closed";
//			    	if(((HashMap) obj.get("hours")).get("Sunday")!=null){
//			    	sunday=(String)(((HashMap) obj.get("hours")).get("Sunday")).toString();
//			    	}
			    	String city=(String)(obj.get("city"));
			    	Double stars=(Double)(obj.get("stars"));
			    	System.out.println(stars);

			    	List<String> attributes =  flatValue("", ((JSONObject)(obj.get("attributes"))));
			    	
			    	try {
						   
			    		preparedBusinessStatement.setString(1, businessID);
			    		preparedBusinessStatement.setString(2, categories);
			    		preparedBusinessStatement.setString(3, name);
			    		preparedBusinessStatement.setString(4, state);
			    		preparedBusinessStatement.setString(5, attributes.toString());
			    		preparedBusinessStatement.setInt(6, i1);
			    		preparedBusinessStatement.setInt(7, j1);
			    		preparedBusinessStatement.setInt(8, i2);
			    		preparedBusinessStatement.setInt(9, j2);
			    		preparedBusinessStatement.setInt(10, i3);
			    		preparedBusinessStatement.setInt(11, j3);
			    		preparedBusinessStatement.setInt(12, i4);
			    		preparedBusinessStatement.setInt(13, j4);
			    		preparedBusinessStatement.setInt(14, i5);
			    		preparedBusinessStatement.setInt(15, j5);
			    		preparedBusinessStatement.setInt(16, i6);
			    		preparedBusinessStatement.setInt(17, j6);
			    		preparedBusinessStatement.setInt(18, i7);
			    		preparedBusinessStatement.setInt(19, j7);
			    		preparedBusinessStatement.setString(20, city);
			    		preparedBusinessStatement.setDouble(21, stars);

			    		
//			    		preparedBusinessStatement.setString(8, tuesday);
//			    		preparedBusinessStatement.setString(9, wednesday);
//			    		preparedBusinessStatement.setString(10, thursday);
//			    		preparedBusinessStatement.setString(11, friday);
//			    		preparedBusinessStatement.setString(12, saturday);
//			    		preparedBusinessStatement.setString(13, sunday);
					    System.out.println(businessID);
						preparedBusinessStatement.execute();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	
			    	ArrayList<String> catList = new ArrayList<String>();
			    	ArrayList<String> mainCategories = new ArrayList<String>();
			    	JSONArray jsonArray = ((JSONArray)(obj.get("categories"))); 
			    	if (jsonArray != null) { 
			    	   int len = jsonArray.size();
			    	   for (int i=0;i<len;i++){ 
			    	    
			    	    if (hm.contains(jsonArray.get(i).toString())) {
			    	    	mainCategories.add(jsonArray.get(i).toString());
			    	    } else {
			    	    	catList.add(jsonArray.get(i).toString());
			    	    }
			    	   } 
			    	} 
			    	for (String mainCat : mainCategories) {
			    	  for (String category : catList) {
			    		  for (String attribute : attributes) {
					    	try {	
								//PreparedStatement preparedStatement = con.prepareStatement(insertSubcategorySQL);
					    		preparedCategoryStatement.setString(1, mainCat);
					    		preparedCategoryStatement.setString(2, category);
					    		preparedCategoryStatement.setString(3, attribute);
					    		preparedCategoryStatement.execute();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
							//	e.printStackTrace();
							}
			    		  }
			    	  }
			    	}
			    	
			    //	String pr = (String) jsonObject.get("Pr");
			        // Parameters start with 1

			    	count++;
			    //	System.out.println(count);
			    }
			   // System.out.println(count);
			    return json;
			}
			
			
	   private static void createbusiness() {
		   try {
			   PreparedStatement dropStatement = con.prepareStatement(""
						+ 
						"DROP TABLE Business"
						+ "");
			   dropStatement.execute();
				PreparedStatement preparedStatement = con.prepareStatement(""
						+ 
						"CREATE TABLE Business(business_id VARCHAR(2000),categories VARCHAR(2000), name VARCHAR(2000), state VARCHAR(2000),attributes VARCHAR(2000),monday_open INT,monday_close INT,tuesday_open INT,tuesday_close INT,wednesday_open INT,wednesday_close INT,thursday_open INT,thursday_close INT,friday_open INT,friday_close INT,saturday_open INT,saturday_close INT,sunday_open INT,sunday_close INT,city VARCHAR(2000),stars FLOAT)"
						+ "");
				preparedStatement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
	   }
	   
	   private static void createSubCategory() {
		   try {
			   PreparedStatement dropStatement = con.prepareStatement(""
						+ 
						"DROP TABLE Category"
						+ "");
			   dropStatement.execute();
				PreparedStatement preparedStatement = con.prepareStatement(""
						+ 
						"CREATE TABLE Category(category VARCHAR(2000),subcategory  VARCHAR(2000), attribute VARCHAR(2000))"
						+ "");
				preparedStatement.execute();
			} catch (SQLException e) {
				//TODO Auto-generated catch block
				//e.printStackTrace();
			}
		   
	   }
			
	}


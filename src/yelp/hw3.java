package yelp;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;

public class hw3 {
	static String dayday="";
	static String timetime="";
	static String city="";
	static String state="";
	static String star="";
	static ArrayList <JCheckBox> list=new ArrayList ();
	static ArrayList <JCheckBox> remove=new ArrayList ();
	static ArrayList <JCheckBox> checkedlist=new ArrayList ();
	static ArrayList <JCheckBox> attributelist=new ArrayList ();
	static ArrayList <JCheckBox> attributecheckedlist=new ArrayList ();
	static ArrayList <JCheckBox> removeattributelist=new ArrayList ();
	static ArrayList <JCheckBox> nextcheckedlist=new ArrayList ();

	
	static String hello;
static String result[];
static Box verticalbox6 = Box.createVerticalBox();
static hw3 l=new hw3();
static JFrame window=new JFrame ("hello");
	public static void main(String args[]){
		JTextArea textarea=new JTextArea();

		
		//-----panels---
		JPanel toppanel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		//----panels----
		
		//-----buttons
		JButton buttonsearch=new JButton("Search");
		JButton buttonclose=new JButton("Close");
		panel1.add(buttonsearch);
		panel1.add(buttonclose);
		//-----
		
		
			    panel2.add(textarea);
		//-------
			    
			    
			    buttonsearch.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e)
			    	{	
			    		
			    		textarea.setText(" \n BUSINESS DETAILS  \n-----------------------------------------------------------------------------------------------------------------------\n");
			    		System.out.println("clicked");
			    		ArrayList <String> strlist=l.result(checkedlist,attributecheckedlist,nextcheckedlist,timetime,city,state,star);
			  
			   for(String s:strlist) {textarea.append(s+"\n");}
//			    
//			    		
			    		window.validate();
			    		JFrame window1=new JFrame();
			    		JPanel newpanel=new JPanel();
			    		JScrollPane newscroll=new JScrollPane(textarea);
			    		newscroll.setPreferredSize(new Dimension(500, 500));
			    		newpanel.setPreferredSize(new Dimension(500, 500));
			    		
			    		newscroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
			    		newpanel.add(newscroll);
			    		window1.add(newpanel);
			    		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    		//window1.setContentPane(toppanel);
			    		//window1.setSize(600,600);
			    		textarea.setLineWrap(true);
			    		textarea.setWrapStyleWord(true);
			    		window1.setTitle("YELP DATwfsfgA FILTER");
			    		window1.pack ();
			    	    window1.setLocationRelativeTo ( null );
			    		window1.setVisible(true);
			    		
			    		window1.validate();
			    		}
			    });
			    
			    
			    
		//---------CHECK BOX CATEGORY
			    String maincategory[]={"Active Life",
			    		 "Arts & Entertainment",
			    		 "Automotive",
			    		" Car Rental",
			    		"Cafes",
			    		" Beauty & Spas",
			    		"Convenience Stores",
			    		 "Dentists",
			    		"Doctors",
			    		"Drugstores",
			    		 "Department Stores",
			    		"Education",
			    		"Event Planning & Services",
			    		 "Flowers & Gifts",
			    		 "Food",
			    		"Health & Medical",
			    		 "Home Services",
			    		"Home & Garden",
			    		 "Hospitals",
			    		 "Hotels & Travel",
			    		 "Hardware Stores",
			    		 "Grocery",
			    		"Medical Centers",
			    		"Nurseries & Gardening",
			    		"Nightlife",
			    		"Restaurants",
			    		"Shopping",
			    		 "Transportation"};
			    Box verticalbox = Box.createVerticalBox();
			    ArrayList <JCheckBox> checkboxarray=new ArrayList();
			    for(int i=0;i<28;i++){
			    JCheckBox chkbox=new JCheckBox(maincategory[i]);
			   checkboxarray.add(chkbox);
			   // verticalbox.add(chkbox);
			    }
			    
			    Box verticalbox5 = Box.createVerticalBox();
			   
			    
			    //ADDING LISTENER TO  JCHECKBOX
			    for(JCheckBox obj: checkboxarray){
			    	 obj.addActionListener(new ActionListener(){
			    		
					    	public void actionPerformed(ActionEvent e)
					    	{
					    		
					    		hello=obj.getActionCommand();
					    		if(obj.isSelected()){
					    			System.out.println(obj.getActionCommand() +" is selected");
					    			checkedlist.add(obj);
					    			}
					    		if(!obj.isSelected()){System.out.println(obj.getActionCommand() +" is deselected");
					    					checkedlist.remove(obj);}
					    		
					    		 int y=0;
//						  
					    		 StringBuilder builder = new StringBuilder();
					    		 for(JCheckBox obj: remove){verticalbox5.remove(obj);}
					    		 verticalbox.validate();
					    		  for(JCheckBox obj2:checkedlist){
					    			  if( checkedlist.get(checkedlist.size()-1)==obj2   )
//						    	     builder.append(" '%"+obj2.getActionCommand()+"%'");
					    				  builder.append("select unique subcategory from category where category like "+"'%"+obj2.getActionCommand()+"%'"); 
					    			  else{
//					    				  builder.append(" '%"+obj2.getActionCommand()+"%'");
					    				  builder.append("select unique subcategory from category where category like "+"'%"+obj2.getActionCommand()+"%'");
						    	     builder.append(" intersect ");
						    	//     builder.append(" like");
						    	     }
						    	 }

					    		System.out.println("the builder is"+builder);
					    		l.funct(builder);
					    	//-----------ATTRIBUTE EVENT LISTENEERS--------------
					    	l.attributefunction(list,checkedlist);
					   
					    	//----------------------------------------------------------
					    		
					    		
					    		
					    	 for(JCheckBox obj: list){verticalbox5.add(obj);}
					    	
					    	 remove=list;
					    	 
					    	 for(JCheckBox obj2:checkedlist){System.out.println(obj2.getActionCommand());}
					    	 //window.setSize(1201,600);
					    	 
					    	 
					    	 window.validate();
					    	}
					    });
			    }
			    //-------------------
			    
			  // JScrollPane  checkboxcontainer =new JScrollPane();
			    for(JCheckBox obj: checkboxarray){verticalbox.add(obj);}
			   
			 
			    JScrollPane checkboxcontainer =new JScrollPane(verticalbox);
			    JScrollPane checkboxcontainers =new JScrollPane(verticalbox5);
			    JScrollPane checkboxcontainersattr =new JScrollPane(verticalbox6);
		       // checkboxcontainer.setLayout(new BoxLayout(checkboxcontainer, BoxLayout.Y_AXIS));
		        
			    checkboxcontainer.setPreferredSize(new Dimension(150, 450));
			    checkboxcontainers.setPreferredSize(new Dimension(150, 450));
			    checkboxcontainersattr.setPreferredSize(new Dimension(150, 450));

			    //---------------
			    
			    ///--------------------making attribute cheeck
			    
			    
			    
			  //---------CHECK BOX SUBCATEGORY
//			    Box verticalbox2 = Box.createVerticalBox();
//			    for(int i=0;i<30;i++){
//			    JCheckBox chkbox2=new JCheckBox("SUBCATEGORY "+i);
//			   
//			    verticalbox2.add(chkbox2);
//			    }
//			    	// panel3.add(verticalbox);
//			    JScrollPane checkboxcontainer2 =new JScrollPane(verticalbox2);
//			   
//			    	// checkboxcontainer.setLayout(new BoxLayout(checkboxcontainer, BoxLayout.Y_AXIS));
//		        
//			    checkboxcontainer2.setPreferredSize(new Dimension(150, 450));

			    //---------------
			    
			    
			    
			    
			  //---------CHECK BOX ATTRIBUTE
			    Box verticalbox3 = Box.createVerticalBox();
			    for(int i=0;i<30;i++){
			    JCheckBox chkbox3=new JCheckBox("ATTRIBUTE "+i);
			   
			    verticalbox3.add(chkbox3);
			    }
			    	// panel3.add(verticalbox);
			    JScrollPane checkboxcontainer3 =new JScrollPane(verticalbox3);
			   
			    	// checkboxcontainer.setLayout(new BoxLayout(checkboxcontainer, BoxLayout.Y_AXIS));
		        
			    checkboxcontainer3.setPreferredSize(new Dimension(150, 450));

			    //---------------
			    
			    
			    
			    
			    //-----COMBO BOXES
			    	//-------day of the week combo box
			    String dayarray[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
			    JComboBox day=new JComboBox(dayarray);
			    JLabel dayname=new JLabel("DAY OF THE WEEK");
			    JPanel panel4 =new JPanel();
			    panel4.add(dayname);
			    panel4.add(day);
			    	//---
			    	//--time to combo box
			    int count=12;
			    String timetoarray[]=new String[25] ;
			   
			    for(int i=0;i<24;i++){
			    	//   if(count==13){count=1;}
			    		timetoarray[i]=(""+i);
			 
			
//			    			else if(i>=12 ) {timetoarray[i]=(count%12)+"PM"; }
//			    			count++;
			    			}
	 
			    JComboBox timeto=new JComboBox(timetoarray);
			    JLabel time=new JLabel("TIME");
			    JPanel panel5 =new JPanel();
			    panel5.add(time);
			    panel5.add(timeto);
			    
			   //----TIME from combo box
	 
			    JComboBox timefrom=new JComboBox(timetoarray);
			    JLabel time1=new JLabel("TIME FROM");
			    JPanel panel6 =new JPanel();
			    panel6.add(time1);
			    panel6.add(timefrom);
			    
			    //------------
			    //---city
			   JLabel labelcity=new JLabel("CITY"); 
			    JTextField fieldcity=new JTextField(6);
			    JPanel panelcity=new JPanel();
			    panelcity.add(labelcity);
			    panelcity.add(fieldcity);
			   
			    //---State
				   JLabel labelstate=new JLabel("STATE"); 
				    JTextField fieldstate=new JTextField(6);
				    JPanel panelstate=new JPanel();
				    panelcity.add(labelstate);
				    panelcity.add(fieldstate);
				    //
				    //--star
				    JLabel labelstar=new JLabel("RATING"); 
				    JTextField fieldstar=new JTextField(6);
				    JPanel panelstar=new JPanel();
				    panelcity.add(labelstar);
				    panelcity.add(fieldstar);
				    
				    
				    fieldcity.addActionListener(new ActionListener(){
				    	public void actionPerformed(ActionEvent e)
				    	{
				    			city=fieldcity.getText();
				    			System.out.println(city);
				    		}
				    });
				    fieldstate.addActionListener(new ActionListener(){
				    	public void actionPerformed(ActionEvent e)
				    	{
				    			state=fieldstate.getText();
				    			
				    		}
				    });
				    fieldstar.addActionListener(new ActionListener(){
				    	public void actionPerformed(ActionEvent e)
				    	{
				    			star=fieldstar.getText();
				    			
				    		}
				    });
				  
			    //-------adding buttons and action listeners
			    
			  //  JButton button=new JButton("click here");
			    buttonsearch.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		System.out.println("Search Button clicked");
			    	
			    		}
			    });
			    
			    buttonclose.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		System.out.println("Close Button clicked");
			    		window.setVisible(false);
			    		window.dispose();
			    		}
			    });
			    
			   
			    day.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		System.out.println( day.getSelectedItem().toString() + " is Selected");
			          
			    		}
			    });
			    
			    
			    timeto.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		System.out.println(timeto.getSelectedItem().toString() + " is Selected");
			    		int time;
				           time =Integer.parseInt(timeto.getSelectedItem().toString());
				            dayday=""+day.getSelectedItem();
				           timetime="select name,city,state,stars from business where "+time+">="+dayday+"_open and "+time+"<="+dayday+"_close";
			    		
			    	}
			    });
			    
			    
			    timefrom.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		System.out.println( timefrom.getSelectedItem().toString() + " is Selected");
			    		
			    		
			    		}
			    });
			    
			
			    //----------
			    
			    
			    
			 
			  

			    
		//toppanel.add(panel2);
			
		toppanel.add(checkboxcontainer);
		toppanel.add(checkboxcontainers);

//		toppanel.add(checkboxcontainer2);
		toppanel.add(checkboxcontainersattr);
		toppanel.add(panel4);
		toppanel.add(panel5);
		//toppanel.add(panel6);
		toppanel.add(panel1);
		toppanel.add(panelcity);
		
		//toppanel.add(button);
		toppanel.setBackground(Color.DARK_GRAY);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(toppanel);
		window.setSize(700,700);
		
		window.setTitle("YELP DATA FILTER");
		
		
		Border black =BorderFactory.createLineBorder(Color.black,5);
	

		
		panel1.setBorder(black);
		panel2.setBorder(black);
		panel3.setBorder(black);
		panel4.setBorder(black);
		panel5.setBorder(black);
		panel6.setBorder(black);
		panelcity.setBorder(black);
		window.setVisible(true);
		
	
	
		
		
	}
				public ArrayList funct(StringBuilder builder){
		
					list.clear();
					populate ked=new populate();
					System.out.println(ked.getConnection());
					
					 try {
//						  
						   Statement st = ked.getConnection().createStatement();
						  
						   String str="select unique subcategory from category where category like"+builder;
						   System.out.println(builder);
						  
								ResultSet rs = st.executeQuery( builder.toString());

						//	ResultSet rs = st.executeQuery("select unique subcategory from category where category like '%"+builder+"%'");
							String S=null;
							String listarray[]=new String[100000];
							String listarray1[]=new String[100000];
							String listarray2[]=new String[100000];
							
							int z=0;
							
							while(rs.next()){
								 S=rs.getString(1);
								 //System.out.println(S);
								 StringTokenizer st1=new StringTokenizer(S,"[,]");
								 int c=st1.countTokens();
									//System.out.println(st1.nextToken());
						
									while(c>0){
								//listarray[z]=st1.nextToken();
										JCheckBox checkbox10=new JCheckBox(st1.nextToken());
										if(list.contains(checkbox10)){continue;}
								list.add(checkbox10);
								c--;
									}
									
							}
							
						   
						} catch (SQLException e) {
							// TODO Auto-generated catch block
						//	e.printStackTrace();
						}
					
					
					return list;
					}
				
				
				public ArrayList functo(StringBuilder builders){
					
					attributelist.clear();
					populate ked=new populate();
					System.out.println(ked.getConnection());
					
					 try {
//						  
						   Statement st = ked.getConnection().createStatement();
						  
						   String str="select unique attribute from category where category like '%Restaurant%' and subcategory like '%Airports%'";
						//   System.out.println(builder);
						  
//								ResultSet rs = st.executeQuery( builder.toString());
							ResultSet rs1 = st.executeQuery(builders.toString());
						//	ResultSet rs = st.executeQuery("select unique subcategory from category where category like '%"+builder+"%'");
							String S1=null;
						//	String listarray[]=new String[100000];
							int z=0;
							
							while(rs1.next()){
								 S1=rs1.getString(1);
								 //System.out.println(S);
								 StringTokenizer st2=new StringTokenizer(S1,"[,]");
								 int c=st2.countTokens();
									//System.out.println(st1.nextToken());
						
									while(c>0){
								//listarray[z]=st1.nextToken();
										JCheckBox checkbox11=new JCheckBox(st2.nextToken());
										//if(attributelist.contains(checkbox11)){continue;}
								attributelist.add(checkbox11);
								c--;
									}
									
							}
							System.out.println(attributelist.get(0).getActionCommand()+"attribute list");
						   
						} catch (SQLException e) {
							// TODO Auto-generated catch block
						//	e.printStackTrace();
						}
					
					
					return list;
					}
				
				
				public void attributefunction(ArrayList <JCheckBox> list,ArrayList <JCheckBox> checkedlist){
					
					
					for(JCheckBox obj:list){
						
						obj.addActionListener(new ActionListener(){
					    	public void actionPerformed(ActionEvent e)
					    	{hw3 l1=new hw3();
					    		if(obj.isSelected()){
					    			System.out.println(obj.getActionCommand() +" is selected new");
					    			attributecheckedlist.add(obj);
					    			}
					    		if(!obj.isSelected()){System.out.println(obj.getActionCommand() +" is deselected new");
					    					attributecheckedlist.remove(obj);}
					    				
					    		
					    		 StringBuilder builders = new StringBuilder();
					  
					    		 
					    		 for(JCheckBox obj: removeattributelist){verticalbox6.remove(obj);}
					    		 
					    		 //---------adding catgories to query
					    		 for(JCheckBox obj2:checkedlist){
					    			  if( checkedlist.get(checkedlist.size()-1)==obj2   )
//						    	     builder.append(" '%"+obj2.getActionCommand()+"%'");
					    				  builders.append("select unique attribute from category where category like "+"'%"+obj2.getActionCommand()+"%'"); 
					    			  else{
//					    				  builder.append(" '%"+obj2.getActionCommand()+"%'");
					    				  builders.append("select unique attribute from category where category like "+"'%"+obj2.getActionCommand()+"%'");
						    	     builders.append(" intersect ");
						    	//     builder.append(" like");
						    	     }
						    	 }
					    		 builders.append("intersect ");
					    		 //--------------------------------
					    		 
					    		 
					    		// verticalbox6.validate();
					    		  for(JCheckBox obj2:attributecheckedlist){
					    			  if( attributecheckedlist.get(attributecheckedlist.size()-1)==obj2   )
//						    	     builder.append(" '%"+obj2.getActionCommand()+"%'");
					    				  builders.append("select unique attribute from category where subcategory like "+"'%"+obj2.getActionCommand()+"%'"); 
					    			  else{
//					    				  builder.append(" '%"+obj2.getActionCommand()+"%'");
					    				  builders.append("select unique attribute from category where subcategory like "+"'%"+obj2.getActionCommand()+"%'");
						    	     builders.append(" intersect ");
						    	//     builder.append(" like");
						    	     }
						    	 }

					    		System.out.println("the builder is"+builders);
					    		l1.functo(builders);
					    		
					    		
					    		
					    		
					    		
						    	 for(JCheckBox obj: attributelist){verticalbox6.add(obj);}
						    	
						    	 removeattributelist=attributelist;
						    	 
						    	 for(JCheckBox obj2:attributecheckedlist){System.out.println(obj2.getActionCommand());}
					    		
					    		
					    		l1.makecheckednextlist(attributelist);
					    		window.validate();
					    		}
					    });
					    
						
						
					}
						
			
				}
				
				public void makecheckednextlist(ArrayList <JCheckBox> attributelist){
					
					for(JCheckBox obj:attributelist){
					 obj.addActionListener(new ActionListener(){
					    	public void actionPerformed(ActionEvent e)
					    	{
					           
					    		if(obj.isSelected()){
					    			System.out.println(obj.getActionCommand() +" is selected new in final list");
					    			nextcheckedlist.add(obj);
					    			}
					    		if(!obj.isSelected()){System.out.println(obj.getActionCommand() +" is deselected new in final list");
					    					nextcheckedlist.remove(obj);}
					    			
					    		for(JCheckBox o:nextcheckedlist){System.out.println(o.getActionCommand());}
					    		
					    		}
					    });
					
				}

				}
				
				public ArrayList result(ArrayList<JCheckBox> checkedlist,ArrayList<JCheckBox> attributecheckedlist, ArrayList<JCheckBox> nextcheckedlist,String timetime,String city,String state,String star ){
				
					ArrayList <String> listarray = new ArrayList();
					ArrayList <String> listarray1 = new ArrayList();
					ArrayList <String> listarray2 = new ArrayList();
						populate ked=new populate();
						System.out.println(ked.getConnection());
						
						
						 try {
//							  
							 StringBuilder searchbuilder = new StringBuilder();
							 if(city!=""){
							 searchbuilder.append("select unique name,city,state,stars from business where city like '%"+city+"%'");
							 searchbuilder.append(" intersect ");
							 }
							 if(state!=""){
								 searchbuilder.append("select unique name,city,state,stars from business where state like '%"+state+"%'");
								 searchbuilder.append(" intersect ");
								 }
							
							 if(star!=""){
							 searchbuilder.append("select unique name,city,state,stars from business where "+star+" <=stars"); 
							 searchbuilder.append(" intersect ");
							 }
							 
							 for(JCheckBox obj2:checkedlist){
				    			  if( checkedlist.get(checkedlist.size()-1)==obj2   )
				    				  searchbuilder.append("select unique name,city,state,stars from business where categories like "+"'%"+obj2.getActionCommand()+"%'"); 
				    			  else{
				    				  searchbuilder.append("select unique name,city,state,stars from business where categories like "+"'%"+obj2.getActionCommand()+"%'");
					    	     searchbuilder.append(" intersect ");
					    	     }
					    	 }
							 
							 if(!attributecheckedlist.isEmpty()){	searchbuilder.append(" intersect ");
							 for(JCheckBox obj2:attributecheckedlist){
				    			  if( attributecheckedlist.get(attributecheckedlist.size()-1)==obj2   )
				    				  searchbuilder.append(" select unique name,city,state,stars from business where categories like "+"'%"+obj2.getActionCommand()+"%'"); 
				    			  else{
				    				  searchbuilder.append(" select unique name,city,state,stars from business where categories like "+"'%"+obj2.getActionCommand()+"%'");
					    	     searchbuilder.append(" intersect ");
					    	     }
					    	 }
							 
							 }
							 
							 if(!nextcheckedlist.isEmpty()){	searchbuilder.append(" intersect ");
							 for(JCheckBox obj2:nextcheckedlist){
				    			  if( nextcheckedlist.get(nextcheckedlist.size()-1)==obj2   )
				    				  searchbuilder.append(" select unique name,city,state,stars from business where attributes like "+"'%"+obj2.getActionCommand()+"%'"); 
				    			  else{
				    				  searchbuilder.append(" select unique name,city,state,stars from business where attributes like "+"'%"+obj2.getActionCommand()+"%'");
					    	     searchbuilder.append(" intersect ");
					    	     }
					    	 }
							 
							 }
							 if(timetime!=""){if(checkedlist.isEmpty() && attributecheckedlist.isEmpty() && nextcheckedlist.isEmpty() )
								 //searchbuilder.append(" intersect ");
							 searchbuilder.append(timetime);
							 else{searchbuilder.append(" intersect ");
							 searchbuilder.append(timetime);}
							 }
							 //else{searchbuilder.append(timetime);}
							 
							 System.out.println("hellow world");
							   Statement st = ked.getConnection().createStatement();
							  
							   //String str="select unique subcategory from category where category like"+builder;
							   System.out.println("hellllooo"+searchbuilder);
							  
									ResultSet rs = st.executeQuery( searchbuilder.toString());

							//	ResultSet rs = st.executeQuery("select unique subcategory from category where category like '%"+builder+"%'");
								String S="";
								String S1="";
								String S3="";
								String S4="";
								
								int z=0;
								
								
								while(rs.next()){
									 S=rs.getString(1);
									 S1=rs.getString(2);
									 S3=rs.getString(3);
									 S4=rs.getString(4);
									 //System.out.println(S);
									 StringTokenizer st1=new StringTokenizer(S,"");
									 StringTokenizer st2=new StringTokenizer(S1,"");
									 StringTokenizer st3=new StringTokenizer(S3,"");
									 StringTokenizer st4=new StringTokenizer(S4,"");
									 int c=st1.countTokens();
									 
										//System.out.println(st1.nextToken());
							int i=0;
							
										while(c>0){
									//listarray[z]=st1.nextToken();
											
											listarray.add(st1.nextToken().toString()+"  \n   "+st2.nextToken().toString()+"   "+st3.nextToken().toString()+"     "+st4.nextToken().toString());
											//listarray.add(st2.nextToken().toString());
//										listarray.add(st1.nextToken().toString());
//										listarray.add(st1.nextToken().toString());
											
											
											
		//System.out.println(listarray.get(i));
//		i++;
												c--;
												
										}
										
								}
								for(String s:listarray){System.out.println(s);}
//								 int x=0;
//									while(x<50){System.out.println(listarray[x].toString()+" checking-----");x++;}
//								
							   
							} catch (SQLException e) {
								// TODO Auto-generated catch block
							//	e.printStackTrace();
							}
						
						return listarray;
						
						
						
				}
				
				public void searchbuttonpress(){
					
						
				}
				
				
				
}
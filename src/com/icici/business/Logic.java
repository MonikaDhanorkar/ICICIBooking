package com.icici.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Logic
{
	public String checkEmp(String  empid1,String dt,String st,String et,String participants) throws ParseException
	{
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy 00:00:00").format(new Date()));
		String new_date=new SimpleDateFormat("dd/MM/yyyy").format(date);
		System.out.println(new_date);

		Date user_date =new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy 00:00:00").format(new SimpleDateFormat("yyyy-MM-dd").parse(dt)));
		System.out.println("System Date="+date);
		System.out.println("User Enterd date="+user_date);

		if(user_date.before(date))
			return "sorry...you have enterred previous date.";

		int user_cap=Integer.valueOf(participants);
		int empiduser=Integer.valueOf(empid1);
		if(user_cap>65)
			return "room is no available for "+participants+" people";

		Connection c = null;
		PreparedStatement stmt = null;
		int empid=0;
		String  name="",email="";

		int count=1;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/ICICI",
							"postgres", "pune@123");
			c.setAutoCommit(false);
			System.out.println("****Database connection and Employee ID checking****\n");
			System.out.println("Opened database succes");
			stmt =  c.prepareStatement( "SELECT * FROM conference;",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery();
			rs.last() ;
			int rowCount =  rs.getRow();
			System.out.println(rowCount);
			rs.beforeFirst();
			while ( rs.next() )
			{

				empid = rs.getInt("empid");
				if(empid==empiduser)
				{
					name = rs.getString("name");
					email = rs.getString("email");


					System.out.println( "EMPID = " + empid );
					System.out.println( "NAME = " + name );
					System.out.println( "EMAIL = " + email );

					System.out.println();
					System.out.println("Record exists");

					System.out.println("count is:"+count);
					System.out.println("**********Jumping to Roommaster table*********\n");

					String result=roomBookingProcess(empiduser, dt, st, et, user_cap, name, email);
					return result;

				}
				else if(count==rowCount)
				{
					return "Sorry..!!Entered employee id is incorrect";
				}
				count=count+1;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace(); 
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}

		return "";


	}
	public static String roomBookingProcess(int  empid1,String dt,String st,String et,int participants,String name,String email)
	{

		Connection c = null;
		int usti=0,ueti=0;int j=1;
		ResultSet rs1 =null;
		PreparedStatement ps=null;
		int rowCount = 1,count=1;
		int capacity=0;
		String roomname="";
		boolean matchfound=false;
		try 
		{

			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb","postgres", "12345");
			ps=c.prepareStatement("SELECT * FROM roommaster1",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs1 = ps.executeQuery();
			rs1.last() ;
			rowCount =  rs1.getRow();
			System.out.println(rowCount);

			rs1.beforeFirst();
			while(rs1.next())
			{  
				capacity = rs1.getInt("capacity");
				int user_capacity=Integer.valueOf(participants);
				if(user_capacity<= capacity)
				{
					roomname=rs1.getString("roomname");
					System.out.println(roomname);
					matchfound=roomBooking(roomname, empid1, dt, st, et, user_capacity, name, email);
					if(matchfound==true)
					{
						return "room is successfully booked..";	
					}else if(count==rowCount)
					{
						return "Room is not available from "+st+" to "+et+" time slot";
					}
				}
				else if(count==rowCount)
				{
					return "Sorry..!!Room is not available";
				}
				count++;

			}
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return "";
	}

	public static boolean roomBooking(String roomname,int  empid1,String dt,String st,String et,int participants,String name,String email) throws SQLException, ClassNotFoundException, ParseException
	{
		Connection c = null;
		int usti=0,ueti=0;int j=1;
		ResultSet rs1 =null;
		PreparedStatement ps=null;
		int rowCount = 1,count=1;
		String datefromDB=null, stfromDB=null,etfromDB=null,token="";

		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb","postgres", "12345");

		String sql1="SELECT * FROM "+roomname;
		ps=c.prepareStatement(sql1,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs1 = ps.executeQuery();
		rs1.next();
		datefromDB = rs1.getString("date1");
		System.out.println(datefromDB);
		j++;
		
		rs1.last() ;
		rowCount =  rs1.getRow();
		System.out.println(rowCount);
		rs1.beforeFirst();
		String ust[]=st.split(":");
		String uet[]=et.split(":");
		usti=Integer.valueOf((String)ust[0]);	
		ueti=Integer.valueOf((String)uet[0]);
		
		System.out.println("hi");
		//System.out.println(datefromDB);
		Date user_date =new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy 00:00:00").format(new SimpleDateFormat("yyyy-MM-dd").parse(dt)));

		while(rs1.next()){
			datefromDB = rs1.getString("date1");
			System.out.println("hello");
			System.out.println(datefromDB);
			Date DB_date =new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy 00:00:00").format(new SimpleDateFormat("yyyy-MM-dd").parse(datefromDB)));
			
			stfromDB=rs1.getString("starttime");
			String s1[]=stfromDB.split(":");
			int stDB = Integer.valueOf(s1[0]);

			etfromDB=rs1.getString("endtime");
			String s2[] = etfromDB.split(":");
			int etDB = Integer.valueOf(s2[0]);
			System.out.println("Start Time="+stDB+" End Time="+etDB+" User Start time="+usti+" User end Time="+ueti+" date="+dt); 
			
			if(user_date.equals(DB_date))
			{
				
				if((stDB<=usti && etDB>=usti) || (stDB<=ueti && etDB>=ueti))
				{
					System.out.println("Room"+roomname+" is not available from "+stDB+" to "+etDB+" time slot");
					String res="Room"+roomname+" is not available from "+stDB+" to "+etDB+" time slot";

					return false;
				}//if st et
				else if(!rs1.next())
				{
					String sql ="insert into "+roomname+" values (?,?,?,?,?,?,?)";	

					ps= c.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, dt);
					ps.setString(2, st);
					ps.setString(3, et);
					ps.setString(4, name);
					ps.setInt(5, empid1);
					ps.setInt(6, participants);
					token=getTicket(dt, roomname);
					ps.setString(7, token);
					ps.executeUpdate();
					return true;
				}//else st et
			}//if for date
			else if(count==rowCount)
			{
				String sql ="insert into "+roomname+" values (?,?,?,?,?,?,?)";	

				ps= c.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ps.setString(1, dt);
				ps.setString(2, st);
				ps.setString(3, et);
				ps.setString(4, name);
				ps.setInt(5, empid1);
				ps.setInt(6, participants);
				token=getTicket(dt, roomname);
				ps.setString(7, token);
				ps.executeUpdate();
				return true;
			}//else 

			count++;
		}
		return false;
	}
	public static String getTicket(String dt,String roomname){
		Random randomNum = new Random();
		String token="";
		 dt=dt.replace("-", "");
		token=roomname+".Ticket#"+dt+ (randomNum.nextInt(10000-5)+123456789);
		System.out.println("Ticket Number="+token);
        return token;
	}

	
}

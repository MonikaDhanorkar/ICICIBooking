package com.icici.main;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.icici.POJOmodel.JavaModel;
import com.icici.POJOmodel.Parameters;
import com.icici.POJOmodel.Result;
import com.icici.business.ApiAi;
import com.icici.business.Logic;

@Path("icici")
public class RequestResponse
{
	@GET
	public Response GetMsg() throws IOException{
		return Response.status(200).entity("Welcome User ").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response odRequest(String outputJSON) throws IOException, InterruptedException
	{
		String empid="";
		String date="";
		String starttime="";
		String endtime="";
		String capacity="";
		String result="";
		JavaModel JM_Response=null;
		ApiAi apiresponse=null;
		Result rs=null;
		Parameters params=null;
		//Tickets ticket=new Tickets();

		try{
			System.out.println("Request recieved");
			apiresponse = new ApiAi();
			System.out.println("responceBO : "+apiresponse.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

		
		try{
			JM_Response = apiresponse.jsonToJava(outputJSON);
			System.out.println("apiAiResponse : " +JM_Response);
		}catch(Exception e){
			e.printStackTrace();
		}

		try{
			rs=JM_Response.getResult();
			System.out.println("rs :"+rs.toString());

		}catch(Exception e){e.printStackTrace();
		}
		try{
			params=rs.getParameters();
			empid=params.getUsername();
			date=params.getDate();
			starttime=params.getStarttime();
			endtime=params.getEndtime();
			capacity=params.getParticipants();
			
			 Logic l1= new Logic();
			result=l1.checkEmp(empid, date, starttime, endtime, capacity);

			//result=ticket.apply(userName, pass, type, to,services,sla,subject,text);
		}
		catch(Exception e){
			e.printStackTrace();
		}


		return Response.status(200).entity(result).build();
	}


}

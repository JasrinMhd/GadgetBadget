package com;

import com.Researcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResearcherAPI
 */
@WebServlet("/ResearcherAPI")
public class ResearcherAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Researcher Obj = new Researcher();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResearcherAPI() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = Obj.insertResearcher(request.getParameter("firstname"), 
				request.getParameter("lastname"),
				request.getParameter("gender"), 
				request.getParameter("email"),
				request.getParameter("password"));

		response.getWriter().write(output);

		 //doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		
		 String output = Obj.updateResearcher(paras.get("hididSave").toString(), 
		 paras.get("firstname").toString(), 
		 paras.get("lastname").toString(), 
		 paras.get("gender").toString(), 
		 paras.get("email").toString(),
		 paras.get("password").toString()); 
		 
		 
		 response.getWriter().write(output); 
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		
		 String output = Obj.deleteResearcher(paras.get("id").toString()); 
		 
		response.getWriter().write(output);
		
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			String queryString = scanner.hasNext() ? 
						scanner.useDelimiter("\\A").next() : ""; 
	
				scanner.close(); 
				String[] params = queryString.split("&"); 
				for (String param : params) 
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]); 
				} 
		} 
		catch (Exception e) 
	 	{ 
	 	} 
		return map; 
		}

}

package com.ollibolli.monkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rxtx.Serial;

/**
 * Servlet implementation class MonkeyServlet
 */
public class MonkeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Serial ardurino;
	private PrintStream ps = null;
	private FileOutputStream f;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MonkeyServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			
			//f  = new FileOutputStream(new File("/var/log/tomcat6/monkey.ollibolli.com.log"));
			f  = new FileOutputStream(new File(config.getServletContext().getRealPath("") + "/monkey.log"));
			ps = new PrintStream(f);
			System.setOut(ps);
			System.out.println("monkey servlet started:");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(ps);
		}
		try {
			ardurino = Serial.getInstance();
			System.out.println("servlet init :" + ardurino.toString());
			ardurino.initialize();
		} catch (Exception e) {
			e.printStackTrace(ps);
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ardurino.sendTrigger(ardurino);
		} catch (InterruptedException e) {
			e.printStackTrace(ps);
		} catch (NullPointerException e) {
			e.printStackTrace(ps);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		ps.close();
		ardurino.close();
		try {
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

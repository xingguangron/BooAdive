package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.book.beans.*;
import com.book.dao.BookDao;
import com.book.util.*;
import com.google.gson.Gson;
public class bookListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
	//	System.out.println("page:"+page+",rows:"+rows);
		String book_name=request.getParameter("book_name");
		//System.out.println("List:"+book_name);
		Book book=new Book();
		BookDao dao=new BookDao();
		List<Book> list=dao.findAllBook();
		String bookJson = gson.toJson(list);
		
		
		if(book_name==null){
			book_name="";
		}
		book.setBook_name(book_name);
	
		if(page!=null&&!"".equals(page)){
		Connection con=null;
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			try {
				con=DbUtil.getConnection();
				JSONObject result=new JSONObject();
				JSONArray jsonArray=JsonUtil.formatRsToJsonArray(dao.bookList(con, pageBean,book));
				int total=dao.bookCount(con,book);
				result.put("rows", jsonArray);
				result.put("total", total);
				ResponseUtil.write(response, result);
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else{
			PrintWriter out=response.getWriter();
			//System.out.println(bookJson);
			out.print(bookJson);
		}
			
		
		}
	}



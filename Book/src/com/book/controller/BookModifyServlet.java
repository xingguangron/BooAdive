package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.BookDao;
import com.book.util.DbUtil;

public class BookModifyServlet extends HttpServlet {

	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//<!-- String book_name, String author,String publish,String content,String reason -->
		System.out.println("修改");
		String book_name=request.getParameter("book_name");
		System.out.println(book_name);
		String book_id=request.getParameter("book_id");
		System.out.println(book_id);
		String author=request.getParameter("author");
		String publish=request.getParameter("publish");
		String content=request.getParameter("content");
		String reason=request.getParameter("reason");
		
		BookDao dao=new BookDao();
		//dao.addBook(con, book_name, author, publish, content, reason);
				dao.updateBook(book_id, book_name, author, publish, content, reason);
				
		
	
	}
}

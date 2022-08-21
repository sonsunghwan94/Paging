<%@page import="model.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="pVO" class="model.ProductVO"></jsp:useBean>
    <jsp:useBean id="pDAO" class="model.ProductDAO"></jsp:useBean>
<%
	String action=request.getParameter("action");
	int pageSize=5;
	int pageNum; 
	int startNum; 
	int endNum; 
	int pageCount;
	int totalcnt;
	if(action.equals("main")){
		if(request.getParameter("pageNum")!=null){
		pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}else{
		pageNum=1;	
		}
		startNum=(pageNum-1)*pageSize+1;
		endNum=(pageNum-1)*pageSize+pageSize;
		totalcnt=pDAO.count();
		pageCount=totalcnt/pageSize+(totalcnt%pageSize==0?0:1);
		request.setAttribute("pcnt", pageCount);
		pVO.setStartNum(startNum);
		pVO.setEndNum(endNum);
		ArrayList<ProductVO> datas=pDAO.selectAll(pVO);
		if(datas.size()==0){
			response.sendRedirect("ctrl.jsp?action=main");
		}else{
		request.setAttribute("datas", datas);
		pageContext.forward("main.jsp");
		}
	}

%>
package edu.kh.todolist.service;

import static edu.kh.todolist.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.dao.TodoListDao;
import edu.kh.todolist.dao.TodoListDaoImpl;
import edu.kh.todolist.dto.Todo;

public class TodoListServiceImpl implements TodoListService{

	// 필드
	private TodoListDao dao = new TodoListDaoImpl();
	
	@Override
	public int todoAdd(String title, String detail) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.todoAdd(conn, title, detail);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	@Override
	public Map<String, Object> todoListFullView() throws Exception {
		
		Connection conn = getConnection();
		
		List<Todo> todoList = dao.todoListFullView(conn);
		
		int completeCount = dao.getCompleteCount(conn);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		return map;
	}
	
	
	@Override
	public int todoUpdate(int todoNo, String title, String detail) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.todoUpdate(conn, todoNo, title, detail);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	@Override
	public int todoDelete(int todoNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.todoDelete(conn, todoNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	@Override
	public int todoComplete(int todoNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.todoComplete(conn, todoNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		close(conn);
		
		return result;
	}
	
	@Override
	public Todo todoDetailView(int todoNo) throws Exception {
		
		Connection conn = getConnection();
		
		Todo todo = dao.todoDetailView(conn, todoNo);
		
		close(conn);
		
		return todo;
	}
}

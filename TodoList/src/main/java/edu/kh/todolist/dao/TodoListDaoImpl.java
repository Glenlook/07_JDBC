package edu.kh.todolist.dao;

//지정된 클래스의 static 메서드를 모두 얻어와 사용
import static edu.kh.todolist.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todolist.common.JDBCTemplate;
import edu.kh.todolist.dto.Todo;

public class TodoListDaoImpl implements TodoListDao{
	
	// 필드
	
	// JDBC 객체 참조 변수 + Properties 참조 변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	// -> K,V가 모두 String인 Map, 파일 입출력이 쉬움
	
	// 기본 생성자
		public TodoListDaoImpl() {
			
			// 객체 생성 시
			// 외부에 존재하는 sql.xml 파일을 읽어와
			// prop에 저장
			
			try {
				String filePath =
						JDBCTemplate.class
						.getResource("/edu/kh/todolist/sql/sql.xml").getPath();
				
				// 지정된 경로의 XML 파일 내용을 읽어와
				// Properties 객체에 K:V 세팅
				prop = new Properties();
				prop.loadFromXML(new FileInputStream(filePath));
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public int todoAdd(Connection conn, Todo todo) throws Exception {
			
			int result = 0;
			
			try {
				String sql = prop.getProperty("todoAdd");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, todo.getTitle());
				pstmt.setString(2, todo.getDetail());
				pstmt.setString(3, todo.getComplete());
				
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		@Override
		public List<Todo> todoListFullView(Connection conn) throws Exception {
			
			List<Todo> todoList = new ArrayList<Todo>();
			
			try {
				String sql = prop.getProperty("todoListFullView");
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					int num = rs.getInt("TODO_NO");
					String title = rs.getString("TODO_TITLE");
					String detail = rs.getString("TODO_DETAIL");
					String complete = rs.getString("TODO_COMPLETE");
					String regDate = rs.getString("REG_DATE");
					
					Todo todo = new Todo(num, title, detail, complete, regDate);
					
					todoList.add(todo);
				}
				
			} finally {
				close(rs);
				close(stmt);
			}
			
			return todoList;
		}
		
		@Override
		public int todoUpdate(Connection conn, Todo todo) throws Exception {
			
			int result = 0;
			
			try {
				String sql = prop.getProperty("todoUpdate");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, todo.getTitle());
				pstmt.setString(2, todo.getDetail());
				pstmt.setString(3, todo.getComplete());
				pstmt.setInt(4, todo.getNum());
				
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		@Override
		public int todoDelete(Connection conn, int num) throws Exception {
			
			int result = 0;
			
			try {
				String sql = prop.getProperty("todoDelete");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}
}

package edu.kh.todolist.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.todolist.dto.Todo;

public interface TodoListDao {
	// public abstract를 명시하지 않아도 public abstract으로 해석됨!!

	
	/** 할일 목록 조회
	 * @param conn
	 * @return todoList
	 * @throws Exception
	 */
	List<Todo> todoListFullView(Connection conn)throws Exception;

	/** 할일 등록
	 * @param conn
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	int todoAdd(Connection conn, Todo todo) throws Exception;

	/** 할일 수정
	 * @param conn
	 * @param todo
	 * @return result
	 * @throws Exception
	 */
	int todoUpdate(Connection conn, Todo todo) throws Exception;

	/** 할일 삭제
	 * @param conn
	 * @param num
	 * @return result
	 * @throws Exception
	 */
	int todoDelete(Connection conn, int num) throws Exception;
}

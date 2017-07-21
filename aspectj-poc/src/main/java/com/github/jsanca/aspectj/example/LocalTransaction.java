package com.github.jsanca.aspectj.example;

import java.util.concurrent.Callable;

public class LocalTransaction {



    /**
	 * 
	 * @param callable
	 * @return
	 * @throws Exception
	 * 
	 * This class can be used to wrap methods in a "local transaction" pattern
	 * this pattern will check to see if the method is being called in an existing transaction.
	 * if it is being called in a transaction, it will do nothing.  If it is not being called in a transaction
	 * it will checkout a db connection,start a transaction, do the work, commit the transaction, return the result 
	 * and  finally close the db connection.  If the SQL call fails, it will rollback the work, close  the db connection 
	 * and throw the error up the stack.
	 * 
	 *  How to use:
	 *  
	 *	return new LocalTransaction().wrapReturn(() ->{
	 *		return myDBMethod(args); 
	 *  }); 
	 */

	static public <T> T wrapReturn(final Callable<T> callable) throws Exception{

		T result = null;
		try {

			System.out.println("starting");
			result= callable.call();
			System.out.println("commit");
		} catch (Exception e) {
			System.out.println("rollback");
            e.printStackTrace();
		} finally {
			System.out.println("closing..");
		}
		return result;
	}
	

}

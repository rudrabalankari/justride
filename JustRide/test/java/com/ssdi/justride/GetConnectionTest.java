package com.ssdi.justride;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.justride.util.GetConnection;

public class GetConnectionTest {

	@Test
	public void testConnection() {

		try {
			Connection connection = GetConnection.getConnection();
			assertEquals(connection != null, true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

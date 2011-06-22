package com.tonyyuzhang.sis.studentinfo;

import java.io.*;
import java.util.*;

public class CourseCatalog {
	private List<Session> sessions = new ArrayList<Session>();

	public void add(Session session) {
		sessions.add(session);
	}

	public void clearAll() {
		sessions.clear();
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void store(String filename) 
			throws IOException {
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new FileOutputStream(filename));
			output.writeObject(sessions);
		}
		finally {
			output.close();
		}
	}
	
	public void load(String filename) 
			throws IOException, ClassNotFoundException {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream(filename));
			sessions = cast(input);
		}
		finally {
			input.close();
		}
	}

	@SuppressWarnings("unchecked")
	private List<Session> cast(ObjectInputStream input)
			throws ClassNotFoundException, IOException {
		return (List<Session>)input.readObject();
	}
	
	
} 
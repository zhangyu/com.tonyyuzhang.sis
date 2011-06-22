package com.jimbob.ach;

import java.util.*;

public class AchResponse {
	public Date timestamp;
	public String traceCode;
	public AchStatus status;
	public List<String> errorMessages;
}

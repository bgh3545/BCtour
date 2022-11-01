package com.greenart.ch1;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class ModelController {
	public String main(HashMap map) {
		map.put("id","asdf");
		map.put("pwd","1111");
		
		return "textView";
	}
}

public class MethodCall{
	public static void main(String[] args) throws Exception {
		HashMap map = new HashMap();
		System.out.println("before:"+map);
		
		ModelController mc = new ModelController();
		String viewName = mc.main(map);
		
		render(map,viewName);

	}
	static void render(HashMap map, String viewName) throws IOException{
	String res = "";
	
	Scanner sc = new Scanner(new File(viewName+".txt"));
	
	while(sc.hasNextLine())
		res+= sc.nextLine()+"\n";
	System.out.println("출력 전처리 전 res: "+res);
	Iterator it = map.keySet().iterator();

	while(it.hasNext()) {
		String key = (String)it.next();
		
		res = res.replace("${"+key+"}",(String)map.get(key));
	}
	
	System.out.println(res);
	
}
}

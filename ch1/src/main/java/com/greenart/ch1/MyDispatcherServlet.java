package com.greenart.ch1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@WebServlet("/myDispatcherServlet")
public class MyDispatcherServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map map = request.getParameterMap();
		Model model = null;
		String viewName="";
		
		try {
			Class clazz = Class.forName("com.greenart.ch1.YoilTellerMVC");
			Object obj = clazz.newInstance();
			
			Method main = clazz.getDeclaredMethod("main",int.class,int.class,int.class,Model.class);
			
			Parameter[] paramArr = main.getParameters();
			Object[] argArr = new Object[main.getParameterCount()];
			
			for(int i=0; i<paramArr.length; i++) {
				String paramName = paramArr[i].getName();
				Class paramType = paramArr[i].getType();
				Object value = map.get(paramName);
				
				if(paramType == Model.class) {
					argArr[i] = model = new BindingAwareModelMap();
				}else if(paramType == HttpServletRequest.class) {
					argArr[i]=request;
				}else if(paramType == HttpServletResponse.class) {
					argArr[i]=response;
				}else if(value != null) {
					String strValue = ((String[])value)[0];
					argArr[i]=convertTo(strValue,paramType);
				}
			}
			viewName = (String)main.invoke(obj, argArr);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		render(model,viewName,response);

	}
	private static Object convertTo(Object value, Class type) {
		if(type==null || value==null || type.isInstance(value)) // 타입이 같으면 그대로 반환 
			return value;

		// 타입이 다르면, 변환해서 반환
		if(String.class.isInstance(value) && type==int.class) { // String -> int
			return Integer.valueOf((String)value);
		} else if(String.class.isInstance(value) && type==double.class) { // String -> double
			return Double.valueOf((String)value);
		}
			
		return value;
	}
	
	private String getResolvedViewName(String viewName) {
		return getServletContext().getRealPath("/WEB-INF/views") +"/"+viewName+".jsp";
	}
	
	private void render(Model model, String viewName,HttpServletResponse response) throws IOException {
		String result = "";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		// 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File(getResolvedViewName(viewName)), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ "\n";
		
		// 2. model을 map으로 변환 
		Map map = model.asMap();
		
		// 3.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 4. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+" }", ""+map.get(key)+"");
		}
		
		// 5.렌더링 결과를 출력한다.
		out.println(result);
	}

}

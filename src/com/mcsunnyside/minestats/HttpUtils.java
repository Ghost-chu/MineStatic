package com.mcsunnyside.minestats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
	    /**
	     * 向指定 URL 发送POST方法的请求
	     *
	     * @param url   发送请求的 URL
	     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	     * @return 所代表远程资源的响应结果
	     */
	    public static String sendPost(String url, String param) {
	    	System.out.println("[MineStats] "+param);
	        PrintWriter out = null;
	        BufferedReader in = null;
	        StringBuilder result = new StringBuilder();
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1);JavaPlugin SunnySide Stats (1.0.0)");
	            conn.setRequestProperty("accept-language", "en-US,en;q=0.5");
	            conn.setRequestProperty("content-type", "application/json");
	            // 发送POST请求,必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();

	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result.append(line);
	            }
	            System.out.println("[MineStats] "+result);
	        } catch (Exception e) {
	        	e.printStackTrace();
	           // LOGGER.error("HTTP POST error : {}", e.getMessage());
	        }
	        //使用finally块来关闭输出流、输入流
	        finally {
	            try {
	                if (out != null) out.close();
	                if (in != null) in.close();
	            } catch (IOException ex) {
	            	ex.printStackTrace();
	            }
	        }
	        return result.toString();
	    }
}

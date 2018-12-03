package com.mcsunnyside.minestats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
	    /**
	     * ��ָ�� URL ����POST����������
	     *
	     * @param url   ��������� URL
	     * @param param ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	     * @return ������Զ����Դ����Ӧ���
	     */
	    public static String sendPost(String url, String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        StringBuilder result = new StringBuilder();
	        try {
	            URL realUrl = new URL(url);
	            // �򿪺�URL֮�������
	            URLConnection conn = realUrl.openConnection();
	            // ����ͨ�õ���������
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1);JavaPlugin SunnySide Stats (1.0.0)");
	            conn.setRequestProperty("accept-language", "en-US,en;q=0.5");
	            conn.setRequestProperty("content-type", "application/json");
	            // ����POST����,����������������
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // ��ȡURLConnection�����Ӧ�������
	            out = new PrintWriter(conn.getOutputStream());
	            // �����������
	            out.print(param);
	            // flush������Ļ���
	            out.flush();

	            // ����BufferedReader����������ȡURL����Ӧ
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result.append(line);
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	           // LOGGER.error("HTTP POST error : {}", e.getMessage());
	        }
	        //ʹ��finally�����ر��������������
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

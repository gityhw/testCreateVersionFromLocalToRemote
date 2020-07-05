package com.atguigu.crud.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.service.TestService;

/**
 * 文件下载测试
 * @author yh
 *
 */
@Controller
public class TestControl {
	
	@Autowired
	private TestService testService;
	
	/**
     * Description: 导出数据 .
     * @Title: export  
     * @param request 。
     * @param response 。 
     * @param startDate 。 
     * @param endDate 。
     * @param startDate2 。
     * @param endDate2 。
     * @throws Exception void 。
     * @author Yang Hui, 2019年4月19日
     */
    
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletRequest request, 
    		HttpServletResponse response) throws Exception {
    	File file = new File("F:\\河南大学2015-2016年校历.doc");
    	exportReport(file, response);
    }

    
    /**
     * @功能 报表下载的方法
     * @param file
     * @param response
     */
    public static void exportReport(File file, HttpServletResponse response){
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(file);
            os = response.getOutputStream();
            response.reset();
            // 头部文件响应设置（导出文件）
            response.setContentType("applicationnd.ms-word;charset=UTF-8");
            //设置下载的文件名称
            String name = new String((file.getName()).getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+name+"\"");
            byte[] b = new byte[4096];
            int len=0;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(os != null){
                    os.flush();
                    os.close();
                }
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
    	final int start = Integer.MAX_VALUE - 2;
        final int end = Integer.MAX_VALUE;
        int count = 0;
        for (int i = start; i <= end; i++)
            count++;
        System.out.println(count);
	}

}

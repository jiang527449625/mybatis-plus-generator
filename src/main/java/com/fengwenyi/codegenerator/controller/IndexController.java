package com.fengwenyi.codegenerator.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.codegenerator.Custom;
import com.fengwenyi.codegenerator.ZIPUtil;
import com.fengwenyi.codegenerator.service.IIndexService;
import com.fengwenyi.codegenerator.vo.CodeGeneratorRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import org.apache.tools.zip.ZipOutputStream;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-12
 */
@Controller
public class IndexController {

    @Autowired
    private IIndexService indexService;

    @Autowired
    private Custom custom;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/code-generator")
    @ResponseBody
    public ResultTemplate<Void> codeGenerator(@RequestBody @Validated CodeGeneratorRequestVo requestVo) {
        ResultTemplate r=indexService.codeGenerator(requestVo);
        //第一个参数是需要压缩的源路径；第二个参数是压缩文件的目的路径，这边需要将压缩的文件名字加上去

        if(r.getMsg().equals("Success")){
            File zipFile = new File(custom.downloaddir);
            if (!zipFile.exists()) {
                zipFile.mkdirs();// 能创建多级目录
            }
            ZIPUtil.compress(custom.outdir+"\\",custom.downloaddir+"\\demo.zip");
        }

        return r;
    }
    @GetMapping("/downloadFile")
    @ResponseBody
    private String downloadFile(HttpServletResponse response) throws IOException {
        String downloadFilePath = custom.downloaddir+"\\demo.zip";//被下载的文件在服务器中的路径,
        String fileName = "demo.zip";//被下载文件的名称
        //设置为png格式的文件
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File(downloadFilePath)));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //删除代码生成目录
        File f = new File(custom.outdir);
        deleteFile(f);//删除完后并没有删除根目录
        //删除生成的zip
        File f1 = new File(custom.downloaddir);
        deleteFile(f1);//删除完后并没有删除根目录
        //成功后返回成功信息
        return "下载成功";
    }

    public static void deleteFile(File file) throws IOException {
        /**
         *  File[] listFiles()
         *    返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件。
         */
        File[] files = file.listFiles();
        if (files!=null){//如果包含文件进行删除操作
            for (int i = 0; i <files.length ; i++) {
                if (files[i].isFile()){
                    //删除子文件
                    files[i].delete();
                }else if (files[i].isDirectory()){
                    //通过递归的方法找到子目录的文件
                    deleteFile(files[i]);
                }
                files[i].delete();//删除子目录
            }
        }
    }

}

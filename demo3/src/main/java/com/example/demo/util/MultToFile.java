package com.example.demo.util;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
public class MultToFile {
    public static File multpartfileTofile(MultipartFile multipartFile) throws IOException {
        File tofile=null;
      if(multipartFile.equals("")||multipartFile.getSize()<=0){
          multipartFile=null;
      }else{
          InputStream ins=null;
          ins=multipartFile.getInputStream();
          tofile=new File(multipartFile.getOriginalFilename());
          inputStreamToFile(ins,tofile);
          ins.close();
      }
      return tofile;
    }
    public static void inputStreamToFile(InputStream inputStream,File file){
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

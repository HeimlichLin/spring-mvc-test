package idv.heimlich.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Test {

}

// File轉MultipartFile
class Test1 {
	
	public String get1() {
		return null;
	}
	
	public static String get2() {
		return null;
	}
	
	public static void main(String[] args) throws Exception {
        String filePath = "F:\\test.txt";
        File file = new File(filePath);
        // 需要導入commons-fileupload的jar
        FileItem fileItem = new DiskFileItem("copyfile.txt", Files.probeContentType(file.toPath()),false,file.getName(),(int)file.length(),file.getParentFile());
        byte[] buffer = new byte[4096];
        int n;
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()){
           while ( (n = inputStream.read(buffer,0,4096)) != -1){
               os.write(buffer,0,n);
           }
            //也可以用IOUtils.copy(inputStream,os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            System.out.println(multipartFile.getName());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

// MultipartFile轉File
class test2 {
	public static void main(String[] args) throws Exception {
        int n;
        // 得到MultipartFile文件
        MultipartFile multipartFile = getFile();
        File f = null;
        // 輸出文件的新name 就是指上傳後的文件名稱
        System.out.println("getName:"+multipartFile.getName());
        // 輸出源文件名稱 就是指上傳前的文件名稱
        System.out.println("Oriname:"+multipartFile.getOriginalFilename());
        // 創建文件
        f = new File(multipartFile.getOriginalFilename());
        try ( InputStream in  = multipartFile.getInputStream(); OutputStream os = new FileOutputStream(f)){
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            while ((n = in.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            // 讀取文件第一行
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            System.out.println(bufferedReader.readLine());
            // 輸出路徑
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        // 輸出file的URL
        System.out.println(f.toURI().toURL().toString());
        // 輸出文件的絕對路徑
        System.out.println(f.getAbsolutePath());
        // 操作完上的文件需要删除在根目錄下生成的文件
        File file = new File(f.toURI());
        if (file.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
	
    /**
     * @Description 返回MultipartFile文件
     */
    public static MultipartFile getFile() throws IOException {
        String filePath = "F:\\test.txt";
        File file = new File(filePath);
        FileItem fileItem = new DiskFileItem("copyfile.txt", Files.probeContentType(file.toPath()),false,file.getName(),(int)file.length(),file.getParentFile());
        byte[] buffer = new byte[4096];
        int n;
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()){
            while ( (n = inputStream.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            //也可以用IOUtils.copy(inputStream,os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            System.out.println(multipartFile.getName());
            return multipartFile;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

// MultipartFile轉File
class test3 {
	public static void main(String[] args) throws Exception {
        String path = "F:\\demo\\";
        File file = new File(path,"demo.txt");
        // 得到MultipartFile文件
        MultipartFile multipartFile = getFile();
        /*byte[] ss = multipartFile.getBytes();
        OutputStream os = new FileOutputStream(file);
        os.write(ss);
        os.close();*/
        multipartFile.transferTo(file);
        // 讀取文件第一行
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        System.out.println(bufferedReader.readLine());
        // 輸出绝對路徑
        System.out.println(file.getAbsolutePath());
        bufferedReader.close();
        // 操作完上的文件 需要删除在根目錄下生成的文件
        if (file.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
	
    /**
     * @Description 返回MultipartFile文件
     */
    public static MultipartFile getFile() throws IOException {
        String filePath = "F:\\test.txt";
        File file = new File(filePath);
        FileItem fileItem = new DiskFileItem("copyfile.txt", Files.probeContentType(file.toPath()),false,file.getName(),(int)file.length(),file.getParentFile());
        byte[] buffer = new byte[4096];
        int n;
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()){
            while ( (n = inputStream.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            //也可以用IOUtils.copy(inputStream,os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            System.out.println(multipartFile.getName());
            return multipartFile;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

// MultipartFile轉File
class test4 {
	public static void main(String[] args) throws Exception {
        String path = "F:\\demo\\";
        File file = new File(path,"demo.txt");
        // 得到MultipartFile文件
        MultipartFile multipartFile = getFile();
        // 把流輸出到文件
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
        // 讀取文件第一行
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        System.out.println(bufferedReader.readLine());
        // 輸出绝對路徑
        System.out.println(file.getAbsolutePath());
        bufferedReader.close();
        // 操作完上的文件需要删除在根目錄下生成的文件
        if (file.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
	
    /**
     * @Description 返回MultipartFile文件
     */
    public static MultipartFile getFile() throws IOException {
        String filePath = "F:\\test.txt";
        File file = new File(filePath);
        FileItem fileItem = new DiskFileItem("copyfile.txt", Files.probeContentType(file.toPath()),false,file.getName(),(int)file.length(),file.getParentFile());
        byte[] buffer = new byte[4096];
        int n;
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()){
            while ( (n = inputStream.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            //也可以用IOUtils.copy(inputStream,os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            System.out.println(multipartFile.getName());
            return multipartFile;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
# JavaIO
## 1. 编码
```java
String s="慕课ABC";
byte[] bytes1=s.getBytes();//这是把字符串转换成字符数组，转换成的字节序列用的是项目默认的编码
for(byte b: bytes1)
System.out.println(Integer.toHexString(b & 0xff)+" ");// & 0xff是为了把前面的24个0去掉只留下后八位
//toHexString这个函数是把字节（转换成了Int）以16进制的方式显示
byte[] bytes1=s.getBytes("gbk");//也可以转换成指定的编码
```
gbk编码：  中文占用两个字节，英文占用一个字节  
utf-8编码：中文占用三个字节，英文占用一个字节  
java是双字节编码，是utf-16be编码  
utf-16be编码：中文占用两个字节，英文占用两个字节

当你的字节序列是某种编码时，这个时候想把字节序列变成字符串，也需要用这种编码方式，否则会出现乱码
```java
String str1=new String(bytes4);//这时会使用项目默认的编码来转换，可能出现乱码
String str2=new String(bytes4，"utf-16be");//要使用字节序列的编码来进行转换
```
文本文件就是字节序列，可以是任意编码的字节序列
如果我们在中文机器上直接创建文本文件，那么该文件只认识ANSI编码。（例如直接在电脑中创建文本文件）

## 2. File类
Java.IO.File类表示文件或目录，只用于表示文件或目录得信息，不能用于文件的访问。  

常用的API：  
1. 创建File对象：File file=new File(String path);注意：File.seperater();获取系统分隔符，如：”\“.
2. boolean file.exists();是否存在.
3. file.mkdir();或者file.mkdirs();创建目录或多级目录。
4. file.isDirectory()或者file.isFile()判断是否是目录或者是否是文件。
5. file.delete();删除文件或目录。
6. file.createNewFile();创建新文件。
7. file.getName()获取文件名称或目录绝对路径。
8. file.getAbsolutePath()获取绝对路径。
9. file.getParent();获取父级绝对路径。
10. file.getSize();获取文件大小。
11. file.getFormat();获取文件格式名。  

```java
import java.io.File;
import java.io.IOException;

// 列出file类的一些常用操作，如过滤、遍历等操作
public class FileUtils {
	public static void listDirectory(File dir) throws IOException{
		// exists()方法用于判断文件或目录是否存在
		if(!dir.exists()){
			throw new IllegalArgumentException("目录：" + dir + "不存在");	
		}
		// isDirectory()方法用于判断File类的对象是否是目录
		if(!dir.isDirectory()){
			throw new IllegalArgumentException(dir + "不是目录");
		}
		// list方法用于列出当前目录下的子目录和文件
		/*String[] filenames = dir.list(); // 返回的是字符串数组 直接子目录的名称 不包含子目录下的内容
		for (String string : filenames) {
			System.out.println(dir + ":\\" + string);
		}*/
		// 如果要遍历子目录下的内容就需要构造成File对象做递归操作，File提供了返回File对象的API
		File[] files = dir.listFiles(); // 返回的是直接子目录（文件）的抽象

		if(files!=null && files.length > 0){
			for (File file : files) {
				if(file.isDirectory()){
					// 递归
					listDirectory(file);
				}else{
					System.out.println(file);
				}
			}
		}
	}
}
```
## 3. RandomAccessFile
`RandomAccessFile`: java提供的对文件内容的访问类，既可以读文件，也可以写文件。支持随机访问文件，可以访问文件的任意位置。

java文件模型：在硬盘上的文件时 byte byte byte存储的，是数据的集合。

打开文件两种模式：“rw”（读写），“r”（只读）

`RandomAccessFile raf = new RandomAccessFile(File,"rw")`  
文件指针，打开文件时指针在开头 pointer = 0；

写方法：  
`raf.write(int)`--->只写一个字节（后8位），同时指针指向下一个位置，准备再次写入

读方法：  
`int b = raf.read()`--->读一个字节

文件读写完成以后一定要关闭
`raf.close()`

注意write方法每次只能写入一个字节：  
`raf.write('A');`//此时指针后移  
`System.out.println(raf.getFilePointer());`此时输出为1  
//这时只写入了一个字节而不是完整的char，只是因为后八位刚好能够表示A  
`raf.write('B');`

若要写入一个整数i则需要写四次  
```java
int i=0x7fffffff;  
raf.write(i>>>24);//高八位  
raf.write(i>>>16);  
raf.write(i>>>8);  
raf.write(i);//写入最低的八位  
System.out.println(raf.getFilePointer());  
//此时打印输出6  
```
可以直接写入一个int  
`raf.writeInt(i);`
```java
String s="中";  
byte[] gbk=s.getBytes("gbk");  
raf.write(gbk);  
System.out.println(raf.length(0);  
//此时打印输出12（中文占俩字节）  
```
```java
读文件，必须把指针移到头部  
raf.seek();  
//一次性读取：  
byte[] buf= new byte[(int)raf.length()];  
raf.read(buf);  
System.out.println(Arrays.toString(buf));  
此时打印输出  
[65,66,127,-1,-1,-1,127,-1,-1,-1,-42,-48]  
开头的65,66是正确的AB，因为后八位已经能表示AB了  

也可按字符串输出  
String s1=new String(buf);  
System.out.println(s1,"gbk");  
打印输出AB?????  
因为“中”的前后都有字节，只有定位到中的两个字节，才能读出他  
```
最后要加上raf.close()

## 4. 字节流
IO流分为输入流、输出流  
还有字节流、字符流  
1、字节流：  
（1）InputStream：抽象了应用程序读取数据的方式  
（2）OutputStream：抽象了应用程序写 出数据的方式  
2）EOF = End 读到-1就读到结尾  
3）输入流基本方法  
int b = in.read();读取一个字节无符号填充到int低八位.-1是EOF  
in.read(byte[] buf) 读取数据填充到字节数组buf  
in.read(byte[] buf,int start, int size)读取数据到字节数组buf从buf的start位置开始存放size长度分数据  
4）输出流基本方法  
out.write(int b)写出一个byte到流，b的低8位  
out.write(byte[] buf)将buf字节数组都写到流  
out.write(byte[] buf, int start,int size) 字节数组buf从start位置开始写size长度的字节到流  
```java
// 批量读取，对大文件而言效率高，也是我们最常用的读取方式
	public static void printHexByByteArray(String fileName) throws IOException {
		FileInputStream in = new FileInputStream(fileName);
		byte[] buf = new byte[20 * 1024];
		// 从in中批量读取字节，放入到buf这个字节数组中， 从0个位置开始放，最多放buf.length个 返回的是读到的字节的个数
		int bytes = in.read(buf, 0, buf.length);
		// 一次性读完，说明字节数组足够大
		int j = 1;
		for (int i = 0; i < bytes; i++) {
			if (buf[i] <= 0xf) {
				System.out.println("0");
			}
			// 将整型b转换成16进制表示的字符串
			System.out.print(Integer.toHexString(buf[i]) + " ");
			if (j++ % 10 == 0) {
				System.out.println();
			}
		}

		int bytes2 = 0, k = 1;
		while ((bytes2 = in.read(buf, 0, buf.length)) != -1) {
			for (int i = 0; i < bytes2; i++) {
				// byte类型为8位，int类型32位，为了避免数据转换错误，通过&0Xff将高24位清零
				System.out.print(Integer.toHexString(buf[i] & 0xff) + " ");
				if (k++ % 10 == 0) {
					System.out.println();
				}
			}
		}
		in.close();
	}
```
```java
public static void copyFile(File srcFile,File destFile)throws IOException{
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件:"+srcFile+"不存在");
		}
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		byte[] buf = new byte[8*1024];
		int b ;
	    while((b = in.read(buf,0,buf.length))!=-1){
	    	
	    	out.write(buf,0,b);
	    	out.flush();//最好加上
	    }
	    in.close();
	    out.close();
		
	}
```
```java
public class DosDemo {
	public static void main(String[] args) throws IOException {
		String file = "demo/dos.dat";
		DataOutputStream dos = new DataOutputStream(
				         new FileOutputStream(file));
		dos.writeInt(10);
		dos.writeInt(-10);
		dos.writeLong(10l);
		dos.writeDouble(10.5);
		//采用utf-8编码写出
		dos.writeUTF("中国");
		//采用utf-16be编码写出
		dos.writeChars("中国");
		dos.close();
		IOUtil.printHex(file);
	}

}

public class DisDemo {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String file = "demo/dos.dat";
		IOUtil.printHex(file);
	   DataInputStream dis = new DataInputStream(
			   new FileInputStream(file));
	   int i = dis.readInt();
	   System.out.println(i);
	   i = dis.readInt();
	   System.out.println(i);
	   long l = dis.readLong();
	   System.out.println(l);
	   double d = dis.readDouble();
	   System.out.println(d);
	   String s = dis.readUTF();
	   System.out.println(s);
	   
       dis.close();
	}

}
```
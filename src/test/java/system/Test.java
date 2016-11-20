package system;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;

public class Test {
	public void testMD5(){
		String str1 = new Md5PasswordEncoder().encodePassword("1", null);
		String str2 = MD5Encoder.encode("1".getBytes());
		System.out.println(str1);
		System.out.println(str2);
	}
	
	public static void main(String[]args){
		new Test().testMD5();
	}
}

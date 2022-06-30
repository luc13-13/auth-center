package com.lc.auth.center;

import org.apache.shiro.codec.Base64;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {

	@Test
	void contextLoads() throws UnsupportedEncodingException {
		String KEY_LUC = "LUCHENG_TWF";
		byte[] bytes = Base64.encode(KEY_LUC.getBytes(StandardCharsets.US_ASCII));
		System.out.println(bytes.length);
		System.out.println(new String(bytes));
		byte[] bytes_decode = Base64.decode(bytes);
		System.out.println(bytes.length);
		System.out.println(new String(bytes_decode));
	}


}

package io.whileaway.apit.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Random {

	public static String getSalt(String password) {
		List<String> result = Arrays.asList(password.split(""));
		Collections.shuffle(result);
		return result.stream().limit(6).collect(Collectors.joining());
	}
	 public static void main(String[] args) {
		String password = "12345678";
		List<String> result = Arrays.asList(password.split(""));
		Collections.shuffle(result);
		 System.out.println(new Md5Hash(password, "123456").toString().length());
		System.out.println(result.stream().limit(6).collect(Collectors.joining("")));
	}
}

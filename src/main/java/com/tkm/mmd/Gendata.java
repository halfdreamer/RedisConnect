package com.tkm.mmd;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.google.gson.Gson;

public class Gendata {

	static Device dv;
	static Gson gsn;
	static Date r;
	static SimpleDateFormat DATE_FORMAT;
	static PrintWriter writer;

	public static void gendata() {

		gsn = new Gson();
		dv = new Device();
		Random random = new Random();
		String s;
		r = new Date();
		DATE_FORMAT = new SimpleDateFormat("dd-MM-yy");
		try {
			try {
				writer = new PrintWriter("C:/indata.txt", "UTF-8");

				for (int i = 0; i < 1000000; i++) {
					dv.setUdid(Integer.toString(i + 999999));
					dv.setFriendlyName("HJSD" + i);
					dv.setSerialNumber(Integer.toString(random
							.nextInt(999999 - 100000 + 1) + 100000));
					dv.setCreationDate(DATE_FORMAT.format(r).toString());
					writer.println(gsn.toJson(dv));
					r.after(r);
				}
				System.out.println("File Written C:/indata.txt ..");

				writer.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void genreaddata() {

		try {
			writer = new PrintWriter("C:/readdata.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-sgenerated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 10000; i++) {
			writer.println(i + 999999);

		}
		writer.close();
	}
}

package com.tkm.mmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Input {

	private Scanner sc;
	private Device d;
	private String tmp;
	private InputStream fis;
	private BufferedReader reader;
	private String line;
	private Gson g;
	static Logger logger = Logger.getLogger(Input.class);

	public void fileinput(Crud op) {

		op.connect();
		sc = new Scanner(System.in);
		fis = ClassLoader.getSystemClassLoader().getResourceAsStream(
				"indata.txt");
		reader = new BufferedReader(new InputStreamReader(fis));
		g = new Gson();
		d = new Device();
		long x;
		x = System.currentTimeMillis();
		try {
			while ((line = reader.readLine()) != null) {

				d = g.fromJson(line, Device.class);
				try {
					op.Create(d);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("1M Records Created " + (System.currentTimeMillis() - x));

		x = System.currentTimeMillis();
		fis = ClassLoader.getSystemClassLoader().getResourceAsStream(
				"readdata.txt");
		reader = new BufferedReader(new InputStreamReader(fis));
		try {
			while ((line = reader.readLine()) != null)
				op.Read(line);
			// System.out.println(line);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("1M Records Read " + (System.currentTimeMillis() - x));

	}

	public void cliinput(Crud op) {

		int c;
		String tmp;
		d = new Device();
		sc = new Scanner(System.in);
		do {
			System.out
			.println("1.Insert Data\n2.Read Data\n3.Update Data\n4.Delete Data\n5.Exit\nSelect : ");
			c = sc.nextInt();

			switch (c) {
			case 1:

				System.out.println("UDID : ");
				d.setUdid(sc.next());
				System.out.println("Fn : ");
				d.setFriendlyName(sc.next());
				System.out.println("Sn : ");
				d.setSerialNumber(sc.next());
				System.out.println("Date : ");
				d.setCreationDate(sc.next());
				op.Create(d);
				break;
			case 2:
				System.out.println("UDID : ");
				tmp = sc.next();
				d = op.Read(tmp);
				System.out
				.println(d.getUdid() + " " + d.getFriendlyName() + " "
						+ d.getSerialNumber() + " "
						+ d.getCreationDate());
				break;
			case 3:
				System.out.println("UDID : ");
				d.setUdid(sc.next());
				System.out.println("Fn : ");
				d.setFriendlyName(sc.next());
				System.out.println("Sn : ");
				d.setSerialNumber(sc.next());
				System.out.println("Date : ");
				d.setCreationDate(sc.next());
				op.update(d);
				break;
			case 4:
				System.out.println("UDID : ");
				tmp = sc.next();
				op.Delete(tmp);
				break;
			case 5:
				System.exit(1);
				break;
			}
		} while (true);

	}
}

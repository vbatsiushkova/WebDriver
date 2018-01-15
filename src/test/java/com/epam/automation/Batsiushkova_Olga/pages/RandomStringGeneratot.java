package com.epam.automation.Batsiushkova_Olga.pages;

import java.util.Random;

/**
 * Created by Volha_Batsiushkova on 1/13/2018.
 */
public class RandomStringGeneratot
{
	public String generateString()
	{
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder stringBuilder = new StringBuilder();
		Random rnd = new Random();
		while (stringBuilder.length() < 10)
		{ // length of the random string.
			int index = (int) (rnd.nextFloat() * chars.length());
			stringBuilder.append(chars.charAt(index));
		}
		String charStr = stringBuilder.toString();
		return charStr;

	}
}

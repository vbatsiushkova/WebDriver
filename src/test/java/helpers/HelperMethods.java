package helpers;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Volha_Batsiushkova on 1/13/2018.
 */
public class HelperMethods
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

	public String parsingString(String stringValue)
	{
		String value = null;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(stringValue);
		while (m.find())
		{
			value = m.group();
		}
		return value;
	}
}

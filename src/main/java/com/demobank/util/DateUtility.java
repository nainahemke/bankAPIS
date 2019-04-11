package com.demobank.util;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class DateUtility {

	public static final boolean validateInputDate(final String isoDate) {
		String[] dateProperties = isoDate.split("-");

		if (dateProperties != null) {
			int year = Integer.parseInt(dateProperties[0]);

			// A valid month by default in the case it is not provided.
			int month = dateProperties.length > 1 ? Integer.parseInt(dateProperties[1]) : 1;

			// A valid day by default in the case it is not provided.
			int day = dateProperties.length > 2 ? Integer.parseInt(dateProperties[2]) : 1;

			try {
				LocalDate.of(year, month, day);
				return true;
			} catch (DateTimeException e) {
				return false;
			}
		}

		return false;
	}

	public static final String getCurrentDateInString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String currentDateInString = formatter.format(new Date());
		return currentDateInString;
	}
}

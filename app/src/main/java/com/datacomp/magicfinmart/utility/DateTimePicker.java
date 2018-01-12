package com.datacomp.magicfinmart.utility;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimePicker {


    public static void showDatePickerDialog(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        //always shows 1 day ahead in calender
        // added 1 day in calender
        // calendar.add(Calendar.MONTH, -6);

        // disable all before date,

        //dialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 180 * 24 * 60 * 60 * 1000);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dialog.show();
    }


    public static void showTimePickerDialog(Context mContex, TimePickerDialog.OnTimeSetListener callBack) {
        final Calendar c = Calendar.getInstance();
        // Current Hour

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContex, callBack,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.HOUR),
                true);

        timePickerDialog.show();
    }

    public static void firstRegNewDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, -6);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void firstRegReNewDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, -6);
        calendar.add(Calendar.YEAR, 15);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        dialog.show();
    }

    public static void policyExpDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 6);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void manufactDatePicker(Context mContex, int year, int month, int date, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, year, month - 1, date);

        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showNextSixMonthDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 6);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showPrevSixMonthDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, -6);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showFirstRegDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MONTH, -9);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.YEAR, -15);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showHealthAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        calendar.add(Calendar.MONTH, -9);
//       dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.YEAR, -18);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showHealthParentAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        calendar.add(Calendar.MONTH, -9);
//       dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.YEAR, -36);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }

    public static void showHealthKidsAgeDatePicker(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        calendar.add(Calendar.MONTH, -9);
//       dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
//        calendar.add(Calendar.MONTH, 9);
        calendar.add(Calendar.MONTH, -3);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dialog.show();
    }


    public static void showDataPickerDialog(Context mContex, DatePickerDialog.OnDateSetListener callBack) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContex, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}

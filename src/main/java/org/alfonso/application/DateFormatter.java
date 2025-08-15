package org.alfonso.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter
{
    private final SimpleDateFormat df;

    public DateFormatter()
    {
        this.df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Date parseString(String date)
    {
        try
        {
            return df.parse(date);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String parseDate(Date date)
    {
         return df.format(date);
    }
}

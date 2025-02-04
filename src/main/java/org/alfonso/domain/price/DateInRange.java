package org.alfonso.domain.price;

import java.util.Date;

public class DateInRange
{
    private final Date startDate;
    private final Date endDate;

    public DateInRange(Date startDate, Date endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate()
    {   return startDate;}

    public Date getEndDate()
    {   return endDate;}

    public Boolean isInRange(Date date)
    {
        return date.after(startDate) && date.before(endDate);
    }
}

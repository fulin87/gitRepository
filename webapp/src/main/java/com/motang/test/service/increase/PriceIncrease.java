package com.motang.test.service.increase;

import org.apache.log4j.Logger;

public class PriceIncrease {
    /** Logger for this class and subclasses */
    protected final Logger logger = Logger.getLogger(getClass());

    private int percentage;

    public void setPercentage(int i) {
        percentage = i;
        logger.info("Percentage set to " + i);
    }

    public int getPercentage() {
        return percentage;
    }
}

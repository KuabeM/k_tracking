package com.korbinian.oneplus3t_oreo.k_tracking;

import java.util.Calendar;

import static java.util.Calendar.*;

/**
 * Created by korbinian on 17/12/17.
 *
 * Implements all the functions for calculating the average data, remaining data and so on.
 */

class DataCalculator {

    // Constants for calling getCal()
    private static int CURR_HOUR = 1;
    private static int CURR_DAY = 2;
    private static int CURR_MAX_DAY = 3;

    /* Function: returns current hour (0..23), day (1..31) and maximum of days in current month  */
    static private int getCal( int alt ) {

        Calendar cal = getInstance();

        switch( alt ){
            case 1:
                return cal.get(HOUR_OF_DAY);
            case 2:
                return cal.get(DAY_OF_MONTH);
            case 3:
                return cal.getActualMaximum( DAY_OF_MONTH );
            default:
                return 1;
        }

    }

    /* Function: calculates the average data used from beginning till now */
    static double calcAvgData( double usedData ){

        int currDay = getCal( CURR_DAY );

        // calc daily average so far
        return usedData / currDay;
    }

    /* Function: calculates the expected data based on the current usage */
    static double calcExpectedData( double usedData ){

        int currHour = getCal( CURR_HOUR);
        int currDay = getCal( CURR_DAY);
        int maximum = getCal( CURR_MAX_DAY);

        // do calculations for expected data
        return (usedData * maximum) / ((currDay - 1) + (currHour / 24));
    }

    /* Function: calculates the average data for the remaining days to meet the data limit */
    static double calcRemainingDataPerDay( double usedData, int dataLimit){

        int currDay = getCal( CURR_DAY);
        int maximum = getCal( CURR_MAX_DAY);
        int currHour = getCal( CURR_HOUR);

        // calc remaining volume per day
        return (dataLimit - usedData) / ( maximum - (currDay-1) - (currHour/24) );
    }

    /* calculates the nominal value used till now with average usage per month */
    static double calcNominalUsedData( int dataLimit ){

        int currDay = getCal( CURR_DAY);
        int currHour = getCal( CURR_HOUR);
        int maximum = getCal( CURR_MAX_DAY);

        //TODO: does not add up
        return  ((double) dataLimit / maximum) * ( currDay - 1 + ((double) currHour / 24));
    }

}

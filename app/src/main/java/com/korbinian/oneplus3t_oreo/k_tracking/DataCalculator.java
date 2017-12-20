package com.korbinian.oneplus3t_oreo.k_tracking;

import java.util.Calendar;

import static java.util.Calendar.*;

/**
 * Created by korbinian on 17/12/17.
 *
 * Implements all the functions for calculating the average data, remaining data and so on.
 */

class DataCalculator {

    private static int CURR_HOUR = 1;
    private static int CURR_DAY = 2;
    private static int CURR_MAX_DAY = 3;

    static private int getCal( int alt ) {

        Calendar cal = getInstance();

        switch( alt ){
            case 1:
                return cal.get(HOUR);
            case 2:
                return cal.get(DAY_OF_MONTH);
            case 3:
                return cal.getActualMaximum( DAY_OF_MONTH );
            default:
                return 1;
        }

    }


    static double calcAvgData( String inputUsedData ){

        int currDay = getCal( CURR_DAY );

        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));

        // calc daily average so far
        return usedData / currDay;
    }

    static double calcExpectedData( String inputUsedData ){

        int currHour = getCal( CURR_HOUR);
        int currDay = getCal( CURR_DAY);
        int maximum = getCal( CURR_MAX_DAY);

        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));

        // do calculations for expected data
        return usedData / (currDay - 1 + currHour / 24) * maximum; //TODO: calculation slightly wrong
    }

    static double calcRemainingDataPerDay( String inputUsedData, int dataLimit){

        int currDay = getCal( CURR_DAY);
        int maximum = getCal( CURR_MAX_DAY);

        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));

        // calc remaining volume per day
        return (dataLimit - usedData) / ( maximum - currDay);
    }

    static double calcNominalUsedData( int dataLimit ){

        int currDay = getCal( CURR_DAY);
        int currHour = getCal( CURR_HOUR);
        int maximum = getCal( CURR_MAX_DAY);

        return (dataLimit / maximum) * (currDay - 1 + currHour / 24);
    }

}

package com.example.kuabe.k_tracking;

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
    private static int CURR_MONTH = 3;
    private static int CURR_MAX_DAY = 4;

    static private int getCal( int alt ) {

        Calendar cal = getInstance();

        switch( alt ){
            case CURR_HOUR:
                return cal.get(HOUR);
            case CURR_DAY:
                return cal.get(DAY_OF_MONTH);
            case CURR_MONTH:
                return cal.get(MONTH);
            case CURR_MAX_DAY:
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
        return usedData / (currDay + currHour / 24) * maximum;
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
        int currHour = getCal(CURR_HOUR);

        
    }

}

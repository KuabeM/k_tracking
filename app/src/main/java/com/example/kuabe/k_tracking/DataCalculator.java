package com.example.kuabe.k_tracking;

import java.util.Calendar;

import static java.util.Calendar.*;

/**
 * Created by korbinian on 17/12/17.
 *
 * Implements all the functions for calculating the average data, remaining data and so on.
 */

class DataCalculator {

    static private int getCal( int alt ) {

        Calendar cal = getInstance();

        switch( alt ){
            case 1:
                return cal.get(DAY_OF_MONTH);
            case 2:
                return cal.get(HOUR);
            case 3:
                return cal.get(MONTH);
            case 4:
                return cal.getActualMaximum( DAY_OF_MONTH );
            default:
                return 1;
        }

    }


    static double calcAvgData( String inputUsedData ){

        int currDay = getCal( 1 );

        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));

        // calc daily average so far
        return usedData / currDay;
    }

    static double calcExpectedData( String inputUsedData ){

        int currHour = getCal( 2);
        int currDay = getCal( 1);
        int maximum = getCal( 4);

        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));

        // do calculations for expected data
        return usedData / (currDay + currHour / 24) * maximum;
    }

    static double calcRemainingDataPerDay( String inputUsedData, int dataLimit){

        int currDay = getCal( 1);
        int maximum = getCal( 4);

        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));

        // calc remaining volume per day
        return (dataLimit - usedData) / ( maximum - currDay);
    }

}

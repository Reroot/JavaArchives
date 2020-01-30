package Collections;

/**
 * <p>
 * Static methods for doing useful math
 * </p>
 * <hr>
 *
 * @version : $Revision: 1.1 $
 *
 *          <hr>
 *          <p>
 *          <font size="-1" color="#336699"><a href="http://www.mbari.org"> The
 *          Monterey Bay Aquarium Research Institute (MBARI)</a> provides this
 *          documentation and code &quot;as is&quot;, with no warranty, express
 *          or implied, of its quality or consistency. It is provided without
 *          support and without obligation on the part of MBARI to assist in its
 *          use, correction, modification, or enhancement. This information
 *          should not be published or distributed to third parties without
 *          specific written permission from MBARI.</font>
 *          </p>
 *          <br>
 *
 *          <font size="-1" color="#336699">Copyright 2002 MBARI.<br>
 *          MBARI Proprietary Information. All rights reserved.</font><br>
 *          <hr>
 *          <br>
 *
 */

public class Utility {

    /**
     * Standard deviation is a statistical measure of spread or variability.The
     * standard deviation is the root mean square (RMS) deviation of the values from
     * their arithmetic mean.
     *
     * <b>populationStandardDeviation</b> normalizes values by N, where N is the
     * sample size. This the <i>Population Standard Deviation</i>
     * 
     * @param values
     * @return
     */
    public static strictfp double populationStandardDeviation(double[] values) {
        double mean = mean(values);
        double n = values.length;
        double dv = 0;
        for (double d : values) {
            double dm = d - mean;
            dv += dm * dm;
        }
        return Math.sqrt(dv / (n - 1));
    }

    /**
     * Calculate the mean of an array of values
     *
     * @param values The values to calculate
     * @return The mean of the values
     */
    public static strictfp double mean(double[] values) {
        return sum(values) / values.length;
    }

    /**
     * Sum up all the values in an array
     *
     * @param values an array of values
     * @return The sum of all values in the Array
     */
    public static strictfp double sum(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("The data array either is null or does not contain any data.");
        } else {
            double sum = 0;
            for (int i = 0; i < values.length; i++) {
                sum += values[i];
            }
            return sum;
        }
    }

    public static double calculateSD(double numArray[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;
        for (double num : numArray) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / (length - 1));
    }
}

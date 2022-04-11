package com.example.wangchenclound.FilterDesign;

import org.springframework.stereotype.Component;

//ParallelCoupledLineFilter
@Component
public class ParallelCoupledLineFilter {
    public double[] getZ0Jn(double[] Elements, double BW) {
        double[] resArray = new double[Elements.length];
        resArray[0] = Math.sqrt((Math.PI * BW) / (2 * Elements[0]));
        for (int i = 1; i < resArray.length - 1; i++) {
            resArray[i] = (BW * Math.PI) / (2 * Math.sqrt(Elements[i - 1] * Elements[i]));
        }
        resArray[resArray.length - 1] = Math.sqrt((Math.PI * BW) / (2 * Elements[Elements.length - 2] * Elements[Elements.length - 1]));
        return resArray;
    }

    public double[] getZ0e(double[] ZJ, double Z0) {
        double[] resArray = new double[ZJ.length];
        for (int i = 0; i < ZJ.length; i++) {
            resArray[i] = Z0 * (1 + ZJ[i] + Math.pow(ZJ[i], 2));
        }
        return resArray;
    }
    public double[] getZ0o(double[] ZJ, double Z0) {
        double[] resArray = new double[ZJ.length];
        for (int i = 0; i < ZJ.length; i++) {
            resArray[i] = Z0 * (1 - ZJ[i] + Math.pow(ZJ[i], 2));
        }
        return resArray;
    }
}

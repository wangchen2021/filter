package com.example.wangchenclound.BaseData;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class MicroStripParam {
    public double[] getWidth(double[] ZArray, double Er, double d) {
        double[] finalvalueArray = new double[ZArray.length];
        for (int i = 0; i < ZArray.length; i++) {
            double finalvalue = 0;
            double tmpA1 = (ZArray[i] / 60) * Math.sqrt((Er + 1) / 2);
            double tmpA2 = ((Er - 1) / (Er + 1)) * ((0.11 / Er) + 0.23);
            double A = tmpA1 + tmpA2;
            double tmpB1 = 377 * Math.PI;
            double tmpB2 = 2 * ZArray[i] * Math.sqrt(Er);
            double B = tmpB1 / tmpB2;
            double tmpWexceptd1 = 8 * Math.pow(Math.E, A);//计算a的b次方;
            double tmpWexceptd2 = Math.pow(Math.E, 2 * A) - 2;
            double Weceptd = tmpWexceptd1 / tmpWexceptd2;
            if (Weceptd <= 2) {
                finalvalue = Weceptd;
            } else {
                double tmpvalue1 = 2 / Math.PI;
                double tmpvalue2 = B - 1 - Math.log(2 * B - 1);
                double tmpvalue3 = (Er - 1) / (2 * Er);
                double tmpvalue4 = Math.log(B - 1) + 0.39 - (0.61 / Er);
                double tmpvalue5 = tmpvalue2 + (tmpvalue3 * tmpvalue4);
                finalvalue = tmpvalue1 * tmpvalue5;
            }
            finalvalueArray[i] = finalvalue * d;
        }
        return finalvalueArray;
    }

    public double[] getEe(double[] WArray, double Er, double d) {
        double[] EeArray = new double[WArray.length];
        for (int i = 0; i < WArray.length; i++) {
            double tmpdata1 = (Er + 1) / 2;
            double tmpdata2 = (Er - 1) / 2;
            double tmpdata3 = 1 / (Math.sqrt(1 + (12 * d / WArray[i])));
            double Ee = tmpdata1 + (tmpdata2 * tmpdata3);
            EeArray[i] = Ee;
        }
        return EeArray;
    }

    public double getK0(double f) {
        double k0 = (2 * Math.PI * f) / (3 * Math.pow(10, 8));
        return k0;
    }

    public double[] getLength(double[] EeArray, double k0, double[] BeltaTimesLArray) {
        double[] lengthArray = new double[EeArray.length];
        for (int i = 0; i < EeArray.length; i++) {
            double ldata1 = BeltaTimesLArray[i] * (Math.PI / 180);
            double ldata2 = Math.sqrt(EeArray[i]) * k0;
            double lengthvalue = ldata1 / ldata2;
            lengthArray[i] = lengthvalue;
        }
        return lengthArray;
    }

    //获取一个
    public double getl(double w, double d, double Er, double BeltaTimesLArray, double f) {
        double tmpdata1 = (Er + 1) / 2;
        double tmpdata2 = (Er - 1) / 2;
        double tmpdata3 = 1 / (Math.sqrt(1 + (12 * d / w)));
        double Ee = tmpdata1 + (tmpdata2 * tmpdata3);
        double k0 = 2 * Math.PI * f / (3 * Math.pow(10, 8));
        double ldata1 = BeltaTimesLArray * (Math.PI / 180);
        double ldata2 = Math.sqrt(Ee) * k0;
        double length = ldata1 / ldata2;
        return length;
    }

    //获取stub的相移
    public double[] getStubL(double[] Ee, double Fc) {
        double waveLength = 3 * Math.pow(10, 8) / Fc;
        double[] BLArray = new double[Ee.length];
        for (int i = 0; i < BLArray.length; i++) {
            BLArray[i] = 53;
        }
        return BLArray;
    }
}

package com.example.wangchenclound.FilterDesign;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;

//阶跃阻抗滤波器设计
// stepped-impedance
@Component
public class SteppedImpedance {
    double Wc = 0;
    double W = 0;
    double Zh = 0;
    double Zl = 0;

    public void setWc(double var) {
        Wc = var;
    }

    public double getWc() {
        return Wc;
    }

    public void setW(double var) {
        W = var;
    }

    public double getW() {
        return W;
    }

    public void setZh(double var) {
        Zh = var;
    }

    public void setZl(double var) {
        Zl = var;
    }

    public double getZh() {
        return Zh;
    }

    public double getZl() {
        return Zl;
    }

    public double getNormalizationVar() {
        double abs = Math.abs(W / Wc);
        double var = abs - 1;
        return var;
    }

    //获取电长度
    public double[] getBeltaTimesL(double[] circuitValue, double Zl, double Zh, double R0) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        double[] BeltMulTimesL = new double[circuitValue.length];
        for (int i = 0; i < circuitValue.length; i++) {
            if (i % 2 == 0) {
//                并联电容
//                java的Π存在一定误差
                double tmpdata = (circuitValue[i] * Zl / R0) * 180 / Math.PI;
                BeltMulTimesL[i] = Double.parseDouble(nf.format(tmpdata));
            } else {
//                串联电感
                double tmpdata = ((circuitValue[i] * R0) / Zh) * 180 / Math.PI;
                BeltMulTimesL[i] = Double.parseDouble(nf.format(tmpdata));
            }
        }
        return BeltMulTimesL;
    }

//    //    获取长度
//    public double[] getLengthArray(double[] BeltaTimesL, double Wc) {
//        double[] lengtharry = new double[BeltaTimesL.length];
//        for (int i = 0; i < BeltaTimesL.length; i++) {
//            lengtharry[i] = (3 * 100000000 / Wc) * (BeltaTimesL[i] * Math.PI / 180);
//        }
//        return lengtharry;
//    }
//    获取阻抗
    public double[] getZArray(double Zl,double Zh,int N){
        double[] ZArray=new double[N];
        for(int i=0;i<N;i++){
            if(i%2==0) {
                ZArray[i]=Zl;
            }else {
                ZArray[i]=Zh;
            }
        }
        return ZArray;
    }
}

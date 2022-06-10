package com.example.wangchenclound.BaseData;

import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BaseData {
    //    最平坦低通滤波器原型的原件值
    public double[] getLowFlat(int N) {
        Map<String, String> LowFlatDataMap = new HashMap<>();
        double[] N1 = new double[2];
        double[] N2 = new double[3];
        double[] N3 = new double[4];
        double[] N4 = new double[5];
        double[] N5 = new double[6];
        double[] N6 = new double[7];
        double[] N7 = new double[8];
        double[] N8 = new double[9];
        double[] N9 = new double[10];
        double[] N10 = new double[11];
//        N=1
        N1[0] = 2.0000;
        N1[1] = 1.0000;
//        N=2
        N2[0] = 1.4142;
        N2[1] = 1.4142;
        N2[2] = 1.0000;
//        N=3
        N3[0] = 1.0000;
        N3[1] = 2.0000;
        N3[2] = 1.0000;
        N3[3] = 1.0000;
//        N=4
        N4[0] = 0.7654;
        N4[1] = 1.8478;
        N4[2] = 1.8478;
        N4[3] = 0.7654;
        N4[4] = 1.0000;
//        N=5
        N5[0] = 0.6180;
        N5[1] = 1.6180;
        N5[2] = 2.0000;
        N5[3] = 1.6180;
        N5[4] = 0.6180;
        N5[5] = 1.0000;
//        N=6
        N6[0] = 0.5176;
        N6[1] = 1.4142;
        N6[2] = 1.9318;
        N6[3] = 1.9318;
        N6[4] = 1.4142;
        N6[5] = 0.5176;
        N6[6] = 1.0000;
//        N=7
        N7[0] = 0.4450;
        N7[1] = 1.2470;
        N7[2] = 1.8019;
        N7[3] = 2.0000;
        N7[4] = 1.8019;
        N7[5] = 1.2470;
        N7[6] = 0.4450;
        N7[7] = 1.0000;
//        N=8
        N8[0] = 0.3902;
        N8[1] = 1.1111;
        N8[2] = 1.6629;
        N8[3] = 1.9615;
        N8[4] = 1.9615;
        N8[5] = 1.6629;
        N8[6] = 1.1111;
        N8[7] = 0.3902;
        N8[8] = 1.0000;
//        N=9
        N9[0] = 0.3473;
        N9[1] = 1.0000;
        N9[2] = 1.5321;
        N9[3] = 1.8794;
        N9[4] = 2.0000;
        N9[5] = 1.8794;
        N9[6] = 1.5321;
        N9[7] = 1.0000;
        N9[8] = 0.3473;
        N9[9] = 1.0000;
//        N=10
        N10[0] = 0.3129;
        N10[1] = 0.9080;
        N10[2] = 1.4142;
        N10[3] = 1.7820;
        N10[4] = 1.9754;
        N10[5] = 1.9754;
        N10[6] = 1.7820;
        N10[7] = 1.4142;
        N10[8] = 0.9080;
        N10[9] = 0.3129;
        N10[10] = 1.0000;
        List<double[]> DataArray = new ArrayList<>();
        DataArray.add(N1);
        DataArray.add(N2);
        DataArray.add(N3);
        DataArray.add(N4);
        DataArray.add(N5);
        DataArray.add(N6);
        DataArray.add(N7);
        DataArray.add(N8);
        DataArray.add(N9);
        DataArray.add(N10);
        return DataArray.get(N - 1);
    }

    //最小损耗【db】和归一化系数
    public int getStepWithMinLoss(double MinLoss, double nomvar) {
//        模拟最平坦滤波器原型的衰减与归一化频率
        int index = 0;
//        符合的阶数
        int N = 0;
        double[] N1 = new double[11];
        double[] N2 = new double[11];
        double[] N3 = new double[11];
        double[] N4 = new double[11];
        double[] N5 = new double[11];
        double[] N6 = new double[11];
        double[] N7 = new double[11];
        double[] N8 = new double[11];
        double[] N9 = new double[11];
        double[] N10 = new double[11];
        List<double[]> dataList = new ArrayList<>();
//        N=1;
        N1[0] = 4;
        N1[1] = 4;
        N1[2] = 4.5;
        N1[3] = 5;
        N1[4] = 6;
        N1[5] = 7;
        N1[6] = 10;
        N1[7] = 12;
        N1[8] = 15;
        N1[9] = 18;
        N1[10] = 21;
//        N=2;
        N2[0] = 4.2;
        N2[1] = 4.7;
        N2[2] = 6;
        N2[3] = 8;
        N2[4] = 10;
        N2[5] = 12;
        N2[6] = 19;
        N2[7] = 22;
        N2[8] = 30;
        N2[9] = 35;
        N2[10] = 41;
//        N=3;
        N3[0] = 5;
        N3[1] = 6;
        N3[2] = 8;
        N3[3] = 11;
        N3[4] = 13;
        N3[5] = 18;
        N3[6] = 28;
        N3[7] = 35;
        N3[8] = 45;
        N3[9] = 53;
        N3[10] = 62;
        //        N=4;
        N4[0] = 5.2;
        N4[1] = 7;
        N4[2] = 10;
        N4[3] = 15;
        N4[4] = 18;
        N4[5] = 23;
        N4[6] = 37;
        N4[7] = 48;
        N4[8] = 62;
        N4[9] = 0;
        N4[10] = 0;
        //        N=5;
        N5[0] = 6;
        N5[1] = 8;
        N5[2] = 11;
        N5[3] = 18;
        N5[4] = 22;
        N5[5] = 30;
        N5[6] = 48;
        N5[7] = 60;
        N5[8] = 0;
        N5[9] = 0;
        N5[10] = 0;
        //        N=6;
        N6[0] = 7;
        N6[1] = 10;
        N6[2] = 14;
        N6[3] = 22;
        N6[4] = 28;
        N6[5] = 36;
        N6[6] = 55;
        N6[7] = 0;
        N6[8] = 0;
        N6[9] = 0;
        N6[10] = 0;
        //        N=7;
        N7[0] = 8;
        N7[1] = 12;
        N7[2] = 16;
        N7[3] = 24;
        N7[4] = 32;
        N7[5] = 40;
        N7[6] = 65;
        N7[7] = 0;
        N7[8] = 0;
        N7[9] = 0;

        //        N=8;
        N8[0] = 8.5;
        N8[1] = 14;
        N8[2] = 18;
        N8[3] = 27;
        N8[4] = 35;
        N8[5] = 47;
        N8[6] = 0;
        N8[7] = 0;
        N8[8] = 0;
        N8[9] = 0;
        N8[10] = 0;
        //        N=9;
        N9[0] = 9;
        N9[1] = 15;
        N9[2] = 20;
        N9[3] = 31;
        N9[4] = 40;
        N9[5] = 53;
        N9[6] = 0;
        N9[7] = 0;
        N9[8] = 0;
        N9[9] = 0;
        N9[10] = 0;
        //        N=10;
        N10[0] = 9.5;
        N10[1] = 16;
        N10[2] = 22;
        N10[3] = 34;
        N10[4] = 45;
        N10[5] = 60;
        N10[6] = 0;
        N10[7] = 0;
        N10[8] = 0;
        N10[9] = 0;
        N10[10] = 0;
        dataList.add(N1);
        dataList.add(N2);
        dataList.add(N3);
        dataList.add(N4);
        dataList.add(N5);
        dataList.add(N6);
        dataList.add(N7);
        dataList.add(N8);
        dataList.add(N9);
        dataList.add(N10);
//        计算归一化系数下不同N的损耗的算法
        if (nomvar > 10) {
            System.out.println("归一化系数过大难以计算");
        } else {
            int lowIndex = 0;
            int highIndex = 0;
            double Xlow = 0;
            double Xhigh = 0;
            if (0.1 <= nomvar && nomvar <= 0.2) {
                lowIndex = 0;
                Xlow = 0.1;
                Xhigh = 0.2;
            } else if (0.2 < nomvar && nomvar <= 0.3) {
                lowIndex = 1;
                Xlow = 0.2;
                Xhigh = 0.3;
            } else if (0.3 < nomvar && nomvar <= 0.5) {
                lowIndex = 2;
                Xlow = 0.3;
                Xhigh = 0.5;
            } else if (0.5 < nomvar && nomvar <= 0.7) {
                lowIndex = 3;
                Xlow = 0.5;
                Xhigh = 0.7;
            } else if (0.7 < nomvar && nomvar <= 1.0) {
                lowIndex = 4;
                Xlow = 0.7;
                Xhigh = 1.0;
            } else if (1.0 < nomvar && nomvar <= 2.0) {
                lowIndex = 5;
                Xlow = 1.0;
                Xhigh = 2.0;
            } else if (2.0 < nomvar && nomvar <= 3.0) {
                lowIndex = 6;
                Xlow = 2.0;
                Xhigh = 3.0;
            } else if (3.0 < nomvar && nomvar <= 5.0) {
                lowIndex = 7;
                Xlow = 3.0;
                Xhigh = 5.0;
            } else if (5.0 < nomvar && nomvar <= 7.0) {
                lowIndex = 8;
                Xlow = 5.0;
                Xhigh = 7.0;
            } else if (7.0 < nomvar) {
                lowIndex = 9;
                Xlow = 7.0;
                Xhigh = 10;
            } else {
                System.out.println("err");
            }
            System.out.println(Xlow + "俩相邻x" + Xhigh);
            highIndex = lowIndex + 1;
//            double[] lowArray=dataList.get(lowIndex);
//            double[] highArray=dataList.get(highIndex);
            double[] lowArray = new double[10];
            double[] highArray = new double[10];
            for (int i = 0; i < 10; i++) {
                lowArray[i] = dataList.get(i)[lowIndex];
            }
            for (int i = 0; i < 10; i++) {
                highArray[i] = dataList.get(i)[highIndex];
            }

            int length = lowArray.length;
            double[] tmpArray = new double[length];
            for (int i = 0; i < length; i++) {
                if (highArray[i] != 0) {
                    double changeVar1 = (nomvar - Xlow) / (Xhigh - Xlow);
                    double changeVar2 = highArray[i] - lowArray[i];
                    double changeSum = changeVar1 * changeVar2;
                    tmpArray[i] = lowArray[i] + changeSum;
                } else {
                    tmpArray[i] = lowArray[i];
                }
            }
            int templength = tmpArray.length;
            for (int i = 0; i < templength; i++) {
                if (tmpArray[i] > MinLoss + 1) {
                    N = i + 1;
                    break;
                }
            }
        }
        return N;
    }

    //等波纹低通滤波器原型0.5dB元件值
    public double[] get05dB(int Step) {
        double[] N1 = new double[2];
        double[] N2 = new double[3];
        double[] N3 = new double[4];
        double[] N4 = new double[5];
        double[] N5 = new double[6];
        double[] N6 = new double[7];
        double[] N7 = new double[8];
        double[] N8 = new double[9];
        double[] N9 = new double[10];
        double[] N10 = new double[11];
//        N=1
        N1[0] = 0.6986;
        N1[1] = 1.0000;
//        N=2
        N2[0] = 1.4029;
        N2[1] = 0.7071;
        N2[2] = 1.9841;
//        N=3
        N3[0] = 1.5963;
        N3[1] = 1.0967;
        N3[2] = 1.5963;
        N3[3] = 1.0000;
//        N=4
        N4[0] = 1.6703;
        N4[1] = 1.1926;
        N4[2] = 2.3661;
        N4[3] = 0.8419;
        N4[4] = 1.9841;
//        N=5
        N5[0] = 1.7058;
        N5[1] = 1.2296;
        N5[2] = 2.5408;
        N5[3] = 1.2296;
        N5[4] = 1.7058;
        N5[5] = 1.0000;
//        N=6
        N6[0] = 1.7254;
        N6[1] = 1.2479;
        N6[2] = 2.6064;
        N6[3] = 1.3137;
        N6[4] = 2.4758;
        N6[5] = 0.8696;
        N6[6] = 1.9841;
//        N=7
        N7[0] = 1.7372;
        N7[1] = 1.2583;
        N7[2] = 2.6381;
        N7[3] = 1.3444;
        N7[4] = 2.6381;
        N7[5] = 1.2583;
        N7[6] = 1.7372;
        N7[7] = 1.0000;
//        N=8
        N8[0] = 1.7451;
        N8[1] = 1.2647;
        N8[2] = 2.6564;
        N8[3] = 1.3590;
        N8[4] = 2.6964;
        N8[5] = 1.3389;
        N8[6] = 2.5093;
        N8[7] = 0.8796;
        N8[8] = 1.9841;
//        N=9
        N9[0] = 1.7504;
        N9[1] = 1.2690;
        N9[2] = 2.6678;
        N9[3] = 1.3673;
        N9[4] = 2.7239;
        N9[5] = 1.3673;
        N9[6] = 2.6678;
        N9[7] = 1.2690;
        N9[8] = 1.7504;
        N9[9] = 1.0000;
//        N=10
        N10[0] = 1.7543;
        N10[1] = 1.2721;
        N10[2] = 2.6754;
        N10[3] = 1.3725;
        N10[4] = 2.7392;
        N10[5] = 1.3806;
        N10[6] = 2.7231;
        N10[7] = 1.3485;
        N10[8] = 2.5239;
        N10[9] = 0.8842;
        N10[10] = 1.9841;
        List<double[]> DataArray = new ArrayList<>();
        DataArray.add(N1);
        DataArray.add(N2);
        DataArray.add(N3);
        DataArray.add(N4);
        DataArray.add(N5);
        DataArray.add(N6);
        DataArray.add(N7);
        DataArray.add(N8);
        DataArray.add(N9);
        DataArray.add(N10);
        return DataArray.get(Step - 1);
    }

    //等波纹低通滤波器原型3dB元件值
    public double[] get3dB(int Step) {
        double[] N1 = new double[2];
        double[] N2 = new double[3];
        double[] N3 = new double[4];
        double[] N4 = new double[5];
        double[] N5 = new double[6];
        double[] N6 = new double[7];
        double[] N7 = new double[8];
        double[] N8 = new double[9];
        double[] N9 = new double[10];
        double[] N10 = new double[11];
//        N=1
        N1[0] = 1.9953;
        N1[1] = 1.0000;
//        N=2
        N2[0] = 3.1013;
        N2[1] = 0.5339;
        N2[2] = 5.8095;
//        N=3
        N3[0] = 3.3487;
        N3[1] = 0.7117;
        N3[2] = 3.3487;
        N3[3] = 1.0000;
//        N=4
        N4[0] = 3.3489;
        N4[1] = 0.7483;
        N4[2] = 4.3471;
        N4[3] = 0.5920;
        N4[4] = 5.8095;
//        N=5
        N5[0] = 3.4817;
        N5[1] = 0.7618;
        N5[2] = 4.5381;
        N5[3] = 0.7681;
        N5[4] = 3.4817;
        N5[5] = 1.0000;
//        N=6
        N6[0] = 3.5042;
        N6[1] = 0.7685;
        N6[2] = 4.6061;
        N6[3] = 0.7929;
        N6[4] = 4.4641;
        N6[5] = 0.6033;
        N6[6] = 5.8095;
//        N=7
        N7[0] = 3.5182;
        N7[1] = 0.7723;
        N7[2] = 4.6386;
        N7[3] = 0.8039;
        N7[4] = 4.6386;
        N7[5] = 0.7723;
        N7[6] = 3.5182;
        N7[7] = 1.0000;
//        N=8
        N8[0] = 3.5277;
        N8[1] = 0.7745;
        N8[2] = 4.6575;
        N8[3] = 0.8089;
        N8[4] = 4.6990;
        N8[5] = 0.8018;
        N8[6] = 4.4990;
        N8[7] = 0.6073;
        N8[8] = 5.8095;
//        N=9
        N9[0] = 3.5340;
        N9[1] = 0.7760;
        N9[2] = 4.6692;
        N9[3] = 0.8118;
        N9[4] = 4.7272;
        N9[5] = 0.8118;
        N9[6] = 4.6692;
        N9[7] = 0.7760;
        N9[8] = 3.5340;
        N9[9] = 1.0000;
//        N=10
        N10[0] = 3.5384;
        N10[1] = 0.7771;
        N10[2] = 4.6768;
        N10[3] = 0.8136;
        N10[4] = 4.7425;
        N10[5] = 0.8164;
        N10[6] = 4.7260;
        N10[7] = 0.8051;
        N10[8] = 4.5142;
        N10[9] = 0.6091;
        N10[10] = 5.8095;
        List<double[]> DataArray = new ArrayList<>();
        DataArray.add(N1);
        DataArray.add(N2);
        DataArray.add(N3);
        DataArray.add(N4);
        DataArray.add(N5);
        DataArray.add(N6);
        DataArray.add(N7);
        DataArray.add(N8);
        DataArray.add(N9);
        DataArray.add(N10);
        return DataArray.get(Step - 1);
    }

    //获取0.5dB等波纹特性的衰减
    public double get05dBLoss(int steps,double nomvar) {
//        符合的阶数
        int N = 0;
        double[] N1 = new double[16];
        double[] N2 = new double[16];
        double[] N3 = new double[16];
        double[] N4 = new double[16];
        double[] N5 = new double[16];
        double[] N6 = new double[16];
        double[] N7 = new double[16];
        double[] N8 = new double[16];
        double[] N9 = new double[16];
        double[] N10 = new double[16];
        List<double[]> dataList = new ArrayList<>();
//        N=1;
        N1[0] = 1;
        N1[1] = 1;
        N1[2] = 1;
        N1[3] = 1;
        N1[4] = 1;
        N1[5] = 1;
        N1[6] = 1;
        N1[7] = 1;
        N1[8] = 1.3;
        N1[9] = 2;
        N1[10] = 2.5;
        N1[11] = 3;
        N1[12] = 4;
        N1[13] = 7;
        N1[14] = 10;
        N1[15] = 12;
//        N=2;
        N2[0] = 1.2;
        N2[1] = 1.2;
        N2[2] = 1.2;
        N2[3] = 1.2;
        N2[4] = 1.2;
        N2[5] = 1.2;
        N2[6] = 2;
        N2[7] = 2.5;
        N2[8] = 3.5;
        N2[9] = 5.5;
        N2[10] = 8.5;
        N2[11] = 15;
        N2[12] = 20;
        N2[13] = 27;
        N2[14] = 32;
        N2[15] = 38;

//        N=3;
        N3[0] = 1.3;
        N3[1] = 1.3;
        N3[2] = 1.3;
        N3[3] = 1.3;
        N3[4] = 1.3;
        N3[5] = 2;
        N3[6] = 3;
        N3[7] = 6;
        N3[8] = 10;
        N3[9] = 13;
        N3[10] = 19;
        N3[11] = 30;
        N3[12] = 38;
        N3[13] = 48;
        N3[14] = 57;
        N3[15] = 65;
        //        N=4;
        N4[0] = 1.5;
        N4[1] = 1.5;
        N4[2] = 1.5;
        N4[3] = 2;
        N4[4] = 2.5;
        N4[5] = 3;
        N4[6] = 8;
        N4[7] = 11;
        N4[8] = 18;
        N4[9] = 22;
        N4[10] = 30;
        N4[11] = 45;
        N4[12] = 55;
        N4[13] = 70;
        N4[14] = 0;
        N4[15] = 0;
        //        N=5;
        N5[0] = 1.8;
        N5[1] = 1.8;
        N5[2] = 1.8;
        N5[3] = 2.5;
        N5[4] = 4;
        N5[5] = 5;
        N5[6] = 12;
        N5[7] = 17;
        N5[8] = 25;
        N5[9] = 32;
        N5[10] = 40;
        N5[11] = 60;
        N5[12] = 70;
        N5[13] = 0;
        N5[14] = 0;
        N5[15] = 0;
        //        N=6;
        N6[0] = 2;
        N6[1] = 2;
        N6[2] = 2.5;
        N6[3] = 4;
        N6[4] = 6;
        N6[5] = 8;
        N6[6] = 17;
        N6[7] = 23;
        N6[8] = 33;
        N6[9] = 41;
        N6[10] = 50;
        N6[11] = 70;
        N6[12] = 0;
        N6[13] = 0;
        N6[14] = 0;
        N6[15] = 0;
        //        N=7;
        N7[0] = 2.2;
        N7[1] = 3;
        N7[2] = 4;
        N7[3] = 6;
        N7[4] = 8;
        N7[5] = 12;
        N7[6] = 22;
        N7[7] = 30;
        N7[8] = 40;
        N7[9] = 52;
        N7[10] = 62;
        N7[11] = 0;
        N7[12] = 0;
        N7[13] = 0;
        N7[14] = 0;
        N7[15] = 0;

        //        N=8;
        N8[0] = 2.5;
        N8[1] = 4;
        N8[2] = 5;
        N8[3] = 7;
        N8[4] = 10;
        N8[5] = 15;
        N8[6] = 28;
        N8[7] = 40;
        N8[8] = 50;
        N8[9] = 60;
        N8[10] = 72;
        N8[11] = 0;
        N8[12] = 0;
        N8[13] = 0;
        N8[14] = 0;
        N8[15] = 0;
        //        N=9;
        N9[0] = 2.8;
        N9[1] = 4.5;
        N9[2] = 6;
        N9[3] = 10;
        N9[4] = 13;
        N9[5] = 20;
        N9[6] = 33;
        N9[7] = 45;
        N9[8] = 60;
        N9[9] = 70;
        N9[10] = 0;
        N9[11] = 0;
        N9[12] = 0;
        N9[13] = 0;
        N9[14] = 0;
        N9[15] = 0;
        //        N=10;
        N10[0] = 3;
        N10[1] = 4.7;
        N10[2] = 7;
        N10[3] = 12;
        N10[4] = 17;
        N10[5] = 23;
        N10[6] = 40;
        N10[7] = 50;
        N10[8] = 65;
        N10[9] = 0;
        N10[10] = 0;
        N10[11] = 0;
        N10[12] = 0;
        N10[13] = 0;
        N10[14] = 0;
        N10[15] = 0;
        dataList.add(N1);
        dataList.add(N2);
        dataList.add(N3);
        dataList.add(N4);
        dataList.add(N5);
        dataList.add(N6);
        dataList.add(N7);
        dataList.add(N8);
        dataList.add(N9);
        dataList.add(N10);
//        计算归一化系数下不同N的损耗的算法
        if (nomvar > 10) {
            System.out.println("归一化系数过大难以计算");
        } else {
            int lowIndex = 0;
            int highIndex = 0;
            double Xlow = 0;
            double Xhigh = 0;
            if (0.01 <= nomvar && nomvar <= 0.02) {
                lowIndex = 0;
                Xlow = 0.01;
                Xhigh = 0.02;
            } else if (0.02 < nomvar && nomvar <= 0.03) {
                lowIndex = 1;
                Xlow = 0.02;
                Xhigh = 0.03;
            } else if (0.03 < nomvar && nomvar <= 0.05) {
                lowIndex = 2;
                Xlow = 0.03;
                Xhigh = 0.05;
            } else if (0.05 < nomvar && nomvar <= 0.07) {
                lowIndex = 3;
                Xlow = 0.05;
                Xhigh = 0.07;
            } else if (0.07 < nomvar && nomvar <= 0.10) {
                lowIndex = 4;
                Xlow = 0.07;
                Xhigh = 0.10;
            } else if (0.10 < nomvar && nomvar <= 0.20) {
                lowIndex = 5;
                Xlow = 0.10;
                Xhigh = 0.20;
            } else if (0.20 < nomvar && nomvar <= 0.30) {
                lowIndex = 6;
                Xlow = 0.20;
                Xhigh = 0.30;
            } else if (0.30 < nomvar && nomvar <= 0.50) {
                lowIndex = 7;
                Xlow = 0.30;
                Xhigh = 0.50;
            } else if (0.50 < nomvar && nomvar <= 0.70) {
                lowIndex = 8;
                Xlow = 0.50;
                Xhigh = 0.70;
            }else if (0.70 < nomvar && nomvar <= 1) {
                lowIndex = 9;
                Xlow = 0.7;
                Xhigh = 1;
            }  else if (1 < nomvar&& nomvar <= 2) {
                lowIndex = 10;
                Xlow = 1;
                Xhigh = 2.0;
            } else if (2 < nomvar&& nomvar <= 3) {
                lowIndex = 11;
                Xlow = 2;
                Xhigh = 3.0;
            }else if (3 < nomvar&& nomvar <= 5) {
                lowIndex = 12;
                Xlow = 3;
                Xhigh = 5.0;
            }else if (5 < nomvar&& nomvar <= 7) {
                lowIndex = 13;
                Xlow = 5;
                Xhigh = 7;
            }else if (7 < nomvar) {
                lowIndex = 14;
                Xlow = 7.0;
                Xhigh = 10;
            }else {
                System.out.println("err");
            }
            System.out.println(Xlow + "俩相邻x" + Xhigh);
            highIndex = lowIndex + 1;
//            double[] lowArray=dataList.get(lowIndex);
//            double[] highArray=dataList.get(highIndex);
            double[] lowArray = new double[10];
            double[] highArray = new double[10];
            for (int i = 0; i < 10; i++) {
                lowArray[i] = dataList.get(i)[lowIndex];
            }
            for (int i = 0; i < 10; i++) {
                highArray[i] = dataList.get(i)[highIndex];
            }

            int length = lowArray.length;
            double[] tmpArray = new double[length];
            for (int i = 0; i < length; i++) {
                if (highArray[i] != 0) {
                    double changeVar1 = (nomvar - Xlow) / (Xhigh - Xlow);
                    double changeVar2 = highArray[i] - lowArray[i];
                    double changeSum = changeVar1 * changeVar2;
                    tmpArray[i] = lowArray[i] + changeSum;
                } else {
                    tmpArray[i] = lowArray[i];
                }
            }
            return tmpArray[steps-1];
        }
        double res = 0;
        return res;
    }
}

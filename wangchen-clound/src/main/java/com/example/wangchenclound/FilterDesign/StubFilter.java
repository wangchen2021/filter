package com.example.wangchenclound.FilterDesign;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StubFilter {
    public double getL(Double Wc) {
        double wavelength = 3 * Math.pow(10, 8) / Wc;
        return wavelength / 8;
    }

    public double[] RichardTransformation(double[] ZArray) {
        double[] RTZArray = new double[ZArray.length];
        for (int i = 0; i < RTZArray.length; i++) {
            if (i % 2 == 0) {
                RTZArray[i] = ZArray[i];
            } else {
                RTZArray[i] = 1 / ZArray[i];
            }
        }
        return RTZArray;
    }

    public double[] KolodaIdentity(double[] tmpRTZArray) {
       //去掉最后一个1
        double[] ZArray=new double[tmpRTZArray.length-1];
        for(int i=0;i<ZArray.length;i++){
            ZArray[i]=tmpRTZArray[i];
        }
        List<Double> ZArrayList=new ArrayList<>();
        //奇数
        if(ZArray.length%2==1){
            //处理从头到尾
            for (int i=0;i<ZArray.length;i++){
                if(i%2==0&&i!= ZArray.length-1){
                    double n2=1+1/ZArray[i];
                    ZArrayList.add(1*n2);
                    ZArrayList.add(ZArray[i]*n2);
                }else if(i!=ZArray.length-1) {
                    ZArrayList.add(ZArray[i]);
                }
            }
            //处理尾
            double endn2=1+1/ZArray[ZArray.length-1];
            ZArrayList.add(ZArray[ZArray.length-1]*endn2);
            ZArrayList.add(1.0*endn2);
        }

        //处理偶数
        if(ZArray.length%2==0){
            //处理从头到尾
            for (int i=0;i<ZArray.length;i++){
                if(i%2==0){
                    double n2=1+1/ZArray[i];
                    ZArrayList.add(1*n2);
                    ZArrayList.add(ZArray[i]*n2);
                }else {
                    ZArrayList.add(ZArray[i]);
                }
            }
        }
        double[] Res=new double[ZArrayList.size()];
        for(int i=0;i< Res.length;i++){
            Res[i]=ZArrayList.get(i);
        }
         return Res;
    }
    public double[] getZArray(double NomZArray[],double Z0){
        double[] ZArray=new double[NomZArray.length];
        for(int i=0;i<ZArray.length;i++){
            ZArray[i]=NomZArray[i]*Z0;
        }
        return ZArray;
    }
}

package com.example.wangchenclound.tools;

import org.springframework.stereotype.Component;

@Component
public class MathTool {
    public double ReserveValidDigits(int bits, double value) {
        String valString = String.valueOf(value);
        String[] valStringARrray = valString.split("");
        double finalvalue = 0;
        String[] finalvalueArray = null;
        if (bits >= valStringARrray.length) {
            finalvalue = value;
        } else {
            int n = 0;
            for (int i = 0; i > valStringARrray.length; i++) {
                if (n <= bits) {
                    if (valStringARrray[i].equals("0") || valStringARrray[i].equals(".")) {
                        if(i==valStringARrray.length-1){
                            break;
                        }else {
                            finalvalueArray[n] = valStringARrray[i];
                        }
                    } else {
                        finalvalueArray[n] = valStringARrray[i];
                        n++;
                    }
                } else {
                    break;
                }
            }
           if(Integer.parseInt(finalvalueArray[finalvalueArray.length-1])>=5){
               int  bitdata=Integer.parseInt(finalvalueArray[finalvalueArray.length-2])+1;
               finalvalueArray[finalvalueArray.length-2]=String.valueOf(bitdata);
           }
           finalvalue=Double.parseDouble(String.valueOf(finalvalueArray));
        }
        return finalvalue;
    }
}

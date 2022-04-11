package com.example.wangchenclound.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wangchenclound.BaseData.BaseData;
import com.example.wangchenclound.BaseData.MicroStripParam;
import com.example.wangchenclound.FilterDesign.ParallelCoupledLineFilter;
import com.example.wangchenclound.FilterDesign.SteppedImpedance;
import com.example.wangchenclound.FilterDesign.StubFilter;
import org.apache.tomcat.util.bcel.classfile.ElementValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.Element;

@RestController
public class FilterDesign {
    //    stepped-impedance filter
    @Resource
    private SteppedImpedance steppedImpedance;
    @Resource
    private BaseData baseData;
    @Resource
    private MicroStripParam microStripParam;
    @Resource
    private StubFilter stubFilter;
    @Resource
    private ParallelCoupledLineFilter parallelCoupledLineFilter;

    @GetMapping("/filterDesign/steppedImpedance")
//    获取阶跃阻抗低通滤波器
    public JSONObject steppedImpedance(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //        最小最大实际阻抗
        double Zl = Double.parseDouble(request.getParameter("Zl"));
        double Zh = Double.parseDouble(request.getParameter("Zh"));
        //        滤波器阻抗
        double R0 = Double.parseDouble(request.getParameter("R0"));
        //        截止频率
        double Fc = Double.parseDouble(request.getParameter("Wc"));
//        情况1
        String type = request.getParameter("type");
        if (type.equals("Minimum loss (DB) determined")) {
            //设置系数以计算归一化系数
//        插入频率
            double F = Double.parseDouble(request.getParameter("W"));
            steppedImpedance.setW(F);
            steppedImpedance.setWc(Fc);
            double nomvar = steppedImpedance.getNormalizationVar();
//        最小损耗db  归一化系数
            double minLoss = Double.parseDouble(request.getParameter("MinLoss"));
            int N = baseData.getStepWithMinLoss(minLoss, nomvar);
            if (N != 0) {
                //归一化原件值
                double[] LowFlatArray = baseData.getLowFlat(N);
//        获取电长度
                double[] BeltaTimesL = steppedImpedance.getBeltaTimesL(LowFlatArray, Zl, Zh, R0);
//        获取电阻
                double[] ZArray = steppedImpedance.getZArray(Zl, Zh, N);
                jsonObject.put("CircuitValue", LowFlatArray);
                jsonObject.put("nomvar", nomvar);
                jsonObject.put("steps", N);
                jsonObject.put("BeltaTimesL", BeltaTimesL);
                jsonObject.put("ZArray", ZArray);
                jsonObject.put("R0", R0);
                jsonObject.put("overflow", "false");
            } else {
                jsonObject.put("overflow", "true");
            }
        }
//        情况2
        else if (type.equals("Required steps determined")) {
            int steps = Integer.parseInt(request.getParameter("Steps"));
            if (steps > 0 && steps <= 10) {
                double[] LowFlatArray = baseData.getLowFlat(steps);
//            获取电长度
                double[] BeltaTimesL = steppedImpedance.getBeltaTimesL(LowFlatArray, Zl, Zh, R0);
//        获取电阻
                double[] ZArray = steppedImpedance.getZArray(Zl, Zh, steps);
                jsonObject.put("CircuitValue", LowFlatArray);
                jsonObject.put("steps", steps);
                jsonObject.put("BeltaTimesL", BeltaTimesL);
                jsonObject.put("ZArray", ZArray);
                jsonObject.put("R0", R0);
                jsonObject.put("overflow", "false");
            } else {
                jsonObject.put("overflow", "true");
            }
        }
        return jsonObject;
    }

    //获取微带线模型
    @GetMapping("/filterDesign/steppedImpedance/getmicrostrip")
    public JSONObject getMicrostrip(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        double d = Double.parseDouble(request.getParameter("d"));
        double fc = Double.parseDouble(request.getParameter("fc"));
        double DieConst = Double.parseDouble(request.getParameter("DieConst"));
        String[] BLString = request.getParameter("BL").split(",");
        double[] BL = new double[BLString.length];
        for (int i = 0; i < BLString.length; i++) {
            BL[i] = Double.parseDouble(BLString[i]);
        }
        String[] tmpZArray = request.getParameter("ZArray").split(",");
        double[] ZArray = new double[tmpZArray.length];
        for (int i = 0; i < ZArray.length; i++) {
            ZArray[i] = Double.parseDouble(tmpZArray[i]);
        }
        double[] W = microStripParam.getWidth(ZArray, DieConst, d);
        double[] EeArray = microStripParam.getEe(W, DieConst, d);
        double k0 = microStripParam.getK0(fc);
        double[] lengthArray = microStripParam.getLength(EeArray, k0, BL);
        jsonObject.put("W", W);
        jsonObject.put("lengthArray", lengthArray);
        double testl = microStripParam.getl(0.391, 0.127, 2.2, 90, 2.5 * Math.pow(10, 8));
        jsonObject.put("test", testl);
        return jsonObject;
    }

    //stub filter
    @GetMapping("/filterDesign/StubFilter")
    public JSONObject getStubFilter(HttpServletRequest request) {
        double Wc = Double.parseDouble(request.getParameter("Wc"));
        int steps = Integer.parseInt(request.getParameter("Steps"));
        double R = Double.parseDouble(request.getParameter("R"));
        String ERC = request.getParameter("Ripple");
        JSONObject jsonObject = new JSONObject();
        double[] ElementValue = new double[steps + 1];
        if (ERC.equals("0.5dB")) {
            double[] ElementVal = baseData.get05dB(steps);
            ElementValue = ElementVal;
            jsonObject.put("ElementsValue", ElementVal);
        } else if (ERC.equals("3dB")) {
            double[] ElementVal = baseData.get3dB(steps);
            ElementValue = ElementVal;
            jsonObject.put("ElementsValue", ElementVal);
        }
        double[] RTZArray = stubFilter.RichardTransformation(ElementValue);
        double[] KIArray = stubFilter.KolodaIdentity(RTZArray);
        double l = stubFilter.getL(Wc);
        double[] ZArray = stubFilter.getZArray(KIArray, R);
        jsonObject.put("getParamWc", Wc);
        jsonObject.put("ZArray", ZArray);
        jsonObject.put("RTZArray", RTZArray);
        jsonObject.put("KIArray", KIArray);
        jsonObject.put("L", l);
        return jsonObject;
    }

    //Stub filter MicroStrip line modal
    @GetMapping("/filterDesign/StubFilter/getmicrostrip")
    public JSONObject getStupMicrostrip(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        double R0 = Double.parseDouble(request.getParameter("R0"));
        double Fc = Double.parseDouble(request.getParameter("Fc"));
        String[] ZArrayStr = request.getParameter("ZArray").split(",");
        double[] ZArray = new double[ZArrayStr.length + 2];
        ZArray[0] = R0;
        ZArray[ZArrayStr.length + 1] = R0;
        for (int i = 0; i < ZArrayStr.length; i++) {
            ZArray[i + 1] = Double.parseDouble(ZArrayStr[i]);
        }
        double DieConst = Double.parseDouble(request.getParameter("DieConst"));
        double d = Double.parseDouble(request.getParameter("d"));
        double[] W = microStripParam.getWidth(ZArray, DieConst, d);
        double[] EeArray = microStripParam.getEe(W, DieConst, d);
        double k0 = microStripParam.getK0(Fc);
        double[] BLArray = microStripParam.getStubL(EeArray, Fc);
        double[] lengthArray = microStripParam.getLength(EeArray, k0, BLArray);
        jsonObject.put("W", W);
        jsonObject.put("ZArray", ZArray);
        jsonObject.put("EeArray", EeArray);
        jsonObject.put("k0", k0);
        jsonObject.put("BLArray", BLArray);
        jsonObject.put("lengthArray", lengthArray);
        return jsonObject;
    }

    //Stub filter MicroStrip line modal
    @GetMapping("/filterDesign/ParallelCoupledLineFilter")
    public JSONObject getParallelCoupledLineFilter(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        double Floss = Double.parseDouble(request.getParameter("Floss"));
        double Fc = Double.parseDouble(request.getParameter("Fc"));
        double Z0 = Double.parseDouble(request.getParameter("Z0"));
        double BW = Double.parseDouble(request.getParameter(("BW")));
        double w = ((Floss / Fc) - (Fc / Floss)) * (1 / BW);
        String ERC = request.getParameter("ERC");
        double nw = Math.abs(w) - 1;
        int steps = Integer.parseInt(request.getParameter("steps"));
        if (ERC.equals("0.5dB")) {
            double loss = baseData.get05dBLoss(steps, nw);
            double[] Elements = baseData.get05dB(steps);
            double[] Z0Jn=parallelCoupledLineFilter.getZ0Jn(Elements,BW);
            double[] Z0e=parallelCoupledLineFilter.getZ0e(Z0Jn,Z0);
            double[] Z0o=parallelCoupledLineFilter.getZ0o(Z0Jn,Z0);
            jsonObject.put("loss", loss);
            jsonObject.put("Elements", Elements);
            jsonObject.put("Z0Jn",Z0Jn);
            jsonObject.put("Z0e",Z0e);
            jsonObject.put("Z0o",Z0o);
        }
        return jsonObject;
    }
    @GetMapping("/filterDesign/ParallelCoupledLineFilter/MicrostripLineModal")
    public JSONObject getParallelCoupledLineFilterMicrostripLineModal(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        double d=Double.parseDouble(request.getParameter("d"));
        double Er=Double.parseDouble(request.getParameter("Er"));
        double Fc=Double.parseDouble(request.getParameter("Fc"));
        String Z0o=request.getParameter("Z0o");
        String[] Z0oArray=Z0o.split(",");
        String Z0e=request.getParameter("Z0e");
        String[] Z0eArray=Z0e.split(",");
        double[] ZArray=new double[Z0eArray.length];
        for(int i=0;i<ZArray.length;i++){
            ZArray[i]=Math.sqrt(Double.parseDouble(Z0oArray[i])*Double.parseDouble(Z0eArray[i]));
        }
        double[] BL=new double[ZArray.length];
        for(int i=0;i<BL.length;i++){
            BL[i]=90;
        }
        double[] widthArray=microStripParam.getWidth(ZArray,Er,d);
        double[] Ee=microStripParam.getEe(widthArray,Er,d);
        double K0=microStripParam.getK0(Fc);
        double[] LArray=microStripParam.getLength(Ee,K0,BL);
        jsonObject.put("ZArray",ZArray);
        jsonObject.put("width",widthArray);
        jsonObject.put("Ee",Ee);
        jsonObject.put("BL",BL);
        jsonObject.put("lArray",LArray);
        return jsonObject;
    }
}

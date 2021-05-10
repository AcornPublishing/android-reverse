package com.namdaehyeon.hookingmodule;

import android.content.Intent;

import org.json.JSONObject;

import java.security.PublicKey;
import java.util.Random;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


/**
 *
 * (c) 2015 namdaehyeon <nam_daehyeon@naver.com>
 *
 */


public class Hook implements IXposedHookLoadPackage {


    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();

    static String randomString( int len ) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


    //#####################################################################
    // Sample!!!
    // 테스트용도로 만들어진것임. 
    //#####################################################################
    static void Hook$com$google$android$gms$internal$zzfm$Verify(final LoadPackageParam lpparam) {
        final String aThisPackageOnly = "com.mobs.ds";

        if(!lpparam.packageName.equals(aThisPackageOnly)){
            return;
        }

        final String aStrPackageName = "패키지이름"; //예 com.test.app
        final String aStrClassName = "클래스이름";   //예 helloClass
        final String aStrMethodName = "메소드이름";  //예 TEST메소드

        System.out.println("##############################################");
        System.out.println("# Hooking Module Loaded ==> "+aThisPackageOnly+ aStrMethodName);
        System.out.println("##############################################");

        String aFinalHookStrClass;
        if (aStrPackageName.length() > 0) {
            aFinalHookStrClass = String.format("%s.%s", aStrPackageName, aStrClassName);
        } else {
            aFinalHookStrClass = String.format("%s%s", aStrPackageName, aStrClassName);
        }

        findAndHookMethod(
                aFinalHookStrClass,
                lpparam.classLoader,
                aStrMethodName,
                PublicKey.class,   //TEST(PublicKey, String, String) 인자3개
                String.class,
                String.class,
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        System.out.println("******************************************");
                        System.out.println("#Hooking Module "+ aStrMethodName +"afterHookedMethod #");
                        System.out.println("param.args[0] --> "+param.args[0]);
                        System.out.println("param.args[1] --> "+param.args[1]);
                        System.out.println("param.args[2] --> "+param.args[2]);
                        System.out.println(param.getResult());
                        System.out.println("******************************************");
                        param.setResult(true);  //항상 True가 되도록설정
                    }
                }
        );
    }

    //#####################################################################
    // Start Here
    //#####################################################################
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

        //Sample!!
        Hook$com$google$android$gms$internal$zzfm$Verify(lpparam);

    }
}
//END OF DOCUMENT

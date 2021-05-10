
////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// FOR HOOKING TEST
// 2013.5.22
// namdaehyeon[nam_daehyeon@naver.com]

// 여기는 sis.or.kr에 있는 안드로이드 해킹문제로서, apk파일을 리패키징방법을 이용하지 않고 
// CydiaSubstrate Code Injection For Android Framework을 이용하여 Hooking을 해봤다.
//
// bank.apk 앱의 bank클래스에는 아래와 같이 난수를 생성하는 함수가 정의되어 있다.
//
//	public int randomRange(int p5, int p6)
//	{
//     return (((int) (Math.random() * ((double) ((p6 - p5) + 1)))) + p5);
//	}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////

package com.namdaehyeon.helloexample;

import java.lang.reflect.Method;
import android.util.Log;
import com.saurik.substrate.MS;


public class Main {
	static void initialize() {
		//CydiaSubstrate Framework는 아래에 정의된 클래스를 만나게되면 Hooking을 시도.
		
        //Hooking하고자 하는 클래스.
		//(패키지(kr.or.spractice) 클래스(bank))
		MS.hookClassLoad("kr.or.spractice.bank", new MS.ClassLoadHook() {
			@Override
			public void classLoaded(Class<?> resources) {
				// TODO Auto-generated method stub
				Method getRandom;
				
				//테스트용 로그
				//Log.v("ApkHookingTest","STEP 1");
				
				try {
					//후킹하고자 하는 findkey.apk bank class에서 public int randomRange(int p5, int p6) Method Type정의
					//randomRange메서드는 Integer.TYPE의 Argument 2개로 구성됨을 정의함.
					getRandom = resources.getMethod("randomRange", Integer.TYPE, Integer.TYPE);
				} catch (NoSuchMethodException e) {
					getRandom = null;
				}
				
				//getRandom Method를 찾았다면
				if (getRandom != null) {
					//Log.v("ApkHookingTest","STEP 2");
					
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(resources, getRandom, old);
				}
			}
			
			//Hooking
			@SuppressWarnings("unchecked")
			private void extracted(Class<?> resources, Method getRandom, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(resources, getRandom, new MS.MethodHook() {
					public Object invoked(final Object resources, final Object... args) throws Throwable {

						//randomRange 메서드에서 생성한 Original Value를 가져옴.
						//(((int) (Math.random() * ((double) ((p6 - p5) + 1)))) + p5);
						final int random = (Integer) old.invoke(resources, args);
						
						int num1 = (Integer) args[0];
						int num2 = (Integer) args[1];
						
						Log.v("ApkHookingTest:", String.format("%d : %d", num1, num2));
						Log.v("ApkHookingTest:", String.format("%d를 --> 2로 바꿈", random));
						
						//실제 함수에는 위처럼 난수가 생성되어 리턴되는데 2로 바꿔서 리턴시킴.
						return 2;
					}
				}, old);
			}
		});
	}
}


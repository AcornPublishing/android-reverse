package com.namdaehyeon.helloexample;

import java.lang.reflect.Method;
//import android.content.res.Resources;
import android.util.Log;

import com.saurik.substrate.MS;

public class Main {
	static void initialize() {
//		Log.v("CydiaSubstrate","namdaehyeon 1");
		
		MS.hookClassLoad("android.content.res.Resources", new MS.ClassLoadHook() {
			public void classLoaded(Class<?> resources) {
				Method getColor;
//				Log.v("CydiaSubstrate","namdaehyeon 2");
				try {
					getColor = resources.getMethod("getColor", Integer.TYPE);
				} catch (NoSuchMethodException e) {
					getColor = null;
				}

				if (getColor != null) {
//					Log.v("CydiaSubstrate","namdaehyeon 3");
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(resources, getColor, old);
				}
			}

			private void extracted(Class<?> resources, Method getColor, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(resources, getColor, new MS.MethodHook() {
					public Object invoked(final Object resources, final Object... args) throws Throwable {
//						Log.v("CydiaSubstrate","namdaehyeon MS.MethodPointer");
						final int color = (Integer) old.invoke(resources, args);
						return color & ~0x0000ff00 | 0x00ff0000;
					}
				}, old);
			}
		});
		
		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
// FOR HOOKING TEST
// 2013.5.22
// namdaehyeon[nam_daehyeon@naver.com]
		
//여기는 sis.or.kr에 있는 안드로이드 해킹문제로서, apk파일을 리패키징방법을 이용하지 않고 CydiaSubstrate Code Injection For Android
//Framework을 이용하여 Hooking을 해봤다.
		
// bank.apk 앱의 bank클래스에는 아래와 같이 난수를 생성하는 함수가 정의되어 있다.
		
//		public int randomRange(int p5, int p6)
//	    {
//	        return (((int) (Math.random() * ((double) ((p6 - p5) + 1)))) + p5);
//	    }
		
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
                         //후킹하고자 하는 클래스.
		                 //(패키지.클래스)
		MS.hookClassLoad("kr.or.spractice.bank", new MS.ClassLoadHook() {
			
			@Override
			public void classLoaded(Class<?> resources) {
				// TODO Auto-generated method stub
				Method getRandom;
//				Log.v("CydiaSubstrate","kr.or.spractice 1-0");
				
				try {
					//public int randomRange(int p5, int p6)
					//randomRange메서드를 가져오는데 아규먼트 2개는 모두 Integer.TYPE이라는것을 정의.
					getRandom = resources.getMethod("randomRange", Integer.TYPE, Integer.TYPE);
					
				} catch (NoSuchMethodException e) {
					getRandom = null;
				}
				
				if (getRandom != null) {
//					Log.v("CydiaSubstrate","kr.or.spractice 2-1");
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(resources, getRandom, old);
				}
			}
			
			private void extracted(Class<?> resources, Method getRandom, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(resources, getRandom, new MS.MethodHook() {
					public Object invoked(final Object resources, final Object... args) throws Throwable {
						final int random = (Integer) old.invoke(resources, args);
						
						//return (((int) (Math.random() * ((double) ((p6 - p5) + 1)))) + p5);
						
						//실제 함수에는 위처럼 난수가 생성되어 리턴되는데 2로 바꿔서 리턴시킴.
						return 2;
					}
				}, old);
			}
		});
		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		com_ahnlab_a3030_a3030
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		MS.hookClassLoad("com_ahnlab_a3030_a3030", new MS.ClassLoadHook() {
			
			@Override
			public void classLoaded(Class<?> resources) {
				// TODO Auto-generated method stub
				Method ahnRooting;
				
				try {
					//public static int q(String p4)
					ahnRooting = resources.getMethod("q", String.class);
				} catch (NoSuchMethodException e) {
					ahnRooting = null;
				}
				
				if (ahnRooting != null) {
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(resources, ahnRooting, old);
				}
			}
			
			private void extracted(Class<?> resources, Method ahnRooting, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(resources, ahnRooting, new MS.MethodHook() {
					public Object invoked(final Object resources, final Object... args) throws Throwable {
						//final int random = (Integer) old.invoke(resources, args);
						
						return 0;
					}
				}, old);
			}
		});
		
		//public native int CheckSystem(String[] paramArrayOfString);
		
		//public static net.nshc.droidx.DroidXAntivirusJNI b04140414ДД041404140414(android.content.Context p5)

//	       builtInMap("int", Integer.TYPE );
//	       builtInMap("long", Long.TYPE );
//	       builtInMap("double", Double.TYPE );
//	       builtInMap("float", Float.TYPE );
//	       builtInMap("bool", Boolean.TYPE );
//	       builtInMap("char", Character.TYPE );
//	       builtInMap("byte", Byte.TYPE );
//	       builtInMap("void", Void.TYPE );
//	       builtInMap("short", Short.TYPE );
	       
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		MS.hookClassLoad("net.nshc.droidx.DroidXAntivirusJNI", new MS.ClassLoadHook() {
		
		//isRootingCheck
		//net/nshc/droidx/service/hanafn/v10/DroidXAntivirus
		
		// * Object localObject;
		MS.hookClassLoad("com.hanabank.ebk.channel.android.hananbank.app.HanaCommActivity", new MS.ClassLoadHook() {
			//net.nshc.droidx.service.hanafn.v10
			@Override
			public void classLoaded(Class<?> resources) {
				// TODO Auto-generated method stub
				Method checkRooting;
				
				Log.v("CydiaSubstrate","HanaCommActivity 1");
				try {
					Log.v("CydiaSubstrate","HanaCommActivity 2");
					
					checkRooting = resources.getMethod("checkDroidXRooting",Void.TYPE);
					
				} catch (NoSuchMethodException e) {
					checkRooting = null;
				}
				
				if (checkRooting != null) {
					Log.v("CydiaSubstrate","HanaCommActivity 3");
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(resources, checkRooting, old);
				}
			}
			
			private void extracted(Class<?> resources, Method checkRooting, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(resources, checkRooting, new MS.MethodHook() {
					public Object invoked(final Object resources, final Object... args) throws Throwable {
						//final int random = (Integer) old.invoke(resources, args);
						return false;
					}
				}, old);
			}
		});
		
		
		
		//net/nshc/droidx/service/hanafn/v10/DroidXAntivirus;->isRooting
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		MS.hookClassLoad("net.nshc.droidx.service.hanafn.v10.DroidXAntivirus", new MS.ClassLoadHook() {
			//net.nshc.droidx.service.hanafn.v10
			@Override
			public void classLoaded(Class<?> resources) {
				// TODO Auto-generated method stub
				Method isRooting;
				
				Log.v("CydiaSubstrate","hanafn.v10.DroidXAntivirus 1");
				try {
					Log.v("CydiaSubstrate","hanafn.v10.DroidXAntivirus 2");
					
					isRooting = resources.getMethod("isRooting",Void.TYPE);
					
				} catch (NoSuchMethodException e) {
					isRooting = null;
				}
				
				if (isRooting != null) {
					Log.v("CydiaSubstrate","hanafn.v10.DroidXAntivirus 3");
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(resources, isRooting, old);
				}
			}
			
			private void extracted(Class<?> resources, Method isRooting, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(resources, isRooting, new MS.MethodHook() {
					public Object invoked(final Object resources, final Object... args) throws Throwable {
						//final int random = (Integer) old.invoke(resources, args);
						return false;
					}
				}, old);
			}
		});
		
		
		//Lnet/nshc/droidx/service/hanafn/v10/DroidXAntivirus2;->isRooting
	}
}


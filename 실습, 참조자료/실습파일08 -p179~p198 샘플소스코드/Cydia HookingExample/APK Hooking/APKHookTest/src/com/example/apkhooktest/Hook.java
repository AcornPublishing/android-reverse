/*
 * written in 2013 namdaehyeon
 */
package com.example.apkhooktest;

import java.lang.reflect.Method;
import android.util.Log;
import com.saurik.substrate.MS;


public class Hook {
	
	//후킹하고 코드 1
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void MYHOOKEXAMPLE() {
		MS.hookClassLoad("com.namdaehyeon.findkey2.MainActivity", new MS.ClassLoadHook() {
			@Override
			public void classLoaded(Class<?> clazz) {
				Method HookMethod;
				
				Log.v("ApkHookingTest:", String.format("namdaehyeon ApkHookingTest VolumeHook STEP1"));
				
				try {
					HookMethod = clazz.getMethod("Volume",new Class<?>[0]);
				} catch (NoSuchMethodException e) {
					HookMethod = null;
				}
				
				if (HookMethod != null) {
					Log.v("ApkHookingTest:", String.format("namdaehyeon ApkHookingTest VolumeHook STEP2"));
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(clazz, HookMethod, old);
				}
			}
			
			//Hooking을 시작한다.
			private void extracted(Class<?> clazz, Method HookMethod, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(clazz, HookMethod, new MS.MethodHook() {
					public Object invoked(final Object clazz, final Object... args) throws Throwable {
						int RET = (Integer) old.invoke(clazz, args);

						Log.v("ApkHookingTest:", String.format("namdaehyeon ApkHookingTest VolumeHook STEP3"));
						Log.v("ApkHookingTest:", String.format("namdaehyeon Original_RET = :%d", RET));
						
						return 53;
					}
				}, old);
			}
		});
	}

	//후킹하고 코드 2
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void MYHOOKEXAMPLE2() {
		MS.hookClassLoad("com.namdaehyeon.findkey2.MainActivity", new MS.ClassLoadHook() {
			@Override
			public void classLoaded(Class<?> clazz) {
				Method HookMethod;
				
				Log.v("ApkHookingTest:", String.format("namdaehyeon ApkHookingTest makeDate STEP1"));
				
				try {
					HookMethod = clazz.getMethod("makeDate", new Class<?>[0]);
				} catch (NoSuchMethodException e) {
					HookMethod = null;
				}
				
				if (HookMethod != null) {
					Log.v("ApkHookingTest:", String.format("namdaehyeon ApkHookingTest makeDate STEP2"));
					final MS.MethodPointer<Object, ?> old = new MS.MethodPointer();
					extracted(clazz, HookMethod, old);
				}
			}
			
			//Hooking을 시작한다.
			private void extracted(Class<?> clazz, Method HookMethod, final MS.MethodPointer<Object, ?> old) {
				MS.hookMethod(clazz, HookMethod, new MS.MethodHook() {
					public Object invoked(final Object clazz, final Object... args) throws Throwable {
						@SuppressWarnings("unused")
						String RET = (String) old.invoke(clazz, args);

						Log.v("ApkHookingTest:", String.format("namdaehyeon ApkHookingTest makeDate STEP3"));
						
						return "2013-11-02-12:35:03";
					}
				}, old);
			}
		});
	}
	
	//Main
	public static void initialize() {
		MYHOOKEXAMPLE();
		MYHOOKEXAMPLE2();
	}
}


////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// FOR HOOKING TEST
// 2013.5.22
// namdaehyeon[nam_daehyeon@naver.com]

// ����� sis.or.kr�� �ִ� �ȵ���̵� ��ŷ�����μ�, apk������ ����Ű¡����� �̿����� �ʰ� 
// CydiaSubstrate Code Injection For Android Framework�� �̿��Ͽ� Hooking�� �غô�.
//
// bank.apk ���� bankŬ�������� �Ʒ��� ���� ������ �����ϴ� �Լ��� ���ǵǾ� �ִ�.
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
		//CydiaSubstrate Framework�� �Ʒ��� ���ǵ� Ŭ������ �����ԵǸ� Hooking�� �õ�.
		
        //Hooking�ϰ��� �ϴ� Ŭ����.
		//(��Ű��(kr.or.spractice) Ŭ����(bank))
		MS.hookClassLoad("kr.or.spractice.bank", new MS.ClassLoadHook() {
			@Override
			public void classLoaded(Class<?> resources) {
				// TODO Auto-generated method stub
				Method getRandom;
				
				//�׽�Ʈ�� �α�
				//Log.v("ApkHookingTest","STEP 1");
				
				try {
					//��ŷ�ϰ��� �ϴ� findkey.apk bank class���� public int randomRange(int p5, int p6) Method Type����
					//randomRange�޼���� Integer.TYPE�� Argument 2���� �������� ������.
					getRandom = resources.getMethod("randomRange", Integer.TYPE, Integer.TYPE);
				} catch (NoSuchMethodException e) {
					getRandom = null;
				}
				
				//getRandom Method�� ã�Ҵٸ�
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

						//randomRange �޼��忡�� ������ Original Value�� ������.
						//(((int) (Math.random() * ((double) ((p6 - p5) + 1)))) + p5);
						final int random = (Integer) old.invoke(resources, args);
						
						int num1 = (Integer) args[0];
						int num2 = (Integer) args[1];
						
						Log.v("ApkHookingTest:", String.format("%d : %d", num1, num2));
						Log.v("ApkHookingTest:", String.format("%d�� --> 2�� �ٲ�", random));
						
						//���� �Լ����� ��ó�� ������ �����Ǿ� ���ϵǴµ� 2�� �ٲ㼭 ���Ͻ�Ŵ.
						return 2;
					}
				}, old);
			}
		});
	}
}


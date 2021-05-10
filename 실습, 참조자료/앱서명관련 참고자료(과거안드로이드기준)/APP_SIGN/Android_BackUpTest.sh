#!/bin/sh

VERSION=`adb shell getprop ro.build.version.release`
SDK=`adb shell getprop ro.build.version.sdk`
echo "Android=$VERSION"
echo "SDK=$SDK"

#adb backup -f /home/namdaehyeon/Desktop/qwerasdf.bin -apk -noshared -nosystem com.kakao.talk
#adb backup -f /home/namdaehyeon/Desktop/qwerasdf.bin -apk -noshared -nosystem -all com.kakao.page #com.google.android.youtube com.kakao.page jp.naver.line.android com.tencent.mobileqqi kvp.jjy.MispAndroid320

#adb backup -f /home/namdaehyeon/Desktop/qwerasdf.bin -apk -noshared -all -nosystem
#java -jar abe.jar unpack /home/namdaehyeon/Desktop/qwerasdf.bin /home/namdaehyeon/Desktop/qwerasdf.tar


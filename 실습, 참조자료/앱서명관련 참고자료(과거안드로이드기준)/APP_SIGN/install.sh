#!/bin/sh

adb uninstall com.sds.BizAppLauncher
java -jar apktool_2.0.0.jar b -f '/home/namdaehyeon/Desktop/BizAppLauncher_opr_15060403' -o aaa.apk
java -jar signapk.jar testkey.x509.pem testkey.pk8 aaa.apk bbb.apk
adb install bbb.apk

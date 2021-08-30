# adb logcat

> adb shell logcat | grep -E "tag1|tag2"

或者你可以使用logcat的内置过滤器：

> adb logcat -s tag1:* tag2:*

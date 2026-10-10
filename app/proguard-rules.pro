# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Firebase FireStore
-keepclassmembers class com.beok.runewords.combination.data.model.** { *; }
-keepclassmembers class com.beok.runewords.detail.data.model.** { *; }

# Firebase ComponentRegistrar
# AGP 9의 R8은 -keep class 규칙만으로 기본 생성자를 유지하지 않는다.
# ComponentDiscovery가 리플렉션으로 기본 생성자를 호출하므로 명시적으로 유지한다.
-keep class * implements com.google.firebase.components.ComponentRegistrar { <init>(); }

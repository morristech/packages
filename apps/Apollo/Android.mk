LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := src/com/andrew/apollo/IApolloService.aidl
LOCAL_SRC_FILES += $(call all-java-files-under, src)

LOCAL_STATIC_JAVA_LIBRARIES := \
<<<<<<< HEAD
    android-support-v4 \
    android-query

LOCAL_PACKAGE_NAME := Apollo

LOCAL_OVERRIDES_PACKAGES := Music

LOCAL_SDK_VERSION := current

LOCAL_PROGUARD_FLAG_FILES := proguard.cfg

include $(BUILD_PACKAGE)

##################################################
include $(CLEAR_VARS)

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := android-query:libs/android-query-0.21.7.jar

include $(BUILD_MULTI_PREBUILT)

=======
    android-support-v4

LOCAL_PACKAGE_NAME := Apollo

LOCAL_SDK_VERSION := current
LOCAL_PROGUARD_FLAG_FILES := proguard.flags

include $(BUILD_PACKAGE)

>>>>>>> 1ad22a3ff979c349d80add0f56eaa888421aec75

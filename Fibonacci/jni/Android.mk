LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Fibonacci
LOCAL_SRC_FILES := Fibonacci.c

include $(BUILD_SHARED_LIBRARY)

LOCAL_PATH := $(call my-dir)
VGS2_PATH := ./vgs2/template

include $(CLEAR_VARS)
LOCAL_MODULE    := vgs2sound
LOCAL_SRC_FILES :=\
	$(VGS2_PATH)/vgs2sound.c\
	$(VGS2_PATH)/vgs2api.c\
	$(VGS2_PATH)/vgs2tone.c\
	jni.c
LOCAL_C_INCLUDES := $(JNI_H_INCLUDE) $(VGS2_PATH) 
LOCAL_LDLIBS := -ljnigraphics -lOpenSLES -llog
LOCAL_LDFLAGS := 
LOCAL_CFLAGS := -O3
CAL_PRELINK_MODULE := false
include $(BUILD_SHARED_LIBRARY)


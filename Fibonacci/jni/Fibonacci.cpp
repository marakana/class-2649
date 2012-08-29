#include <jni.h>

namespace com_intel_fibonacci {

	static jlong fibN(jlong n) {
		if(n==0) return 0;
		if(n==1) return 1;
		return fibN(n-1)+fibN(n-2);
	}

	static JNINativeMethod method_table[] = {
		{ "fibN", "(J)J", (void *) fibN }
	 };
}

using namespace com_intel_fibonacci;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    } else {
        jclass clazz = env->FindClass("com/intel/fibonacci/FibLib");
        if (clazz) {
            env->RegisterNatives(clazz, method_table, sizeof(method_table) / sizeof(method_table[0]));
            env->DeleteLocalRef(clazz);
            return JNI_VERSION_1_6;
        } else {
            return -1;
        }
    }
}

#include <jni.h>

namespace com_intel_fibonacciservice {

	static jlong fib(jlong n) {
		if(n==0) return 0;
		if(n==1) return 1;
		return fib(n-1)+fib(n-2);
	}

	static jlong fibN(JNIEnv *env, jclass clazz, jlong n){
		return fib(n);
	}

	static jlong fibNI(JNIEnv *env, jclass clazz, jlong n){
        jlong previous = -1;
        jlong result = 1;
        for (jlong i = 0; i <= n; i++) {
        	jlong sum = result + previous;
            previous = result;
            result = sum;
        }
        return result;
	}

	static JNINativeMethod method_table[] = {
		{ "fibN", "(J)J", (void *) fibN },
		{ "fibNI", "(J)J", (void *) fibNI }
	 };
}

using namespace com_intel_fibonacciservice;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    } else {
        jclass clazz = env->FindClass("com/intel/fibonacciservice/FibLib");
        if (clazz) {
            env->RegisterNatives(clazz, method_table, sizeof(method_table) / sizeof(method_table[0]));
            env->DeleteLocalRef(clazz);
            return JNI_VERSION_1_6;
        } else {
            return -1;
        }
    }
}

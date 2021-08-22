//
// Created by 仙贝 on 2021/8/22.
//

#include <jni.h>

int ndk_test() {
    return 123;
}

// 注意ndk和test前面要加个“1”
#ifdef __cplusplus
extern "C" {
#endif
    jint Java_com_colin_demo03_MainActivity_native_1test(JNIEnv *env, jobject thiz) {
        return ndk_test();
    }
#ifdef __cplusplus
}
#endif

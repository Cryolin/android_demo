package com.colin.aidllib;

import com.colin.aidllib.IMediaPlayer;
import com.colin.aidllib.IMediaPlayerClient;

interface IMediaPlayerService {
    // aidl作为传参实现回调，aidl作为返回值
    IMediaPlayer createMediaPlayer(IMediaPlayerClient client);
}
package com.excel.lib;

import java.util.List;

public interface ProgressListener {

    /**
     * 导入或导出进度
     *
     * @param progress
     */
    void onProgress(String progress);

    /**
     * 导入或导出个别数据错误或不完整
     *
     * @param msg
     */
    void onFailed(String msg);


    /**
     * 从excel表导入生成对象
     *
     * @param list
     */
    void onSuccess(List list);

    /**
     * 导出到excel成功
     */
    void onSuccess();

    /**
     * 导出到excel成功,返回文件路径
     */
    void onSuccess(String msg);


}
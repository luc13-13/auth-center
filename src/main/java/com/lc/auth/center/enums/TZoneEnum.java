package com.lc.auth.center.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时区枚举类
 * @author: lucheng
 * @data: 2022/4/28 19:50
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum TZoneEnum {

    SHANGHAI("Asia/Shanghai","中国上海");

    private String zone;
    private String desc;
}

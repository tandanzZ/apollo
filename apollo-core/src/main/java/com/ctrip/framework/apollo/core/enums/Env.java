package com.ctrip.framework.apollo.core.enums;

import com.google.common.base.Preconditions;

public enum Env {
    //默认
    LOCAL, FWS, UAT, LPT, PRO, TOOLS, UNKNOWN,

    //开发
    DEV,
    //135
    FAT,

    //uat
    UATERP,
    UATDTS,
    UATIAC,

    //prod
    PRODTS1,
    PRODTS2,
    PROIAC1,
    PROIAC2,
    PROERP1,
    PROERP2,
    PROERP3,
    PROERP7,
    PROERP8,
    PROERP9,
    PROERP10,
    PROERP11,
    PROERP12,
    PROERP13,
    PROERP15,
    PROERP16,
    PROERP17,
    PROERP18,
    PROERP19,
    PROERP20;



    public static Env fromString(String env) {
        Env environment = EnvUtils.transformEnv(env);
        Preconditions.checkArgument(environment != UNKNOWN, String.format("Env %s is invalid", env));
        return environment;
    }
}

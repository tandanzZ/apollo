package com.ctrip.framework.apollo.core.enums;

import com.ctrip.framework.apollo.core.utils.StringUtils;

public final class EnvUtils {

  public static Env transformEnv(String envName) {
    if (StringUtils.isBlank(envName)) {
      return Env.UNKNOWN;
    }
    switch (envName.trim().toUpperCase()) {
      case "LPT":
        return Env.LPT;
      case "FWS":
        return Env.FAT;

      case "PRO":
      case "PROD": //just in case
        return Env.PRO;
      case "LOCAL":
        return Env.LOCAL;
      case "TOOLS":
        return Env.TOOLS;
      case "DEV":
        return Env.DEV;

      case "FAT":
        return Env.FAT;
      case "UAT":
        return Env.UAT;
      case "UATERP":
        return Env.UATERP;
      case "UATDTS":
        return Env.UATDTS;
      case "UATIAC":
        return Env.UATIAC;
      case "PRODTS1":
        return Env.PRODTS1;
      case "PRODTS2":
        return Env.PRODTS2;
      case "PROIAC1":
        return Env.PROIAC1;
      case "PROIAC2":
        return Env.PROIAC2;
      case "PROERP1":
        return Env.PROERP1;
      case "PROERP2":
        return Env.PROERP2;
      case "PROERP3":
        return Env.PROERP3;
      case "PROERP7":
        return Env.PROERP7;
      case "PROERP8":
        return Env.PROERP8;
      case "PROERP9":
        return Env.PROERP9;
      case "PROERP10":
        return Env.PROERP10;
      case "PROERP11":
        return Env.PROERP11;
      case "PROERP12":
        return Env.PROERP12;
      case "PROERP13":
        return Env.PROERP13;
      case "PROERP15":
        return Env.PROERP15;
      case "PROERP16":
        return Env.PROERP16;
      case "PROERP17":
        return Env.PROERP17;
      case "PROERP18":
        return Env.PROERP18;
      case "PROERP19":
        return Env.PROERP19;
      case "PROERP20":
        return Env.PROERP20;
      default:
        return Env.UNKNOWN;
    }
  }
}

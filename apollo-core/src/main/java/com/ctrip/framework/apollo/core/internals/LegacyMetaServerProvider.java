package com.ctrip.framework.apollo.core.internals;

import com.ctrip.framework.apollo.core.enums.Env;
import com.ctrip.framework.apollo.core.spi.MetaServerProvider;
import com.ctrip.framework.apollo.core.utils.ResourceUtils;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * For legacy meta server configuration use, i.e. apollo-env.properties
 */
public class LegacyMetaServerProvider implements MetaServerProvider {

    // make it as lowest as possible, yet not the lowest
    public static final int ORDER = MetaServerProvider.LOWEST_PRECEDENCE - 1;
    private static final Map<Env, String> domains = new HashMap<>();

    public LegacyMetaServerProvider() {
        initialize();
    }

    private void initialize() {
        Properties prop = new Properties();
        prop = ResourceUtils.readConfigFile("apollo-env.properties", prop);
        Properties env = System.getProperties();
        domains.put(Env.LOCAL, getMetaServerAddress(prop, "local_meta", "local.meta"));
        domains.put(Env.DEV, getMetaServerAddress(prop, "dev_meta", "dev.meta"));
        domains.put(Env.LPT, getMetaServerAddress(prop, "lpt_meta", "lpt.meta"));
        domains.put(Env.PRO, getMetaServerAddress(prop, "pro_meta", "pro.meta"));
        domains.put(Env.FAT, getMetaServerAddress(prop, "fat_meta", "fat.meta"));
        domains.put(Env.UAT, getMetaServerAddress(prop, "uat_meta", "uat.meta"));

        domains.put(Env.UATERP,
                env.getProperty("uaterp_meta", prop.getProperty("uaterp.meta")));
        domains.put(Env.UATDTS,
                env.getProperty("uatdts_meta", prop.getProperty("uatdts.meta")));
        domains.put(Env.UATIAC,
                env.getProperty("uatiac_meta", prop.getProperty("uatiac.meta")));
        domains.put(Env.PRODTS1,
                env.getProperty("prodts1_meta", prop.getProperty("prodts1.meta")));
        domains.put(Env.PRODTS2,
                env.getProperty("prodts2_meta", prop.getProperty("prodts2.meta")));
        domains.put(Env.PROIAC1,
                env.getProperty("proiac1_meta", prop.getProperty("proiac1.meta")));
        domains.put(Env.PROIAC2,
                env.getProperty("proiac2_meta", prop.getProperty("proiac2.meta")));
        domains.put(Env.PROERP1,
                env.getProperty("proerp1_meta", prop.getProperty("proerp1.meta")));
        domains.put(Env.PROERP2,
                env.getProperty("proerp2_meta", prop.getProperty("proerp2.meta")));
        domains.put(Env.PROERP3,
                env.getProperty("proerp3_meta", prop.getProperty("proerp3.meta")));
        domains.put(Env.PROERP7,
                env.getProperty("proerp7_meta", prop.getProperty("proerp7.meta")));
        domains.put(Env.PROERP8,
                env.getProperty("proerp8_meta", prop.getProperty("proerp8.meta")));
        domains.put(Env.PROERP9,
                env.getProperty("proerp9_meta", prop.getProperty("proerp9.meta")));
        domains.put(Env.PROERP10,
                env.getProperty("proerp10_meta", prop.getProperty("proerp10.meta")));
        domains.put(Env.PROERP11,
                env.getProperty("proerp11_meta", prop.getProperty("proerp11.meta")));
        domains.put(Env.PROERP12,
                env.getProperty("proerp12_meta", prop.getProperty("proerp12.meta")));
        domains.put(Env.PROERP13,
                env.getProperty("proerp13_meta", prop.getProperty("proerp13.meta")));
        domains.put(Env.PROERP15,
                env.getProperty("proerp15_meta", prop.getProperty("proerp15.meta")));
        domains.put(Env.PROERP16,
                env.getProperty("proerp16_meta", prop.getProperty("proerp16.meta")));
        domains.put(Env.PROERP17,
                env.getProperty("proerp17_meta", prop.getProperty("proerp17.meta")));
        domains.put(Env.PROERP18,
                env.getProperty("proerp18_meta", prop.getProperty("proerp18.meta")));
        domains.put(Env.PROERP19,
                env.getProperty("proerp19_meta", prop.getProperty("proerp19.meta")));
        domains.put(Env.PROERP20,
                env.getProperty("proerp20_meta", prop.getProperty("proerp20.meta")));
    }

    private String getMetaServerAddress(Properties prop, String sourceName, String propName) {
        // 1. Get from System Property.
        String metaAddress = System.getProperty(sourceName);
        if (Strings.isNullOrEmpty(metaAddress)) {
            // 2. Get from OS environment variable, which could not contain dot and is normally in UPPER case,like DEV_META.
            metaAddress = System.getenv(sourceName.toUpperCase());
        }
        if (Strings.isNullOrEmpty(metaAddress)) {
            // 3. Get from properties file.
            metaAddress = prop.getProperty(propName);
        }
        return metaAddress;
    }

    @Override
    public String getMetaServerAddress(Env targetEnv) {
        String metaServerAddress = domains.get(targetEnv);
        return metaServerAddress == null ? null : metaServerAddress.trim();
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}

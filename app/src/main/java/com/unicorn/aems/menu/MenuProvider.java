package com.unicorn.aems.menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unicorn.aems.app.dagger.App;
import com.unicorn.aems.base.Provider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@App
public class MenuProvider implements Provider<List<Menu>> {

    @Inject
    public MenuProvider() {
    }

    @Override
    public ArrayList<Menu> provide() {
        return new Gson().fromJson(getMenuString(), new TypeToken<ArrayList<Menu>>() {}.getType());
    }

    private String getMenuString() {
        return "[\n" +
                "  {\n" +
                "    \"name\": \"领导驾驶舱\",\n" +
                "    \"childList\": [\n" +
                "      {\n" +
                "        \"name\": \"单耗指标\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"综合能耗强度\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"495e1d22c22942ecb603314731bb54e3\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"旅客能耗强度\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"ec9f4c0bf1264234ad6e09ac52a8efe4\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"供冷能耗强度\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"9fe1a082fded40168b3cea86d9e49037\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"供暖能耗强度\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"79353c4a0bd54cddbe3a42a89da37e3f\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"e2a3ccfa150b4f98bddea8d5d0704d17\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"能耗指标\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"供冷能耗强度\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"4f9d43e1bcd04ee2b6a56c31b7c9bf42\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"f3b50775c7294070b82f5e042ece8009\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"objectId\": \"a7cd801fa0874325b6e932c7de9de3e1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"区域平台\",\n" +
                "    \"childList\": [\n" +
                "      {\n" +
                "        \"name\": \"T1航站楼\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"综合\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"d8ff26b03e9540ad9193bc7a5aa46cd2\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"33d7bf3e1df7488ea8804b3136caa051\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"冷\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"0bac658b0a024b2ca971d3aa17b0b3b7\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"热\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"b9bcf4625cf046d2946e8e6448174669\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"9f371ee952ac4fb9ae34a37bb87dbb0e\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"气\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"019157a987b34288a71ff95dec66714c\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"汽\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"51d1a2958ab54accad304d68cb1542d7\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"行李厅\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"84798a6803bd4215880ce83180390014\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"到达厅\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"acc8162cd96c4c62a345c4db6663a77b\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"区域温度\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"fb4c6fe9c53240008bef4b437afbe785\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"策略区域\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"606fc978662b489bb478e1c9522a4d38\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"654bd50256314bb08cfc2991347b1ba7\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"T2航站楼\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"综合\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"1dc0fd4c353143adaf9cab593dbbf0f8\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"75002ebd0e624abd9592dba0a17a1db4\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"冷\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"050cc07acf57464bb559864ed96264dd\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"热\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"9f592cb44f7d417caa59eb012e45ce7f\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"db4d36bf8e40448f85197408dbfa4295\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"气\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"c755b1a5813446008cc68a86a8230c15\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"汽\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"12f8e55df4dd4a0ab76a792deca69362\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"cd7efae3e6d040878dc1df6692aaea65\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"热源\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"能流图\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"3036f210cac545478f70c0de10af6f1c\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"f7d425753e86402fb23de467c29effc4\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"热\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"9a3993dad08243ad8e95cbc0e99cb4ef\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"1cf29f155c724e0ca6125470c4e63501\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"气\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"a2799743ae2b4caaad292a021be22bd2\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"汽\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"0709ea1b8286405a849ba190e730f838\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"c8faeafbb00d4f7792f7be1b70d99e54\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"冷源\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"能流图\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"55cf306ea73844baad1bf67154a40de1\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"用冷\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"3edd0ff08c1c4e6e9a398d55e638da9b\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"耗电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"f11e4f09f2e64aa7b5d98fa9612304f9\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"冷水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"672883b84d6445c497d9cbbba6b200cd\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"输配\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"d8918229b3fb4d69978873c23415e42b\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"1acbc3490f354ae58f9397e555cd2ef7\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"objectId\": \"3e6201f7e6174d5193ef358695645a34\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"系统平台\",\n" +
                "    \"childList\": [\n" +
                "      {\n" +
                "        \"name\": \"供冷\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"冷\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"fe05dfd009df403ba38df668911c5d85\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"7fbe78c325e24b82b3de48c5e7dab211\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"8bee0efda00a472a99d5ceb04b6b40ba\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"0b23797d9e174e4cb173e070e0d8e326\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"供暖\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"热\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"d7387498186d45989d33e708d7bf0836\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"d00b67dd86b04b82a5a6239888290f81\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"a4105e970e7244f8a602d5c1f8bd9ff4\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"气\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"d871a3b0e6e24b2283887a8dcbb50908\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"煤\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"7564d6820dcd4ab39b8ab9d7028c0d64\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"c36c0fa179ad4b89838fd26cd4ce474a\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"供电\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"系统用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"082417007cf6456ea29a1313f6a026a4\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"生产用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"262d89a7238f4496a2fad3f1376073fa\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"生活用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"87752fd6a8f14071898c107a04145824\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"商业用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"67ba912a884f4e60a1f8fa1a653f12d4\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"特殊用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"b3a4a4a2af834a468e798d95cefbd1f5\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"ba7daa10dea24754a092077b839ce9a5\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"供水\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"能流图\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"2cbe1418f3bc4bd0bb43daf4eea70755\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"自来水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"cfa59e63f3d04e7ba95f882fd9a69079\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"中水\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"b0ec9c7458a84793a13fe3f0b8d0bb9c\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"2cfc0a2ef7fe4fafb4bbcc92c6088724\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"燃气\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"系统图\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"2eb9436ae5e849ba866fb17133f30eba\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"生产用气\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"70e6651934964367bf7f7e00b5f53a4e\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"生活用气\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"04be6d1079634338951d13d0ac8d1c34\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"商业用气\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"c57a973f464645618cd31c2b9ca7bfc5\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"c64486cd68604a40a3eb2c27999daa63\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"objectId\": \"5aa855833c4f44c69d42c2c9d0913ea2\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"能耗分析\",\n" +
                "    \"childList\": [\n" +
                "      {\n" +
                "        \"name\": \"综合\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"实时\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"afb0d4bf54824c4d843f8ab2f1bbaea0\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"综合能耗\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"2c7a75e8ebfc4d5196d3b1aa782cade3\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"546758fe2ac4423a911e2702f88d1fdc\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"电\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"空调用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"a7dc0b7106c7429bb5cc1870b77f51e0\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"照明用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"1f000edb882447fe95ece719a1a222af\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"行李用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"ba83a060bda843d8a8b929d7d903e29c\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"电扶梯用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"e6fa6770ad59436995c31579ad479bef\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"廊桥用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"f080e285c9cb4139b988e81b61d714a8\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"商业用电\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"776871c87c5349acb64f3ea8c8de90ca\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"27d1c0e2a40c46b5aadea64863ff1097\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"热\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"总热量\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"4b3d50494e6d4200be541ff50d429334\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对比\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"5be0805315b84013974ec7c8ed0ac0e0\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对标\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"7c7296f2208d4e1ba7665d989d4e36f6\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"27a37abec6044672b0b9960bce1e8502\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"冷\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"总冷量\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"e1e11887765a470ebabd5b91c99b3cf0\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对比\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"2637735912c04e66b313918c4bef44e3\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对标\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"9c77eb0ed6fa4ba487398ac2c24f2af8\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"3ac78c8aa0964b2c8e656bf0bdcca021\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"水\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"总水量\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"4c31ac7fbccc4a5dad3b9a6b7cfc1acb\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对比\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"b8c0efe27cdd476dad324b941881b9d5\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对标\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"ee61d48519824dc5b38bb03a1226d068\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"050f842c5e974e69a63fbcfb72df51ae\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"气\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"总气量\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"773c4418971f4feea0def7c494e262ce\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对比\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"b3148616a6d24c5f859fdbc4776a7026\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对标\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"857074d717dd4411a4d7666a1ee0bbc4\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"b4643eb98baf41779b1eb23a76df83ad\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"蒸汽\",\n" +
                "        \"childList\": [\n" +
                "          {\n" +
                "            \"name\": \"总汽量\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"d6825efaa2434bc4974bed4ec74cff4c\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对比\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"5199b6f09933411f9d9d535267395adc\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"name\": \"对标\",\n" +
                "            \"childList\": [],\n" +
                "            \"objectId\": \"11888095cc8c495ca34d723b89a977a2\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"objectId\": \"14ea7e4855c1427ea3405e937a44be7d\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"objectId\": \"a2fcf9058abc481c89030565af5fcdb4\"\n" +
                "  }\n" +
                "]";
    }

}

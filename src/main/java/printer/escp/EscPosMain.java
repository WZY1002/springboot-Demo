package printer.escp;

import printer.escp.EscPos;

import java.io.IOException;

public class EscPosMain {

    public static void main(String[] args) {
        String param="{\n" +
                "    \"header\": [\n" +
                "        {\n" +
                "            \"text\": \"{$shopname}\",\n" +
                "            \"size\": 2,\n" +
                "            \"bold\": true,\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"underline\": true,\n" +
                "            \"type\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"text\": \"{$barCode}\",\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"type\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"path\": \"{$logo}\",\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"type\": 3\n" +
                "        },\n" +
                "        {\n" +
                "            \"text\": \"{$qrCode}\",\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"type\": 2\n" +
                "        }\n" +
                "    ],\n" +
                "    \"goods\": [\n" +
                "        {\n" +
                "            \"name\": \"商品名\",\n" +
                "            \"width\": 24,\n" +
                "            \"format\": 0,\n" +
                "            \"variable\": \"name\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"数量\",\n" +
                "            \"width\": 8,\n" +
                "            \"format\": 1,\n" +
                "            \"variable\": \"num\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"单价\",\n" +
                "            \"width\": 8,\n" +
                "            \"format\": 1,\n" +
                "            \"variable\": \"price\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"金额\",\n" +
                "            \"width\": 8,\n" +
                "            \"format\": 2,\n" +
                "            \"variable\": \"pay\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"bill\": [\n" +
                "        {\n" +
                "            \"text\": \"实收现金\",\n" +
                "            \"size\": 3,\n" +
                "            \"bold\": true,\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"underline\": false,\n" +
                "            \"type\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"text\": \"{$cash}\",\n" +
                "            \"size\": 3,\n" +
                "            \"bold\": true,\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"underline\": false,\n" +
                "            \"type\": 0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"footer\": [\n" +
                "        {\n" +
                "            \"text\": \"详情请访问官网\",\n" +
                "            \"size\": 2,\n" +
                "            \"bold\": true,\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"underline\": true,\n" +
                "            \"type\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"text\": \"http://www.sublulu.com\",\n" +
                "            \"format\": 1,\n" +
                "            \"line\": 2,\n" +
                "            \"type\": 2\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        String params="{\n" +
                "  \"keys\": {\n" +
                "    \"shopname\": \"黄太吉\",\n" +
                "    \"barCode\": \"6921734976505\",\n" +
                "    \"qrCode\": \"http://www.sublulu.com\",\n" +
                "    \"time\": \"15:35\",\n" +
                "    \"num\": 14,\n" +
                "    \"cash\": 324.5,\n" +
                "    \"logo\": \"/sdcard/qr.png\",\n" +
                "    \"adv\": \"关注微信，有大大地活动哦\"\n" +
                "  },\n" +
                "  \"goods\": [\n" +
                "    {\n" +
                "      \"name\": \"鱼香肉丝\",\n" +
                "      \"num\": 1,\n" +
                "      \"price\": 12.8,\n" +
                "      \"pay\": 12.8\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"葱油粑粑\",\n" +
                "      \"num\": 1,\n" +
                "      \"price\": 4.8,\n" +
                "      \"pay\": 4.8\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"辣椒炒肉\",\n" +
                "      \"num\": 1,\n" +
                "      \"price\": 14.8,\n" +
                "      \"pay\": 14.8\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        try {
            EscPos escPos=EscPos.getInstance("localhost");
            escPos.print(param,params);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

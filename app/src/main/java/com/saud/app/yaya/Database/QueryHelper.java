package com.saud.app.yaya.Database;

import java.util.Map;

public class QueryHelper {


    public static String createTable(String tableName, Map<String,String> map){
        StringBuilder builder=new StringBuilder();

        String query=String.format("CREATE TABLE %s(",tableName);
        builder.append(query);
        int size= map.size();
        int i=0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();

                if(i+1==size) {
                    builder.append(" ").append(k);
                    builder.append(" ").append(v);

                }else{
                    builder.append(" ").append(k).append(" ");
                    builder.append(" ").append(v).append(",");
                }

                i++;
            System.out.println("Key: " + k + ", Value: " + v);
        }
        builder.append(")");
        return builder.toString();
    }
    public static String makeForeignKey(String table,String RefTable,String FColumn,String RefColumn){
        String query="ALTER TABLE "+table+" ADD CONSTRAINT FK_"+table +" FOREIGN KEY ("+FColumn+") REFERENCES "+RefTable+"("+RefColumn+")";
        return query;
    }
}

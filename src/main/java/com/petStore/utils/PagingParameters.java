package com.petStore.utils;



public class PagingParameters {
    private int start;
    private int limit;
    private int total;

    public PagingParameters(){
    	
    }
    
    public PagingParameters(int limit, Integer start) {
        this.limit = limit;
        if (start != null) {
            this.start = start;
        }
    }

    public int getLimit() {
        return limit;
    }

    public int getStart() {
        return start;
    }

    public String addToSql(String sql) {
        if (!sql.contains("SQL_CALC_FOUND_ROWS")) {
            sql = sql.replaceFirst("SELECT ", "SELECT SQL_CALC_FOUND_ROWS ");
        }
        sql += " LIMIT " + limit;
        if (start > 0) {
            sql += " OFFSET " + start;
        }
        return sql;
    }
    
    public String addLimitToSql(String sql) {
        
        sql += " LIMIT " + limit;
        if (start > 0) {
            sql += " OFFSET " + start;
        }
        return sql;
    }

    @Override
    public String toString() {
        return "PagingParameters{" +
                "start=" + start +
                ", limit=" + limit +
                ", total=" + total +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

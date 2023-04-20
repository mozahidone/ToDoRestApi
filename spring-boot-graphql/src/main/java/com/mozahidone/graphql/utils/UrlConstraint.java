package com.mozahidone.graphql.utils;

public final class UrlConstraint {

    private UrlConstraint(){}
    private static final String API = "/api";
    private static final String VERSION = "/v1";

    public static class TransactionManagement{
        public static final String ROOT = API + VERSION + "/transactions";
        public static final String create = "/create";
        public static final String LIST = "/all";
    }

}

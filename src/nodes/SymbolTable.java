package nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {

    // function table:
    // (function name) -> ([parameter types], return type)
    // per the writeup, no duplicate function names are allowed (no method overloading)
    private static HashMap<String, Tuple<List<String>, String>> functionTable = new HashMap<>();

    // variable table:
    // (function name) -> ( (variable name) -> (data type) )
    private static HashMap<String, HashMap<String, String>> variableTable = new HashMap<>();

    private static String currentScope;

    public static String getCurrentScope() {
        return currentScope;
    }

    public static void setCurrentScope(String currentScope) {
        SymbolTable.currentScope = currentScope;
    }

    public static boolean variableExistsInScope(String scope, String varName) {

        HashMap<String, String> scopeMap = variableTable.get(scope);

        if(scopeMap == null) {
            return false;
        }

        return scopeMap.containsKey(varName);
    }

    public static boolean variableExistsInScope(String varName) {
        return variableExistsInScope(getCurrentScope(), varName);
    }
    
    public static boolean functionExists(String functionName) {

        return functionTable.get(functionName) != null;

    }

    public static String getVariableType(String functionName, String varName) {
        return variableTable.get(functionName).get(varName);
    }

    public static void addVariableToScope(String scope, String varName, String varType) {

        HashMap<String, String> scopeMap = variableTable.get(scope);

        if(scopeMap == null) {
            scopeMap = new HashMap<String, String>();
        }

        scopeMap.put(varName, varType);

        variableTable.put(scope, scopeMap);
    }

    public static void addVariableToScope(String varName, String varType) {
        addVariableToScope(getCurrentScope(), varName, varType);
    }

    public static boolean addFunction(String funcName, List<String> paramTypes, String returnType) {
        if(functionExists(funcName)) {
            return false;
        }
        functionTable.put(funcName, new Tuple<List<String>, String>(paramTypes, returnType));
        return true;
    }

    public static String getReturnType(String funcName) {
        return functionTable.get(funcName).y;
    }

    public static List<String> getFunctionParamTypes(String funcName) {
        return functionTable.get(funcName).x;
    }

    public static String asString() {
        String table = "";
        for(String func : functionTable.keySet()) {
            table += func + ":" + functionTable.get(func).x + "," + functionTable.get(func).y + "\n";


            if(variableTable.get(func) != null) {
                for (String var : variableTable.get(func).keySet()) {
                    table += var + "," + variableTable.get(func).get(var);
                }
            }

            table += "\n";
        }
        return table;
    }

    public static void clearTables() {
        functionTable = new HashMap<>();
        variableTable = new HashMap<>();
        init();
    }

    public static void init() {
        functionTable.put("print", new Tuple<List<String>, String>(new ArrayList<>(List.of("Any")), "Void"));
        functionTable.put("concat", new Tuple<List<String>, String>(new ArrayList<>(List.of("String", "String")), "Void"));
        functionTable.put("length", new Tuple<List<String>, String>(new ArrayList<>(List.of("String")), "Integer"));
    }
}

class Tuple<X, Y> {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}


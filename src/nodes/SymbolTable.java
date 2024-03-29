package nodes;

import java.util.HashMap;
import java.util.List;

public class SymbolTable {

    // function table:
    // (function name) -> ([parameter types], return type)
    // per the writeup, no duplicate function names are allowed (no method overloading)
    private static final HashMap<String, Tuple<List<String>, String>> functionTable = new HashMap<>();

    // variable table:
    // (function name) -> ( (variable name) -> (data type) )
    private static final HashMap<String, HashMap<String, String>> variableTable = new HashMap<>();

    private static String currentScope;

    public static String getCurrentScope() {
        return currentScope;
    }

    public static void setCurrentScope(String currentScope) {
        SymbolTable.currentScope = currentScope;
    }

    public static boolean variableExistsInScope(String scope, String varName, String varType) {

        HashMap<String, String> scopeMap = variableTable.get(scope);

        if(scopeMap == null) {
            return false;
        }

        return scopeMap.containsKey(varName);
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

    private class Tuple<X, Y> {
        public final X x;
        public final Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}


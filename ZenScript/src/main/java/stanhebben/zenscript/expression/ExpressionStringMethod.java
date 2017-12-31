package stanhebben.zenscript.expression;

import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.compiler.IEnvironmentMethod;
import stanhebben.zenscript.type.ZenType;
import stanhebben.zenscript.util.ZenPosition;

import java.lang.reflect.Method;

public class ExpressionStringMethod extends Expression {

    private final Expression source;
    private final String methodName;
    private final Class<?> returnClass;
    private final ZenType type;

    public ExpressionStringMethod(ZenPosition position, Expression expression, String method, IEnvironmentGlobal environment) {
        super(position);
        this.source = expression;
        this.methodName = method;
        this.returnClass = getMethod(method).getReturnType();
        this.type = environment.getType(returnClass);
    }

    private static Method getMethod(String name) {
        try {
            return String.class.getMethod(name, new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static boolean hasMethod(String name) {
        return getMethod(name) != null;
    }

    @Override
    public ZenType getType() {
        return type;
    }

    @Override
    public void compile(boolean result, IEnvironmentMethod environment) {
        source.compile(result, environment);
        environment.getOutput().invokeVirtual(String.class, methodName, returnClass, new Class[]{});
    }

}

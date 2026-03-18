package jsanca.artofjava.cap3;

@FunctionalInterface
public interface VariableResolver {
    double resolve(String variableName) throws InterpreterException;
}
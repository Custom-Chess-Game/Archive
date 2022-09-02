package utilitys;

/**
 * Represents a result
 */
public class ResultChecker {

    /**
     * Used to check a value
     * @param value Value to check
     * @param result Result to check aganst
     * @return This instance to chain
     */
    public ResultChecker expect(Object value, Result result) throws Exception {
        Exception Exception = new Exception();

        if (!result.check(value)) throw Exception;

        return this;
    }
}

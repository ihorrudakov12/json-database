class Seven {
    public static SeptenaryStringFunction fun = ((s1, s2, s3, s4, s5, s6, s7) -> (s1 + s2 + s3 + s4 + s5 +s6 + s7).toUpperCase());

    public static void main(String[] args) {
        System.out.println(fun.apply("The", "lambda", "has", "too", "many", "string", "arguments."));
    }
}

@FunctionalInterface
interface SeptenaryStringFunction {
    String apply(String s1, String s2, String s3, String s4, String s5, String s6, String s7);
}
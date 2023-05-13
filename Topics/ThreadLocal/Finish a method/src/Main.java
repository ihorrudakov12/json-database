class UseThreadLocal {
    public static ThreadLocal<Integer> makeThreadLocal(int counter) {
        return ThreadLocal.withInitial(() -> counter + 1);
    }
}
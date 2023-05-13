class Problem {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i += 2 ) {
            if (args[i + 1] != null) {
                System.out.println(args[i] + "=" + args[i + 1]);
            } else {
                args[i + 1] = "";
                System.out.println(args[i] + "=" + args[i + 1]);
            }

        }


    }
}

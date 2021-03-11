package com.jdk8.demo;



public class FunctionCall {

    public static void main(String args[]){

        excute("hx",(x) -> {
            System.out.println(x);
        });

        Callback callback = FunctionCall::cl;
        excute("hx1", FunctionCall::cl);
        excute("hx2", callback);

        Supplier<String> spf = ()->{ return  new String("1234");};
        System.out.println("sp" +  spf.get());

        Supplier<String> spf2 = (FunctionCall::sp);
        System.out.println("sp2" + spf2.get());
    }

    private static void excute(String s, Callback callback){

        callback.call(s);
    }

    private static void cl(String s){
        System.out.println(s);
    }

    private static String sp(){
        return  "abc";
    }
    @FunctionalInterface
    interface Callback{
        void call(String s);
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }
}

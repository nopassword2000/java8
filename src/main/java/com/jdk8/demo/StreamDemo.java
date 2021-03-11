package com.jdk8.demo;

import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

    @Data
    static class Person {
        private String name;
        private Integer age;
        private String country;
        private char sex;

        public Person(String name, Integer age, String country, char sex) {
            this.name = name;
            this.age = age;
            this.country = country;
            this.sex = sex;
        }
    }
    public static void main(String args[]) throws Exception{


        List<Person> personList = new ArrayList<>();
        personList.add(new Person("欧阳雪",18,"中国",'F'));
        personList.add(new Person("Tom",24,"美国",'M'));
        personList.add(new Person("Harley",22,"英国",'F'));
        personList.add(new Person("向天笑",20,"中国",'M'));
        personList.add(new Person("李康",22,"中国",'M'));
        personList.add(new Person("小梅",20,"中国",'F'));
        personList.add(new Person("何雪",21,"中国",'F'));
        personList.add(new Person("李康",22,"中国",'M'));


        List<Person> people = personList.stream().filter(x-> x.getAge() == 20).collect(Collectors.toList());
        people.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        personList.stream().filter(distinctByKey(x->x.getAge())).forEach(System.out::println);
        System.out.println("----------------------------------------------------------");


        Map<Integer, List<Person>> map = personList.stream().collect(Collectors.groupingBy(Person::getAge));
        Stream.of(map).map(x-> x.entrySet().stream().map(y-> y.getValue())).collect(Collectors.toList()).forEach(System.out::println);
        //Stream.of(map).collect(() -> new HashMap<>(), (mapx ,p) ->mapx.put(p.entrySet().toArray(),p),(m ,n) -> m.putAll(n))))
        List<Object> people1 = Stream.of(map).collect(() -> new ArrayList<>(), (mapx ,p) ->mapx.addAll(Arrays.asList(p.keySet())),(m , n) -> m.add(n));
        people1.forEach(System.out::println);
    }



    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private static void foreachx(){
        Stream.of("hello", "world").map(s -> s.split("")).forEach(System.out::println);
        System.out.println("--------------");
        Stream.of("hello", "world").flatMap(s -> Stream.of(s.split(""))).forEach(System.out::println);
        System.out.println("--------------");
    }

    private static void sortedx(){
        Integer[] arr = new Integer[]{5, 1, 2, 1, 3, 1, 2, 4};    // 千万不要用int
        System.out.println("--------------1-----------------------");
        Stream.of(arr).forEach(System.out::println);
        System.out.println("--------------2-----------------------");
        Stream.of(arr).sorted().forEach(System.out::println);
        System.out.println("--------------3-----------------------");
        Stream.of(arr).distinct().forEach(System.out::println);
        System.out.println("--------------4-----------------------");
        Stream.of(arr).distinct().sorted().forEach(System.out::println);
    }
    private static void streamx(){
        IntStream.range(1,1000000).forEach(System.out::println);

        Integer[] arr = new Integer[]{5, 1, 2, 1, 3, 1, 2, 4};    // 千万不要用int
        Stream.of(arr).parallel().sorted().collect(Collectors.toList());
    }
}

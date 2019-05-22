package com.example.springbootnetty;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/16 16:25
 * Version 1.0
 **/
public class Test {
    private static ConcurrentMap<Student ,String> map =new ConcurrentHashMap<Student, String>();

    private static class Student{
        public Student() {
        }

        public Student(String id, String chind) {
            this.id = id;
            Chind = chind;
        }

        private String id;
        private String Chind;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getChind() {
            return Chind;
        }

        public void setChind(String chind) {
            Chind = chind;
        }
    }

    public static void main(String[] args) {
//        Map[] maps = new Map[]();
        Student student = new Student("111","asas");
        map.put(student,"sasa");
        Student student1 = new Student("222","zxzx");
        map.put(student1,"xzxz");
//        maps[0] = new HashMap<String ,String>();
      /*  maps[0].put("")
        Map.Entry<>*/


        System.out.println(map.size());
        Student st = new Student("111","asas");
        System.out.println(map.get(st));
    }
}

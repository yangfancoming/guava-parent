package com.google.common.goat.collect.multimap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * Guava的Multimap就提供了一个方便地把一个键对应到多个值的数据结构。
 * 让我们可以简单优雅的实现上面复杂的数据结构，让我们的精力和时间放在实现业务逻辑上，而不是在数据结构上.
 */
public class MultiMapTest {

    @Test
    public void teststuScoreMultimap(){
        Multimap<String,StudentScore> root = ArrayListMultimap.create();
        for(int i = 1;i <= 10;i++){
            StudentScore studentScore = new StudentScore();
            studentScore.courseid = 10 + i;
            studentScore.score = 100 - i;
            root.put("peida",studentScore);
        }
        Assert.assertEquals(root.size(),10);

        Collection<StudentScore> collection = root.get("peida");
        Assert.assertEquals(root.size(),10);
        Assert.assertEquals(collection.size(),10);
        collection.clear();// 操作视图 也会影响 root
        Assert.assertEquals(root.size(),0);
        Assert.assertEquals(collection.size(),0);


        StudentScore studentScoreNew = new StudentScore();
        studentScoreNew.courseid = 1034;
        studentScoreNew.score = 67;
        collection.add(studentScoreNew);// 操作视图 也会影响 root
        Assert.assertEquals(root.size(),1);
        Assert.assertEquals(collection.size(),1);

    }
}

package com.bjpowernode.demopro2.service;

import com.bjpowernode.demopro2.dao.StudentDao;
import com.bjpowernode.model.Student;
import com.bjpowernode.service.StudentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
//暴露dubbo的服务
@DubboService(interfaceClass = StudentService.class,version = "1.0",timeout = 5000)
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * @param student 添加的学生
     * @return 返回添加的条数
     */
    @Override
    public int addStudent(Student student) {
        int result = 0;
        if (student.getName() != null) {
            Student stuName = studentDao.selectByName(student.getName());
            if (stuName != null) {
                //已有相同的name 无法创建
                return result = -1;
            } else {
                //name合法插入数据
                result = studentDao.insertStudent(student);
                return result;
            }

        }
        if (student.getId() != null) {
            Student stuId = studentDao.selectById(student.getId());
            if (stuId != null) {
                //已有相同的name 无法创建
                return result = -2;
            } else {
                //name合法插入数据
                result = studentDao.insertStudent(student);
                return result;
            }

        }

        /*
         * 在进行插入数据是要判断对象的各个属性是否为合法值
         * 例如：是否为空值 是否已存在
         * */
        return result;
    }

    @Override
    public Student queryStudent(Integer id) {
        //设置key与value的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Student>(Student.class));

        final String user = "STUDENT:";

        Student selectForId = null;
        //id不为null 开始查
        if (id != null) {
            String key = user + id;
            //先查缓存
            Student student = (Student) redisTemplate.opsForValue().get(user + id);
            //缓存未命中查数据库
            if (student == null) {
                selectForId = studentDao.selectById(id);
                if (selectForId != null) {
                    //查到了直接存
                    redisTemplate.opsForValue().set(key, selectForId);

                } else {
                    //没查到 就骂他
                    redisTemplate.opsForValue().set(key, "别查了，啥都没有");

                }
            }
        }
        return selectForId;
    }
}
